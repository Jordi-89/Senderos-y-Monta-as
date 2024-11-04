// src/javaEnjoyers/modelo/SocioFactory.java
package javaEnjoyers.modelo;

public class SocioFactory {
    public static Socio createSocio(String tipo, String numeroSocio, String nombre, String nif, Seguro seguro, Federacion federacion, String numeroSocioAdulto) {
        switch (tipo.toUpperCase()) {
            case "ESTANDAR":
                return new SocioEstandar(numeroSocio, nombre, nif, seguro);
            case "FEDERADO":
                return new SocioFederado(numeroSocio, nombre, nif, federacion);
            case "INFANTIL":
                return new SocioInfantil(numeroSocio, nombre, numeroSocioAdulto);
            default:
                throw new IllegalArgumentException("Tipo de socio desconocido: " + tipo);
        }
    }
}
