package ejercicio3;

public class Main {
    public static void main(String[] args) {
        EmpleadoModelo modelo = new EmpleadoModelo("src/empleados.txt");
        EmpleadoVista vista = new EmpleadoVista();
        EmpleadoControlador controlador = new EmpleadoControlador(modelo, vista);
        controlador.iniciar();
    }
}