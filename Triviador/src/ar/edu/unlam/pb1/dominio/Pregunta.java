package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.auxiliar.Preguntas;

public class Pregunta {
	// preguntas clasicas
	private static String[] pregunta = Preguntas.getPregunta();
	private static String opciones[][] = Preguntas.getOpciones();
	private static Integer[] respuestaCorrecta = Preguntas.getRespuestaCorrectaClasicas();
	// preguntas proximidad
	private static String[] preguntaProximidad = Preguntas.getPreguntaProximidad();
	private static Integer[] respuestaCorrectaProximidad = Preguntas.getRespuestaCorrectaProximidad();

	private Pregunta() {
	}

	public static String[] getPreguntaClasica() {
		return pregunta;
	}

	public static String[][] getOpcionesClasicas() {
		return opciones;
	}

	public static Integer[] getRespuestaCorrectaClasicas() {
		return respuestaCorrecta;
	}

	public static String[] getPreguntaProximidad() {
		return preguntaProximidad;
	}

	public static Integer[] getRespuestaCorrectaProximidad() {
		return respuestaCorrectaProximidad;
	}

}
