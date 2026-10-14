package ejercicio3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoModelo {
    private final String rutaArchivo;
    private List<Empleado> listaEmpleados;

    public EmpleadoModelo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.listaEmpleados = new ArrayList<>();
    }

    public List<Empleado> leerEmpleados() throws IOException {
        File archivo = new File(rutaArchivo);
        listaEmpleados.clear();

        if (!archivo.exists()) {
            return listaEmpleados;
        }

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] datos = linea.split("\t");
                if (datos.length >= 3) {
                    int num = Integer.parseInt(datos[0]);
                    String nom = datos[1];
                    double sueldo = Double.parseDouble(datos[2]);
                    listaEmpleados.add(new Empleado(num, nom, sueldo));
                }
            }
        }
        return listaEmpleados;
    }

    private void guardarArchivo() throws IOException {
        try (FileWriter fw = new FileWriter(rutaArchivo);
             PrintWriter pw = new PrintWriter(fw)) {
            for (Empleado e : listaEmpleados) {
                pw.println(e.getNumero() + "\t" + e.getNombre() + "\t" + e.getSueldo());
            }
        }
    }

    public boolean agregarEmpleado(Empleado nuevo) throws IOException {
        if (buscarEmpleado(nuevo.getNumero()) != null) {
            return false;
        }
        listaEmpleados.add(nuevo);
        guardarArchivo();
        return true;
    }

    public Empleado buscarEmpleado(int numero) {
        for (Empleado e : listaEmpleados) {
            if (e.getNumero() == numero) {
                return e;
            }
        }
        return null;
    }

    public boolean eliminarEmpleado(int numero) throws IOException {
        Empleado e = buscarEmpleado(numero);
        if (e != null) {
            listaEmpleados.remove(e);
            guardarArchivo();
            return true;
        }
        return false;
    }
}