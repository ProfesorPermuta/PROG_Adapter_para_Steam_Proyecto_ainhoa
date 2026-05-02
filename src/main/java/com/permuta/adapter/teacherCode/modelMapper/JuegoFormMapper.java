package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.form.JuegoForm;
import com.permuta.adapter.teacherCode.model.enums.ClasificacionEdad;
import com.permuta.adapter.teacherCode.model.enums.EstadoJuego;
import org.ainhoamarfer.modelo.enums.JuegoClasificacionEdad;
import org.ainhoamarfer.modelo.enums.JuegoEstado;

import java.util.Arrays;

/**
 * Mapea JuegoForm del profesor al JuegoForm de la alumna.
 *
 * Diferencias:
 * - idiomasDisponibles: String[] (profesor) -> String idiomas (alumna) [se unen con coma]
 * - descuentoActual: int (profesor) -> Double (alumna)
 * - ClasificacionEdad (profesor) -> JuegoClasificacionEdad (alumna) [mismos valores]
 * - EstadoJuego (profesor) -> JuegoEstado (alumna) [mismos valores]
 */
public class JuegoFormMapper {

    private JuegoFormMapper() {}

    public static org.ainhoamarfer.modelo.form.JuegoForm toStudent(JuegoForm teacherForm) {
        String idiomas = teacherForm.idiomasDisponibles() != null
            ? String.join(",", teacherForm.idiomasDisponibles())  // String[] -> String con coma
            : null;

        JuegoClasificacionEdad clasificacion = teacherForm.clasificacionEdad() != null
            ? JuegoClasificacionEdad.valueOf(teacherForm.clasificacionEdad().name())
            : null;

        JuegoEstado estado = teacherForm.estado() != null
            ? JuegoEstado.valueOf(teacherForm.estado().name())
            : null;

        return new org.ainhoamarfer.modelo.form.JuegoForm(
            teacherForm.titulo(),
            teacherForm.descripcion(),
            teacherForm.desarrollador(),
            teacherForm.fechaLanzamiento(),
            teacherForm.precioBase(),
            teacherForm.descuentoActual(),  // int -> Double
            teacherForm.categoria(),
            idiomas,                                 // String[] -> String
            clasificacion,                           // ClasificacionEdad -> JuegoClasificacionEdad
            estado                                   // EstadoJuego -> JuegoEstado
        );
    }
}
