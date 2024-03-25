package com.example.deliverysystem.Controller;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;

@RestController
public class FileController {
    @GetMapping("/download1")
    @ResponseBody
    public ResponseEntity<Resource> downloadBook1() throws IOException {
        // 获取Book目录下的文件
        File file = new File("src/main/resources/Books/余华--活着.pdf");
        InputStream inputStream = Files.newInputStream(file.toPath());
        // 将文件转换为可下载的Resource
        InputStreamResource resource = new InputStreamResource(inputStream);

        HttpHeaders headers = new HttpHeaders();
        String encodedFilename = URLEncoder.encode("余华--活着.pdf", "UTF-8").replaceAll("\\+", "%20");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/download2")
    @ResponseBody
    public ResponseEntity<Resource> downloadBook2() throws IOException {
        // 获取Book目录下的文件
        File file = new File("src/main/resources/Books/《三体2 黑暗森林》刘慈欣著.pdf");
        InputStream inputStream = Files.newInputStream(file.toPath());
        // 将文件转换为可下载的Resource
        InputStreamResource resource = new InputStreamResource(inputStream);

        HttpHeaders headers = new HttpHeaders();
        String encodedFilename = URLEncoder.encode("《三体2 黑暗森林》刘慈欣著.pdf", "UTF-8").replaceAll("\\+", "%20");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/download3")
    @ResponseBody
    public ResponseEntity<Resource> downloadBook3() throws IOException {
        // 获取Book目录下的文件
        File file = new File("src/main/resources/Books/百年孤独(黄锦炎等译).pdf");
        InputStream inputStream = Files.newInputStream(file.toPath());
        // 将文件转换为可下载的Resource
        InputStreamResource resource = new InputStreamResource(inputStream);

        HttpHeaders headers = new HttpHeaders();
        String encodedFilename = URLEncoder.encode("百年孤独(黄锦炎等译).pdf", "UTF-8").replaceAll("\\+", "%20");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(resource);
    }
}
