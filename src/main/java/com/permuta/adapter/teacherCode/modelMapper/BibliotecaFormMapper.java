package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.form.BibliotecaForm;
import com.permuta.adapter.teacherCode.model.enums.EstadoInstalacion;

/**
 * Mapea BibliotecaForm del profesor al BibliotecaForm de la alumna.
 *
 * Diferencias:
 * - tiempoJuegoTotal (profesor) -> tiempoJuego (alumna)
 * - ultimaFechaJuego (profesor) -> fechaUltimaJugado (alumna)
 * - estadoInstalacion: EstadoInstalacion enum (profesor) -> Boolean instalado (alumna)
 *   [INSTALADO -> true, NO_INSTALADO -> false]
 */
public class BibliotecaFormMapper {

    private BibliotecaFormMapper() {}

    public static org.ainhoamarfer.modelo.form.BibliotecaForm toStudent(BibliotecaForm teacherForm) {
        Boolean instalado = teacherForm.estadoInstalacion() != null
            ? teacherForm.estadoInstalacion() == EstadoInstalacion.INSTALADO
            : null;

        return new org.ainhoamarfer.modelo.form.BibliotecaForm(
            teacherForm.idUsuario(),
            teacherForm.idJuego(),
            teacherForm.fechaAdquisicion(),
            teacherForm.tiempoJuegoTotal(),   // tiempoJuegoTotal -> tiempoJuego
            teacherForm.ultimaFechaJuego(),   // ultimaFechaJuego -> fechaUltimaJugado
            instalado                         // EstadoInstalacion -> Boolean
        );
    }
}
