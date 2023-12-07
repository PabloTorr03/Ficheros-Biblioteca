package ficherosbiblio;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import java.util.Scanner;
import java.util.stream.Stream;

import org.w3c.dom.*;

// Clase que gestiona los de archivos y datos de los libros, autores y préstamos
public class GestorFicheros {
    // Listas para almacenar libros y autores
    static ArrayList<Libro> libros = new ArrayList();
    static ArrayList<Autor> autores = new ArrayList();

    // Método para agregar un libro a la lista y al archivo binario
    public static void agregarLibro(Libro libro) {
        try {
            ArrayList<Libro> listaLibros = leerLibros();
            listaLibros.add(libro);
            libros.add(libro);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Libros.bin", false));
            oos.writeObject(listaLibros);
            oos.close();
            System.out.println("Libro Creado!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Método para imprimir la lista de libros
    public static void imprimirLibros() {
        ArrayList<Libro> listaLibros = leerLibros();

        if (listaLibros.size() != 0) {
            for (Libro libro : listaLibros) {
                System.out.println("--------------------");
                System.out.println("ID: " + libro.getId() + "\nTítulo: " + libro.getTitulo() + "\nAutor: "
                        + libro.getAutor() + "\nPublicación: " + libro.getAnioPublicacion() + "\nGénero: "
                        + libro.getGenero());
            }
        } else {
            System.out.println("No hay libros guardados.");
        }
    }

    // Método para editar un libro en la lista y en el archivo binario
    public static void editarLibro() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        ArrayList<Libro> listaLibros1 = new ArrayList();
        ArrayList<Libro> listaLibros = leerLibros();
        if (listaLibros.isEmpty() || listaLibros.size() == 0) {
            System.out.println("No hay libros guardados para editar");
        } else {
            System.out.println("Hay libros para editar");
            while (!salir) {
                System.out.println("Inserte id del libro:");
                int id = scanner.nextInt();
                for (Libro libro : listaLibros) {
                    if (id == libro.getId()) {
                        salir = true;
                        System.out.println("Introduce los nuevos datos para el libro con id " + id);
                        System.out.println("Introduzca el Título del libro:");
                        String titulo = scanner.nextLine();
                        scanner.nextLine();
                        System.out.println("Introduzca el Autor del libro:");
                        String autor = scanner.nextLine();
                        System.out.println("Introduzca el Año de publicación del libro:");
                        int anioPublicacion = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Introduzca el Género del libro:");
                        String genero = scanner.nextLine();
                        try {
                            for (Libro libro1 : listaLibros) {
                                if (id == libro1.getId()) {
                                    Libro libro2 = new Libro(id, titulo, autor, anioPublicacion, genero);
                                    listaLibros1.add(libro2);
                                } else {
                                    listaLibros1.add(libro1);
                                }
                            }
                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Libros.bin", false));
                            oos.writeObject(listaLibros1);
                            libros = listaLibros1;
                            oos.close();
                        } catch (Exception e) {
                            System.out.println("Error al crear nueva lista de libros");
                            System.out.println(e);
                        }
                    }
                }
                if (!salir) {
                    System.out.println("El id no es valido introduzca otro");
                }
            }
            System.out.println("Se han hecho los cambios correctamente");
        }
    }

    // Método para eliminar un libro de la lista y del archivo binario
    public static void eliminarLibro() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        ArrayList<Libro> listaLibros1 = new ArrayList();
        ArrayList<Libro> listaLibros = leerLibros();
        if (listaLibros.isEmpty() || listaLibros.size() == 0) {
            System.out.println("No hay libros guardados para eliminar");
        } else {
            System.out.println("Hay libros para eliminar");
            while (!salir) {
                System.out.println("Inserte id del libro:");
                int id = scanner.nextInt();
                for (Libro libro : listaLibros) {
                    if (id == libro.getId()) {
                        salir = true;
                        try {
                            for (Libro libro1 : listaLibros) {
                                if (id != libro1.getId()) {
                                    listaLibros1.add(libro1);
                                }
                            }
                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Libros.bin", false));
                            oos.writeObject(listaLibros1);
                            libros = listaLibros1;
                            oos.close();
                        } catch (Exception e) {
                            System.out.println("Error al crear nueva lista de libros");
                            System.out.println(e);
                        }
                    }
                }
                if (!salir) {
                    System.out.println("El id no es valido introduzca otro");
                }
            }
            System.out.println("Se ha eliminado correctamente");
        }
    }

    // Método para leer la lista de libros desde el archivo binario
    private static ArrayList<Libro> leerLibros() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Libros.bin"))) {
            try {
                // Leer el objeto ArrayList<Libro> desde el archivo
                return (ArrayList<Libro>) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            // El archivo aún no existe o está vacío
        }
        return new ArrayList<>();
    }

