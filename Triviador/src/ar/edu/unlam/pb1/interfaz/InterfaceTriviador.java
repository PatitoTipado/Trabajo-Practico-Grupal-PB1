package ar.edu.unlam.pb1.interfaz;

import ar.edu.unlam.pb1.dominio.*;

import java.util.Scanner;
import ar.edu.unlam.pb1.enums.*;

public class InterfaceTriviador {
	private static Scanner sc = new Scanner(System.in);
	private static Scanner sc1 = new Scanner(System.in);

	public static void main(String args[]) {
		mostrarTituloDelJuego();
		Triviador juegoClasico = new Triviador();
		Triviador JuegoProximidad = new Triviador();
		Triviador juegoAventura = new Triviador();
		MenuPrincipal opcion = null;
		System.out.println("\nBienvenido");
		do {
			opcion = elegirOpcionDelMenuPrincipal();
			switch (opcion) {
			case INICIAR_MODO_DE_JUEGO:
				elegirModoDeJuego(juegoClasico, JuegoProximidad, juegoAventura);
				break;
			case ESTADISTICAS:
				mostrarEstadisticas(juegoAventura);
				break;
			case REGLAS:
				introduccionAlJuego();
				break;
			case SALIR:
				salirDelJuego(juegoAventura);
				break;
			default:
				mostrarMensajeConSeparacion("ELEGÍ UNA DE LAS OPCIONES DISPONIBLES");
			}
		} while (!opcion.equals(MenuPrincipal.SALIR));
	}

	private static void elegirModoDeJuego(Triviador juegoClasico, Triviador JuegoProximidad, Triviador juegoAventura) {
		ModosDeJuego opcion = elegirOpcionDelModoDeJuego();
		switch (opcion) {
		case JUEGO_CLASICO:
			jugarModoClasico(juegoClasico);
			break;
		case JUEGO_PROXIMIDAD:
			jugarProximidad(JuegoProximidad);
			break;
		case MIXTO:
			jugarMixto(juegoAventura);
			break;
		case SALIR:
			mostrarMensajeConSeparacion("Volviendo al menu principal");
			break;
		default:
			mostrarMensajeConSeparacion("ELEGÍ UNA DE LAS OPCIONES DISPONIBLES");
		}

	}

	private static void jugarMixto(Triviador juegoCompleto) {
		// cargamos jugadores para poder referirse
		cargarJugadores(juegoCompleto);
		// elegir al jugador que le toca
		int juega = seleccionarElJugadorQueLeToca(juegoCompleto);
		// cargamos provincias por primera vez
		cargamosLaProvinciasPorPrimeraVez(juega, juegoCompleto);
		// preguntas de proximidad
		preguntasProximidadPorTiempo(juega, juegoCompleto);
		if (!juegoCompleto.ganadorEnModosDeJuego()) {
			// luego empiezan las preguntas clasicas entre los jugadores
			preguntasClasicasPorTiempoEntreJugadores(juega, juegoCompleto);
		}
		mostrarProvinciasDisponibles(juegoCompleto);
	}

	private static void jugarModoClasico(Triviador juegoClasico) {
		// cargamos jugadores para poder referirse
		cargarJugadores(juegoClasico);
		// elegir entre 2 modos juego
		int jugador = seleccionarElJugadorQueLeToca(juegoClasico);
		// cargamos dos veces provincias
		cargamosLaProvinciasPorPrimeraVez(jugador, juegoClasico);
		// llegan a ocupar todas las provincias del juego
		preguntasClasicasPorTiempoEntreJugadores(jugador, juegoClasico);
		preguntasClasicasPorTiempo(jugador, juegoClasico);
		if (!juegoClasico.ganadorEnModosDeJuego()) {
		}

	}

	private static void jugarProximidad(Triviador juegoProximidad) {
		// cargamos jugadores para poder referirse
		cargarJugadores(juegoProximidad);
		// elegir entre 2 modos juego
		int juega = seleccionarElJugadorQueLeToca(juegoProximidad);
		// cargamos dos veces provincias
		cargamosLaProvinciasPorPrimeraVez(juega, juegoProximidad);
		// hasta seleccionar todas
		preguntasProximidadPorTiempo(juega, juegoProximidad);
		if (!juegoProximidad.ganadorEnModosDeJuego()) {
			preguntasProximidadPorTiempoEntreJugadores(juega, juegoProximidad);
		}
		mostrarProvinciasDisponibles(juegoProximidad);
	}

	public static int seleccionarElJugadorQueLeToca(Triviador juego) {

		int ganadorNumeroAleatorio = -1;
		int numeroAleatorio = (int) (Math.random() * 10);
		int numeroJugador = -1;

		for (int i = 0; i < juego.getJugadores().length; i++) {
			mostrarMensajeConSeparacion("Le toca elegir un numero " + juego.getJugadores()[i]);
			numeroJugador = elegirNumeroConLimite(0, 10);
			juego.cargarNumeroAleatorio(numeroJugador, i);
		}

		ganadorNumeroAleatorio = juego.definirGanadorNumeroAleatorio(numeroAleatorio);

		if (ganadorNumeroAleatorio == -1) {
			mostrarMensaje("Se define por proximidad");
			ganadorNumeroAleatorio = juego.definirPorProximidadNumeroAleatorio(numeroAleatorio);
			mostrarMensajeConSeparacion("Elegis primero jugador: " + juego.getJugadores()[ganadorNumeroAleatorio]);
		} else {
			mostrarMensajeConSeparacion("Elegis primero jugador: " + juego.getJugadores()[ganadorNumeroAleatorio]);
		}

		mostrarMensaje("El numero aleatorio es: " + numeroAleatorio);

		return ganadorNumeroAleatorio;
	}

