package com.cc.study.springboot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author chenc
 * @create 2019-09-22 14:32
 **/
@RestController
public class FileUpload {

    public String upload(MultipartFile file) throws IOException {
        file.transferTo(new File(""));
        return "upload success";
    }
}