    // Método para agregar un autor a la lista y al archivo binario
    public static void agregarAutor(Autor autor) {
        try {
            ArrayList<Autor> listaAutor = leerAutores();
            listaAutor.add(autor);
            autores.add(autor);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Autores.bin", false));
            oos.writeObject(listaAutor);
            oos.close();
            System.out.println("Autor Creado!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Método para imprimir la lista de autores
    public static void imprimirAutores() {
        ArrayList<Autor> listaAutores = leerAutores();

        if (listaAutores.size() != 0) {
            for (Autor autor : listaAutores) {
                System.out.println("--------------------");
                System.out.println("ID: " + autor.getId() + "\nNombre: " + autor.getNombre() + "\nNacionalidad: "
                        + autor.getNacionalidad() + "\nAño de naciemiento: " + autor.getAniodenacimiento());
            }
        } else {
            System.out.println("No hay autores guardados.");
        }
    }

    // Método para editar un autor en la lista y en el archivo binario
    public static void editarAutor() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        ArrayList<Autor> listaAutores1 = new ArrayList();
        ArrayList<Autor> listaAutores = leerAutores();
        if (listaAutores.isEmpty() || listaAutores.size() == 0) {
            System.out.println("No hay autores guardados para editar");
        } else {
            System.out.println("Hay autores para editar");
            while (!salir) {
                System.out.println("Inserte id del autor:");
                int id = scanner.nextInt();
                for (Autor autor : listaAutores) {
                    if (id == autor.getId()) {
                        salir = true;
                        System.out.println("Introduce los nuevos datos para el autor con id " + id);
                        System.out.println("Introduzca el nombre del autor:");
                        String nombre = scanner.nextLine();
                        scanner.nextLine();
                        System.out.println("Introduzca la nacionalidad del autor:");
                        String nacionalidad = scanner.nextLine();
                        System.out.println("Introduzca el Año de nacimiento del autor:");
                        int aniodenacimiento = scanner.nextInt();
                        try {
                            for (Autor autor1 : listaAutores) {
                                if (id == autor1.getId()) {
                                    Autor autor2 = new Autor(id, nombre, nacionalidad, aniodenacimiento);
                                    listaAutores1.add(autor2);
                                } else {
                                    listaAutores1.add(autor1);
                                }
                            }
                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Autores.bin", false));
                            oos.writeObject(listaAutores1);
                            autores = listaAutores1;
                            oos.close();
                        } catch (Exception e) {
                            System.out.println("Error al crear nueva lista de autores");
                            System.out.println(e);
                        }
                    }
                }
                if (!salir) {
                    System.out.println("El id no es valido introduzca otro");
                }
            }
            System.out.println("Se han hecho los cambios correctamente");
        }
    }

    // Método para eliminar un autor de la lista y del archivo binario
    public static void eliminarAutor() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        ArrayList<Autor> listaAutores1 = new ArrayList();
        ArrayList<Autor> listaAutores = leerAutores();
        if (listaAutores.isEmpty() || listaAutores.size() == 0) {
            System.out.println("No hay autores guardados para eliminar");
        } else {
            System.out.println("Hay autores para eliminar");
            while (!salir) {
                System.out.println("Inserte id del autor:");
                int id = scanner.nextInt();
                for (Autor autor : listaAutores) {
                    if (id == autor.getId()) {
                        salir = true;
                        try {
                            for (Autor autor1 : listaAutores) {
                                if (id != autor1.getId()) {
                                    listaAutores1.add(autor1);
                                }
                            }
                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Autores.bin", false));
                            oos.writeObject(listaAutores1);
                            autores = listaAutores1;
                            oos.close();
                        } catch (Exception e) {
                            System.out.println("Error al crear nueva lista de autores");
                            System.out.println(e);
                        }
                    }
                }
                if (!salir) {
                    System.out.println("El id no es valido introduzca otro");
                }
            }
            System.out.println("Se ha eliminado correctamente");
        }
    }

    // Método para leer la lista de autores desde el archivo binario
    private static ArrayList<Autor> leerAutores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Autores.bin"))) {
            try {
                // Leer el objeto ArrayList<Autor> desde el archivo
                return (ArrayList<Autor>) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            // El archivo aún no existe o está vacío
        }
        return new ArrayList<>();
    }