	private static void cargarJugadores(Triviador nuevoJuego) {
		imprimirTitulos(nuevoJuego, "CARGUE JUGADORES");
		for (int i = 0; i < nuevoJuego.getJugadores().length; i++) {
			mostrarMensajeConSeparacion("Estamos cargando al jugador: " + i);
			String nombre = ingresarStringConMensaje("Ingrese el nombre del jugador");
			ColoresDeInterfaz color = seleccionarColor();
			if (nuevoJuego.agregarJugador(new Jugador(nombre, color))) {
				mostrarMensajeConSeparacion("se pudo cargar el jugador");
			}
		}
	}

	private static void cargamosLaProvinciasPorPrimeraVez(int jugador, Triviador nuevoJuego) {

		for (int j = 0; j < nuevoJuego.getJugadores().length; j++) {
			for (int i = 0; i < 3; i++) {
				elegirProvincias(jugador, nuevoJuego);
			}
			jugador = nuevoJuego.cambioDeTurno(jugador);
		}
		nuevoJuego.vaciarElArrayDeTurnos();
	}

	// PREGUNTAS DE PROXIMIDAD POR TIEMPO
	private static void preguntasProximidadPorTiempo(int jugador, Triviador nuevoJuego) {
		mostrarMensajeConSeparacion("inician las preguntas de proximidad por tiempo");
		while (nuevoJuego.sePuedeSeguirJugandoInicioConProvinciasDeTriviador() && !nuevoJuego.ganadorEnModosDeJuego()) {

			int random = elegirPreguntaRandomDeProximidad(nuevoJuego);
			int elegir = 0;
			// turnos
			for (int i = 0; i < nuevoJuego.getJugadores().length; i++) {
				mostrarMensajeConSeparacion(nuevoJuego.preguntasProximidad()[random]);
				// empieza a correr el tiempo
				long tiempoInicio = System.currentTimeMillis();
				mostrarMensajeConSeparacion("Le toca a: " + nuevoJuego.getJugadores()[jugador]);
				elegir = ingresarEnteroConMensaje("ingresa su respuesta");
				// termina de correr el tiempo
				long tiempoFin = System.currentTimeMillis();
				// metodo que calcula y te devuelve el tiempo
				// array de tiempo para guardar los tiempos de los jugadores
				nuevoJuego.getTiempoDeRespuestaDeJugadores()[jugador] = calcularTiempoDeRespuesta(tiempoInicio,
						tiempoFin);
				// mostrar el tiempo el jugador
				mostrarMensajeConSeparacion(
						"Tiempo total: " + calcularTiempoDeRespuesta(tiempoInicio, tiempoFin) + " segundos");
				nuevoJuego.agregarRespuestaDeProximidad(jugador, elegir);
				jugador = nuevoJuego.cambioDeTurno(jugador);
			}
			// mostrar el tiempo de los dos
			obtenerGanadorDeProximidad(nuevoJuego, random);
			if (nuevoJuego.ganadorEnModosDeJuego()) {
				mostrarMensajeConSeparacion("El ganador del juego es: ");
				imprimirTitulos(nuevoJuego, nuevoJuego.elGanadorEs().toString());
			}
		}

	}

	// PREGUNTAS CLASICAS POR TIEMPO
	private static void preguntasClasicasPorTiempo(int jugador, Triviador nuevoJuego) {
		mostrarMensajeConSeparacion("Inician las preguntas clasicas por tiempo");
		while (!nuevoJuego.ganadorEnModosDeJuego()) {

			int random = elegirPreguntaRandomClasica(nuevoJuego);
			int elegir = 0;
			// turnos
			for (int i = 0; i < nuevoJuego.getJugadores().length; i++) {
				mostrarMensajeConSeparacion(nuevoJuego.preguntasClasicas()[random]);
				// empieza a correr el tiempo
				long tiempoInicio = System.currentTimeMillis();
				mostrarMensajeConSeparacion("Le toca a: " + nuevoJuego.getJugadores()[jugador]);
				// mostrar opciones
				mostrarOpcionesDePreguntasClasicas(nuevoJuego, random);
				elegir = elegirNumeroConLimite(0, 5);
				// termina de correr el tiempo
				long tiempoFin = System.currentTimeMillis();
				// metodo que calcula y te devuelve el tiempo
				// array de tiempo para guardar los tiempos de los jugadores
				nuevoJuego.getTiempoDeRespuestaDeJugadores()[jugador] = calcularTiempoDeRespuesta(tiempoInicio,
						tiempoFin);
				// mostrar el tiempo el jugador
				mostrarMensajeConSeparacion(
						"Tiempo total: " + calcularTiempoDeRespuesta(tiempoInicio, tiempoFin) + " segundos");
				nuevoJuego.agregarRespuestaClasica(jugador, elegir);
				jugador = nuevoJuego.cambioDeTurno(jugador);
			}
			obtenerGanadorClasico(nuevoJuego, random);
			if (nuevoJuego.ganadorEnModosDeJuego()) {
				mostrarMensajeConSeparacion("El ganador del juego es: ");
				imprimirTitulos(nuevoJuego, nuevoJuego.elGanadorEs().toString());
			}
		}
	}

