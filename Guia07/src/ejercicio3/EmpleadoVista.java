package ejercicio3;

import java.util.List;
import java.util.Scanner;

public class EmpleadoVista {
    private Scanner sc;

    public EmpleadoVista() {
        this.sc = new Scanner(System.in);
    }

    public void mostrarEncabezado() {
    	System.out.println();
    	System.out.println("  SISTEMA DE GESTIÓN DE EMPLEADOS");
    }

    public void mostrarMenu() {
        System.out.println("\n--- MENÚ DE OPCIONES ---");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar un nuevo empleado");
        System.out.println("3. Buscar un empleado por su número");
        System.out.println("4. Eliminar un empleado por su número");
        System.out.println("5. Salir del programa");
    }

    public void mostrarListaEmpleados(List<Empleado> lista) {
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay empleados registrados en el archivo.");
        } else {
            for (Empleado e : lista) {
                System.out.println(e);
            }
        }
    }

    public void mostrarEmpleado(Empleado e) {
        if (e != null) {
            System.out.println("\nEmpleado encontrado:");
            System.out.println(e);
        } else {
            System.out.println("\nINFORMACIÓN: No se encontró ningún empleado con ese número.");
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }

    public int pedirEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(sc.nextLine());
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public double pedirDouble(String mensaje) {
        System.out.print(mensaje);
        return Double.parseDouble(sc.nextLine());
    }
}