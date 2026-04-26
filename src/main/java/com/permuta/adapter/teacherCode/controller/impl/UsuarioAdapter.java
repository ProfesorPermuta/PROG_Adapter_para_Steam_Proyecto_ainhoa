package com.permuta.adapter.teacherCode.controller.impl;

import com.permuta.adapter.teacherCode.controller.IUsuarioController;
import com.permuta.adapter.teacherCode.excepciones.ValidationException;
import com.permuta.adapter.teacherCode.model.dto.ErrorDto;
import com.permuta.adapter.teacherCode.model.dto.UsuarioDTO;
import com.permuta.adapter.teacherCode.model.enums.ErrorType;
import com.permuta.adapter.teacherCode.model.form.UsuarioForm;
import org.ainhoamarfer.controlador.UsuarioControlador;
import org.ainhoamarfer.excepciones.ExcepcionValidacion;
import com.permuta.adapter.teacherCode.modelMapper.UsuarioDTOMapper;
import com.permuta.adapter.teacherCode.modelMapper.UsuarioFormMapper;

import java.util.List;

public class UsuarioAdapter implements IUsuarioController {

    private final UsuarioControlador usuarioControlador;

    public UsuarioAdapter(UsuarioControlador controller) {
        this.usuarioControlador = controller;
    }

    @Override
    public UsuarioDTO creaUsuarioDTO(UsuarioForm form) throws ValidationException {
        var alumnoForm = UsuarioFormMapper.toStudent(form);
        try {
            var result = usuarioControlador.registrarNuevoUsuario(alumnoForm);
            return UsuarioDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public UsuarioDTO consultarPerfil(long id) {
        try {
            var result = usuarioControlador.consultarPerfil(id);
            return UsuarioDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            return null;
        }
    }

    @Override
    public UsuarioDTO consultarPerfil(String nombreUsuario) {
        throw new UnsupportedOperationException("La alumna no implementa busqueda por nombre de usuario en el controlador");
    }

    @Override
    public UsuarioDTO aniadirSaldo(long id, double cantidad) throws ValidationException {
        try {
            usuarioControlador.anadirSaldoCartera(cantidad, id);
            return UsuarioDTOMapper.toTeacher(usuarioControlador.consultarPerfil(id));
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public double consultarSaldo(long id) throws ValidationException {
        try {
            return usuarioControlador.consultarSaldoCartera(id);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }
}