	private static void preguntasClasicasPorTiempoEntreJugadores(int juega, Triviador juegoCompleto) {
		while (!juegoCompleto.ganadorEnModosDeJuego()) {

			int auxiliarDefenza = juegoCompleto.cambioDeTurno(juega);
			mostrarMensajeConSeparacion("Pelea entre jugadores provincias en poder defiende "
					+ juegoCompleto.getJugadores()[auxiliarDefenza]);
			mostrarProvinciasEnPoderDeElJugador(juegoCompleto, auxiliarDefenza);
			mostrarMensajeConSeparacion("Ataca el " + juegoCompleto.getJugadores()[juega]);
			int atacar = elegirNumeroConLimite(-1,
					juegoCompleto.getJugadores()[auxiliarDefenza].contadorDeProvincias());
			imprimirTitulos(juegoCompleto, juegoCompleto.getJugadores()[auxiliarDefenza].getProvinciasEnPoder()[atacar],
					juegoCompleto.getJugadores()[auxiliarDefenza].getColorSeleccionado());

			// elegir pregunta random
			int random = elegirPreguntaRandomClasica(juegoCompleto);
			int elegir = 0;
			// turnos
			for (int i = 0; i < juegoCompleto.getJugadores().length; i++) {
				mostrarMensajeConSeparacion(juegoCompleto.preguntasClasicas()[random]);
				// empieza a correr el tiempo
				long tiempoInicio = System.currentTimeMillis();
				mostrarMensajeConSeparacion("Le toca a: " + juegoCompleto.getJugadores()[juega]);
				// mostrar opciones
				mostrarOpcionesDePreguntasClasicas(juegoCompleto, random);
				elegir = elegirNumeroConLimite(0, 5);
				// termina de correr el tiempo
				long tiempoFin = System.currentTimeMillis();
				// metodo que calcula y te devuelve el tiempo
				juegoCompleto.getTiempoDeRespuestaDeJugadores()[juega] = calcularTiempoDeRespuesta(tiempoInicio,
						tiempoFin);
				// mostrar el tiempo de los dos
				mostrarMensajeConSeparacion(
						"Tiempo total: " + calcularTiempoDeRespuesta(tiempoInicio, tiempoFin) + " segundos");
				juegoCompleto.agregarRespuestaClasica(juega, elegir);
				juega = juegoCompleto.cambioDeTurno(juega);
			}
			// auxiliarDefenza (quien se defiende) juega (quien ataca)
			obtenerGanadorClasicoEntreJugadores(juegoCompleto, random, atacar, auxiliarDefenza, juega);
			mostrarProvinciasEnPoderDeLosJugadores(juegoCompleto);
			if (juegoCompleto.ganadorEnModosDeJuego()) {
				mostrarMensajeConSeparacion("El ganador del juego es: ");
				imprimirTitulos(juegoCompleto, juegoCompleto.elGanadorEs().toString());
			}
			juega = juegoCompleto.cambioDeTurno(juega);
		}
	}

	public static void mostrarProvinciasEnPoderDeElJugador(Triviador nuevoJuego, int jugador) {
		for (int j = 0; j < nuevoJuego.getJugadores()[jugador].getProvinciasEnPoder().length; j++) {
			// no se le agregan al jugador las provincias
			if (nuevoJuego.getJugadores()[jugador].getProvinciasEnPoder()[j] != null) {
				mostrarMensaje(
						j + "- " + Jugador.aniadirleColor(nuevoJuego.getJugadores()[jugador].getColorSeleccionado(),
								nuevoJuego.getJugadores()[jugador].getProvinciasEnPoder()[j]));
			}
		}
	}

	private static void mostrarOpcionesDePreguntasClasicas(Triviador nuevoJuego, int preguntaElegida) {
		for (int i = 0; i < nuevoJuego.opcionesDePreguntasClasicas()[preguntaElegida].length; i++) {
			if (i % 2 == 0) {
				mostrarMensaje("");
				System.out.print(nuevoJuego.opcionesDePreguntasClasicas()[preguntaElegida][i] + " ");
			} else {
				System.out.print(nuevoJuego.opcionesDePreguntasClasicas()[preguntaElegida][i] + " ");
			}
		}
	}

