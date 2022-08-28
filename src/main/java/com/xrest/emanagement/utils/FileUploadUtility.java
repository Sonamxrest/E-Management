package com.xrest.emanagement.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtility {

    public String upload(MultipartFile file, String path) {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            byte [] bytes = file.getBytes();
            String root = "";
            if (os.contains("win")) {
                root += "C:/";
            } else {
                root += "documents/";
            }
            Path path1 = Paths.get(root + path);
            File file1 =path1.toFile();
            if (!Files.exists(path1)) {
                String filePath = path1.toString() + System.currentTimeMillis()  + "." + file.getOriginalFilename();
                new File(filePath).mkdirs();
                Files.write(path1,bytes);
            }
        } catch (Exception ex) {

        }
        return "";
    }
}
