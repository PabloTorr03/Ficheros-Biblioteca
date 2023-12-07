package ficherosbiblio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Clase Autor
public class Autor implements Serializable {

    // Identificador para la serialización
    private static final long serialVersionUID = 1L;

    // Almacenamos todos los autores creados
    public static final List<Autor> listaAutores = new ArrayList<Autor>();

    // Atributos de la clase Autor
    private int id;
    private String nombre;
    private String nacionalidad;
    private int aniodenacimiento;

    // Constructor de Autor default
    public Autor() {
        id = 0;
        nombre = " ";
        nacionalidad = " ";
        aniodenacimiento = 0;
    }

    // Constructor Autor con sus atributos
    public Autor(int id, String nombre, String nacionalidad, int aniodenacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.aniodenacimiento = aniodenacimiento;
    }

    // GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getAniodenacimiento() {
        return aniodenacimiento;
    }

    public void setAniodenacimiento(int aniodenacimiento) {
        this.aniodenacimiento = aniodenacimiento;
    }
}