	// pelea entre jugadores
	private static void obtenerGanadorClasicoEntreJugadores(Triviador nuevoJuego, int preguntaRandom,
			int provinciaAtacar, int defensor, int atacante) {
		int ganadorPorTiempo = -1;
		int respuestaDeJugadorAtacante = nuevoJuego.getRespuestaClasicasDeJugadores()[atacante];
		int respuestaDeJugadorDefensor = nuevoJuego.getRespuestaClasicasDeJugadores()[defensor];
		int jugadorGanador = -1;
		int jugadorPerdedor = -1;

		if (respuestaDeJugadorAtacante == nuevoJuego.respuestaDePreguntasClasicas()[preguntaRandom]) {
			if (respuestaDeJugadorAtacante == respuestaDeJugadorDefensor) {
				ganadorPorTiempo = definirQuienGanoPorTiempo(nuevoJuego);
				mostrarMensajeConSeparacion("el jugador: " + ganadorPorTiempo + " gano por tiempo");
				if (ganadorPorTiempo == defensor) {
					jugadorPerdedor = ganadorPorTiempo;
				} else {
					jugadorPerdedor = nuevoJuego.cambioDeTurno(ganadorPorTiempo);
				}
				nuevoJuego.asignarProvinciasSiGanaLaPelea(provinciaAtacar, defensor, ganadorPorTiempo, jugadorPerdedor);
			} else {
				jugadorGanador = atacante;
				jugadorPerdedor = defensor;
				mostrarMensajeConSeparacion("jugador 0 tiene la respuesta correcta");
				nuevoJuego.asignarProvinciasSiGanaLaPelea(provinciaAtacar, defensor, jugadorGanador, jugadorPerdedor);
			}
		} else if (respuestaDeJugadorDefensor == nuevoJuego.respuestaDePreguntasClasicas()[preguntaRandom]) {
			if (respuestaDeJugadorDefensor == respuestaDeJugadorAtacante) {
				ganadorPorTiempo = definirQuienGanoPorTiempo(nuevoJuego);
				mostrarMensajeConSeparacion("el jugador: " + ganadorPorTiempo + " gano por tiempo");
				if (ganadorPorTiempo == defensor) {
					jugadorPerdedor = ganadorPorTiempo;
				} else {
					jugadorPerdedor = nuevoJuego.cambioDeTurno(ganadorPorTiempo);
				}
				nuevoJuego.asignarProvinciasSiGanaLaPelea(provinciaAtacar, defensor, ganadorPorTiempo, jugadorPerdedor);
			} else {
				jugadorGanador = defensor;
				jugadorPerdedor = defensor;
				mostrarMensajeConSeparacion("jugador 1 tiene la respuesta correcta");
				nuevoJuego.asignarProvinciasSiGanaLaPelea(provinciaAtacar, defensor, jugadorGanador, jugadorPerdedor);
			}
		} else {
			mostrarMensajeConSeparacion("No acerto la pregunta nadie");
		}
	}

	private static void preguntasProximidadPorTiempoEntreJugadores(int juega, Triviador juegoCompleto) {
		while (!juegoCompleto.ganadorEnModosDeJuego()) {
			int auxiliarDefenza = juegoCompleto.cambioDeTurno(juega);
			mostrarMensajeConSeparacion("Pelea entre jugadores provincias en poder defiende "
					+ juegoCompleto.getJugadores()[auxiliarDefenza]);
			mostrarProvinciasEnPoderDeElJugador(juegoCompleto, auxiliarDefenza);
			mostrarMensajeConSeparacion("Ataca el " + juegoCompleto.getJugadores()[juega]);
			mostrarMensaje("Elige una de estas provincias para atacar");
			int atacar = elegirNumeroConLimite(-1,
					juegoCompleto.getJugadores()[auxiliarDefenza].contadorDeProvincias());
			imprimirTitulos(juegoCompleto, juegoCompleto.getJugadores()[auxiliarDefenza].getProvinciasEnPoder()[atacar],
					juegoCompleto.getJugadores()[auxiliarDefenza].getColorSeleccionado());

			// elegir pregunta random
			int random = elegirPreguntaRandomDeProximidad(juegoCompleto);
			int elegir = 0;
			// turnos
			for (int i = 0; i < juegoCompleto.getJugadores().length; i++) {
				mostrarMensajeConSeparacion(juegoCompleto.preguntasProximidad()[random]);
				// empieza a correr el tiempo
				long tiempoInicio = System.currentTimeMillis();
				mostrarMensajeConSeparacion("Le toca a: " + juegoCompleto.getJugadores()[juega]);
				elegir = ingresarEnteroConMensaje("\ningresa un numero");
				// termina de correr el tiempo
				long tiempoFin = System.currentTimeMillis();
				// metodo que calcula y te devuelve el tiempo
				juegoCompleto.getTiempoDeRespuestaDeJugadores()[juega] = calcularTiempoDeRespuesta(tiempoInicio,
						tiempoFin);
				// mostrar el tiempo de los dos
				mostrarMensajeConSeparacion(
						"Tiempo total: " + calcularTiempoDeRespuesta(tiempoInicio, tiempoFin) + " segundos");
				juegoCompleto.agregarRespuestaDeProximidad(juega, elegir);
				juega = juegoCompleto.cambioDeTurno(juega);
			}
			// auxiliarDefenza (quien se defiende) juega (quien ataca)
			obtenerGanadorProximidadEntreJugadores(juegoCompleto, random, atacar, auxiliarDefenza, juega);
			mostrarProvinciasEnPoderDeLosJugadores(juegoCompleto);
			if (juegoCompleto.ganadorEnModosDeJuego()) {
				mostrarMensajeConSeparacion("El ganador del juego es: ");
				imprimirTitulos(juegoCompleto, juegoCompleto.elGanadorEs().toString());
			}
			juega = juegoCompleto.cambioDeTurno(juega);
		}
	}

