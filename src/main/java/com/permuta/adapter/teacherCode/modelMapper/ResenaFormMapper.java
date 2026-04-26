package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.form.ResenaForm;
import org.ainhoamarfer.modelo.enums.ResenaEstado;

import java.time.LocalDate;

/**
 * Mapea ResenaForm del profesor al ResenaForm de la alumna.
 *
 * Diferencias:
 * - El form del profesor NO incluye: fechaPublicacion, fechaUltEdicion
 *   -> Se asignan valores por defecto: LocalDate.now(), null
 * - EstadoResena (profesor) -> ResenaEstado (alumna) [mismos valores]
 * @CAMPO_NO_EXISTE fechaPublicacion: no existe en el form del profesor, se asigna LocalDate.now()
 * @CAMPO_NO_EXISTE fechaUltEdicion: no existe en el form del profesor, se asigna null
 */
public class ResenaFormMapper {

    private ResenaFormMapper() {}

    public static org.ainhoamarfer.modelo.form.ResenaForm toStudent(ResenaForm teacherForm) {
        ResenaEstado estado = teacherForm.estado() != null
            ? ResenaEstado.valueOf(teacherForm.estado().name())
            : ResenaEstado.PUBLICADA;

        return new org.ainhoamarfer.modelo.form.ResenaForm(
            teacherForm.idUsuario(),
            teacherForm.idJuego(),
            teacherForm.recomendado(),
            teacherForm.texto(),
            teacherForm.horasJugadas(),
            LocalDate.now(),  // @CAMPO_NO_EXISTE: fechaPublicacion no existe en el form del profesor
            null,             // @CAMPO_NO_EXISTE: fechaUltEdicion no existe en el form del profesor
            estado            // EstadoResena -> ResenaEstado
        );
    }
}
