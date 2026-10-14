package actividades;

import java.io.Serializable;

public class Alumno implements Serializable {
 private String nif;
 private String nombre;
 private int edad;
 private Fecha fechaMatricula;

 public Alumno() {
     this.fechaMatricula = new Fecha();
 }

 public Alumno(String nif, String nombre, int edad, Fecha fechaMatricula) {
     this.nif = nif;
     this.nombre = nombre;
     this.edad = edad;
     this.fechaMatricula = new Fecha();
     setFechaMatricula(fechaMatricula);
 }

 public String getNif() { return nif; }
 public String getNombre() { return nombre; }
 public int getEdad() { return edad; }
 public Fecha getFechaMatricula() { return fechaMatricula; }

 public void setFechaMatricula(Fecha fechaMatricula) {
     this.fechaMatricula.setDia(fechaMatricula.getDia());
     this.fechaMatricula.setMes(fechaMatricula.getMes());
     this.fechaMatricula.setAño(fechaMatricula.getAño());
 }
}