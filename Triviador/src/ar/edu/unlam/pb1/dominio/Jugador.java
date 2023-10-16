package ar.edu.unlam.pb1.dominio;

public class Jugador {
	private String provinciasEnPoder[] = new String[23];
	// private String provinciasLimitrofes[];
	private int puntos; // tropas?

	public Jugador() {
	}

	public boolean sePuedeAgregarProvincias(String provincia) {
		int indice = 0;
		boolean encontrado = false;
		while (indice < provinciasEnPoder.length && !encontrado) {
			if (provinciasEnPoder[indice] == null) {
				provinciasEnPoder[indice] = provincia;
				encontrado = true;
			}
		}
		return encontrado;
	}

	public String[] getProvinciasEnPoder() {
		return provinciasEnPoder;
	}

	public int getPuntos() {
		return puntos;
	}

}
