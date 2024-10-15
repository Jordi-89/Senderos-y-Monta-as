import java.util.ArrayList;

public class centroExcursionista {

    //Atributos
    private ArrayList<socio> socios;
    private ArrayList<inscripcion> inscripciones;
    private ArrayList<excursion> excursiones;

    //Constructor
    public centroExcursionista(ArrayList<socio> socios, ArrayList<inscripcion> inscripciones, ArrayList<excursion> excursiones) {
        socios = new ArrayList<>();
        inscripciones = new ArrayList<>();
        excursiones = new ArrayList<>();
    }

    //Getters y Setters
    public ArrayList<socio> getSocios() { return socios; }
    public ArrayList<inscripcion> getInscripciones() { return inscripciones; }
    public ArrayList<excursion> getExcursiones() { return excursiones; }

    public void setSocios(ArrayList<socio> socios) { this.socios = socios; }
    public void setInscripciones(ArrayList<inscripcion> inscripciones) { this.inscripciones = inscripciones; }
    public void setExcursiones(ArrayList<excursion> excursiones) { this.excursiones = excursiones; }

    //toString
    @Override
    public String toString() {
        return "centroExcursionista{" +
                "socios=" + socios +
                ", inscripciones=" + inscripciones +
                ", excursiones=" + excursiones +
                '}';
    }
}
