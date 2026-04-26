package com.permuta.factory;

import com.permuta.adapter.teacherCode.controller.impl.BibliotecaAdapter;
import com.permuta.adapter.teacherCode.controller.impl.CompraAdapter;
import com.permuta.adapter.teacherCode.controller.impl.JuegoAdapter;
import com.permuta.adapter.teacherCode.controller.impl.ReseniaAdapter;
import com.permuta.adapter.teacherCode.controller.impl.UsuarioAdapter;
import org.ainhoamarfer.controlador.BibliotecaControlador;
import org.ainhoamarfer.controlador.CompraControlador;
import org.ainhoamarfer.controlador.JuegosControlador;
import org.ainhoamarfer.controlador.ResenasControlador;
import org.ainhoamarfer.controlador.UsuarioControlador;
import org.ainhoamarfer.repositorio.implementacion_memoria.BibliotecaRepo;
import org.ainhoamarfer.repositorio.implementacion_memoria.CompraRepo;
import org.ainhoamarfer.repositorio.implementacion_memoria.JuegoRepo;
import org.ainhoamarfer.repositorio.implementacion_memoria.ResenaRepo;
import org.ainhoamarfer.repositorio.implementacion_memoria.UsuarioRepo;
import org.ainhoamarfer.transaction.NoOpTransactionManager;

import java.lang.reflect.Field;
import java.util.List;

public final class AdapterFactory {


    public static AdapterBundle getAdapterBundle(String groupID)
            throws NoSuchFieldException, IllegalAccessException {

        resetRepoStatics(UsuarioRepo.class, "USUARIOS", "idContador");
        resetRepoStatics(JuegoRepo.class, "JUEGOS", "idContador");
        resetRepoStatics(CompraRepo.class, "COMPRAS", "idContador");
        resetRepoStatics(ResenaRepo.class, "RESENAS", "idContador");
        resetRepoStatics(BibliotecaRepo.class, "BIBLIOTECAS", "idContador");

        UsuarioRepo usuarioRepo = new UsuarioRepo();
        JuegoRepo juegoRepo = new JuegoRepo();
        CompraRepo compraRepo = new CompraRepo();
        ResenaRepo resenaRepo = new ResenaRepo();
        BibliotecaRepo bibliotecaRepo = new BibliotecaRepo();

        UsuarioControlador usuarioCtrl = new UsuarioControlador(usuarioRepo);
        JuegosControlador juegoCtrl = new JuegosControlador(juegoRepo);
        CompraControlador compraCtrl = new CompraControlador(compraRepo, juegoRepo, usuarioRepo, bibliotecaRepo);
        ResenasControlador resenasCtrl = new ResenasControlador(resenaRepo, bibliotecaRepo);
        BibliotecaControlador bibliotecaCtrl = new BibliotecaControlador(bibliotecaRepo, usuarioRepo, juegoRepo);

        return new AdapterBundle(
            new UsuarioAdapter(usuarioCtrl),
            new CompraAdapter(compraCtrl),
            new JuegoAdapter(juegoCtrl),
            new ReseniaAdapter(resenasCtrl),
            new BibliotecaAdapter(bibliotecaCtrl)
        );
    }

    @SuppressWarnings("unchecked")
    private static void resetRepoStatics(Class<?> repoClass, String listFieldName, String counterFieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field listField = repoClass.getDeclaredField(listFieldName);
        listField.setAccessible(true);
        ((List<?>) listField.get(null)).clear();

        Field counterField = repoClass.getDeclaredField(counterFieldName);
        counterField.setAccessible(true);
        counterField.set(null, 1L);
    }
}
