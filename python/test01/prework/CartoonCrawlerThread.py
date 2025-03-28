import os.path
import requests
from bs4 import BeautifulSoup
from reportlab.pdfgen import canvas
from PIL import Image
import json
import concurrent.futures
from tqdm import tqdm


class FasterCartoonCrawlerForCMH5:
    def __init__(self, target):
        # add your VPN info
        self.proxies = {
            'http': "http://127.0.0.1:10900",
            'https': "http://127.0.0.1:10900",
            'socks': "socks5://127.0.0.1:10011"
        }
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36'}
        self.target = target
        self.session = requests.Session()
        self.proxy_code = 1
        # set your thread number according to your settings in python editor, default value is 5
        self.max_thread_number = 5
        self.soup = None
        self.alldata = {}
        self.absolute_path = None
        self.comic_name = None

    def get_contents_component(self, web):
        # update soup and init_content to avoid repeated works
        if web:
            if self.proxy_code == 1:
                response = self.session.get(web, headers=self.headers, timeout=5)
                if response.status_code == 200:
                    init_content = response.text
                    if init_content is None or []:
                        return False
                    self.soup = BeautifulSoup(init_content, 'html.parser')
                    return True
            else:
                response = self.session.get(web, headers=self.headers, proxies=self.proxies, timeout=5)
                if response.status_code == 200:
                    init_content = response.text
                    if init_content is None or []:
                        return False
                    self.soup = BeautifulSoup(init_content, 'html.parser')
                    return True
        return False

    # To sniffer your target web service address
    # if it is in foreign countries, you need to use proxy to get access to its sources, else keep initial status.
    def proxy_sniffer(self, web):
        if web:
            try:
                # timeout -> your preference
                response = self.session.get(web, headers=self.headers, timeout=3)
                if response.status_code == 200:
                    init_content = response.text
                    if init_content is None or []:
                        return False
                    self.soup = BeautifulSoup(init_content, 'html.parser')
                    return True
            except requests.exceptions.ProxyError:
                print("~~~使用代理爬取~~~")
                self.proxy_code = 0
                response = self.session.get(web, headers=self.headers, proxies=self.proxies, timeout=5)
                if response.status_code == 200:
                    init_content = response.text
                    if init_content is None or []:
                        return False
                    self.soup = BeautifulSoup(init_content, 'html.parser')
                    return True
        return False

    def get_crawl_list(self):
        is_state = self.proxy_sniffer(self.target)
        # get crawl list
        if is_state:
            print("~~~正在获取漫画章节~~~")
            self.comic_name = self.soup.find('p', class_='comic-title j-comic-title').get_text(strip=True)
            links = self.soup.find_all('a', class_='j-chapter-link')
            chapter_links = [{'chapter_name': link.get_text(strip=True),
                              'chapter_link': 'https://www.cmh5.com' + str(link.get('href'))}
                             for link in links]
            return chapter_links
        return {}

    def get_chapter_src(self, target_link_dist):
        for item in target_link_dist:
            name, link = item['chapter_name'], item['chapter_link']
            is_state = self.get_contents_component(link)
            if is_state:
                img_tags = self.soup.find_all('img', class_='lazy-read')
                src_list = [str(img['data-original']) for img in img_tags]
                item['chapter_src_list'] = src_list
        print("~~~获取漫画章节具体信息~~~")
        self.alldata = target_link_dist

    def create_folder(self, name):
        current_directory = os.path.dirname(os.path.abspath(__file__))
        new_folder_path = os.path.join(os.path.dirname(current_directory), 'results', name)
        os.makedirs(new_folder_path, exist_ok=True)
        return new_folder_path

    def downloader(self, img_url):
        if self.proxy_code == 1:
            response = self.session.get(img_url, headers=self.headers, timeout=5)
            if response.status_code == 200:
                return response.content
            return None
        else:
            response = self.session.get(img_url, headers=self.headers, proxies=self.proxies, timeout=5)
            if response.status_code == 200:
                return response.content
            return None

    # In order to download images faster, we import thread pools to accelerate this process.
    def download_img_threaded(self, item, pbar_chapter):
        chapter_name = item['chapter_name']
        chapter_src_list = item['chapter_src_list']
        img_folder_path = os.path.join(self.absolute_path, 'img')
        os.makedirs(img_folder_path, exist_ok=True)
        img_folder_path = os.path.normpath(img_folder_path)

        with concurrent.futures.ThreadPoolExecutor(max_workers=self.max_thread_number) as executor:
            for per_src in chapter_src_list:
                future = executor.submit(self.downloader, per_src)
                img_data = future.result()
                if img_data:
                    idx = chapter_src_list.index(per_src)
                    img_name = f'{idx + 1}.jpg'
                    chapter_folder = os.path.join(img_folder_path, chapter_name)
                    os.makedirs(chapter_folder, exist_ok=True)
                    img_path = os.path.join(chapter_folder, img_name)
                    with open(img_path, 'wb') as img_file:
                        img_file.write(img_data)
                    # update bar
                    pbar_chapter.update(1)
                    pbar_chapter.set_postfix_str(f'正在下载{chapter_name}')

        return chapter_name, len(chapter_src_list)

    def download_img(self):
        total_img_count = sum(len(item['chapter_src_list']) for item in self.alldata)
        src_path_data = {}
        with tqdm(total=total_img_count, desc=f'正在下载[{self.comic_name}]漫画') as pbar_main:
            if self.alldata and self.target:
                self.absolute_path = self.create_folder(self.comic_name)
                self.datalog(self.alldata, 'crawl-data-set.json')
                with concurrent.futures.ThreadPoolExecutor(max_workers=self.max_thread_number) as executor:
                    # 使用多线程下载每个章节的图片
                    for item in self.alldata:
                        future_to_url = executor.submit(self.download_img_threaded, item, pbar_main)
                        try:
                            chapter_name, length = future_to_url.result()
                            img_folder_path = os.path.join(self.absolute_path, 'img')
                            chapter_folder_path = os.path.join(img_folder_path, chapter_name)
                            chapter_src_path = [os.path.join(chapter_folder_path, f'{i}.jpg') for i in
                                                range(1, length + 1)]
                            src_path_data[chapter_name] = chapter_src_path
                        except Exception as exc:
                            print(f"处理章节图片时发生异常: {exc}")
        return src_path_data

    def datalog(self, dist_data, json_file_path):
        json_folder_path = os.path.join(self.absolute_path, 'json')
        os.makedirs(json_folder_path, exist_ok=True)

        json_file_path = os.path.join(json_folder_path, json_file_path)
        json_file_path = os.path.normpath(json_file_path)

        with open(json_file_path, "w", encoding="utf-8") as json_file:
            json.dump(dist_data, json_file, ensure_ascii=False, indent=4)

    def Launcher(self):
        chapter_list = self.get_crawl_list()
        if chapter_list:
            self.get_chapter_src(chapter_list)
            isok = self.download_img()
            if isok:
                print("检查这个文件是否存在: real-img-path.json")
                self.datalog(isok, 'real-img-path.json')
                print("CartoonCrawlerForCMH5 爬取完成！")
                return isok
            else:
                print("爬取过程中意外失败...")
                return False
        else:
            print("没有得到爬取漫画列表！请检查target_web_list.txt！")


