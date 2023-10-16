package ar.edu.unlam.pb1.dominio;

public class Pregunta {
	private static String[] pregunta;
	private String opciones[];
	private int respuestaCorrecta;

	private Pregunta(int cantidadDePregunta, String opciones[], int respuestaCorrecta) {
		pregunta = new String[cantidadDePregunta];
		this.opciones = opciones;
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public static String[] getPregunta() {
		return pregunta;
	}

	public String[] getOpciones() {
		return this.opciones;
	}

	public int getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

}
