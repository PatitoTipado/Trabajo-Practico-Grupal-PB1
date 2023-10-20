package ar.edu.unlam.pb1.auxArrays;

public class Preguntas {
	private static String[] pregunta = {
			"¿Cual es el baile tradicional de Argentina que se caracteriza por el abrazo entre los bailarines?",
			"¿Cual es la moneda Oficial de Argentina?",
			"¿Quien fue la figura historica argentina conocida como 'El libertador'?",
			"¿En qué ciudad argentina se encuentra el famoso teatro Colón, conocido por ser uno de los mejores teatros de ópera del mundo?",
			"¿Cuál es el plato nacional de Argentina que consiste en carne asada a la parrilla?",
			"¿Qué montaña, ubicada en la frontera entre Argentina y Chile, es la montaña más alta de América del Sur?",
			"¿Cuál es la capital de Argentina?",
			"¿Quién es la escritora argentina ganadora del Premio Nobel de Literatura en 2010?",
			"¿Cuál es el equipo de fútbol más exitoso de Argentina, con numerosos títulos nacionales e internacionales" };

	public static String[] getPregunta() {
		return pregunta;
	}

	private static String opciones[][] = { { "A) Samba", "B) Cumbia", "C) Tango", "D) Flamenco" },
			{ "A) Real", "B) Peso argentino", "C) Sol", "D) Bolíva" },
			{ "A) José de San Martín", "B) Simón Bolívar", "C) Eva Perón", "D) Juan Manuel de Rosas" },
			{ "A) Mendoza", "B) Córdoba", "C) Buenos Aires", "D) Rosario" },
			{ "A) Paella", "B) Empanadas", "C) Asado", "D) Ceviche" },
			{ "A) Aconcagua", "B) Chimborazo", "C) Huascarán", "D) Fitz Roy" },
			{ "A) Montevideo", "B) Santiago", "C) Buenos Aire", "D) Lima" },
			{ "A) Isabel Allend", "B) Julio Cortázar", "C) Jorge Luis Borges", "D) Gabriela Mistral" },
			{ "A) River Plate", "B) Boca Juniors", "C) Independiente", "D) Racing Club" } };

	public static String[][] getOpciones() {
		return opciones;
	}

	private static String[] respuestaCorrecta = { "C) Tango", "B) Peso argentino", "A) José de San Martín",
			"C) Buenos Aires", "C) Asado", "A) Aconcagua", "C) Buenos Aires", "C) Jorge Luis Borgess",
			"B) Boca Juniors" };

	public static String[] getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	private static String[] preguntaProximidad = { "¿Cuando gano su ultmo mundial Argentina?", "¿Cuando nacio Messi?",
			"¿En que año fue la revolucion de mayo?", "Cuando gano su primer mundial Argentina",
			"¿en que posicion quedo francia en el mundial 2022?", "¿Cuántas provincias tiene Argentina?",
			"¿En qué año se declaró la independencia de Argentina?", "¿Cuántos Obeliscos hay en Buenos Aires?" };

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
