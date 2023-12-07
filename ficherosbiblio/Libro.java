import java.io.Serializable;

// Clase Libro
public class Libro implements Serializable {
    
    // Identificador para la serialización
    private static final long serialVersionUID = 3804753909162539963L;
    
    // Atributos de la clase Libro.
    private int id;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String genero;

    // Constructor de Libro default
    public Libro() {
        // Inicializa los atributos con valores por defecto.
        id = 0;
        titulo = " ";
        autor = " ";
        anioPublicacion = 0;
        genero = " ";
    }

    // Constructor Libro con sus atributos
    public Libro(int id, String titulo, String autor, int anioPublicacion, String genero) {
        // Inicializa los atributos con los valores proporcionados.
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
    }

    // GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
