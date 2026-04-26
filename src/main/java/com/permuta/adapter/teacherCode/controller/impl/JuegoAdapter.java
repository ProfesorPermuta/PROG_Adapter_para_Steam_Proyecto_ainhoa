package com.permuta.adapter.teacherCode.controller.impl;

import com.permuta.adapter.teacherCode.controller.IJuegoController;
import com.permuta.adapter.teacherCode.excepciones.ValidationException;
import com.permuta.adapter.teacherCode.model.dto.ErrorDto;
import com.permuta.adapter.teacherCode.model.dto.JuegoDTO;
import com.permuta.adapter.teacherCode.model.enums.ErrorType;
import com.permuta.adapter.teacherCode.model.enums.EstadoJuego;
import com.permuta.adapter.teacherCode.model.form.BusquedaForm;
import com.permuta.adapter.teacherCode.model.form.JuegoForm;
import org.ainhoamarfer.controlador.JuegosControlador;
import org.ainhoamarfer.excepciones.ExcepcionValidacion;
import com.permuta.adapter.teacherCode.modelMapper.BusquedaFormMapper;
import com.permuta.adapter.teacherCode.modelMapper.JuegoDTOMapper;
import com.permuta.adapter.teacherCode.modelMapper.JuegoFormMapper;

import java.util.List;
import java.util.stream.Collectors;

public class JuegoAdapter implements IJuegoController {

    private final JuegosControlador juegosControlador;

    public JuegoAdapter(JuegosControlador controller) {
        this.juegosControlador = controller;
    }

    @Override
    public JuegoDTO crearJuego(JuegoForm form) throws ValidationException {
        var alumnoForm = JuegoFormMapper.toStudent(form);
        try {
            var result = juegosControlador.anadirJuego(alumnoForm);
            return JuegoDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public List<JuegoDTO> buscarJuegos(BusquedaForm form) {
        var alumnoForm = BusquedaFormMapper.toStudent(form);
        try {
            var results = juegosControlador.buscarJuegos(alumnoForm);
            return results.stream()
                .map(JuegoDTOMapper::toTeacher)
                .collect(Collectors.toList());
        } catch (ExcepcionValidacion e) {
            return List.of();
        }
    }

    @Override
    public List<JuegoDTO> listarCatalogo() {
        throw new UnsupportedOperationException("La alumna ha comentado el metodo consultarCatalogo, no esta implementado");
    }

    @Override
    public JuegoDTO aplicarDescuento(long id, int descuento) throws ValidationException {
        try {
            var result = juegosControlador.aplicarDescuento(id, (double) descuento);
            return JuegoDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public JuegoDTO cambiarEstado(long id, EstadoJuego nuevoEstado) throws ValidationException {
        try {
            var result = juegosControlador.cambiarEstadoJuego(id, nuevoEstado.name());
            return JuegoDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }
}
