package com.example.FileRestful.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Controller
public class FileController {

    private final Path root = Paths.get("uploads");

    public FileController() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            // Directory already exists.
        }
    }

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        // 這裡可以添加一些需要傳遞給模板的數據
        // model.addAttribute("files", listFiles());
        return "upload";  // 返回模板名稱，不包括 .html
    }


    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/files")
    public ResponseEntity<?> listUploadedFiles() throws IOException {
        return ResponseEntity.ok(
                Files.walk(this.root, 1)
                        .filter(path -> !path.equals(this.root))
                        .map(this.root::relativize)
                        .map(path -> MvcUriComponentsBuilder
                                .fromMethodName(FileController.class, "serveFile", path.toString())
                                .build().toUri().toString())
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(404).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
