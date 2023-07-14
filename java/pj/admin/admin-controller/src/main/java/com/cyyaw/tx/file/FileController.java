package com.cyyaw.tx.file;


import com.cyyaw.web.service.WebImageService;
import com.cyyaw.web.table.entity.WebImage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@RestController
@Api(tags = "文件上传下载")
@RequestMapping("/admin/file")
public class FileController {

    @Autowired
    private WebImageService webImageService;

//    @Autowired
//    private FtpService ftpService;


    @ApiOperation(value = "上传文件", notes = "上传文件")
    @GetMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // 上传到本地
            InputStream inputStream = file.getInputStream();

            // 上传到影像平台


            // 收集信息保存到影像表

            // 返回影像ID

        } else {

        }

    }


    @ApiOperation(value = "下载文件", notes = "下载文件")
    @GetMapping("/download")
    public void download(String code, HttpServletResponse response) {
        WebImage webImage = webImageService.findByTid(code);
        String path = webImage.getPath();
        // ftp 下载文件
//        String file = ftpService.download(path);
        String filePath = "";
        File file = new File(filePath);
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            if (file.exists()) {
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while (bis.read(buffer) != -1) {
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
