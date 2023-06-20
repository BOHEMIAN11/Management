package com.example.community.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/img")
public class ImageController {

    @Value("${hong.path}")
    private String basePath;

    @PostMapping("/upload")
    public String upload(MultipartFile file){
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //对原始名进行截取"."后面的字符
        //String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = originalFilename;
        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在：目录不存在，需要创建
        if(!dir.exists()) dir.mkdirs();
        System.out.println(dir);
        System.out.println(fileName);
        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }




}
