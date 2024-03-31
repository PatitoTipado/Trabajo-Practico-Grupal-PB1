package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.enums.ColoresDeInterfaz;

public class Jugador {
	private String provinciasEnPoder[] = new String[23];
	private int puntos;
	private String nombre;
	private static int id = 1;
	private boolean soyGanador;
	private static String[] coloresDisponibles = new String[10];
	private ColoresDeInterfaz colorSeleccionado;
	private Double[] tiempoDeRespuesta;
	private String[] respuestas;

	public Jugador(String nombre, ColoresDeInterfaz color) {
		this.nombre = nombre;
		this.puntos = 1000;
		id++;
		this.soyGanador = false;
		cargarColores();
		this.colorSeleccionado = color;
		this.tiempoDeRespuesta = new Double[100000];
		this.respuestas = new String[10000];
	}

	private void cargarColores() {

		// anda bien los colores escapeados, mientras que los maneje
		coloresDisponibles[0] = "\"\u001B[31m\"";// rojo
		coloresDisponibles[1] = "\"\u001B[34m\""; // azul
		coloresDisponibles[2] = "\"\u001B[33m\""; // amarrillo
		coloresDisponibles[3] = "\"\u001B[32m\""; // verde
		coloresDisponibles[4] = "\"\u001B[36m\""; // cyan
		coloresDisponibles[5] = "\"\u001B[35m\""; // purpula
		coloresDisponibles[6] = "\"\u001B[0m\""; // blanco
		coloresDisponibles[7] = "\"\u001B[30m\""; // negro

	}

	public void agregarTiempoDeRespuesta(double tiempoDelJugador) {
		int indice = 0;
		boolean encontrado = false;
		while (indice < tiempoDeRespuesta.length && !encontrado) {
			if (tiempoDeRespuesta[indice] == null) {
				tiempoDeRespuesta[indice] = tiempoDelJugador;
				encontrado = true;
			}
			indice++;
		}

	}

	private int contadorDeRespuestasCorrectas() {
		int contador = 0;
		for (int i = 0; i < respuestas.length; i++) {
			if (respuestas[i].equalsIgnoreCase("CORRECTA")) {
				contador++;
			}
		}

		return contador;
	}

	private int contadorDeRespuestasIncorrectas() {
		int contador = 0;
		for (int i = 0; i < respuestas.length; i++) {
			if (respuestas[i].equalsIgnoreCase("INCORRECTA")) {
				contador++;
			}
		}

		return contador;
	}

	public double promedioDeRespuestaCorrectas() {
		int cantidadDeRespuestas = 0;

		for (int i = 0; i < respuestas.length; i++) {
			if (respuestas != null) {
				cantidadDeRespuestas += tiempoDeRespuesta[i];
			}
		}

		return cantidadDeRespuestas / contadorDeRespuestasCorrectas();
	}

	public double promedioDeRespuestaIncorrectas() {
		int cantidadDeRespuestas = 0;

		for (int i = 0; i < respuestas.length; i++) {
			if (respuestas != null) {
				cantidadDeRespuestas += tiempoDeRespuesta[i];
			}
		}

		return cantidadDeRespuestas / contadorDeRespuestasIncorrectas();
	}

	private int cantidadDeRespuestas() {
		int contador = 0;

		for (int i = 0; i < tiempoDeRespuesta.length; i++) {
			if (tiempoDeRespuesta != null) {
				contador++;
			}
		}

		return contador;
	}

	public double promedioDeTiempoDeRespuesta() {
		double tiempoTotal = 0;

		for (int i = 0; i < tiempoDeRespuesta.length; i++) {
			if (tiempoDeRespuesta != null) {
				tiempoTotal += tiempoDeRespuesta[i];
			}
		}

		return tiempoTotal / cantidadDeRespuestas();
	}

	public double tiempoTotalJugado() {
		double tiempoTotal = 0;

		for (int i = 0; i < tiempoDeRespuesta.length; i++) {
			if (tiempoDeRespuesta != null) {
				tiempoTotal += tiempoDeRespuesta[i];
			}
		}
		return tiempoTotal;
	}

	public boolean isSoyGanador() {
		return soyGanador;
	}