    // Método para agregar un préstamo a un archivo de texto
    public static void agregarPrestamo(Prestamo prestamo) {
        File f = new File("Prestamos.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(f, true);
            pw = new PrintWriter(fw);

            pw.append("-----------------------" + "\nID-libro: " + prestamo.getIdlibro() + "\nNombre del usuario: "
                    + prestamo.getNombreusuario() + "\nFecha del prestamo: " + prestamo.getFechaprestamo()
                    + "\nFecha de devolución: " + prestamo.getFechadevolucion() + "\n");
            fw.close();
            System.out.println("Prestamo Creado!");
        } catch (Exception e) {
        } finally {
            try {
                if (null != fw) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    // Método para imprimir los préstamos desde un archivo de texto
    public static void imprimirPrestamos() {
        File f = new File("Prestamos.txt");
        FileReader fr = null;
        BufferedReader br = null;
        if (!f.exists()) {
            System.out.println("No existe Prestamos.txt");
        } else {
            try {
                fr = new FileReader(f);
                br = new BufferedReader(fr);
                String linea;
                int contador = 0;

                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                    contador++;
                }
                if (contador == 0) {
                    System.out.println("No hay nada dentro de Prestamos.txt");
                }
                fr.close();

            } catch (Exception e) {

            } finally {
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    // Método para importar datos desde un archivo XML
    public static void importarXML() {
        ArrayList<Libro> nuevaListaLibros = new ArrayList<>();
        ArrayList<Autor> nuevaListaAutores = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("lista-libros-autores.xml"));

            NodeList nbiblioteca = document.getElementsByTagName("biblioteca");
            NodeList nlibros = ((Element) nbiblioteca.item(0)).getElementsByTagName("libros").item(0)
                    .getChildNodes();
            NodeList nautores = ((Element) nbiblioteca.item(0)).getElementsByTagName("autores").item(0)
                    .getChildNodes();

            for (int i = 0; i < nlibros.getLength(); i++) {
                Node nodoLibro = nlibros.item(i);
                if (nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
                    Element libro = (Element) nodoLibro;
                    String idLibroStr = libro.getAttribute("id");
                    int idLibro = Integer.parseInt(idLibroStr);
                    String tituloLibro = libro.getElementsByTagName("titulo").item(0).getTextContent();
                    String autorLibro = libro.getElementsByTagName("autor").item(0).getTextContent();
                    String anioPubStr = libro.getElementsByTagName("anio_publicacion").item(0).getTextContent();
                    int anioPub = Integer.parseInt(anioPubStr);
                    String generoLibro = libro.getElementsByTagName("genero").item(0).getTextContent();
                    nuevaListaLibros.add(new Libro(idLibro, tituloLibro, autorLibro, anioPub, generoLibro));
                }
            }

            for (int i = 0; i < nautores.getLength(); i++) {
                Node nodoAutor = nautores.item(i);
                if (nodoAutor.getNodeType() == Node.ELEMENT_NODE) {
                    Element autor = (Element) nodoAutor;
                    String idAutorStr = autor.getAttribute("id");
                    int idAutor = Integer.parseInt(idAutorStr);
                    String nombreAutor = autor.getElementsByTagName("nombre").item(0).getTextContent();
                    String nacionalidadAutor = autor.getElementsByTagName("nacionalidad").item(0).getTextContent();
                    String anioNacimientoStr = autor.getElementsByTagName("ano_nacimiento").item(0).getTextContent();
                    int anioNacimiento = Integer.parseInt(anioNacimientoStr);
                    nuevaListaAutores.add(new Autor(idAutor, nombreAutor, nacionalidadAutor, anioNacimiento));
                }
            }
            libros.clear();
            libros.addAll(nuevaListaLibros);
            autores.clear();
            autores.addAll(nuevaListaAutores);

            try {
                ObjectOutputStream escribirLibro = new ObjectOutputStream(new FileOutputStream(new File("Libros.bin")));
                escribirLibro.writeObject(libros);
                escribirLibro.close();

                System.out.println("Importación exitosa. Lista de libros actualizada.");

                ObjectOutputStream escribirAutor = new ObjectOutputStream(new FileOutputStream(new File("Autores.bin")));
                escribirAutor.writeObject(autores);
                escribirAutor.close();

                System.out.println("Importación exitosa. Lista de autores actualizada.");

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Error al escribir en el archivo binario.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error en la importación XML.");
        }
    }

    // Método para exportar datos a un archivo XML
    public static void exportarXML() {
        if (libros.isEmpty() || autores.isEmpty()) {
            System.out.println("Las listas de libros o autores están vacías.");
            return;
        }
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element nbiblioteca = (Element) document.createElement("biblioteca");

            Element nlibros = (Element) document.createElement("libros");

            Element nautores = (Element) document.createElement("autores");

            for (Libro libro : libros) {
                nlibros.appendChild(creaLibro(document, libro.getId() + "", libro.getTitulo(), libro.getAutor(),
                        libro.getAnioPublicacion() + "", libro.getGenero()));
            }

            for (Autor autor : autores) {
                nautores.appendChild(creaAutor(document, autor.getId() + "", autor.getNombre(),
                        autor.getNacionalidad(), autor.getAniodenacimiento() + ""));
            }
            nbiblioteca.appendChild(nlibros);
            nbiblioteca.appendChild(nautores);
			
			document.appendChild(nbiblioteca);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt%7Dindent-amount", "2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("lista-libros-autores.xml"));
            transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Método para crear XML de Libro
	private static Element creaLibro(Document document, String id, String titulo, String autor, String anoPublicacion, String genero) {
		Element nLibro = (Element) document.createElement("libro");
		nLibro.setAttribute("id", id);
		
		Element ntitulo = (Element) document.createElement("titulo");
		ntitulo.appendChild(document.createTextNode(titulo));
		nLibro.appendChild(ntitulo);
		
		Element nautor = (Element) document.createElement("autor");
		nautor.appendChild(document.createTextNode(autor));
		nLibro.appendChild(nautor);
		
		Element nanoPublicacion = (Element) document.createElement("anio_publicacion");
		nanoPublicacion.appendChild(document.createTextNode(anoPublicacion));
		nLibro.appendChild(nanoPublicacion);
		
		Element ngenero = (Element) document.createElement("genero");
		ngenero.appendChild(document.createTextNode(genero));
		nLibro.appendChild(ngenero);
		
		return nLibro;
	}
	//Método para crear XML de Autor
	private static Element creaAutor(Document document, String id, String nombre, String nacionalidad, String aniodenacimiento) {
		Element nAutores = (Element) document.createElement("autor");
		nAutores.setAttribute("id", id);
		
		Element nnombre = (Element) document.createElement("nombre");
		nnombre.appendChild(document.createTextNode(nombre));
		nAutores.appendChild(nnombre);
		
		Element nnacionalidad = (Element) document.createElement("nacionalidad");
		nnacionalidad.appendChild(document.createTextNode(nacionalidad));
		nAutores.appendChild(nnacionalidad);
		
		Element naniodenacimiento = (Element) document.createElement("ano_nacimiento");
		naniodenacimiento.appendChild(document.createTextNode(aniodenacimiento));
		nAutores.appendChild(naniodenacimiento);
		
		return nAutores;
	}
}
