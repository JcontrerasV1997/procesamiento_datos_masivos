package com.app.procesamiento_datos.util;

import java.util.Objects;
import java.util.regex.Pattern;

/* Clase utilitaria que valida formato de correo
 esta expresión regular coincide con cualquier cadena que comience con uno o más caracteres alfanuméricos (o +, _, . o -),
  seguido de un @, y luego uno o más de cualquier carácter,
 y eso es todo lo que hay en la línea. Esto sería un formato válido para un correo electrónico*/

public class ValidarCorreo {
    public static boolean validarCorreo(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pat = Pattern.compile(emailRegex);
        if (Objects.equals(email, null))
            return false;
        return pat.matcher(email).matches();
    }
}
