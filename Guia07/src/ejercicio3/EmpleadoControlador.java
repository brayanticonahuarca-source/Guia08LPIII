package ejercicio3;
import java.io.IOException;
import java.util.List;

public class EmpleadoControlador {
    private EmpleadoModelo modelo;
    private EmpleadoVista vista;

    public EmpleadoControlador(EmpleadoModelo modelo, EmpleadoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarEncabezado();
        
        listarEmpleados();

        int opcion = 0;
        do {
            vista.mostrarMenu();
            try {
                opcion = vista.pedirEntero("Seleccione una opción: ");
                switch (opcion) {
                    case 1:
                        listarEmpleados();
                        break;
                    case 2:
                        agregarEmpleado();
                        break;
                    case 3:
                        buscarEmpleado();
                        break;
                    case 4:
                        eliminarEmpleado();
                        break;
                    case 5:
                        vista.mostrarMensaje("¡Programa finalizado correctamente!");
                        break;
                    default:
                        vista.mostrarMensaje("Error: Debe seleccionar una opción del 1 al 5.");
                }
            } catch (NumberFormatException e) {
                vista.mostrarMensaje("Error de entrada: Debe ingresar un valor numérico válido.");
            }
        } while (opcion != 5);
    }

    private void listarEmpleados() {
        try {
            List<Empleado> lista = modelo.leerEmpleados();
            vista.mostrarListaEmpleados(lista);
        } catch (IOException e) {
            vista.mostrarMensaje("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void agregarEmpleado() {
        try {
            int num = vista.pedirEntero("Ingrese el número del empleado (entero > 0): ");
            String nom = vista.pedirTexto("Ingrese el nombre del empleado: ");
            double sueldo = vista.pedirDouble("Ingrese el sueldo del empleado (ej. 1500.50): ");

            Empleado nuevo = new Empleado(num, nom, sueldo);
            if (modelo.agregarEmpleado(nuevo)) {
                vista.mostrarMensaje("¡ÉXITO: Empleado agregado y guardado en el archivo!");
            } else {
                vista.mostrarMensaje("ERROR: Ya existe un empleado registrado con el número " + num);
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("ERROR DE ENTRADA: El número debe ser un entero y el sueldo un decimal.");
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("ERROR DE VALIDACIÓN: " + e.getMessage());
        } catch (IOException e) {
            vista.mostrarMensaje("ERROR DE ARCHIVO: " + e.getMessage());
        }
    }

    private void buscarEmpleado() {
        try {
            int num = vista.pedirEntero("Ingrese el número del empleado a buscar: ");
            Empleado e = modelo.buscarEmpleado(num);
            vista.mostrarEmpleado(e);
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("ERROR DE ENTRADA: Debe ingresar un valor entero válido.");
        }
    }

    private void eliminarEmpleado() {
        try {
            int num = vista.pedirEntero("Ingrese el número del empleado a eliminar: ");
            if (modelo.eliminarEmpleado(num)) {
                vista.mostrarMensaje("¡ÉXITO: Empleado eliminado y archivo actualizado!");
            } else {
                vista.mostrarMensaje("ERROR: No se encontró ningún empleado con el número " + num);
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("ERROR DE ENTRADA: Debe ingresar un valor entero válido.");
        } catch (IOException e) {
            vista.mostrarMensaje("ERROR DE ARCHIVO: " + e.getMessage());
        }
    }
}