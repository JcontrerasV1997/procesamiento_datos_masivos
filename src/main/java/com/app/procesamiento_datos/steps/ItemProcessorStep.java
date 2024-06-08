package com.app.procesamiento_datos.steps;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import org.springframework.batch.item.ItemProcessor;

import java.util.Objects;
import java.util.regex.Pattern;

public class ItemProcessorStep  implements ItemProcessor<UsuarioEntity, UsuarioEntity> {


    @Override
    public UsuarioEntity process(UsuarioEntity item) throws Exception {

        item.setNombre(item.getNombre().toUpperCase());
        // Validar el correo electrónico
        if (!validarCorreo(item.getEmail())) {
            throw new Exception("Email Incorrecto: " + item.getEmail());
        }

        return item;
    }

    private boolean validarCorreo(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pat = Pattern.compile(emailRegex);
        if (Objects.equals(email,null))
            return false;
        return pat.matcher(email).matches();
    }
}
