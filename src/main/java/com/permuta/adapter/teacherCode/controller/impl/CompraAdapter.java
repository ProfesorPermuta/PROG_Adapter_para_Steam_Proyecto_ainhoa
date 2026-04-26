package com.permuta.adapter.teacherCode.controller.impl;

import com.permuta.adapter.teacherCode.controller.ICompraController;
import com.permuta.adapter.teacherCode.excepciones.ValidationException;
import com.permuta.adapter.teacherCode.model.dto.CompraDTO;
import com.permuta.adapter.teacherCode.model.dto.ErrorDto;
import com.permuta.adapter.teacherCode.model.enums.ErrorType;
import com.permuta.adapter.teacherCode.model.form.CompraForm;
import org.ainhoamarfer.controlador.CompraControlador;
import org.ainhoamarfer.excepciones.ExcepcionValidacion;
import com.permuta.adapter.teacherCode.modelMapper.CompraDTOMapper;

import java.util.List;

public class CompraAdapter implements ICompraController {

    private final CompraControlador compraControlador;

    public CompraAdapter(CompraControlador controller) {
        this.compraControlador = controller;
    }

    @Override
    public CompraDTO realizarCompra(CompraForm form) throws ValidationException {
        var metodoPago = org.ainhoamarfer.modelo.enums.CompraMetodoPagoEnum.valueOf(form.metodoPago().name());
        try {
            var result = compraControlador.realizarCompra(form.idUsuario(), form.idJuego(), metodoPago);
            return CompraDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public CompraDTO procesarPago(long idCompra) throws ValidationException {
        throw new UnsupportedOperationException("La alumna requiere metodoPago en procesarPago, no implementable sin parametro adicional");
    }

    @Override
    public CompraDTO consultarCompra(long id, long idUsuario) throws ValidationException {
        try {
            var result = compraControlador.consultarDetallesCompra(id, idUsuario);
            return CompraDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public CompraDTO solicitarReembolso(long idCompra) throws ValidationException {
        throw new UnsupportedOperationException("La alumna no ha implementado solicitarReembolso");
    }
}
