package com.app.procesamiento_datos.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BatchController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private  Job job;


    @PostMapping("/upload")
    public ResponseEntity<?> cargarArchivo(@RequestParam("file") MultipartFile archivo) {

        String nombreArchivo = archivo.getOriginalFilename();
        try {
            Path path = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "files" + File.separator + nombreArchivo);
            Files.createDirectories(path.getParent());
            Files.copy(archivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File archivoCompleto = path.toAbsolutePath().toFile();

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("nombre", nombreArchivo)
                    .addDate("fecha", new Date())
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);

            Map<String, String> response = new HashMap<>();
            response.put("archivo", nombreArchivo);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
