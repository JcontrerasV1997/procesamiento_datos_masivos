package com.app.procesamiento_datos.steps.step1;

import com.app.procesamiento_datos.model.entity.UsuarioEntity;
import com.app.procesamiento_datos.util.ValidarCorreo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



      /*  La clase ItemProcessorStep es una implementación de la interfaz Tasklet de Spring Batch. Esta clase se utiliza para procesar una lista de entidades UsuarioEntity. En el método execute, se realiza lo siguiente:
        Se recupera una lista de entidades UsuarioEntity del contexto de ejecución del trabajo.
        Si la lista no es nula, se procesa cada entidad UsuarioEntity de la siguiente manera:
        Se convierte el nombre del usuario a mayúsculas.
        Se valida el correo electrónico del usuario utilizando el método validarCorreo de la clase ValidarCorreo. Si el correo electrónico no es válido, se lanza una excepción.
        Después de procesar todas las entidades, se almacena la lista procesada de nuevo en el contexto de ejecución del trabajo.*/
@Slf4j
public class ItemProcessorStep implements Tasklet {
    /*En este paso se procesara nuestro archivo para realizar las validaciones para convertir nombres a mayusculas y validacion del correo*/
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("__________________ Inicia Procesamiento__________________");
        List<UsuarioEntity> listaDeUsuarios = (List<UsuarioEntity>) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("listaDeUsuarios");

        if (!Objects.equals(listaDeUsuarios, null)) {
            List<UsuarioEntity> procesamientoFinal = listaDeUsuarios.stream().map(usuario -> {
                try {
                    usuario.setNombre(usuario.getNombre().toUpperCase());
                    if (!ValidarCorreo.validarCorreo(usuario.getEmail())) {
                        throw new Exception("Email Incorrecto: " + usuario.getEmail());
                    }
                    return usuario;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            chunkContext.getStepContext()
                    .getStepExecution()
                    .getJobExecution()
                    .getExecutionContext()
                    .put("listaDeUsuarios", procesamientoFinal);
        }
        return null;
    }

}
