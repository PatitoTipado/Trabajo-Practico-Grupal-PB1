package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.enums.ColoresDeInterfaz;

public class Triviador {

	private String[] provincias = { "Buenos Aires", "Catamarca", "Chaco", "Chubut", "Córdoba", "Corrientes",
			"Entre Ríos", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén", "Río Negro",
			"Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero", "Tierra del Fuego",
			"Tucumán" };
	// si le subimos a la interfazGraficaAcaTmb 1
	private static final int POSICION_INSERTADA_TITULOS = 1;
	public static final int CANTIDAD_DE_PROVINCIAS = 23;
	// si queremos mostrar uno mas grande le subimos de a 2 para que quede simetrico
	private static final int CANTIDAD_ASTERISCOS = 50;
	// la cantidad de puntos que nos sacariamos entre jugadores
	private static final int PUNTOS_ENTRE_JUGADORES = 200;
	private Jugador[] jugadores = new Jugador[2];
	private static Jugador[] jugadoresTotales;
	private Integer[] turnosJugadores;
	private Integer[] respuestaDeProximidad;
	private Integer[] respuestaClasicas;
	private String[] interfazTitulos = new String[3];
	private Integer[] numeroAleatorioJugador;
	private static final int CANTIDAD_DE_TIPOS_DE_JUEGOS = 3;
	private double[] tiempoDeRespuesta;

	public Triviador() {
		this.turnosJugadores = new Integer[jugadores.length];
		this.respuestaDeProximidad = new Integer[jugadores.length];
		this.tiempoDeRespuesta = new double[jugadores.length];
		this.respuestaClasicas = new Integer[jugadores.length];
		this.numeroAleatorioJugador = new Integer[jugadores.length];
		jugadoresTotales = new Jugador[jugadores.length * CANTIDAD_DE_TIPOS_DE_JUEGOS];
	}

	public String[] interfazTitulos(String provinciaMostrar) {
		// llenar el array
		llenarElArrayDeAsteriscos();
		// 25 es la mitad del array quiero insertar una palabra alli
		insertarPalabraEnLaInterfaz(provinciaMostrar);
		// y eliminar las puntas hasta que la cantidad sea igual al de la palabra
		extraerLasPuntasDelArray(provinciaMostrar);

		return interfazTitulos;
	}

	public String[] interfazTitulos(String provinciaMostrar, ColoresDeInterfaz color) {
		// llenar el array
		llenarElArrayDeAsteriscos();
		// 25 es la mitad del array quiero insertar una palabra alli
		insertarPalabraEnLaInterfaz(provinciaMostrar);
		// y eliminar las puntas hasta que la cantidad sea igual al de la palabra
		extraerLasPuntasDelArray(provinciaMostrar);
		// aniadirle color
		aniadirleColor(color);

		return interfazTitulos;
	}

	private void aniadirleColor(ColoresDeInterfaz color) {
		for (int i = 0; i < interfazTitulos.length; i++) {
			interfazTitulos[i] = Jugador.aniadirleColor(color, interfazTitulos[i]);
		}
	}

	private void extraerLasPuntasDelArray(String provinciaMostrar) {
		int indice = 1;
		while (indice < (provinciaMostrar.length() + 1)) {
			if (indice % 2 == 0) {
				interfazTitulos[POSICION_INSERTADA_TITULOS] = interfazTitulos[POSICION_INSERTADA_TITULOS].substring(1);
			} else {
				interfazTitulos[POSICION_INSERTADA_TITULOS] = interfazTitulos[POSICION_INSERTADA_TITULOS].substring(0,
						(interfazTitulos[POSICION_INSERTADA_TITULOS].length() - 1));
			}
			indice++;
		}
	}

	private void insertarPalabraEnLaInterfaz(String provincia) {
		// agarramos la palabra en una variable auxiliar
		String auxiliar = interfazTitulos[POSICION_INSERTADA_TITULOS];
		// posicion de insercion de la palabra en cuestion
		Integer posicionDeInsercionDePalabra = 25;
		// dividimos la palabra en dos partes
		String parteInicial = auxiliar.substring(0, posicionDeInsercionDePalabra);
		String parteFinal = auxiliar.substring(posicionDeInsercionDePalabra);
		// devolvemos el resultado de el junte de la cadena total
		interfazTitulos[POSICION_INSERTADA_TITULOS] = parteInicial + provincia + parteFinal;
	}

