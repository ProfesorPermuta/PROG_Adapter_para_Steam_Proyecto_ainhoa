package com.permuta.adapter.teacherCode.controller.impl;

import com.permuta.adapter.teacherCode.controller.ICompraController;
import com.permuta.adapter.teacherCode.excepciones.ValidationException;
import com.permuta.adapter.teacherCode.model.dto.CompraDTO;
import com.permuta.adapter.teacherCode.model.dto.ErrorDto;
import com.permuta.adapter.teacherCode.model.enums.ErrorType;
import com.permuta.adapter.teacherCode.model.form.CompraForm;
import com.permuta.adapter.teacherCode.modelMapper.CompraFormMapper;
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
        var alumnoForm = CompraFormMapper.toStudent(form);  // ← mapeas el form del profesor al de la alumna
        try {
            var result = compraControlador.realizarCompra(alumnoForm);  // ← pasas el formulario
            return CompraDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public CompraDTO procesarPago(long idCompra) throws ValidationException {
        try {
            var result = compraControlador.procesarPago(idCompra);
            return CompraDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
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
        try {
            var result = compraControlador.solicitarReembolso(idCompra);
            return CompraDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }
}
