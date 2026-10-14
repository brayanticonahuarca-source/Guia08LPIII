package ejercicio4;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ContadorPalabras {

    public static void main(String[] args) {
        File archivo = seleccionarArchivoValido();

        if (archivo == null) {
            System.out.println("Operación cancelada por el usuario.");
            return;
        }

        procesarYReportarArchivo(archivo);
    }

    private static File seleccionarArchivoValido() {
        File archivoSeleccionado = null;

        while (archivoSeleccionado == null) {
            JFileChooser selector = new JFileChooser();
            selector.setDialogTitle("Seleccione el archivo de texto a analizar (ej. lear.txt)");
            int resultado = selector.showOpenDialog(null);

            if (resultado != JFileChooser.APPROVE_OPTION) {
                return null;
            }

            File archivoTemp = selector.getSelectedFile();

            if (archivoTemp != null && archivoTemp.exists() && archivoTemp.isFile()) {
                archivoSeleccionado = archivoTemp;
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "El archivo seleccionado no existe o no es válido. Por favor, seleccione otro.",
                    "Archivo Inválido",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
        return archivoSeleccionado;
    }

    private static void procesarYReportarArchivo(File archivo) {
        int totalLineas = 0;
        int totalPalabras = 0;
        long totalCaracteres = 0;
        Map<String, Integer> frecuenciaPalabras = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();
                StringBuilder palabraActual = new StringBuilder();
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);

                    if (Character.isLetterOrDigit(c)) {
                        palabraActual.append(c);
                    } else {
                        if (palabraActual.length() > 0) {
                            String palabra = palabraActual.toString().toLowerCase();
                            frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                            totalPalabras++;
                            palabraActual.setLength(0);
                        }
                    }
                }
                if (palabraActual.length() > 0) {
                    String palabra = palabraActual.toString().toLowerCase();
                    frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                    totalPalabras++;
                }
            }

            mostrarReporteConsola(archivo.getName(), totalLineas, totalPalabras, totalCaracteres, frecuenciaPalabras);

        } catch (IOException e) {
            System.out.println("Error de entrada/salida al leer el archivo: " + e.getMessage());
        }
    }

    private static void mostrarReporteConsola(String nombreArchivo, int lineas, 
    		int palabras, long caracteres, Map<String, Integer> frecuencias) {
        double promedioPalabrasPorLinea = (lineas > 0) ? (double) palabras / lineas : 0.0;
        System.out.println("   REPORTE DE ANÁLISIS: " + nombreArchivo);
        System.out.println();
        System.out.println("1. Total de líneas: " + lineas);
        System.out.println("2. Total de palabras: " + palabras);
        System.out.println("3. Total de caracteres (sin contar saltos de línea): " + caracteres);
        System.out.printf("4. Promedio de palabras por línea: %.2f%n", promedioPalabrasPorLinea);
        System.out.println("5. Palabras más frecuentes:");

        int maxFrecuencia = 0;
        for (int conteo : frecuencias.values()) {
            if (conteo > maxFrecuencia) {
                maxFrecuencia = conteo;
            }
        }

        if (maxFrecuencia > 0) {
            for (Map.Entry<String, Integer> entry : frecuencias.entrySet()) {
                if (entry.getValue() == maxFrecuencia) {
                    System.out.println("   - \"" + entry.getKey() + "\" (Aparece " + entry.getValue() + " veces)");
                }
            }
        } else {
            System.out.println("   - No se encontraron palabras en el archivo.");
        }
    }
}