	private void llenarElArrayDeAsteriscos() {
		String asteriscos = "";
		for (int i = 0; i < interfazTitulos.length; i++) {
			for (int j = 0; j < CANTIDAD_ASTERISCOS; j++) {
				asteriscos += "*";
			}
			interfazTitulos[i] = asteriscos;
			asteriscos = "";
		}
	}

	private static boolean agregar(Jugador jugador) {
		int indice = 0;
		boolean encontrado = false;
		while (indice < jugadoresTotales.length && !encontrado) {
			if (jugadoresTotales[indice] == null) {
				jugadoresTotales[indice] = jugador;
				encontrado = true;
			}
			indice++;
		}

		return encontrado;
	}

	public boolean agregarJugador(Jugador jugador) {
		int indice = 0;
		boolean encontrado = false;
		while (indice < jugadores.length && !encontrado) {
			if (jugadores[indice] == null) {
				jugadores[indice] = jugador;
				agregar(jugador);
				encontrado = true;
			}
			indice++;
		}
		return encontrado;
	}

	// llega el numero
	public boolean asignarProvinciasDelPropioJuego(int indice, int jugador) {
		boolean sePudoAsignarProvincia = false;
		// esta habitada la provincia
		if (estaHabitada(provincias[indice]) && estaDisponible(provincias[indice])) {
			// y llama al metodo y asignarle al jugador y darsela o no, si no esta habitida
			if (jugadores[jugador].sePuedeAgregarProvincias(provincias[indice])) {
				eliminarDelArrayDeProvincias(provincias[indice], indice); // del array se elimina la provincia
				jugadores[jugador].modificarPuntos(100);
				sePudoAsignarProvincia = true;
			}
		}
		// devolver true si se puede
		return sePudoAsignarProvincia;
	}

	public boolean asignarProvinciasSiGanaLaPelea(int indice, int jugadorDefensor, int jugadorGanador,
			int jugadorPerdedor) {
		boolean asignarProvincia = false;
		// guardar la provincia en un auxiliar igual
		String auxProvincia = this.jugadores[jugadorDefensor].getProvinciasEnPoder()[indice];
		// agregarsela al ganador agregamos igual, aunque este en el array
		if (this.jugadores[jugadorGanador].sePuedeAgregarProvincias(auxProvincia)) {
			// se la eliminas al perdedor se la eliminamos de la anterior posicion
			this.jugadores[jugadorPerdedor].eliminarProvincia(auxProvincia);
			// y le agregas los puntos y le agregamos puntos
			this.jugadores[jugadorGanador].modificarPuntos(PUNTOS_ENTRE_JUGADORES);
			// al perdedor le sacamos la misma cantidad de puntos
			this.jugadores[jugadorPerdedor].modificarPuntos(-PUNTOS_ENTRE_JUGADORES);
			asignarProvincia = true;
		}

		return asignarProvincia;
	}

	private boolean estaDisponible(String provincia) {
		boolean estaDisponible = false;
		for (int i = 0; i < provincias.length; i++) {
			// si encuentra la provincia en poder de alguien, no esta disponible
			if (provincias[i] != null && provincias[i].equals(provincia)) {
				estaDisponible = true;
			}
		}
		return estaDisponible;

	}

