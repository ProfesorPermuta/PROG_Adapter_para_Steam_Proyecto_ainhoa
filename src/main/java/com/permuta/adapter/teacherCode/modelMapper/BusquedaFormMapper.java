package com.permuta.adapter.teacherCode.modelMapper;

import com.permuta.adapter.teacherCode.model.form.BusquedaForm;
import com.permuta.adapter.teacherCode.model.enums.ClasificacionEdad;
import com.permuta.adapter.teacherCode.model.enums.EstadoJuego;
import org.ainhoamarfer.modelo.enums.JuegoClasificacionEdad;
import org.ainhoamarfer.modelo.enums.JuegoEstado;

/**
 * Mapea BusquedaForm del profesor al CriteriosBusquedaForm de la alumna.
 *
 * Diferencias:
 * - BusquedaForm del profesor: texto, categoria, clasificacionEdad, estado
 * - CriteriosBusquedaForm de la alumna: titulo, descripcion, desarrollador, fechaLanzamiento, precioBase, categoria
 *
 * Mapeo:
 * - texto (profesor) -> titulo (alumna)
 * - clasificacionEdad: no existe en CriteriosBusquedaForm de alumna -> se ignora
 * - estado: no existe en CriteriosBusquedaForm de alumna -> se ignora
 * - descripcion, desarrollador, fechaLanzamiento, precioBase: no existen en BusquedaForm del profesor -> valores por defecto
 * @CAMPO_NO_EXISTE descripcion: no existe en el form del profesor, se asigna null
 * @CAMPO_NO_EXISTE desarrollador: no existe en el form del profesor, se asigna null
 * @CAMPO_NO_EXISTE fechaLanzamiento: no existe en el form del profesor, se asigna null
 * @CAMPO_NO_EXISTE precioBase: no existe en el form del profesor, se asigna 0.0
 */
public class BusquedaFormMapper {

    private BusquedaFormMapper() {}

    public static org.ainhoamarfer.modelo.form.CriteriosBusquedaForm toStudent(BusquedaForm teacherForm) {
        return new org.ainhoamarfer.modelo.form.CriteriosBusquedaForm(
            teacherForm.texto(),    // texto -> titulo
            null,                   // @CAMPO_NO_EXISTE: descripcion no existe en el form del profesor
            null,                   // @CAMPO_NO_EXISTE: desarrollador no existe en el form del profesor
            null,                   // @CAMPO_NO_EXISTE: fechaLanzamiento no existe en el form del profesor
            0.0,                    // @CAMPO_NO_EXISTE: precioBase no existe en el form del profesor
            teacherForm.categoria() // categoria -> categoria
        );
    }
}
