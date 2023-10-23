package ar.edu.unlam.pb1.interfaz;

import ar.edu.unlam.pb1.dominio.Triviador;
import ar.edu.unlam.pb1.auxiliar.*;
import java.util.Random;
import java.util.Scanner;

public class InterfaceTriviador {
	private static Scanner sc = new Scanner(System.in);
	private static Scanner sc1 = new Scanner(System.in);
	private static Random r1 = new Random();

	public static void main(String args[]) {

		System.out.println("TRIVIADOR!\nBienvenido");
		pantallaInicio();
		// esta mal planteado por que
		// osino luego de salir del menu seguira funcionando esto
		// elegirProvincias();
	}

	// desarrollar todos los metodos
	public static void pantallaInicio() {
		int opcion = 0;
		// System.out.println("¿cuantos jugadores quieren jugar?");
		Triviador nuevoJuego = new Triviador();
		do {
			System.out.println("\nElija una opción:\n1:Jugar 2:Reglas 3:Salir");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				iniciarUnNuevoJuego(nuevoJuego);
				break;
			case 2:
				introduccionAlJuego();
				break;
			case 3:
				System.out.println("Chau");
				break;
			default:
				System.out.println("ELEGÍ BIEN");
			}
		} while (opcion != 3);
	}

	public static void iniciarUnNuevoJuego(Triviador nuevoJuego) {
		// cargamos jugadores para poder referirse
		cargarJugadores(nuevoJuego);
		int jugador = seleccionarElJugadorQueLeToca();
		// cargamos dos veces provincias
		jugador = cargamosLaProvinciasPorPrimeraVez(jugador, nuevoJuego);
		preguntasProximidad(jugador, nuevoJuego);
		// asignacion de provincia aletoria al ganador, que luego no podria ser peleada
		// hasta conseguir todo el territorio
		// bucle en el cual vos eligas
		// metodo para eliminar si pierde las provincias del otro.
		// asignacion de provincia aletoria al ganador, que luego si podria ser peleada
		// ver como sumar los puntos
		// metodo para comparar cuantas provincias tienen en el array
		// si llega a meter mas de la mitad (12) de las provincias ya esta
		// si llega a jugar el juego rapido por puntos, dsp de una cierta cantidad de
		// turnos si quedan empatados, se define con una pregunta de proximidad

		// si llegan los dos a perder, ver quien esta mas proximo
		// 20 yo 19 otro 35
		// 1925 1926 == -1 1923 2
		// si abs if (abs -1 != -1 ){} (abs 1!=1)
		// ver que onda con el tiempo es posible y sencillo dentro de todo
		// si el valor abs ==, otra pregunta
		// que mas se acerque a 0
		// le toca al otro jugador
		// // se verifica si esta bien
		// se elige una provincia que no tengan
		// si gana
	}

	private static int cargamosLaProvinciasPorPrimeraVez(int jugador, Triviador nuevoJuego) {
		for (int j = 0; j < nuevoJuego.getJugadores().length; j++) {
			elegirProvincias(jugador, nuevoJuego);
			// solo funcionara con 2 jugadores este metodo capaz verlo con ordenamiento
			jugador = leTocaAlOtroJugador(jugador); // revisar metodo
		}
		return jugador;
	}

	private static void preguntasProximidad(int jugador, Triviador nuevoJuego) {
		// empezar con preguntas de proximidad
		int buscarProvincia = 0;
		mostrarMensajeConSeparacion("inician las preguntas de proximidad");
		// elige una pregunta random del array
		int random = (int) (Math.random() * nuevoJuego.preguntasProximidad().length);
		mostrarMensajeConSeparacion(nuevoJuego.preguntasProximidad()[random]);
		int elegir = ingresarEnteroConMensaje("elegi un numero");
		// elige una pregunta el otro jugador
		jugador = leTocaAlOtroJugador(jugador);
		mostrarMensajeConSeparacion("Le toca a: " + nuevoJuego.getJugadores()[jugador]);
		int elegir1 = ingresarEnteroConMensaje("elegi un numero");
		if (elegir == nuevoJuego.respuestaProximidad()[random]) {
			mostrarMensajeConSeparacion("jugador 0 tiene la respuesta correcta");
			if (!ganaronLosDosProximidad(elegir, elegir1, jugador, nuevoJuego)) {
				// asignar la provincia si gano uno solo
				do {
					buscarProvincia = (int) (Math.random() * nuevoJuego.getProvincias().length);
				} while (nuevoJuego.asignarProvincias(buscarProvincia, jugador));
			}
		} else if (elegir1 == nuevoJuego.respuestaProximidad()[random]) {
			mostrarMensajeConSeparacion("jugador 1 tiene la respuesta correcta");
			// si no ganaron todos asignar la provincia a quien gano
			if (!ganaronLosDosProximidad(elegir, elegir1, jugador, nuevoJuego)) {
				// asignar la provincia si gano uno solo
				do {
					buscarProvincia = (int) (Math.random() * nuevoJuego.getProvincias().length);
				} while (nuevoJuego.asignarProvincias(buscarProvincia, jugador));
			}
		} else {
			// metodo que diga quien estuvo mas cerca
			mostrarMensajeConSeparacion("No acerto la pregunta nadie");
		}
		// preguntar por que ocurrio dos veces esto, es por la "recursividad", o por que
		// tiene que terminar una ejecucion en distintos como hilos
		for (int i = 0; i < nuevoJuego.getJugadores().length; i++) {
			for (int j = 0; j < nuevoJuego.getJugadores()[i].getProvinciasEnPoder().length; j++) {
				System.out.println(nuevoJuego.getJugadores()[i].getProvinciasEnPoder()[j]);
			}
		}

	}

	private static boolean ganaronLosDosProximidad(int elegir, int elegir1, int jugador, Triviador nuevoJuego) {
		boolean ganaronLosDos = (elegir == elegir1);
		if (ganaronLosDos) {
			System.out.println("Ganaron todos, otra ronda de mas preguntas");
			preguntasProximidad(jugador, nuevoJuego);
		}
		return ganaronLosDos;
	}

