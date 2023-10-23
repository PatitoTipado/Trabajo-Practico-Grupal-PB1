package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.auxiliar.Jugador;
import ar.edu.unlam.pb1.auxiliar.Pregunta;

public class Triviador {
	private String[] provincias = { "Buenos Aires", "Catamarca", "Chaco", "Chubut", "Córdoba", "Corrientes",
			"Entre Ríos", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén", "Río Negro",
			"Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero", "Tierra del Fuego",
			"Tucumán" };
	private Jugador[] jugadores = new Jugador[2];
	private Integer[] turnosJugadores;

	public Triviador() {
		this.turnosJugadores = new Integer[jugadores.length];
	}

	public String[] preguntasProximidad() {
		return Pregunta.getPreguntaProximidad();
	}

	public int[] respuestaProximidad() {
		return Pregunta.getRespuestaCorrectaProximidad();
	}

	public boolean agregarJugador(Jugador jugador) {
		int indice = 0;
		boolean encontrado = false;
		while (indice < jugadores.length && !encontrado) {
			if (jugadores[indice] == null) {
				jugadores[indice] = jugador;
				encontrado = true;
			}
			indice++;
		}
		return encontrado;
	}

	// llega el numero
	public boolean asignarProvincias(int indice, int jugador) {
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
		// buscamos en el array de jugadores dara 2 vueltas
		for (int i = 0; i < jugadores.length; i++) {
			// buscamos en el array de provincia
			for (int j = 0; j < provincias.length; j++) {
				// si encuentra la provincia en poder de alguien, no esta disponible
				if (jugadores[i].getProvinciasEnPoder()[j] != null
						&& jugadores[i].getProvinciasEnPoder()[j].equals(provincia)) {
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

	private void vaciarElArray(Integer[] vaciarTurnos) {
		for (int i = vaciarTurnos.length; i > 0; i--) {
			vaciarTurnos[i] = null;
		}
	}

	private void invadirTerritorioEnemigo() {

	}

	public int leTocaAlOtroJugador() {
		int jugadorAlQueLetoca = 0;
		// lo primero que tuviera que validar, seria que se tenga que vaciar el array
		if (turnosJugadores[turnosJugadores.length] != null) {
			vaciarElArray(turnosJugadores);
		}
		// 1 == null + idDelJugador (2)
		// 2 == null id del jugador (1)
		// 3 == null + 3
		// 3!= null (3)
		// array aux
		// aux 2 // null -- 1 o 2
		// ramdon con el valor se fija si es el mas alto y si no lo es se incrementa
		// osino se decrementa
		// se repite con
		return jugadorAlQueLetoca;
	}

}
