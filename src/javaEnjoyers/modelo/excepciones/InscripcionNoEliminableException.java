package javaEnjoyers.modelo.excepciones;

public class InscripcionNoEliminableException extends Exception {
    // Constructor que acepta un mensaje
    public InscripcionNoEliminableException(String mensaje) {
        super(mensaje);  // Llama al constructor de la clase Exception con el mensaje
    }
}