//	private static void ganaronLosDosPregunta(int elegir, int elegir1, int jugador, Triviador nuevoJuego) {
//		if (elegir == elegir1) {
//			System.out.println("Ganaron todos, otra ronda de mas preguntas");
//			preguntasProximidad(jugador, nuevoJuego);
//		}
//	}

	private static void cargarJugadores(Triviador nuevoJuego) {
		for (int i = 0; i < nuevoJuego.getJugadores().length; i++) {
			System.out.println("Estamos cargando al jugador: " + i);
			String nombre = ingresarStringConMensaje("Ingrese el nombre del jugador");
			Jugador jugador = new Jugador(nombre);
			if (nuevoJuego.agregarJugador(jugador)) {
				mostrarMensajeConSeparacion("se pudo cargar el jugador");
			}
		}
	}

//	public static void inicioConPregunta() {
//		mostrarMensajeConSeparacion("Inicia el juego");
//		int numeroRandom = (int) (Math.random() * 3);
//		// mostramos la pregunta
//		System.out.println(Pregunta.getPregunta()[numeroRandom]);
//		for (int i = 0; i < Pregunta.getOpciones().length; i++) {
//			if (i % 2 == 0) {
//				System.out.println();
//			} else {
//				System.out.print(Pregunta.getOpciones()[i]);
//			}
//		}
//		Jugador jugador = nuevoJuego.getJugadores()[0];
//		System.out.println("inicia el jugador " + jugador);
//	}

	public static int seleccionarElJugadorQueLeToca() {
		int jugadorArray = 0;
		System.out.println("jugador " + jugadorArray);
		// +1 dejaria pendiente para mas adelante el 1 ya que es una v.prueba y me
		// molestaria para el tema de referencia de array
		// podriamos darle sentido a la salida
		int numero = r1.nextInt(2);
		int num1 = ingresarEnteroConMensaje("elige un numero 0 o 1");
		if (numero == num1) {
			System.out.println("Elegis primero jugador: " + jugadorArray);
		} else {
			//
			jugadorArray++; // hardcodeado
			System.out.println("Elegis primero jugador: " + jugadorArray);
		}
		// deberia devolverte que jugador le toca
		return jugadorArray;
	}

	// faltan agregar reglas
	public static void introduccionAlJuego() {
		int salir = 0;
		do {
			System.out.println("El juego consiste en un juego de trivia, en el cual participan dos jugadores");
			System.out.println("Al inicio juegas por cara (0) o cruz (1), para ver quien elige primero");
			System.out.println("posterior a eso eligiran 3 provincias a su gusto");
			System.out.println("Juegan respondiendo preguntas sobre argentina, para ganar provincias ");
			System.out.println(
					"Los jugadores luego de las preguntas de proximidad se enfrentaran entre si para la colonizacion de terrtorios");
			// TODO
			System.out.println("para ganar tuvieran que conquistar 13 provincias o ganar cierta cantidad de puntos");
			System.out.println("Precione '1' para salir");
			salir = sc.nextInt();
		} while (salir != 1);
		pantallaInicio();
	}

	private static void elegirProvincias(int alQueLeTocaJugar, Triviador nuevoJuego) {
		System.out.println("le toca jugar al jugar: " + alQueLeTocaJugar);
		System.out.println("Elegi 1 de estas provincias: ");
		for (int i = 0; i < nuevoJuego.getProvincias().length; i++) {
			System.out.print(" " + (i + 1) + "-" + nuevoJuego.getProvincias()[i]);
		}
		int provincia = 0;
		for (int i = 1; i < 4; i++) {
			do {
				provincia = ingresarEnteroConMensaje("elegi una provincia valida:");
			} while (!siEligioBienElNumero(provincia, nuevoJuego));
			// if (metodo de si se puede agregar provincia) imprima:
			if (nuevoJuego.asignarProvincias(provincia, alQueLeTocaJugar)) {
				System.out.println("La provincia ha sido agregada: " + nuevoJuego.getProvincias()[(provincia - 1)]);
			} else {
				System.out.println(
						"La provincia: " + nuevoJuego.getProvincias()[(provincia - 1)] + " no se ha podido ingresar");
			}
		}

	}

	private static boolean siEligioBienElNumero(int provincia, Triviador nuevoJuego) {
		return provincia > 0 && provincia < nuevoJuego.getProvincias().length;
	}

	private static int leTocaAlOtroJugador(int juega) {
		if (juega == 0) {
			juega = 1;
		} else {
			juega = 0;
		}
		// ramdon =3
		// almacenar los numeros que ya salieron
		// mostrar mensjae de ayuda o tips mientras juegan
		// otro filtrar
		return juega;
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

	private static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarMensajeConSeparacion(String mensaje) {
		System.out.println("\n" + mensaje + "\n");
	}

	private static void mostrarMensajeError(String mensaje) {
		System.err.println(mensaje);
	}
}
