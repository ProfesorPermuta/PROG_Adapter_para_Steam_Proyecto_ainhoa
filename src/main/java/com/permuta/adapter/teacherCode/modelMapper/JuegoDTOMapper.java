package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.dto.JuegoDTO;
import com.permuta.adapter.teacherCode.model.enums.ClasificacionEdad;
import com.permuta.adapter.teacherCode.model.enums.EstadoJuego;
import org.ainhoamarfer.modelo.enums.JuegoClasificacionEdad;
import org.ainhoamarfer.modelo.enums.JuegoEstado;

/**
 * Mapea JuegoDTO de la alumna al JuegoDTO del profesor.
 *
 * Diferencias:
 * - idiomas: String (alumna) -> String[] idiomasDisponibles (profesor) [se divide por coma]
 * - JuegoClasificacionEdad (alumna) -> ClasificacionEdad (profesor) [mismos valores]
 * - JuegoEstado (alumna) -> EstadoJuego (profesor) [mismos valores]
 */
public class JuegoDTOMapper {

    private JuegoDTOMapper() {}

    public static JuegoDTO toTeacher(org.ainhoamarfer.modelo.dtos.JuegoDTO studentDTO) {
        if (studentDTO == null) return null;

        String[] idiomas = studentDTO.getIdiomas() != null
            ? studentDTO.getIdiomas().split(",")  // String -> String[]
            : null;

        ClasificacionEdad clasificacion = studentDTO.getClasificacionEdad() != null
            ? ClasificacionEdad.valueOf(studentDTO.getClasificacionEdad().name())
            : null;

        EstadoJuego estado = studentDTO.getEstado() != null
            ? EstadoJuego.valueOf(studentDTO.getEstado().name())
            : null;

        return new JuegoDTO(
            studentDTO.getId(),
            studentDTO.getTitulo(),
            studentDTO.getDescripcion(),
            studentDTO.getDesarrollador(),
            studentDTO.getFechaLanzamiento(),
            studentDTO.getPrecioBase(),
            studentDTO.getDescuentoActual(),
            studentDTO.getCategoria(),
            clasificacion,                    // JuegoClasificacionEdad -> ClasificacionEdad
            idiomas,                          // String -> String[]
            estado                            // JuegoEstado -> EstadoJuego
        );
    }
}
