package com.app.procesamiento_datos.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<?> cargarArchivo(@RequestParam("file") MultipartFile archivo) {

        if (archivo.isEmpty()) {
            return new ResponseEntity<>("Por favor selecciona un archivo!", HttpStatus.OK);
        }

        try {
            guardarArchivo(archivo);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Carga Finalizada - " +
                archivo.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

    private void guardarArchivo(MultipartFile file) throws IOException {

    }
}