	private boolean estaHabitada(String provincia) {
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

	public int cambioDeTurno(int juega) {
		if (juega == 0) {
			juega = 1;
		} else {
			juega = 0;
		}
		return juega;
	}

	public boolean puedeJugarElJugador(int idJugador) {
		// buscamos en el array de turnos si ya jugo
		boolean existeEnArray = buscarEnELArrayDeTurnos(idJugador);
		// si no juego lo dejamos jugar
		if (!existeEnArray) {
			aniadirElTurnoDelJugador(idJugador);
		}

		return !existeEnArray;
	}

	public void aniadirElTurnoDelJugador(int puedeJugar) {
		boolean ingresado = false;
		int indice = 0;
		while (indice < turnosJugadores.length && !ingresado) {
			if (turnosJugadores[indice] == null) {
				turnosJugadores[indice] = puedeJugar;
				ingresado = true;
			}
			indice++;
		}
	}

	private boolean buscarEnELArrayDeTurnos(int puedeJugar) {
		int indice = 0;
		boolean ExisteEnElArray = false;
		while (indice < turnosJugadores.length && !ExisteEnElArray) {
			if (turnosJugadores[indice] != null && turnosJugadores[indice] == puedeJugar) {
				ExisteEnElArray = true;
			}
			indice++;
		}
		return ExisteEnElArray;
	}

	public void vaciarElArrayDeTurnos() {
		for (int i = 0; i < turnosJugadores.length; i++) {
			turnosJugadores[i] = null;
		}

	}

	public int definirPorProximidad(int respuestaJugador0, int respuestaJugador1, int laPreguntaDeProximidad) {
		int ganador = -1;
		int respuestaDeProximidad = respuestaProximidad()[laPreguntaDeProximidad];
		int distanciaGanarJugador0 = Math.abs(respuestaDeProximidad - respuestaJugador0);
		int distanciaGanarJugador1 = Math.abs(respuestaDeProximidad - respuestaJugador1);
		if (distanciaGanarJugador0 < distanciaGanarJugador1) {
			ganador = 0;
		} else if (distanciaGanarJugador1 < distanciaGanarJugador0) {
			ganador = 1;
		} else if (distanciaGanarJugador0 == distanciaGanarJugador1) {
			ganador = (int) Math.random() * jugadores.length;
		}

		return ganador;
	}

	public int definirGanadorNumeroAleatorio(int numeroAleatorioProximidad) {
		int ganador = -1;
		int indice = 0;
		while (indice < numeroAleatorioJugador.length) {
			if (numeroAleatorioProximidad == numeroAleatorioJugador[indice]) {
				ganador = indice;
			}
			indice++;
		}

		return ganador;
	}

	public int definirPorProximidadNumeroAleatorio(int numeroAleatorio) {
		int ganador = 0;

		int distancia = Math.abs(numeroAleatorioJugador[0] - numeroAleatorio);

		for (int i = 1; i < numeroAleatorioJugador.length; i++) {
			if (distancia > Math.abs(numeroAleatorioJugador[i] - numeroAleatorio)) {
				distancia = Math.abs(numeroAleatorioJugador[i] - numeroAleatorio);
				ganador = i;
			}
		}

		return ganador;
	}

	private boolean eliminarDelArrayDeProvincias(String provinciaEliminar, int indice) {
		boolean eliminar = false;
		if (provincias[indice] != null && provincias[indice].equals(provinciaEliminar)) {
			provincias[indice] = null;
			eliminar = true;
		}

		return eliminar;
	}

	// se puede pelear entre las provincais del array del juego
	public boolean sePuedeSeguirJugandoInicioConProvinciasDeTriviador() {
		int indice = 0;
		boolean seguirJugando = false;
		while (indice < provincias.length && !seguirJugando) {
			if (provincias[indice] != null) {
				seguirJugando = true;
			}
			indice++;
		}
		return seguirJugando;
	}

	// se puede pelear entre ellos lasprovincias
	public boolean sePuedeSeguirJugandoInicioConProvinciasDelPoder() {
		boolean seguirJugando = false;
		int i = 0;
		int j = 0;
		while (i < jugadores.length && !seguirJugando) {
			while (j < jugadores[i].getProvinciasEnPoder().length && !seguirJugando) {
				if (jugadores[i].getProvinciasEnPoder()[j] != null) {
					seguirJugando = true;
				}
				j++;
			}
			i++;
		}
		return seguirJugando;
	}

	public boolean ganadorEnModosDeJuego() {
		boolean ganador = false;
		int i = 0;
		int contador = 0;
		int j = 0;
		while (!ganador && i < jugadores.length) {
			// VOLVER A LA NORMALIDAD LOS PUNTOS
			if (jugadores[i] != null && jugadores[i].getPuntos() >= 3000) {
				ganador = true;
				jugadores[i].setSoyGanador(ganador);
			} else {
				while (!ganador && j < jugadores[i].getProvinciasEnPoder().length) {
					if (jugadores[i].getProvinciasEnPoder()[j] != null) {
						contador++;
						// cambiar provincias para ganar
						ganador = contador == 16;
						jugadores[i].setSoyGanador(ganador);
					}
					j++;
				}
			}
			i++;
		}

		return ganador;
	}

	// condicion ganadora que gane o tenga cierta catnidad de provincias
	public boolean hayUnGanador() {
		boolean ganador = true;
		int i = 0;
		int j = 0;
		while (i < jugadores.length) {
			jugadores[i].setSoyGanador(true);
			while (j < jugadores[i].getProvinciasEnPoder().length && ganador) {
				if (jugadores[i].getProvinciasEnPoder()[j] == null) {
					ganador = false;
					jugadores[i].setSoyGanador(false);
				}
				j++;
			}
			i++;
		}
		return ganador;
	}

	public Jugador elGanadorEs() {

		Jugador jugadorGanadoExiste = null;
		int indice = 0;

		while (indice < jugadores.length) {
			if (jugadores[indice].isSoyGanador()) {
				jugadorGanadoExiste = jugadores[indice];
			}
			indice++;
		}

		return jugadorGanadoExiste;
	}

	// mostrar la victora mas rapida

	public static Jugador mostrarLaVictoriaMasRapida() {

		Jugador aux = primerJugadorEncontrado();

		if (aux != null) {
			for (int i = 0; i < jugadoresTotales.length; i++) {
				if (jugadoresTotales[i] != null && aux.tiempoTotalJugado() > jugadoresTotales[i].tiempoTotalJugado()) {
					aux = jugadoresTotales[i];
				}
			}
		}

		return aux;
	}

	// mostrar la victora con mas provincias

	public static Jugador mostrarLaVictoriaConMasProvincias() {

		Jugador aux = primerJugadorEncontrado();

		if (aux != null) {
			for (int i = 0; i < jugadoresTotales.length; i++) {
				if (jugadoresTotales[i] != null
						&& aux.contadorDeProvincias() < jugadoresTotales[i].contadorDeProvincias()) {
					aux = jugadoresTotales[i];
				}
			}
		}

		return aux;
	}

	// mostrar la victoria con mas puntos

	public static Jugador mostrarLaVictoriaConMasPuntos() {

		Jugador aux = primerJugadorEncontrado();

		if (aux != null) {
			for (int i = 0; i < jugadoresTotales.length; i++) {
				if (jugadoresTotales[i] != null && aux.getPuntos() < jugadoresTotales[i].getPuntos()) {
					aux = jugadoresTotales[i];
				}
			}
		}

		return aux;
	}

	public static Jugador primerJugadorEncontrado() {
		Jugador aux = null;
		int indice = 0;
		boolean encontrado = false;

		while (indice < jugadoresTotales.length && !encontrado) {
			if (jugadoresTotales[indice] != null) {
				aux = jugadoresTotales[indice];
				encontrado = true;
			}
			indice++;
		}
		return aux;
	}

	public void cargarNumeroAleatorio(int numeroAleatorio, int jugador) {
		numeroAleatorioJugador[jugador] = numeroAleatorio;
	}

	public String[] getProvincias() {
		return provincias;
	}

	public Jugador[] getJugadores() {
		return jugadores;
	}

	public void agregarRespuestaClasica(int jugador, int elegir) {
		this.respuestaClasicas[jugador] = elegir;
	}

	public void agregarRespuestaDeProximidad(int jugador, int elegir) {
		this.respuestaDeProximidad[jugador] = elegir;
	}

	public static int getPosicionInsertada() {
		return POSICION_INSERTADA_TITULOS;
	}

	public Integer[] getTurnosJugadores() {
		return turnosJugadores;
	}

	public String[] getInterfazGrafica() {
		return interfazTitulos;
	}

	public static int getCantidadDeProvincias() {
		return CANTIDAD_DE_PROVINCIAS;
	}

	public Integer[] getRespuestaDeProximidadDeJugadores() {
		return respuestaDeProximidad;
	}

	public double[] getTiempoDeRespuestaDeJugadores() {
		return tiempoDeRespuesta;
	}

	public Integer[] getRespuestaClasicasDeJugadores() {
		return respuestaClasicas;
	}

	public static int getPosicionInsertadaTitulos() {
		return POSICION_INSERTADA_TITULOS;
	}

	public static int getCantidadAsteriscos() {
		return CANTIDAD_ASTERISCOS;
	}

	public static int getPuntosEntreJugadores() {
		return PUNTOS_ENTRE_JUGADORES;
	}

	public String[] getInterfazTitulos() {
		return interfazTitulos;
	}

	public Integer[] getNumeroJugador() {
		return numeroAleatorioJugador;
	}

	public String[] preguntasClasicas() {
		return Pregunta.getPreguntaClasica();
	}

	public String[][] opcionesDePreguntasClasicas() {
		return Pregunta.getOpcionesClasicas();
	}

	public Integer[] respuestaDePreguntasClasicas() {
		return Pregunta.getRespuestaCorrectaClasicas();
	}

	public String[] preguntasProximidad() {
		return Pregunta.getPreguntaProximidad();
	}

	public Integer[] respuestaProximidad() {
		return Pregunta.getRespuestaCorrectaProximidad();
	}

	public static Jugador[] getJugadoresTotales() {
		return jugadoresTotales;
	}

	public static int getCantidadDeTiposDeJuegos() {
		return CANTIDAD_DE_TIPOS_DE_JUEGOS;
	}

}
