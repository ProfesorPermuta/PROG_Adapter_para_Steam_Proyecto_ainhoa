package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.dto.UsuarioDTO;
import com.permuta.adapter.teacherCode.model.enums.EstadoCuenta;
import org.ainhoamarfer.modelo.enums.UsuarioEstadoCuenta;

/**
 * Mapea UsuarioDTO de la alumna al UsuarioDTO del profesor.
 *
 * Diferencias:
 * - fechaNaci (alumna) -> fechaNacimiento (profesor)
 * - UsuarioEstadoCuenta (alumna) -> EstadoCuenta (profesor) [mismos valores]
 * - La alumna no incluye contrasena en el DTO -> se pasa null
 * - La alumna no asigna pais en el DTO (bug) -> getPais() devolvera null
 */
public class UsuarioDTOMapper {

    private UsuarioDTOMapper() {}

    public static UsuarioDTO toTeacher(org.ainhoamarfer.modelo.dtos.UsuarioDTO studentDTO) {
        if (studentDTO == null) return null;
        return new UsuarioDTO(
            studentDTO.getId(),
            studentDTO.getNombreUsuario(),
            studentDTO.getEmail(),
            null,                                           // contrasena no incluida en DTO alumna
            studentDTO.getNombreReal(),
            studentDTO.getPais(),
            studentDTO.getFechaNaci(),                      // fechaNaci -> fechaNacimiento
            studentDTO.getFechaRegistro(),
            studentDTO.getAvatar(),
            studentDTO.getSaldoCartera(),
            mapEstadoCuenta(studentDTO.getEstadoCuenta())   // UsuarioEstadoCuenta -> EstadoCuenta
        );
    }

    private static EstadoCuenta mapEstadoCuenta(UsuarioEstadoCuenta estadoCuenta) {
        if (estadoCuenta == null) return null;
        return EstadoCuenta.valueOf(estadoCuenta.name());
    }
}