	private static void obtenerGanadorProximidadEntreJugadores(Triviador nuevoJuego, int preguntaRandom,
			int provinciaAtacar, int defensor, int atacante) {
		int ganadorPorTiempo = -1;
		int respuestaDeJugadorAtacante = nuevoJuego.getRespuestaDeProximidadDeJugadores()[atacante];
		int respuestaDeJugadorDefensor = nuevoJuego.getRespuestaDeProximidadDeJugadores()[defensor];
		int jugadorGanador = -1;
		int jugadorPerdedor = -1;

		if (respuestaDeJugadorAtacante == nuevoJuego.respuestaProximidad()[preguntaRandom]) {
			if (respuestaDeJugadorAtacante == respuestaDeJugadorDefensor) {
				ganadorPorTiempo = definirQuienGanoPorTiempo(nuevoJuego);
				mostrarMensajeConSeparacion("el jugador: " + ganadorPorTiempo + " gano por tiempo");
				if (ganadorPorTiempo == defensor) {
					jugadorPerdedor = ganadorPorTiempo;
				} else {
					jugadorPerdedor = nuevoJuego.cambioDeTurno(ganadorPorTiempo);
				}
				nuevoJuego.asignarProvinciasSiGanaLaPelea(provinciaAtacar, defensor, ganadorPorTiempo, jugadorPerdedor);
			} else {
				jugadorGanador = atacante;
				jugadorPerdedor = defensor;
				mostrarMensajeConSeparacion("jugador 0 tiene la respuesta correcta");
				nuevoJuego.asignarProvinciasSiGanaLaPelea(provinciaAtacar, defensor, jugadorGanador, jugadorPerdedor);
			}
		} else if (respuestaDeJugadorDefensor == nuevoJuego.respuestaProximidad()[preguntaRandom]) {
			if (respuestaDeJugadorDefensor == respuestaDeJugadorAtacante) {
				ganadorPorTiempo = definirQuienGanoPorTiempo(nuevoJuego);
				mostrarMensajeConSeparacion("el jugador: " + ganadorPorTiempo + " gano por tiempo");
				if (ganadorPorTiempo == defensor) {
					jugadorPerdedor = ganadorPorTiempo;
				} else {
					jugadorPerdedor = nuevoJuego.cambioDeTurno(ganadorPorTiempo);
				}
				nuevoJuego.asignarProvinciasSiGanaLaPelea(provinciaAtacar, defensor, ganadorPorTiempo, jugadorPerdedor);
			} else {
				jugadorGanador = defensor;
				jugadorPerdedor = defensor;
				mostrarMensajeConSeparacion("jugador 1 tiene la respuesta correcta");
				nuevoJuego.asignarProvinciasSiGanaLaPelea(provinciaAtacar, defensor, jugadorGanador, jugadorPerdedor);
			}
		} else {
			mostrarMensajeConSeparacion("No acerto la pregunta nadie");
		}
	}

	private static void obtenerGanadorClasico(Triviador nuevoJuego, int random) {
		int ganadorPorTiempo = -1;
		int repuestaJugadorCero = nuevoJuego.getRespuestaClasicasDeJugadores()[0];
		int repuestaJugadorUno = nuevoJuego.getRespuestaClasicasDeJugadores()[1];
		if (repuestaJugadorCero == nuevoJuego.respuestaDePreguntasClasicas()[random]) {
			if (repuestaJugadorCero == repuestaJugadorUno) {
				ganadorPorTiempo = definirQuienGanoPorTiempo(nuevoJuego);
				mostrarMensajeConSeparacion("el jugador: " + ganadorPorTiempo + " gano por tiempo");
				asignarLaProvinciaSiGanoUnoSolo(ganadorPorTiempo, nuevoJuego);
			} else {
				asignarLaProvinciaSiGanoUnoSolo(0, nuevoJuego);
				mostrarMensajeConSeparacion("jugador 0 tiene la respuesta correcta");
			}
		} else if (repuestaJugadorUno == nuevoJuego.respuestaDePreguntasClasicas()[random]) {
			if (repuestaJugadorCero == repuestaJugadorUno) {
				ganadorPorTiempo = definirQuienGanoPorTiempo(nuevoJuego);
				mostrarMensajeConSeparacion("el jugador: " + ganadorPorTiempo + " gano por tiempo");
				asignarLaProvinciaSiGanoUnoSolo(ganadorPorTiempo, nuevoJuego);
			} else {
				asignarLaProvinciaSiGanoUnoSolo(1, nuevoJuego);
				mostrarMensajeConSeparacion("jugador 1 tiene la respuesta correcta");
			}
		} else {
			mostrarMensajeConSeparacion("No acerto la pregunta nadie");
		}
	}

