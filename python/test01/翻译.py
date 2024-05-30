import concurrent.futures
import json
import os
import re
import shutil
import requests
from bs4 import BeautifulSoup
from tqdm import tqdm
import time


class BaiduTranslate:

    def __init__(self, target):
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) ' 'Chrome/72.0.3626.121 Safari/537.36'}
        self.target = target
        self.session = requests.Session()
        self.proxy_code = 1
        # set your thread number according to your settings in python editor, default value is 5
        self.max_thread_number = 5
        self.soup = None
        self.alldata = []
        self.ts_data = []
        self.absolute_path = None
        self.video_name = None

    def translateChinese(self, chinese):
        ts = int(time.time() * 1000)
        data = {
            "from": "zh",
            "to": "en",
            "query": chinese,
            "simple_means_flag": 3,
            "sign": 36941.291708,
            "token": "f82b93b395d1e855c302c50d12afe864",
            "domain": "common",
            "ts": ts
        }
        response = self.session.post(url=self.target, data=data, headers=self.headers)
        if response.status_code == 200:
            init_content = response.text
            if init_content is None or []:
                return False
            print(init_content)


if __name__ == '__main__':
    target = 'https://fanyi.baidu.com/v2transapi?from=zh&to=en'
    baiduTranslate = BaiduTranslate(target=target)
    baiduTranslate.translateChinese(chinese="翻译")
