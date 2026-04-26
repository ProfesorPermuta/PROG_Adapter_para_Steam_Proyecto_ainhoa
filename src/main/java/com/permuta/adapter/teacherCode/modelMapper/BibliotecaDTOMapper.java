package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.dto.BibliotecaDTO;
import com.permuta.adapter.teacherCode.model.dto.JuegoDTO;
import com.permuta.adapter.teacherCode.model.dto.UsuarioDTO;
import com.permuta.adapter.teacherCode.model.enums.EstadoInstalacion;

/**
 * Mapea BibliotecaDTO de la alumna al BibliotecaDTO del profesor.
 *
 * Diferencias:
 * - tiempoJuego (alumna) -> tiempoJuegoTotal (profesor)
 * - fechaUltimaJugado (alumna) -> ultimaFechaJuego (profesor)
 * - instalado: boolean (alumna) -> estadoInstalacion: EstadoInstalacion (profesor)
 *   [true -> INSTALADO, false -> NO_INSTALADO]
 * - usuario: UsuarioEntidad (alumna) -> UsuarioDTO (profesor)
 * - juego: JuegoEntidad (alumna) -> JuegoDTO (profesor)
 */
public class BibliotecaDTOMapper {

    private BibliotecaDTOMapper() {}

    public static BibliotecaDTO toTeacher(org.ainhoamarfer.modelo.dtos.BibliotecaDTO studentDTO) {
        if (studentDTO == null) return null;

        EstadoInstalacion estadoInstalacion = studentDTO.isInstalado()
            ? EstadoInstalacion.INSTALADO
            : EstadoInstalacion.NO_INSTALADO;

        UsuarioDTO usuarioDTO = studentDTO.getUsuario() != null
            ? new UsuarioDTO(
                studentDTO.getUsuario().getId(),
                studentDTO.getUsuario().getNombreUsuario(),
                studentDTO.getUsuario().getEmail(),
                null,
                studentDTO.getUsuario().getNombreReal(),
                studentDTO.getUsuario().getPais(),
                studentDTO.getUsuario().getFechaNaci(),
                studentDTO.getUsuario().getFechaRegistro(),
                studentDTO.getUsuario().getAvatar(),
                studentDTO.getUsuario().getSaldoCartera(),
                com.permuta.adapter.teacherCode.model.enums.EstadoCuenta.valueOf(
                    studentDTO.getUsuario().getEstadoCuenta().name())
            )
            : null;

        JuegoDTO juegoDTO = studentDTO.getJuego() != null
            ? JuegoDTOMapper.toTeacher(new org.ainhoamarfer.modelo.dtos.JuegoDTO(
                studentDTO.getJuego().getId(),
                studentDTO.getJuego().getTitulo(),
                studentDTO.getJuego().getDescripcion(),
                studentDTO.getJuego().getDesarrollador(),
                studentDTO.getJuego().getFechaLanzamiento(),
                studentDTO.getJuego().getPrecioBase(),
                studentDTO.getJuego().getDescuentoActual(),
                studentDTO.getJuego().getCategoria(),
                studentDTO.getJuego().getIdiomas(),
                studentDTO.getJuego().getClasificacionEdad(),
                studentDTO.getJuego().getEstado()
            ))
            : null;

        return new BibliotecaDTO(
            studentDTO.getId(),
            studentDTO.getIdUsuario(),
            usuarioDTO,
            studentDTO.getIdJuego(),
            juegoDTO,
            studentDTO.getFechaAdquisicion(),
            studentDTO.getTiempoJuego(),       // tiempoJuego -> tiempoJuegoTotal
            studentDTO.getFechaUltimaJugado(), // fechaUltimaJugado -> ultimaFechaJuego
            estadoInstalacion                  // boolean -> EstadoInstalacion
        );
    }
}
