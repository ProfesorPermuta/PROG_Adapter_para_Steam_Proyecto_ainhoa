package com.permuta.adapter.teacherCode.controller.impl;

import com.permuta.adapter.teacherCode.controller.IReseniaController;
import com.permuta.adapter.teacherCode.excepciones.ValidationException;
import com.permuta.adapter.teacherCode.model.dto.ErrorDto;
import com.permuta.adapter.teacherCode.model.dto.ResenaDTO;
import com.permuta.adapter.teacherCode.model.enums.ErrorType;
import com.permuta.adapter.teacherCode.model.form.ResenaForm;
import org.ainhoamarfer.controlador.ResenasControlador;
import org.ainhoamarfer.excepciones.ExcepcionValidacion;
import com.permuta.adapter.teacherCode.modelMapper.ResenaDTOMapper;
import com.permuta.adapter.teacherCode.modelMapper.ResenaFormMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ReseniaAdapter implements IReseniaController {

    private final ResenasControlador resenasControlador;

    public ReseniaAdapter(ResenasControlador controller) {
        this.resenasControlador = controller;
    }

    @Override
    public ResenaDTO crearResena(ResenaForm form) throws ValidationException {
        try {
            var result = resenasControlador.escribirResena(
                form.idUsuario(),
                form.idJuego(),
                form.recomendado(),
                form.texto()
            );
            return ResenaDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public void eliminarResena(long id, long idUsuario) throws ValidationException {
        try {
            resenasControlador.eliminarResena(id, idUsuario);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public List<ResenaDTO> listarResenasJuego(long idJuego) throws ValidationException {
        var results = resenasControlador.verResenasJuego(idJuego, "", "recientes");
        return results.stream()
            .map(ResenaDTOMapper::toTeacher)
            .collect(Collectors.toList());
    }

    @Override
    public void ocultarResena(long id, long idUsuario) throws ValidationException {
        try {
            resenasControlador.ocultarResena(id, idUsuario);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public List<ResenaDTO> listarResenasPorUsuario(long idUsuario) throws ValidationException {
        try {
            var results = resenasControlador.verResenasUsuario(idUsuario, "");
            return results.stream()
                .map(ResenaDTOMapper::toTeacher)
                .collect(Collectors.toList());
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }
}
