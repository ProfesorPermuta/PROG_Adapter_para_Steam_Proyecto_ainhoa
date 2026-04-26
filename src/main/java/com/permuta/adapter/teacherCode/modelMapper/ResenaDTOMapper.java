package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.dto.JuegoDTO;
import com.permuta.adapter.teacherCode.model.dto.ResenaDTO;
import com.permuta.adapter.teacherCode.model.dto.UsuarioDTO;
import com.permuta.adapter.teacherCode.model.enums.EstadoResena;

/**
 * Mapea ResenaDTO de la alumna al ResenaDTO del profesor.
 *
 * Diferencias:
 * - fechaUltEdicion (alumna) -> fechaUltimaEdicion (profesor)
 * - ResenaEstado (alumna) -> EstadoResena (profesor) [mismos valores]
 * - La alumna no incluye usuario ni juego en el DTO -> se pasan null
 */
public class ResenaDTOMapper {

    private ResenaDTOMapper() {}

    public static ResenaDTO toTeacher(org.ainhoamarfer.modelo.dtos.ResenaDTO studentDTO) {
        if (studentDTO == null) return null;

        EstadoResena estado = studentDTO.getEstado() != null
            ? EstadoResena.valueOf(studentDTO.getEstado().name())
            : null;

        return new ResenaDTO(
            studentDTO.getId(),
            studentDTO.getIdUsuario(),
            null,                               // usuario no incluido en DTO alumna
            studentDTO.getIdJuego(),
            null,                               // juego no incluido en DTO alumna
            studentDTO.isRecomendado(),
            studentDTO.getTexto(),
            studentDTO.getHorasJugadas(),
            studentDTO.getFechaPublicacion(),
            studentDTO.getFechaUltEdicion(),    // fechaUltEdicion -> fechaUltimaEdicion
            estado                              // ResenaEstado -> EstadoResena
        );
    }
}