	private static void obtenerGanadorDeProximidad(Triviador nuevoJuego, int random) {
		int ganadorPorTiempo = -1;
		int jugadorGanador = -1;
		int repuestaJugadorCero = nuevoJuego.getRespuestaDeProximidadDeJugadores()[0];
		int repuestaJugadorUno = nuevoJuego.getRespuestaDeProximidadDeJugadores()[1];
		if (repuestaJugadorCero == nuevoJuego.respuestaProximidad()[random]) {
			if (repuestaJugadorCero == repuestaJugadorUno) {
				ganadorPorTiempo = definirQuienGanoPorTiempo(nuevoJuego);
				mostrarMensajeConSeparacion("el jugador: " + ganadorPorTiempo + " gano por tiempo");
				asignarLaProvinciaSiGanoUnoSolo(ganadorPorTiempo, nuevoJuego);
			} else {
				mostrarMensajeConSeparacion("jugador 0 tiene la respuesta correcta");
				asignarLaProvinciaSiGanoUnoSolo(0, nuevoJuego);
			}
		} else if (repuestaJugadorUno == nuevoJuego.respuestaProximidad()[random]) {
			if (repuestaJugadorCero == repuestaJugadorUno) {
				ganadorPorTiempo = definirQuienGanoPorTiempo(nuevoJuego);
				mostrarMensajeConSeparacion("el jugador: " + ganadorPorTiempo + " gano por tiempo");
				asignarLaProvinciaSiGanoUnoSolo(ganadorPorTiempo, nuevoJuego);
			} else {
				mostrarMensajeConSeparacion("jugador 1 tiene la respuesta correcta");
				asignarLaProvinciaSiGanoUnoSolo(1, nuevoJuego);
			}
		} else {
			mostrarMensajeConSeparacion("No acerto la pregunta nadie");
			// metodo exclusivo para proximidad
			jugadorGanador = nuevoJuego.definirPorProximidad(repuestaJugadorCero, repuestaJugadorUno, random);
			mostrarMensaje("El jugador gano por proximidad es: " + jugadorGanador);
			asignarLaProvinciaSiGanoUnoSolo(jugadorGanador, nuevoJuego);
		}
	}

	// capaz deberia por el tema del dominio, esto lo tuviera que definir el juego
	private static int definirQuienGanoPorTiempo(Triviador nuevoJuego) {
		Double auxiliar = nuevoJuego.getTiempoDeRespuestaDeJugadores()[0];
		int posicion = 0;
		mostrarTiempos(nuevoJuego);
		for (int i = 1; i < nuevoJuego.getTiempoDeRespuestaDeJugadores().length; i++) {
			if (nuevoJuego.getTiempoDeRespuestaDeJugadores()[i] < auxiliar) {
				auxiliar = nuevoJuego.getTiempoDeRespuestaDeJugadores()[i];
				posicion = i;
			}
		}
		return posicion;
	}

	private static void mostrarTiempos(Triviador nuevoJuego) {
		for (int i = 0; i < nuevoJuego.getTiempoDeRespuestaDeJugadores().length; i++) {
			mostrarMensajeConSeparacion("El tiempo del jugador: " + nuevoJuego.getJugadores()[i] + " es de: "
					+ nuevoJuego.getTiempoDeRespuestaDeJugadores()[i]);
		}
	}

	// tmb tuviera que ir al dominio
	private static double calcularTiempoDeRespuesta(long tiempoInicio, long tiempoFin) {
		// Calcula la diferencia de tiempo en segundos
		long tiempoTotal = tiempoFin - tiempoInicio;
		double segundos = tiempoTotal / 1000.0;

		// Muestra el tiempo total en segundos
		return segundos;
	}

	private static int elegirPreguntaRandomDeProximidad(Triviador nuevoJuego) {
		return (int) (Math.random() * nuevoJuego.preguntasProximidad().length);
	}

	private static int elegirPreguntaRandomClasica(Triviador nuevoJuego) {
		return (int) (Math.random() * nuevoJuego.preguntasClasicas().length);
	}

	public static void mostrarProvinciasEnPoderDeLosJugadores(Triviador nuevoJuego) {
		for (int i = 0; i < nuevoJuego.getJugadores().length; i++) {
			for (int j = 0; j < nuevoJuego.getJugadores()[i].getProvinciasEnPoder().length; j++) {
				if (nuevoJuego.getJugadores()[i].getProvinciasEnPoder()[j] != null) {
					System.out.print("- " + nuevoJuego.getJugadores()[i].getProvinciasEnPoder()[j]);
				}
			}
			mostrarMensaje("");
		}
	}

