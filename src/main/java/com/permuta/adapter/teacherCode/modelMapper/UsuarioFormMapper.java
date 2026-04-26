package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.form.UsuarioForm;

/**
 * Mapea UsuarioForm del profesor al UsuarioForm de la alumna.
 *
 * Diferencias:
 * - fechaNacimiento (profesor) -> fechaNaci (alumna)
 * - El form del profesor NO incluye: fechaRegistro, saldoCartera, estadoCuenta
 *   -> Se asignan valores por defecto: null
 * @CAMPO_NO_EXISTE fechaRegistro: no existe en el form del profesor, se asigna null
 * @CAMPO_NO_EXISTE saldoCartera: no existe en el form del profesor, se asigna null
 * @CAMPO_NO_EXISTE estadoCuenta: no existe en el form del profesor, se asigna null
 */
public class UsuarioFormMapper {

    private UsuarioFormMapper() {}

    public static org.ainhoamarfer.modelo.form.UsuarioForm toStudent(UsuarioForm teacherForm) {
        return new org.ainhoamarfer.modelo.form.UsuarioForm(
            teacherForm.nombreUsuario(),
            teacherForm.email(),
            teacherForm.contrasena(),
            teacherForm.nombreReal(),
            teacherForm.pais(),
            teacherForm.fechaNacimiento(), // fechaNacimiento -> fechaNaci
            null,                          // @CAMPO_NO_EXISTE: fechaRegistro no existe en el form del profesor
            teacherForm.avatar(),
            0d,                          // @CAMPO_NO_EXISTE: saldoCartera no existe en el form del profesor
            null                           // @CAMPO_NO_EXISTE: estadoCuenta no existe en el form del profesor
        );
    }
}
