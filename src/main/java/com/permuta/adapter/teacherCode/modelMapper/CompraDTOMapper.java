package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.dto.CompraDTO;
import com.permuta.adapter.teacherCode.model.dto.JuegoDTO;
import com.permuta.adapter.teacherCode.model.dto.UsuarioDTO;
import com.permuta.adapter.teacherCode.model.enums.EstadoCompra;
import com.permuta.adapter.teacherCode.model.enums.MetodoPago;
import org.ainhoamarfer.modelo.enums.CompraEstadoEnum;
import org.ainhoamarfer.modelo.enums.CompraMetodoPagoEnum;

/**
 * Mapea CompraDTO de la alumna al CompraDTO del profesor.
 *
 * Diferencias:
 * - precioSinDes (alumna) -> precioSinDescuento (profesor)
 * - descuento: double (alumna) -> descuentoAplicado: double (profesor)
 * - CompraMetodoPagoEnum (alumna) -> MetodoPago (profesor) [mismos valores]
 * - CompraEstadoEnum (alumna) -> EstadoCompra (profesor) [alumna tiene CANCELADA que no existe en profesor]
 * - usuario/juego: alumna usa Optional<Entidad>, profesor usa DTO -> se mapean
 */
public class CompraDTOMapper {

    private CompraDTOMapper() {}

    public static CompraDTO toTeacher(org.ainhoamarfer.modelo.dtos.CompraDTO studentDTO) {
        if (studentDTO == null) return null;

        MetodoPago metodoPago = studentDTO.getMetodoPago() != null
            ? MetodoPago.valueOf(studentDTO.getMetodoPago().name())
            : null;

        EstadoCompra estadoCompra = studentDTO.getEstadoCompra() != null
            ? EstadoCompra.valueOf(studentDTO.getEstadoCompra().name())
            : null;

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
                com.permuta.adapter.teacherCode.model.enums.EstadoCuenta.valueOf(studentDTO.getUsuario().getEstadoCuenta().name())
            )
            : null;

        JuegoDTO juegoDTO = studentDTO.getJuego() != null
            ? JuegoDTOMapper.toTeacher(studentDTO.getJuego())
            : null;

        return new CompraDTO(
            studentDTO.getId(),
            studentDTO.getIdUsuario(),
            usuarioDTO,
            studentDTO.getIdJuego(),
            juegoDTO,
            studentDTO.getFechaCompra(),
            metodoPago,                         // CompraMetodoPagoEnum -> MetodoPago
            studentDTO.getPrecioBase(),        // precioSinDes -> precioSinDescuento
            studentDTO.getPorcentajeDescuento(),            // descuento -> descuentoAplicado
            estadoCompra                          // CompraEstadoEnum -> EstadoCompra
        );
    }
}