	private static void asignarLaProvinciaSiGanoUnoSolo(int jugador, Triviador nuevoJuego) {
		int buscarProvincia = 0;
		boolean salir = false;
		String auxiliar = "";
		do {
			buscarProvincia = (int) (Math.random() * nuevoJuego.getProvincias().length);
			auxiliar = nuevoJuego.getProvincias()[buscarProvincia];
			if (nuevoJuego.asignarProvinciasDelPropioJuego(buscarProvincia, jugador)) {
				salir = true;
			}
		} while (!salir);
		mostrarMensajeConSeparacion("GANO LA PROVINCIA: ");
		imprimirTitulos(nuevoJuego, auxiliar);

	}

	// elegir provincias por primera vez
	private static void elegirProvincias(int alQueLeTocaJugar, Triviador nuevoJuego) {
		mostrarMensajeConSeparacion("le toca jugar al jugar: " + nuevoJuego.getJugadores()[alQueLeTocaJugar]);
		mostrarMensaje("Elegi 1 de estas provincias: ");
		mostrarProvinciasDisponibles(nuevoJuego);
		int provincia = (elegiLaProvincias(nuevoJuego) - 1);
		if (nuevoJuego.asignarProvinciasDelPropioJuego(provincia, alQueLeTocaJugar)) {
			mostrarMensajeConSeparacion("La provincia ha sido agregada ");
		} else {
			mostrarMensajeConSeparacion("La provincia no ha podido ser agregada ");
			elegirProvincias(alQueLeTocaJugar, nuevoJuego);
		}

	}

	public static void imprimirTitulos(Triviador nuevoJuego, String titulos) {
		for (int i = 0; i < nuevoJuego.interfazTitulos(titulos).length; i++) {
			System.out.println("\t\t\t\t\t " + nuevoJuego.interfazTitulos(titulos)[i]);
		}
	}

	public static void imprimirTitulos(Triviador nuevoJuego, String titulos, ColoresDeInterfaz color) {
		for (int i = 0; i < nuevoJuego.interfazTitulos(titulos).length; i++) {
			System.out.println("\t\t\t\t\t " + nuevoJuego.interfazTitulos(titulos, color)[i]);
		}
	}

	private static void mostrarProvinciasDisponibles(Triviador nuevoJuego) {
		for (int i = 0; i < nuevoJuego.getProvincias().length; i++) {
			if (nuevoJuego.getProvincias()[i] != null) {
				System.out.print(" " + (i + 1) + "-" + nuevoJuego.getProvincias()[i]);
			}
		}
	}

	private static int elegiLaProvincias(Triviador nuevoJuego) {
		int provincia = 0;
		boolean salir = false;
		do {
			provincia = (ingresarEnteroConMensaje("elegi una provincia valida:"));
			if (siEligioBienElNumero(provincia, nuevoJuego)) {
				salir = true;
			}
		} while (!salir);

		return provincia;
	}

	private static void menuModosDeJuego() {
		mostrarMensaje("1. Modo de juego clasico");
		mostrarMensaje("2. Modo de juego proximidad");
		mostrarMensaje("3. Modo de juego aventura");
		mostrarMensaje("4. Salir");

	}

	private static void menuElegirLosColores() {
		mostrarMensaje("Selecciona un color para tu personaje");
		mostrarMensaje("1. color rojo");
		mostrarMensaje("2. color azul");
		mostrarMensaje("3. color amarrillo");
		mostrarMensaje("4. color verde");
		mostrarMensaje("5. color cyan");
		mostrarMensaje("6. color purpula");
		mostrarMensaje("7. color blanco");
		mostrarMensaje("8. color negro");

	}

	private static boolean siEligioBienElNumero(int provincia, Triviador nuevoJuego) {
		return provincia > 0 && provincia < nuevoJuego.getProvincias().length;
	}

	private static String ingresarStringConMensaje(String mensaje) {
		System.out.println(mensaje);
		return sc1.next().toLowerCase();
	}

	private static int ingresarEnteroConMensaje(String mensaje) {
		mostrarMensaje(mensaje);
		while (!sc.hasNextInt()) {
			mostrarMensaje(mensaje);
			mostrarMensajeError("Ingrese un numero entero");
			sc.nextLine();
		}
		return sc.nextInt();
	}

	// faltan agregar reglas
	public static void introduccionAlJuego() {
		int salir = 0;
		do {
			System.out.println("El juego consiste en un juego de trivia, en el cual participan dos jugadores");
			System.out.println("Inicia jugando el que este mas cercano al numero elegido al azar de los dos jugadores");
			System.out.println("posterior a eso eligiran 3 provincias a su gusto");
			System.out.println("Juegan respondiendo preguntas sobre argentina, para ganar provincias ");
			System.out.println(
					"Los jugadores luego de las preguntas de proximidad se enfrentaran entre si para la colonizacion de terrtorios");
			System.out.println("para ganar tuvieran que conquistar 17 provincias o ganar 3000 cantidad de puntos");
			salir = ingresarEnteroConMensaje("Precione '1' para salir");
		} while (salir != 1);
	}

	private static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarMensajeConSeparacion(String mensaje) {
		System.out.println("\n" + mensaje + "\n");
	}

	private static void mostrarMensajeError(String mensaje) {
		System.err.println(mensaje);
	}

