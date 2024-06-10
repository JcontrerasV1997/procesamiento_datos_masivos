package com.app.procesamiento_datos.util;

import java.util.Objects;
import java.util.regex.Pattern;

public class ValidarCorreo {
    public static boolean validarCorreo(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pat = Pattern.compile(emailRegex);
        if (Objects.equals(email, null))
            return false;
        return pat.matcher(email).matches();
    }
}
