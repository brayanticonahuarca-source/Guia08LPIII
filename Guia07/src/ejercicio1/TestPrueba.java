package ejercicio1;

import java.util.List;
import java.util.Scanner;

public class TestPrueba {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorPersonaje gestor = new GestorPersonaje("src/ejercicio1/personajes.txt");

        int opcion = 0;
        do {
            System.out.println(" GESTOR DE PERSONAJES DE VIDEOJUEGO ");
            System.out.println("1. Mostrar todos los personajes");
            System.out.println("2. Agregar personaje");
            System.out.println("3. Modificar personaje ");
            System.out.println("4. Actualizar un solo atributo");
            System.out.println("5. Subir de nivel a un personaje");
            System.out.println("6. Borrar personaje");
            System.out.println("7. Filtrar / Ordenar personajes por atributo");
            System.out.println("8. Cargar personajes aleatorios");
            System.out.println("9. Mostrar estadísticas generales");
            System.out.println("10. Importar personajes desde archivo externo");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        List<Personaje> lista = gestor.mostrarPersonajes();
                        if (lista.isEmpty()) {
                            System.out.println("No hay personajes registrados en el archivo.");
                        } else {
                            for (Personaje p : lista) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 2:
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Vida (>0): ");
                        int vida = Integer.parseInt(sc.nextLine());
                        System.out.print("Ataque (>0): ");
                        int ataque = Integer.parseInt(sc.nextLine());
                        System.out.print("Defensa (>0): ");
                        int defensa = Integer.parseInt(sc.nextLine());
                        System.out.print("Alcance (>0): ");
                        int alcance = Integer.parseInt(sc.nextLine());

                        Personaje nuevo = new Personaje(nombre, vida, ataque, defensa, alcance);
                        if (gestor.agregarPersonaje(nuevo)) {
                            System.out.println("¡Personaje agregado y guardado con éxito!");
                        } else {
                            System.out.println("Error: El personaje ya existe.");
                        }
                        break;

                    case 3:
                        System.out.print("Nombre del personaje a modificar: ");
                        String nomMod = sc.nextLine();
                        System.out.print("Nueva Vida (>0): ");
                        int nVida = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo Ataque (>0): ");
                        int nAtaque = Integer.parseInt(sc.nextLine());
                        System.out.print("Nueva Defensa (>0): ");
                        int nDefensa = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo Alcance (>0): ");
                        int nAlcance = Integer.parseInt(sc.nextLine());

                        if (gestor.modificarPersonaje(nomMod, nVida, nAtaque, nDefensa, nAlcance)) {
                            System.out.println("¡Personaje modificado en el archivo!");
                        } else {
                            System.out.println("No se encontró ningún personaje con ese nombre.");
                        }
                        break;

                    case 4:
                        System.out.print("Nombre del personaje: ");
                        String nomIndiv = sc.nextLine();
                        System.out.println("Seleccione el atributo a modificar:");
                        System.out.println("1. Vida");
                        System.out.println("2. Ataque");
                        System.out.println("3. Defensa");
                        System.out.println("4. Alcance");
                        System.out.print("Opción (1-4): ");
                        int opcAtributo = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo valor (>0): ");
                        int nValor = Integer.parseInt(sc.nextLine());

                        if (gestor.actualizarAtributoIndividual(nomIndiv, opcAtributo, nValor)) {
                            System.out.println("¡Atributo actualizado con éxito!");
                        } else {
                            System.out.println("No se pudo actualizar el atributo o el personaje no existe.");
                        }
                        break;

                    case 5:
                        System.out.print("Nombre del personaje que subirá de nivel: ");
                        String nomLvl = sc.nextLine();
                        if (gestor.subirNivelPersonaje(nomLvl)) {
                            System.out.println("¡Nivel incrementado y archivo actualizado!");
                        } else {
                            System.out.println("No se encontró ningún personaje con ese nombre.");
                        }
                        break;

                    case 6:
                        System.out.print("Nombre del personaje a borrar: ");
                        String nomBorrar = sc.nextLine();
                        if (gestor.borrarPersonaje(nomBorrar)) {
                            System.out.println("¡Personaje borrado del archivo!");
                        } else {
                            System.out.println("No se encontró el personaje.");
                        }
                        break;

                    case 7:
                        System.out.println("Seleccione el atributo por el cual desea ordenar:");
                        System.out.println("1. Vida");
                        System.out.println("2. Ataque");
                        System.out.println("3. Defensa");
                        System.out.println("4. Alcance");
                        System.out.println("5. Nivel");
                        System.out.print("Opción (1-5): ");
                        int opcFiltro = Integer.parseInt(sc.nextLine());
                        gestor.filtrarPorAtributo(opcFiltro);
                        break;

                    case 8:
                        System.out.print("Ingrese la cantidad de personajes aleatorios a generar: ");
                        int cant = Integer.parseInt(sc.nextLine());
                        gestor.cargarPersonajesAleatorios(cant);
                        break;

                    case 9:
                        gestor.mostrarEstadisticas();
                        break;

                    case 10:
                        System.out.print("Ingrese la ruta del archivo externo (ej. src/ejercicio1/externo.txt): ");
                        String rutaExt = sc.nextLine();
                        gestor.importarDesdeArchivo(rutaExt);
                        break;

                    case 11:
                        System.out.println("¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error de validación: " + e.getMessage());
            }
        } while (opcion != 11);

        sc.close();
    }
}