	private static void salirDelJuego(Triviador juegoAventura) {
		mostrarMensaje("  ----------------------------- ");
		mostrarMensaje("| ¡Gracias por jugar Triviador! |");
		mostrarMensaje("  ----------------------------- ");
		mostrarMensajeConSeparacion("\nHecho por: Meza Martín, Cairo Facundo, Hodara Federico y Domínguez Mauricio.");
	}

	public static String centrarTexto() {
		return "				  ";
	}

	public static void mostrarTituloDelJuego() {
		mostrarMensaje("\n");
		mostrarMensaje(centrarTexto() + "///////////////////////////////////////////////////////////////");
		mostrarMensaje(centrarTexto() + "///////////////////////// TRIVIADOR ///////////////////////////");
		mostrarMensaje(centrarTexto() + "///////////////////////////////////////////////////////////////");
	}

	private static MenuPrincipal elegirOpcionDelMenuPrincipal() {
		int opcion = 0;
		boolean salir = false;
		do {
			System.out.println("\nElija una opción:\n1:Jugar 2:Estadisticas 3:Reglas 4:Salir");
			opcion = ingresarEnteroConMensaje("ingresa un entero valido");
			if (opcion < MenuPrincipal.values().length + 1 && opcion > 0) {
				salir = true;
			}
		} while (!salir);
		return MenuPrincipal.values()[opcion - 1];
	}

	private static ModosDeJuego elegirOpcionDelModoDeJuego() {
		int opcion = 0;
		boolean salir = false;
		do {
			menuModosDeJuego();
			opcion = ingresarEnteroConMensaje("ingresa un entero valido");
			if (opcion < 5 && opcion > 0) {
				salir = true;
			}
		} while (!salir);
		return ModosDeJuego.values()[opcion - 1];
	}

	private static ColoresDeInterfaz seleccionarColor() {

		int opcion = 0;
		boolean salir = false;
		do {
			menuElegirLosColores();
			opcion = ingresarEnteroConMensaje("ingresa un entero valido");
			if (opcion < 10 && opcion > 0) {
				salir = true;
			}
		} while (!salir);
		return ColoresDeInterfaz.values()[opcion - 1];
	}

	private static int elegirNumeroConLimite(int parametroInferior, int parametroSuperior) {
		int opcion = 0;
		boolean salir = false;

		do {

			opcion = ingresarEnteroConMensaje(
					"\n\nElegi un numero entre " + parametroInferior + " y " + parametroSuperior);

			if (opcion > parametroInferior && opcion < parametroSuperior) {
				salir = true;
				mostrarMensajeConSeparacion("El numero elegido esta dentro del parametro");
			} else {
				mostrarMensajeConSeparacion("El numero elegido fue incorrecto, ingrese uno correcto");
			}

		} while (!salir);

		return opcion;
	}

	private static void mostrarEstadisticas(Triviador juegoAventura) {
		mostrarMensajeConSeparacion("LA TABLA DE ESTADISTICA CON TODOS LOS SCORE JUGADOS");

		for (int i = 0; i < Triviador.getJugadoresTotales().length; i++) {
			if (Triviador.getJugadoresTotales()[i] != null) {
				mostrarMensajeConSeparacion(Triviador.getJugadoresTotales()[i].mostrarEstadisticas());
			}
		}

		mostrarMensajeConSeparacion("mostrar al jugador con la victora mas rapida");

		if (Triviador.mostrarLaVictoriaMasRapida() == null) {
			mostrarMensajeConSeparacion("Todavia no hay jugador registrado con por lo menos 1 victoria");
		} else {
			mostrarMensajeConSeparacion(Triviador.mostrarLaVictoriaMasRapida().mostrarEstadisticas());
			mostrarMensaje("El tiempo del jugador con la victoria mas rapida es de: "
					+ Triviador.mostrarLaVictoriaMasRapida().tiempoTotalJugado() + " segundos");
		}

		mostrarMensajeConSeparacion("mostrar la victora con mas provincias");

		if (Triviador.mostrarLaVictoriaMasRapida() == null) {
			mostrarMensajeConSeparacion("Todavia no hay jugador registrado con por lo menos 1 victoria");
		} else {
			mostrarMensajeConSeparacion(Triviador.mostrarLaVictoriaConMasProvincias().mostrarEstadisticas());
			mostrarMensaje("las provincias del jugador con la victoria de mayor provincias es: "
					+ Triviador.mostrarLaVictoriaConMasProvincias().contadorDeProvincias());
		}

		mostrarMensajeConSeparacion("mostrar la victoria con mas puntos");

		if (Triviador.mostrarLaVictoriaMasRapida() == null) {
			mostrarMensajeConSeparacion("Todavia no hay jugador registrado con por lo menos 1 victoria");
		} else {
			mostrarMensajeConSeparacion(Triviador.mostrarLaVictoriaConMasPuntos().mostrarEstadisticas());
			mostrarMensaje("el jugador con la victoria de mayor puntuacion tiene: "
					+ Triviador.mostrarLaVictoriaConMasPuntos().getPuntos() + " puntos");
		}

	}

}
