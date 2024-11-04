package javaEnjoyers.modelo.dao;
import javaEnjoyers.modelo.Socio;
import java.util.List;

public interface SocioDAO {
    Socio findByNumeroSocio(String numeroSocio);  // Buscar socio por número de socio
    List<Socio> findAll();                        // Listar todos los socios
    void save(Socio socio);                       // Guardar un nuevo socio
    void update(Socio socio);                     // Actualizar un socio
    void delete(String numeroSocio);              // Eliminar un socio por número de socio
}
