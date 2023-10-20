package ar.edu.unlam.pb1.auxiliar;

public class Jugador {
	private String provinciasEnPoder[] = new String[23];
	// private String provinciasLimitrofes[];
	private int puntos; // tropas?
	private String nombre;
	private int id;

	public Jugador(String nombre) {
		this.nombre = nombre;
		this.puntos = 1000;
		this.id++;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean sePuedeAgregarProvincias(String provincia) {
		int indice = 0;
		boolean encontrado = false;
		while (indice < provinciasEnPoder.length && !encontrado) {
			if (provinciasEnPoder[indice] == null) {
				provinciasEnPoder[indice] = provincia;
				encontrado = true;
			}
			indice++;
		}
		return encontrado;
	}

	public String[] getProvinciasEnPoder() {
		return provinciasEnPoder;
	}

	public int getPuntos() {
		return puntos;
	}

	public void ganaPuntos(int cuantoPuntos) {
		this.puntos += cuantoPuntos;
	}

	public void pierdePuntos(int cuantoPuntos) {
		this.puntos -= cuantoPuntos;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Jugador [ puntos=" + puntos + ", nombre=" + nombre + "]";
	}
}