	public void setSoyGanador(boolean soyGanador) {
		this.soyGanador = soyGanador;
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

	public boolean eliminarProvincia(String eliminarProvincia) {
		int indice = 0;
		boolean encontrado = false;
		while (indice < provinciasEnPoder.length && !encontrado) {
			if (provinciasEnPoder[indice] != null && provinciasEnPoder[indice].equals(eliminarProvincia)) {
				provinciasEnPoder[indice] = null;
				encontrado = true;
			}
			indice++;
		}
		moverProvinciasNulos();
		return encontrado;
	}

	private void moverProvinciasNulos() {
		for (int i = 0; i < this.provinciasEnPoder.length; i++) {
			for (int j = 0; j < this.provinciasEnPoder.length - 1; j++) {
				if (this.provinciasEnPoder[j] == null && this.provinciasEnPoder[j + 1] != null) {
					this.provinciasEnPoder[j] = this.provinciasEnPoder[j + 1];
					this.provinciasEnPoder[j + 1] = null;
				}
			}
		}
	}

	public static String aniadirleColor(ColoresDeInterfaz color, String textoAColorear) {
		switch (color) {
		case COLOR_ROJO:
			if (textoAColorear != null) {
				textoAColorear = coloresDisponibles[0] + textoAColorear + coloresDisponibles[6];
			}
			break;
		case COLOR_AZUL:
			if (textoAColorear != null) {
				textoAColorear = coloresDisponibles[1] + textoAColorear + coloresDisponibles[6];
			}
			break;
		case COLOR_AMARILLO:
			if (textoAColorear != null) {
				textoAColorear = coloresDisponibles[2] + textoAColorear + coloresDisponibles[6];
			}
			break;
		case COLOR_VERDE:
			if (textoAColorear != null) {
				textoAColorear = coloresDisponibles[3] + textoAColorear + coloresDisponibles[6];
			}
			break;
		case COLOR_CYAN:
			if (textoAColorear != null) {
				textoAColorear = coloresDisponibles[4] + textoAColorear + coloresDisponibles[6];
			}
			break;
		case COLOR_PURPLE:
			if (textoAColorear != null) {
				textoAColorear = coloresDisponibles[5] + textoAColorear + coloresDisponibles[6];
			}
			break;
		case COLOR_BLANCO:
			if (textoAColorear != null) {
				textoAColorear = coloresDisponibles[6] + textoAColorear + coloresDisponibles[6];
			}
			break;
		case COLOR_NEGRO:
			if (textoAColorear != null) {
				textoAColorear = coloresDisponibles[7] + textoAColorear + coloresDisponibles[6];
			}
			break;
		}
		return textoAColorear;
	}

	public String[] getProvinciasEnPoder() {

		return ordenarAlfabeticamenteProvincias();
	}

	public int getPuntos() {
		return puntos;
	}

	public void modificarPuntos(int cuantoPuntos) {
		this.puntos += cuantoPuntos;
	}

	public int getId() {
		return id;
	}

	public String[] getColoresDisponibles() {
		return coloresDisponibles;
	}

	public ColoresDeInterfaz getColorSeleccionado() {
		return colorSeleccionado;
	}

	public Integer contadorDeProvincias() {
		Integer provincias = 0;
		for (int i = 0; i < provinciasEnPoder.length; i++) {
			if (provinciasEnPoder[i] != null) {
				provincias++;
			}
		}
		return provincias;
	}

	private String[] ordenarAlfabeticamenteProvincias() {
		String aux = "";
		moverProvinciasNulos();
		for (int i = 0; i < provinciasEnPoder.length; i++) {
			for (int j = 0; j < provinciasEnPoder.length - 1; j++) {
				if (provinciasEnPoder[j] != null && provinciasEnPoder[j + 1] != null
						&& provinciasEnPoder[j].compareTo(provinciasEnPoder[j + 1]) > 0) {
					aux = provinciasEnPoder[j];
					provinciasEnPoder[j] = provinciasEnPoder[j + 1];
					provinciasEnPoder[j + 1] = aux;
				}
			}
		}

		return this.provinciasEnPoder;
	}

	private String mostrarProvincias() {
		String provincias = "";
		ordenarAlfabeticamenteProvincias();
		for (int i = 0; i < provinciasEnPoder.length; i++) {
			if (provinciasEnPoder[i] != null) {
				provincias += i + "-" + aniadirleColor(this.colorSeleccionado, provinciasEnPoder[i]) + "\n";
			}
		}
		return provincias;
	}

	public String mostrarEstadisticas() {
		return "Jugador [puntos=" + puntos + ", nombre=" + nombre + ", id=" + id + ", promedioDeRespuestaCorrectas()="
				+ promedioDeRespuestaCorrectas() + ", promedioDeRespuestaIncorrectas()="
				+ promedioDeRespuestaIncorrectas() + ", promedioDeTiempoDeRespuesta()=" + promedioDeTiempoDeRespuesta()
				+ ", provinciasEnPoder=" + mostrarProvincias() + "\ntiempoTotalJugadoEnSegundos=" + tiempoTotalJugado()
				+ "]";
	}

	@Override
	public String toString() {
		return "Jugador [ puntos=" + puntos + ", nombre=" + nombre + "]";
	}

}
