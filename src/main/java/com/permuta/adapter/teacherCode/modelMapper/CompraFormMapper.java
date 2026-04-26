package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.form.CompraForm;
import com.permuta.adapter.teacherCode.model.enums.EstadoCompra;
import com.permuta.adapter.teacherCode.model.enums.MetodoPago;
import org.ainhoamarfer.modelo.enums.CompraEstadoEnum;
import org.ainhoamarfer.modelo.enums.CompraMetodoPagoEnum;

import java.time.LocalDate;

/**
 * Mapea CompraForm del profesor al CompraForm de la alumna.
 *
 * Diferencias:
 * - precioSinDescuento (profesor) -> precioSinDes (alumna)
 * - descuentoAplicado: int (profesor) -> Double descuento (alumna)
 * - MetodoPago (profesor) -> CompraMetodoPagoEnum (alumna) [mismos valores]
 * - EstadoCompra (profesor) -> CompraEstadoEnum (alumna) [mismos valores, alumna tiene CANCELADA extra]
 * - El form del profesor NO incluye fechaCompra -> se asigna LocalDate.now()
 * @CAMPO_NO_EXISTE fechaCompra: no existe en el form del profesor, se asigna LocalDate.now()
 */
public class CompraFormMapper {

    private CompraFormMapper() {}

    public static org.ainhoamarfer.modelo.form.CompraForm toStudent(CompraForm teacherForm) {
        CompraMetodoPagoEnum metodoPago = teacherForm.metodoPago() != null
            ? CompraMetodoPagoEnum.valueOf(teacherForm.metodoPago().name())
            : null;

        CompraEstadoEnum estadoCompra = teacherForm.estado() != null
            ? CompraEstadoEnum.valueOf(teacherForm.estado().name())
            : null;

        return new org.ainhoamarfer.modelo.form.CompraForm(
            teacherForm.idUsuario(),
            teacherForm.idJuego(),
            LocalDate.now(),                              // @CAMPO_NO_EXISTE: fechaCompra no existe en el form del profesor
            teacherForm.precioSinDescuento(),             // precioSinDescuento -> precioSinDes
            (double) teacherForm.descuentoAplicado(),     // int -> Double descuento
            estadoCompra,                                 // EstadoCompra -> CompraEstadoEnum
            metodoPago                                    // MetodoPago -> CompraMetodoPagoEnum
        );
    }
}
