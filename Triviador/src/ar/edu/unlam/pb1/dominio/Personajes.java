package ar.edu.unlam.pb1.dominio;

public class Personajes {
	private static final int CANTIDAD_DE_PERSONAJES = 3;
	public static final int ALTURA_PERSONAJE = 3;
	// para mostrar personaje los pj
	private String[] personajes = new String[CANTIDAD_DE_PERSONAJES];

	public Personajes() {
	}

	public void cargarPersonajesJugables() {
		personajes[0] = personajeRombo();
		personajes[1] = personajeEspada();
		personajes[2] = arbolDeNavidad();
	}

	public String personajeEspada() {
		String personajeEspada = "  *  ";
		for (int i = 0; i <= ALTURA_PERSONAJE; i++) {
			if (Personajes.ALTURA_PERSONAJE - i == 1) {
				personajeEspada += "\n" + "*****";
			} else {
				personajeEspada += "\n" + "  *  ";
			}
		}
		return personajeEspada;
	}

	public String arbolDeNavidad() {
		String arbolDeNavidad = "";

		// declarar la altura del árbol
		int altura = ALTURA_PERSONAJE;

		// bucle para recorrer todas las filas (altura)
		for (int fila = 0; fila < altura; fila++) {

			// bucle para los espacios
			for (int espacio = 0; espacio < (altura - fila - 1); espacio++) {
				arbolDeNavidad += " ";
			}
			// bucle para los asteriscos
			for (int asterisco = 0; asterisco < (fila * 2) + 1; asterisco++) {
				arbolDeNavidad += "*";
			}

			arbolDeNavidad += "\n";

		}

		// tronco
		int largoTronco = 2;
		for (int base = 0; base < largoTronco; base++) {

			// espacios en blanco
			for (int espacio = 0; espacio < (altura - 2); espacio++) {
				arbolDeNavidad += " ";
			}

			// barritas tronco
			for (int tronco = 0; tronco < 3; tronco++) {
				arbolDeNavidad += "|";
			}

			arbolDeNavidad += "\n";

		}

		return arbolDeNavidad;
	}

	public String personajeRombo() {
		String personajeRombo = "";
		int altura = ALTURA_PERSONAJE;
		if (altura % 2 == 0) {
			// si es numero impar aumentar num
			altura++;
		}

		// para un triangulo
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < altura - i - 1; j++) {
				personajeRombo += " ";
			}
			for (int j = 0; j < 2 * i + 1; j++) {
				personajeRombo += "*";
			}
			personajeRombo += "\n";
		}

		// esta es la parte inferior del rombo
		for (int i = altura - 2; i >= 0; i--) {
			for (int j = 0; j < altura - i - 1; j++) {
				personajeRombo += " ";
			}
			for (int j = 0; j < 2 * i + 1; j++) {
				personajeRombo += "*";
			}
			personajeRombo += "\n";
		}
		return personajeRombo;
	}

	public String[] getPersonajes() {
		return personajes;
	}

}
