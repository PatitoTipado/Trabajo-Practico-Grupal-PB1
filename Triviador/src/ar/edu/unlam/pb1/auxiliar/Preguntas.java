package ar.edu.unlam.pb1.auxiliar;

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
			"¿Cuál es el equipo de fútbol más exitoso de Argentina, con numerosos títulos nacionales e internacionales",
			"¿En que anio se estreno la ganadora del oscar 'El secreto de tus ojos'?",
			"¿Cual de estas canciones no es soda Stereo?",
			"¿en que juegos olimpicos gano argentina la medalla de oro en basket?",
			"¿Cual de estos libros escribio Jorge Luis Borges?",
			"¿Quien fue el primer presidente despues de la dictadura militar?",
			"¿Cual fue la primer pelicula argentina en ganar un oscar?",
			"A que procer se lo apoda 'el padre de la educacion'", "¿Cuantos mundiales tienen los pumas?",
			"¿Que banda compuso el album OKTUBRE?", "¿En que equipo de la NBA jugo casi toda su carrera Manu Ginobili?",
			"¿Cuantos Grand Slam gano Juan Martin Del Potro?", "¿Cual es la carta mas 'poderosa' del truco?",
			"¿Quien canta la cancion 'Zamba para olvidar'?", "¿Cual de estos clubes no tiene copas libertadores?" };

	public static String[] getPregunta() {
		return pregunta;
	}

	private static String opciones[][] = { { "1) Samba", "2) Cumbia", "3) Tango", "4) Flamenco" },
			{ "1) Real", "2) Peso argentino", "3) Sol", "4) Bolíva" },
			{ "1) José de San Martín", "2) Simón Bolívar", "3) Eva Perón", "4) Juan Manuel de Rosas" },
			{ "1) Mendoza", "2) Córdoba", "3) Buenos Aires", "4) Rosario" },
			{ "1) Paella", "2) Empanadas", "3) Asado", "4) Ceviche" },
			{ "1) Aconcagua", "2) Chimborazo", "3) Huascarán", "4) Fitz Roy" },
			{ "1) Montevideo", "2) Santiago", "3) Buenos Aire", "4) Lima" },
			{ "1) Isabel Allend", "2) Julio Cortázar", "3) Jorge Luis Borges", "4) Gabriela Mistral" },
			{ "1) River Plate", "2) Boca Juniors", "3) Independiente", "4) Racing Club" },
			{ "1) 2007", "2) 2009", "3) 2000", "4) 2005" },
			{ "1) te para tres", "2) persiana americana", "3) bajan", "4) cancion animal" },
			{ "1) Atenas 2004", "2) Pekin 2008", "3) Sidney 2000", "4) Londres 2012" },
			{ "1) 100 anios de soledad", "2) El Aleph", "3) Rayuela", "4) Operacion Masacre" },
			{ "1) Menen", "2) De la Rua", "3) Alfonsin ", "4) Peron" },
			{ "1) Nueve reinas", "2) Esperando la carroza", "3) La historia oficial", "4) Argentina 1985" },
			{ "1) San Martin", "2) Sarmiento", "3) Roca", "4) Rosas" }, { "1) 1", "2) 3", "3) 5", "4) 0 " },
			{ "1) Sumo", "2) Pescado Rabioso", "3) Patricio Rey y sus redonditos de ricota", "4) Soda Stereo" },
			{ "1) San Antonio Spurs", "2) Los angeles Lakers", "3) Boston Celtics", "4) Chicago Bulls" },
			{ "1) 0", "2) 2", "3) 4", "4) 1" },
			{ "1) Ancho de espadas", "2) Ancho de basto", "3) 7 de espadas", "4) 7 de copas" },
			{ "1) Mercedes Sosa", "2) Valeria Lynch", "3) Fabiana Cantilo", "4) Natalia Oreiro" },
			{ "1) Velez", "2) Argentinos Jrs", "3) Estudiantes", "4) Lanus" }, };

	public static String[][] getOpciones() {
		return opciones;
	}

	private static Integer[] respuestaCorrectaClasicas = { 3, 2, 1, 3, 3, 1, 3, 3, 2, 2, 3, 1, 2, 3, 3, 2, 4, 3, 1, 4,
			1, 1, 4 };

	public static Integer[] getRespuestaCorrectaClasicas() {
		return respuestaCorrectaClasicas;
	}

	private static String[] preguntaProximidad = { "¿Cuando gano su ultmo mundial Argentina?", "¿Cuando nacio Messi?",
			"¿En que año fue la revolucion de mayo?", "Cuando gano su primer mundial Argentina",
			"¿en que posicion quedo francia en el mundial 2022?", "¿Cuántas provincias tiene Argentina?",
			"¿En qué año se declaró la independencia de Argentina?", "¿Cuántos Obeliscos hay en Buenos Aires?" };

	public static String[] getPreguntaProximidad() {
		return preguntaProximidad;
	}

	private static Integer[] respuestaCorrectaProximidad = { 2022, 1987, 1810, 1978, 2, 23, 1816, 1 };

	public static Integer[] getRespuestaCorrectaProximidad() {
		return respuestaCorrectaProximidad;
	}

	private Preguntas() {

	}

}
