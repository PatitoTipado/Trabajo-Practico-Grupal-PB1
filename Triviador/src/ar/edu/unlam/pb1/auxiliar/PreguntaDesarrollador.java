package ar.edu.unlam.pb1.auxiliar;

public class PreguntaDesarrollador {
	private String[] pregunta;
	private String opciones[][];
	private String[] respuestaCorrecta;

	// si es para que se personalize, podria ser un apartado para jugar con amigos,
	// que eliga las preguntas
	public PreguntaDesarrollador(int cantidadDePreguntas, int cantidadDeOpciones) {
		pregunta = new String[cantidadDePreguntas];
		opciones = new String[cantidadDeOpciones][cantidadDePreguntas];
		respuestaCorrecta = new String[cantidadDePreguntas];
	}

	public String[] getPregunta() {
		return pregunta;
	}

	public String[][] getOpciones() {
		return opciones;
	}

	public String[] getRespuestaCorrecta() {
		return respuestaCorrecta;
	}
	
}
