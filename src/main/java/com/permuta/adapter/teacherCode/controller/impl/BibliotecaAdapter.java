package com.permuta.adapter.teacherCode.controller.impl;

import com.permuta.adapter.teacherCode.controller.IBibliotecaController;
import com.permuta.adapter.teacherCode.excepciones.ValidationException;
import com.permuta.adapter.teacherCode.model.dto.BibliotecaDTO;
import com.permuta.adapter.teacherCode.model.dto.ErrorDto;
import com.permuta.adapter.teacherCode.model.enums.ErrorType;
import org.ainhoamarfer.controlador.BibliotecaControlador;
import org.ainhoamarfer.excepciones.ExcepcionValidacion;
import com.permuta.adapter.teacherCode.modelMapper.BibliotecaDTOMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaAdapter implements IBibliotecaController {

    private final BibliotecaControlador bibliotecaControlador;

    public BibliotecaAdapter(BibliotecaControlador controller) {
        this.bibliotecaControlador = controller;
    }

    @Override
    public List<BibliotecaDTO> obtenerJuegosUsuario(Long idUsuario) throws ValidationException {
        try {
            var results = bibliotecaControlador.verBibliotecaPersonal(idUsuario, "");
            return results.stream()
                .map(BibliotecaDTOMapper::toTeacher)
                .collect(Collectors.toList());
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public boolean agregarJuegoBiblioteca(Long idUsuario, Long idJuego) throws ValidationException {
        try {
            return bibliotecaControlador.anadirJuegoBiblioteca(idUsuario, idJuego) != null;
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("La alumna no ha implementado anadirJuegoBiblioteca");
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public void eliminarJuegoBiblioteca(Long idUsuario, Long idJuego) throws ValidationException {
        try {
            bibliotecaControlador.eliminarJuegoBiblioteca(idUsuario, idJuego);
        } catch (ExcepcionValidacion e) {
            // convertir a ValidationException con los detalles que necesites
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public BibliotecaDTO actualizarTiempoJuego(long idUsuario, long idJuego, int tiempoJugado) throws ValidationException {
        try {
            var result = bibliotecaControlador.actualizarTiempoJuego(idUsuario, idJuego, (double) tiempoJugado);
            return BibliotecaDTOMapper.toTeacher(result);
        } catch (ExcepcionValidacion e) {
            throw new ValidationException(List.of(new ErrorDto("error", ErrorType.FORMATO_INVALIDO)));
        }
    }

    @Override
    public BibliotecaDTO consultaUltimaSesion() {
        throw new UnsupportedOperationException("La alumna requiere idUsuario e idJuego en consultarUltimaSesion, no implementable sin parametros");
    }
}