class PDF_generator:
    def __init__(self, output_path, src_path_data):
        self.output_path = output_path
        self.src_path_data = src_path_data

    def create_pdf(self, chapter_name, img_paths):
        # create a pdf
        pdf_folder_path = os.path.join(self.output_path, 'pdf')
        os.makedirs(pdf_folder_path, exist_ok=True)
        pdf_path = os.path.join(pdf_folder_path, f'{chapter_name}.pdf')
        pdf_path = os.path.normpath(pdf_path)

        c = canvas.Canvas(pdf_path, pagesize=(595, 842))  # A4 size
        page_width = 595
        page_height = 842

        for img_path in img_paths:
            img = Image.open(img_path)
            img_width, img_height = img.size
            aspect_ratio = img_height / img_width

            scaled_width = page_width
            scaled_height = scaled_width * aspect_ratio

            if scaled_height > page_height:
                scaled_height = page_height
                scaled_width = scaled_height / aspect_ratio

            x = (page_width - scaled_width) / 2
            y = (page_height - scaled_height) / 2

            c.drawImage(img_path, x, y, width=scaled_width, height=scaled_height)
            c.showPage()
        c.save()
        print(f"{chapter_name} PDF文件 DONE~~~")
        return pdf_path

    def connect_img(self):
        print("开始拼接PDF~~~")
        # src_path_data = {chapter_name,chapter_src_paths} str:list->[]
        pdf_paths = []
        for chapter_name, chapter_src_paths in self.src_path_data.items():
            pdf_path = self.create_pdf(chapter_name, chapter_src_paths)
            pdf_paths.append(pdf_path)
        return pdf_paths

    def Launcher(self):
        return self.connect_img()


def PreChecker():
    # get current absolute path
    # check target_web_list.txt already exist or not?
    # if not then create one, else check the content in it.
    current_directory = os.path.dirname(os.path.abspath(__file__))
    file_path = os.path.join(current_directory, 'target_web_list.txt')
    # check its existence
    if os.path.exists(file_path):
        with open(file_path, 'r', encoding='utf-8') as file:
            content = file.readlines()
        return content
    else:
        with open(file_path, 'w', encoding='utf-8') as file:
            pass
        return []


if __name__ == '__main__':
    target_web_list = PreChecker()
    cleaned_list = [item.strip() for item in target_web_list if item.strip()]

    if target_web_list:
        for target in cleaned_list:
            cartoon_crawler = FasterCartoonCrawlerForCMH5(target=target)
            is_success = cartoon_crawler.Launcher()
            if is_success:
                pdf_gen = PDF_generator(output_path=cartoon_crawler.absolute_path, src_path_data=is_success)
                pdf_paths = pdf_gen.Launcher()
                print('~~~PDF拼接完成！~~~')
