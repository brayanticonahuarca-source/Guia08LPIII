package ejercicio1;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.io.*;

public class GestorPersonaje {
    private List<Personaje> personajes;
    private final String ruteArchivo;
    
    public GestorPersonaje(String nombreArchivo) {
        this.ruteArchivo = nombreArchivo;
        this.personajes = new ArrayList<>();
        cargarArchivo();
    }
    
    public void cargarArchivo() {
        File archivo = new File(ruteArchivo);
        if (!archivo.exists()) {
            return;
        }
        personajes.clear();
        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }
                String[] datos = linea.split("\t");
                if (datos.length >= 5) {
                    String nombre = datos[0];
                    int vida = Integer.parseInt(datos[1]);
                    int ataque = Integer.parseInt(datos[2]);
                    int defensa = Integer.parseInt(datos[3]);
                    int alcance = Integer.parseInt(datos[4]);
                    int nivel = (datos.length >= 6) ? Integer.parseInt(datos[5]) : 1;
                    personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance, nivel));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    public Personaje buscarPersonaje(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void guardarArchivo() {
        try (FileWriter fw = new FileWriter(ruteArchivo);
             PrintWriter pw = new PrintWriter(fw)) {
            
            for (Personaje p : personajes) {
                pw.println(p.getNombre() + "\t" + p.getVida() + "\t" + 
                           p.getAtaque() + "\t" + p.getDefensa() + "\t" + 
                           p.getAlcance() + "\t" + p.getNivel());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public boolean agregarPersonaje(Personaje nuevo) {
        if (buscarPersonaje(nuevo.getNombre()) != null) {
            return false;
        }
        personajes.add(nuevo);
        guardarArchivo();
        return true;
    }

    public boolean modificarPersonaje(String nombre, int nuevaVida, int nuevoAtaque, int nuevaDefensa, int nuevoAlcance) {
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            p.setVida(nuevaVida);
            p.setAtaque(nuevoAtaque);
            p.setDefensa(nuevaDefensa);
            p.setAlcance(nuevoAlcance);
            guardarArchivo();
            return true;
        }
        return false;
    }

    public boolean borrarPersonaje(String nombre) {
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            personajes.remove(p);
            guardarArchivo();
            return true;
        }
        return false;
    }

    public boolean actualizarAtributoIndividual(String nombre, int opcionAtributo, int nuevoValor) {
        Personaje p = buscarPersonaje(nombre);
        if (p == null) return false;

        switch (opcionAtributo) {
            case 1: p.setVida(nuevoValor); break;
            case 2: p.setAtaque(nuevoValor); break;
            case 3: p.setDefensa(nuevoValor); break;
            case 4: p.setAlcance(nuevoValor); break;
            default: return false;
        }
        guardarArchivo();
        return true;
    }
    
    public void filtrarPorAtributo(int opcionAtributo) {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para ordenar.");
            return;
        }
        List<Personaje> lista = new ArrayList<>(personajes);
        String nombreAtributo = "";
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - 1 - i; j++) {
                Personaje p1 = lista.get(j);
                Personaje p2 = lista.get(j + 1);
                boolean intercambiar = false;
                switch (opcionAtributo) {
                    case 1:
                        nombreAtributo = "VIDA";
                        if (p1.getVida() < p2.getVida()) intercambiar = true;
                        break;
                    case 2:
                        nombreAtributo = "ATAQUE";
                        if (p1.getAtaque() < p2.getAtaque()) intercambiar = true;
                        break;
                    case 3:
                        nombreAtributo = "DEFENSA";
                        if (p1.getDefensa() < p2.getDefensa()) intercambiar = true;
                        break;
                    case 4:
                        nombreAtributo = "ALCANCE";
                        if (p1.getAlcance() < p2.getAlcance()) intercambiar = true;
                        break;
                    case 5:
                        nombreAtributo = "NIVEL";
                        if (p1.getNivel() < p2.getNivel()) intercambiar = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        return;
                }
                if (intercambiar) {
                    lista.set(j, p2);
                    lista.set(j + 1, p1);
                }
            }
        }
        System.out.println("\n--- PERSONAJES ORDENADOS POR " + nombreAtributo + " (MAYOR A MENOR) ---");
        for (Personaje p : lista) {
            System.out.println(p);
        }
    }
    
    public void cargarPersonajesAleatorios(int cantidad) {
        Random rand = new Random();
        String[] clases = {"Guerrero", "Mago", "Arquero", "Caballero", "Asesino"};
        int agregados = 0;

        for (int i = 0; i < cantidad; i++) {
            String nombre = clases[rand.nextInt(clases.length)] + "_" + (rand.nextInt(900) + 100);
            if (buscarPersonaje(nombre) == null) {
                int vida = rand.nextInt(5) + 1;
                int ataque = rand.nextInt(5) + 1;
                int defensa = rand.nextInt(5) + 1;
                int alcance = rand.nextInt(5) + 1;
                personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance));
                agregados++;
            }
        }
        guardarArchivo();
        System.out.println("Se generaron y guardaron " + agregados + " personajes aleatorios.");
    }

    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para calcular estadísticas.");
            return;
        }

        int total = personajes.size();
        double sumVida = 0, sumAtaque = 0, sumDefensa = 0, sumAlcance = 0, sumNivel = 0;

        for (Personaje p : personajes) {
            sumVida += p.getVida();
            sumAtaque += p.getAtaque();
            sumDefensa += p.getDefensa();
            sumAlcance += p.getAlcance();
            sumNivel += p.getNivel();
        }

        System.out.println("\n=== ESTADÍSTICAS GENERALES ===");
        System.out.println("Total de personajes: " + total);
        System.out.printf("Vida promedio:    %.2f%n", sumVida / total);
        System.out.printf("Ataque promedio:  %.2f%n", sumAtaque / total);
        System.out.printf("Defensa promedio: %.2f%n", sumDefensa / total);
        System.out.printf("Alcance promedio: %.2f%n", sumAlcance / total);
        System.out.printf("Nivel promedio:   %.2f%n", sumNivel / total);
    }
    
    public void importarDesdeArchivo(String rutaExterna) {
        File archivoExt = new File(rutaExterna);
        if (!archivoExt.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        int agregados = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoExt))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                String[] datos = linea.split("\t");
                if (datos.length >= 5) {
                    String nom = datos[0];
                    int v = Integer.parseInt(datos[1]);
                    int a = Integer.parseInt(datos[2]);
                    int d = Integer.parseInt(datos[3]);
                    int alc = Integer.parseInt(datos[4]);
                    int niv = (datos.length >= 6) ? Integer.parseInt(datos[5]) : 1;

                    if (agregarPersonaje(new Personaje(nom, v, a, d, alc, niv))) {
                        agregados++;
                    }
                }
            }
            System.out.println("Se importaron " + agregados + " personajes correctamente.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
    }

    public boolean subirNivelPersonaje(String nombre) {
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            p.subirNivel();
            guardarArchivo();
            System.out.println("¡" + p.getNombre() + " ha subido al Nivel " + p.getNivel() + "!");
            return true;
        }
        return false;
    }
    
    public List<Personaje> mostrarPersonajes() {
        return personajes;
    }
}