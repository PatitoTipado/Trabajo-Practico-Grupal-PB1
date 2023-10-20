package ar.edu.unlam.pb1.auxArrays;

public class Preguntas {
	private static String[] pregunta = {
			"�Cual es el baile tradicional de Argentina que se caracteriza por el abrazo entre los bailarines?",
			"�Cual es la moneda Oficial de Argentina?",
			"�Quien fue la figura historica argentina conocida como 'El libertador'?",
			"�En qu� ciudad argentina se encuentra el famoso teatro Col�n, conocido por ser uno de los mejores teatros de �pera del mundo?",
			"�Cu�l es el plato nacional de Argentina que consiste en carne asada a la parrilla?",
			"�Qu� monta�a, ubicada en la frontera entre Argentina y Chile, es la monta�a m�s alta de Am�rica del Sur?",
			"�Cu�l es la capital de Argentina?",
			"�Qui�n es la escritora argentina ganadora del Premio Nobel de Literatura en 2010?",
			"�Cu�l es el equipo de f�tbol m�s exitoso de Argentina, con numerosos t�tulos nacionales e internacionales" };

	public static String[] getPregunta() {
		return pregunta;
	}

	private static String opciones[][] = { { "A) Samba", "B) Cumbia", "C) Tango", "D) Flamenco" },
			{ "A) Real", "B) Peso argentino", "C) Sol", "D) Bol�va" },
			{ "A) Jos� de San Mart�n", "B) Sim�n Bol�var", "C) Eva Per�n", "D) Juan Manuel de Rosas" },
			{ "A) Mendoza", "B) C�rdoba", "C) Buenos Aires", "D) Rosario" },
			{ "A) Paella", "B) Empanadas", "C) Asado", "D) Ceviche" },
			{ "A) Aconcagua", "B) Chimborazo", "C) Huascar�n", "D) Fitz Roy" },
			{ "A) Montevideo", "B) Santiago", "C) Buenos Aire", "D) Lima" },
			{ "A) Isabel Allend", "B) Julio Cort�zar", "C) Jorge Luis Borges", "D) Gabriela Mistral" },
			{ "A) River Plate", "B) Boca Juniors", "C) Independiente", "D) Racing Club" } };

	public static String[][] getOpciones() {
		return opciones;
	}

	private static String[] respuestaCorrecta = { "C) Tango", "B) Peso argentino", "A) Jos� de San Mart�n",
			"C) Buenos Aires", "C) Asado", "A) Aconcagua", "C) Buenos Aires", "C) Jorge Luis Borgess",
			"B) Boca Juniors" };

	public static String[] getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	private static String[] preguntaProximidad = { "�Cuando gano su ultmo mundial Argentina?", "�Cuando nacio Messi?",
			"�En que a�o fue la revolucion de mayo?", "Cuando gano su primer mundial Argentina",
			"�en que posicion quedo francia en el mundial 2022?", "�Cu�ntas provincias tiene Argentina?",
			"�En qu� a�o se declar� la independencia de Argentina?", "�Cu�ntos Obeliscos hay en Buenos Aires?" };

	public static String[] getPreguntaProximidad() {
		return preguntaProximidad;
	}

	private static int[] respuestaCorrectaProximidad = { 2022, 1987, 1810, 1978, 2, 23, 1816, 1 };

	public static int[] getRespuestaCorrectaProximidad() {
		return respuestaCorrectaProximidad;
	}

	private Preguntas() {

	}
}
