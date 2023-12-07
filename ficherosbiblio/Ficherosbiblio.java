package ficherosbiblio;

import java.util.Scanner;
// Importar otras clases necesarias

// Clase principal para ejecutar el código
public class Ficherosbiblio {
    // Scanner
	private static Scanner scanner = new Scanner(System.in);


    // Método Main
	public static void main(String[] args) {

		boolean salir = false;

        // Bucle principal que muestra el menú hasta que el usuario sale
		while (!salir) {

            // Mostrar el menú principal
			mostrarMenu();

            // Escaneamos la opción intrtoducida por el usuario
			int opcion = scanner.nextInt();
			scanner.nextLine();
			switch (opcion) {
			case 1:// Gestionar libros
				boolean salirlibros = true;
				do {
					gestionarLibros();
					opcion = scanner.nextInt();
					switch (opcion) {
					case 1:
                                // Lógica para agregar un nuevo libro
						System.out.println("Introduzca el ID del libro:");
						int id = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Introduzca el Título del libro:");
						String titulo = scanner.nextLine();
						System.out.println("Introduzca el Autor del libro:");
						String autor = scanner.nextLine();
						System.out.println("Introduzca el Año de publicación del libro:");
						int anioPublicacion = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Introduzca el Género del libro:");
						String genero = scanner.nextLine();
						GestorFicheros.agregarLibro(new Libro(id, titulo, autor, anioPublicacion, genero));
						break;
					case 2:
                                // Lógica para imprimir la lista de libros
						GestorFicheros.imprimirLibros();
						break;
					case 3:
                                // Lógica para editar un libro existente
						GestorFicheros.editarLibro();
						break;
					case 4:
                                // Lógica para eliminar un libro
						GestorFicheros.eliminarLibro();
						break;
					case 5:
						salirlibros = false;
						break;
					default:
						System.out.println("Opción no valida.\n");
					}

				} while (salirlibros);
				break;
			case 2:// Gestionar autores
				boolean salirautores = true;
				do {
					gestionarAutores();
					opcion = scanner.nextInt();
					switch (opcion) {
					case 1:
						System.out.println("Introduzca el ID del autor:");
						int id = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Introduzca el nombre del autor:");
						String nombre = scanner.nextLine();
						System.out.println("Introduzca la nacionalidad del autor:");
						String nacionalidad = scanner.nextLine();
						System.out.println("Introduzca el Año de nacimiento del autor:");
						int aniodenacimiento = scanner.nextInt();
                        GestorFicheros.agregarAutor(new Autor(id, nombre, nacionalidad, aniodenacimiento));
						break;
					case 2:
                        GestorFicheros.imprimirAutores();
						break;
					case 3:
                        GestorFicheros.editarAutor();
						break;
					case 4:
                        GestorFicheros.eliminarAutor();
						break;
					case 5:
						salirautores = false;
						break;
					default:
						System.out.println("Opción no valida.\n");
					}
				} while (salirautores);
				break;
			case 3:// Gestionar préstamos
				boolean salirprestamos = true;
				do {
					gestionarPrestamos();
					opcion = scanner.nextInt();
					switch (opcion) {
					case 1:
						System.out.println("Introduzca el ID del libro a prestar:");
						int id = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Introduzca el nombre de quien recibe el prestamo:");
						String nombreusuario = scanner.nextLine();
						System.out.println("Introduzca la fecha del prestamo:");
						String fechaprestamo = scanner.nextLine();
						System.out.println("Introduzca la fecha para la devolucion:");
						String fechadevolucion = scanner.nextLine();
                        GestorFicheros.agregarPrestamo(new Prestamo(id,nombreusuario,fechaprestamo,fechadevolucion));
						break;
					case 2:
                        GestorFicheros.imprimirPrestamos();
						break;
					case 3:
						salirprestamos = false;
						break;
					default:
						System.out.println("Opción no valida.\n");
					}
				} while (salirprestamos);
				break;
			case 4:// Exportar/Importar datos con XML
				boolean salirxml = true;
				do {
					gestionarExportImportXML();
					opcion = scanner.nextInt();
					switch (opcion) {
					case 1:
						GestorFicheros.importarXML();
						break;
					case 2:
						GestorFicheros.exportarXML();
						break;
					case 3:
						salirxml = false;
						break;
					default:
						System.out.println("Opción no valida.\n");
					}
				} while (salirxml);
				break;
			case 5:
				salir = true;
				break;
			default:
				System.out.println("Opción no válida. Por favor, intente de nuevo.");
			}
		}
	}

//	Metodo para mostrar el Menú al que se llamará
	private static void mostrarMenu() {
		System.out.println("Bienvenido al Sistema de Gestión de Biblioteca");
		System.out.println("1. Gestionar Libros");
		System.out.println("2. Gestionar Autores");
		System.out.println("3. Gestionar Préstamos");
		System.out.println("4. Exportar/Importar Datos (XML)");
		System.out.println("5. Salir");
		System.out.println("Seleccione una opción: ");
	}

	private static void gestionarLibros() {
// Implementar lógica para gestionar libros
		System.out.println("-----Gestion de libros-----\n" 
				+ "1. Crear libro.\n" 
				+ "2. Mostrar libros.\n"
				+ "3. Actualizar libro.\n" 
				+ "4. Eliminar libro.\n" 
				+ "5. Volver al menu principal.\n"
				+ "Seleccione una opción: ");
	}

	private static void gestionarAutores() {
// Implementar lógica para gestionar autores
		System.out.println("-----Gestion de autores-----\n" 
				+ "1. Crear autor.\n" 
				+ "2. Mostrar autores.\n"
				+ "3. Actualizar autor.\n" 
				+ "4. Eliminar autor.\n" 
				+ "5. Volver al menu principal.\n"
				+ "Seleccione una opción: ");
	}
	
	private static void gestionarPrestamos() {
		// Implementar lógica para gestionar libros
		System.out.println("-----Gestion de prestamos-----\n" 
						+ "1. Crear prestamo.\n" 
						+ "2. Mostrar prestamos.\n"
						+ "3. Volver al menu principal.\n"
						+ "Seleccione una opción: ");
	}

	private static void gestionarExportImportXML() {
// Implementar lógica para exportar/importar datos con XML
		System.out.println("-----Gestion de XML-----\n" 
				+ "1. Importar XML.\n" 
				+ "2. Exportar XML.\n"
				+ "3. Volver al menu principal.\n"
				+ "Seleccione una opción: ");
	}
// Otros métodos según sea necesario
}
