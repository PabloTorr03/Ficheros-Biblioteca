import java.io.Serializable;

// Clase Prestamo
public class Prestamo implements Serializable {

    // Atributos de la clase Prestamo.
    private int idlibro;
    private String nombreusuario;
    private String fechaprestamo;
    private String fechadevolucion;

    // Constructor de Prestamo default
    public Prestamo() {
        // Inicializa los atributos con valores por defecto.
        idlibro = 0;
        nombreusuario = "";
        fechaprestamo = "";
        fechadevolucion = "";
    }

    // Constructor Libro con sus atributos
    public Prestamo(int idlibro, String nombreusuario, String fechaprestamo, String fechadevolucion) {
        // Inicializa los atributos con los valores proporcionados.
        this.idlibro = idlibro;
        this.nombreusuario = nombreusuario;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
    }

    // GETTERS Y SETTERS

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(String fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public String getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(String fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }
}
