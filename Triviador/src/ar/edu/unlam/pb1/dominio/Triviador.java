package ar.edu.unlam.pb1.dominio;

public class Triviador {
	private String[] provincias = { "Buenos Aires", "Catamarca", "Chaco", "Chubut", "Córdoba", "Corrientes",
			"Entre Ríos", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén", "Río Negro",
			"Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero", "Tierra del Fuego",
			"Tucumán" };
	private Jugador[] jugadores = new Jugador[2];

	public Triviador() {

	}

	public boolean agregarJugador(Jugador jugador) {
		int indice = 0;
		boolean encontrado = false;
		while (indice < jugadores.length && !encontrado) {
			if (jugadores[indice] == null) {
				jugadores[indice] = jugador;
				encontrado = true;
			}
		}
		return encontrado;
	}

	// llega el numero
	public boolean asignarProvincia(int indice, int jugador) {
		boolean sePudoAsignarProvincia = false;
		// esta habitada la provincia
		if (estaDisponibleLaProvincia(provincias[indice])) {
			// y llama al metodo y asignarle al jugador y darsela o no, si no esta habitida
			jugadores[jugador].sePuedeAgregarProvincias(provincias[indice]);
			sePudoAsignarProvincia = true;
		}
		// devolver true si se puede
		return sePudoAsignarProvincia;
	}

	private boolean estaDisponibleLaProvincia(String provincia) {
		boolean estaDisponible = true;
		for (int i = 0; i < jugadores.length; i++) {
			for (int j = 0; j < provincias.length; j++) {
				if (jugadores[i] != null && jugadores[i].getProvinciasEnPoder()[j].equals(provincia)) {
					estaDisponible = false;
				}
			}
		}
		return estaDisponible;
	}

	public String[] getProvincias() {
		return provincias;
	}

	public Jugador[] getJugadores() {
		return jugadores;
	}

}
