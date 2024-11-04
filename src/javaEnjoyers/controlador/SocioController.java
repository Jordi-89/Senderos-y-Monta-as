package javaEnjoyers.controlador;

import javaEnjoyers.modelo.Socio;
import javaEnjoyers.service.SocioService;

import java.util.List;

public class SocioController {
    private SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    public void mostrarSocios() {
        List<Socio> socios = socioService.findAll();
        for (Socio socio : socios) {
            System.out.println(socio);
        }
    }

    public void agregarSocio(Socio socio) {
        socioService.save(socio);
        System.out.println("Socio añadido correctamente.");
    }

    public void eliminarSocio(String numeroSocio) {
        socioService.delete(numeroSocio);
        System.out.println("Socio eliminado correctamente.");
    }
}
