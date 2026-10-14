package ejercicio3;
public class Empleado {
    private int numero;
    private String nombre;
    private double sueldo;
    public Empleado(int numero, String nombre, double sueldo) {
        setNumero(numero);
        setNombre(nombre);
        setSueldo(sueldo);
    }
    public int getNumero() { return numero; }
    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de empleado debe ser un entero positivo.");
        }
        this.numero = numero;
    }
    public String getNombre() { return nombre;  }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }
    public double getSueldo() { 
        return sueldo; 
    }
    public void setSueldo(double sueldo) {
        if (sueldo < 0) {
            throw new IllegalArgumentException("El sueldo no puede ser un valor negativo.");
        }
        this.sueldo = sueldo;
    }
    @Override
    public String toString() {
        return String.format("Número: %-5d | Nombre: %-20s | Sueldo: S/ %.2f", numero, nombre, sueldo);
    }
}