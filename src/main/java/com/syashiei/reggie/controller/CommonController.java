package com.syashiei.reggie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;

@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {

    @PostMapping("/upload")
    public void upload(MultipartFile file, HttpServletResponse response){
        log.info("获取上传文件");

        //创建输入流获取文件
        FileInputStream fileInputStream = new FileInputStream(file);
        //把临时文件转存

        //
    }
}
