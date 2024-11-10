package javaEnjoyers.service;

import javaEnjoyers.modelo.*;
import javaEnjoyers.modelo.dao.*;
import javaEnjoyers.modelo.impl_dao.FederacionDAOImpl;

import java.util.List;

public class SocioService {
    private SocioEstandarDAO socioEstandarDAO;
    private SocioFederadoDAO socioFederadoDAO;
    private SocioInfantilDAO socioInfantilDAO;
    private FederacionDAO federacionDAO;

    public SocioService(SocioEstandarDAO socioEstandarDAO, SocioFederadoDAO socioFederadoDAO, SocioInfantilDAO socioInfantilDAO, FederacionDAOImpl federacionDAO) {
        this.socioEstandarDAO = socioEstandarDAO;
        this.socioFederadoDAO = socioFederadoDAO;
        this.socioInfantilDAO = socioInfantilDAO;
        this.federacionDAO = federacionDAO;
    }

    public Socio findByNumeroSocio(String numeroSocio) {
        Socio socio = socioEstandarDAO.findByNumeroSocio(numeroSocio);
        if (socio == null) {
            socio = socioFederadoDAO.findByNumeroSocio(numeroSocio);
        }
        if (socio == null) {
            socio = socioInfantilDAO.findByNumeroSocio(numeroSocio);
        }
        return socio;
    }

    public List<Socio> findAll() {
        List<Socio> socios = socioEstandarDAO.findAll();
        socios.addAll(socioFederadoDAO.findAll());
        socios.addAll(socioInfantilDAO.findAll());
        return socios;
    }

    public List<Federacion> findAllFederaciones() {
        return federacionDAO.findAll();
    }


    public void save(Socio socio) {
        if (socio instanceof SocioEstandar) {
            socioEstandarDAO.save((SocioEstandar) socio);
        } else if (socio instanceof SocioFederado) {
            socioFederadoDAO.save((SocioFederado) socio);
        } else if (socio instanceof SocioInfantil) {
            socioInfantilDAO.save((SocioInfantil) socio);
        }
    }

    public void update(Socio socio) {
        if (socio instanceof SocioEstandar) {
            socioEstandarDAO.update((SocioEstandar) socio);
        } else if (socio instanceof SocioFederado) {
            socioFederadoDAO.update((SocioFederado) socio);
        } else if (socio instanceof SocioInfantil) {
            socioInfantilDAO.update((SocioInfantil) socio);
        }
    }

    public void delete(String numeroSocio) {
        if (socioEstandarDAO.findByNumeroSocio(numeroSocio) != null) {
            socioEstandarDAO.delete(numeroSocio);
        } else if (socioFederadoDAO.findByNumeroSocio(numeroSocio) != null) {
            socioFederadoDAO.delete(numeroSocio);
        } else if (socioInfantilDAO.findByNumeroSocio(numeroSocio) != null) {
            socioInfantilDAO.delete(numeroSocio);
        }
    }
}
