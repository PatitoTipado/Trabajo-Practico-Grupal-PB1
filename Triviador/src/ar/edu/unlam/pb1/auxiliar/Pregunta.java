package ar.edu.unlam.pb1.auxiliar;

import ar.edu.unlam.pb1.auxArrays.Preguntas;

public class Pregunta {
	private static String[] pregunta = Preguntas.getPregunta();
	private static String opciones[][] = Preguntas.getOpciones();
	private static String[] respuestaCorrecta = Preguntas.getRespuestaCorrecta();
	private static String[] preguntaProximidad = Preguntas.getPreguntaProximidad();
	private static int[] respuestaCorrectaProximidad = Preguntas.getRespuestaCorrectaProximidad();

	private Pregunta() {
	}

	public static String[] getPregunta() {
		return pregunta;
	}

	public static String[][] getOpciones() {
		return opciones;
	}

	public static String[] getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public static String[] getPreguntaProximidad() {
		return preguntaProximidad;
	}

	public static int[] getRespuestaCorrectaProximidad() {
		return respuestaCorrectaProximidad;
	}

}
