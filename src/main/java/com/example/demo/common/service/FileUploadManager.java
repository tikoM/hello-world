package com.example.demo.common.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUploadManager {
    public static String createFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "-uploaded.jpg";
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/images/" + fileName)));
        stream.write(bytes);
        stream.close();
        return fileName;
    }
}
