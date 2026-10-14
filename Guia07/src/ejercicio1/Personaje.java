package ejercicio1;

public class Personaje {
	private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    private int nivel;
    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        setNombre(nombre);
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
        setAlcance(alcance);
        this.nivel = 1;
    }
    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        this(nombre, vida, ataque, defensa, alcance);
        setNivel(nivel);
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }
    public int getVida() { return vida; }
    public void setVida(int vida) {
        if (vida <= 0) {
        	throw new IllegalArgumentException("La vida debe ser mayor que cero.");
        }
        this.vida = vida;
    }
    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) {
        if (ataque <= 0) {
        	throw new IllegalArgumentException("El ataque debe ser mayor que cero.");
        }
        this.ataque = ataque;
    }
    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) {
        if (defensa <= 0) {
        	throw new IllegalArgumentException("La defensa debe ser mayor que cero.");
        }
        this.defensa = defensa;
    }
    public int getAlcance() { return alcance; }
    public void setAlcance(int alcance) {
        if (alcance <= 0) {
        	throw new IllegalArgumentException("El alcance debe ser mayor que cero.");
        }
        this.alcance = alcance;
    }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) {
        if (nivel <= 0) {
        	throw new IllegalArgumentException("El nivel debe ser mayor que cero.");
        }
        this.nivel = nivel;
    }
    public void subirNivel() {
        this.nivel++;
        this.vida += 2;
        this.ataque += 1;
        this.defensa += 1;
        this.alcance += 1;
    }
    @Override
    public String toString() {
        return String.format("Nombre: %-12s | Nivel: %-2d | Vida: %-3d | Ataque: %-3d | Defensa: %-3d | Alcance: %-3d",
                nombre, nivel, vida, ataque, defensa, alcance);
    }
}
