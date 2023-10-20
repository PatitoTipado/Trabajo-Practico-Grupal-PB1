package ar.edu.unlam.pb1.interfaz;

import ar.edu.unlam.pb1.dominio.Triviador;
import ar.edu.unlam.pb1.auxiliar.*;
import java.util.Random;
import java.util.Scanner;

public class InterfaceTriviador {

	private static Triviador nuevoJuego = new Triviador();
	private static Scanner sc = new Scanner(System.in);
	private static Scanner sc1 = new Scanner(System.in);
	static Random r1 = new Random();

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
		do {
			System.out.println("\nElija una opción:\n1:Jugar 2:Reglas 3:Salir");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				iniciarUnNuevoJuego();
				break;
			case 2:
				reglas();
				break;
			case 3:
				System.out.println("Chau");
				break;
			default:
				System.out.println("ELEGÍ BIEN");
			}
		} while (opcion != 3);
	}

	public static void iniciarUnNuevoJuego() {
		// cargamos jugadores para poder referirse
		cargarJugadores();
		int jugador = seleccionarElJugadorQueLeToca();
		// cargamos dos veces provincias
		jugador = cargamosLaProvinciasPorPrimeraVez(jugador);
		preguntasProximidad(jugador);
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
		//
	}

	private static int cargamosLaProvinciasPorPrimeraVez(int jugador) {
		for (int j = 0; j < nuevoJuego.getJugadores().length; j++) {
			elegirProvincias(jugador);
			// solo funcionara con 2 jugadores este metodo capaz verlo con ordenamiento
			jugador = leTocaAlOtroJugador(jugador); // revisar metodo
		}
		return jugador;
	}

	private static void preguntasProximidad(int jugador) {
		// empezar con preguntas de proximidad
		mostrarMensajeConSeparacion("inician las preguntas de proximidad");
		// elige una pregunta random del array
		int random = (int) (Math.random() * nuevoJuego.preguntasProximidad().length);
		mostrarMensajeConSeparacion(nuevoJuego.preguntasProximidad()[random]);
		int elegir = ingresarEnteroConMensaje("elegi un numero");
		// elige una pregunta el otro jugador
		jugador = leTocaAlOtroJugador(jugador);
		int elegir1 = ingresarEnteroConMensaje("elegi un numero");
		if (elegir == nuevoJuego.respuestaProximidad()[random]) {
			mostrarMensajeConSeparacion("jugador 0 tiene la respuesta correcta");
			ganaronLosDos(elegir, elegir1, jugador);
			// asignar la provincia si gano uno solo
		} else if (elegir1 == nuevoJuego.respuestaProximidad()[random]) {
			mostrarMensajeConSeparacion("jugador 1 tiene la respuesta correcta");
			ganaronLosDos(elegir, elegir1, jugador);
			// asignar la provincia si gano uno solo
		} else {
			mostrarMensajeConSeparacion("No acerto la pregunta nadie");
		}
	}

	private static void ganaronLosDos(int elegir, int elegir1, int jugador) {
		if (elegir == elegir1) {
			System.out.println("Ganaron los dos ronda de mas preguntas");
			preguntasProximidad(jugador);
		}
	}

	private static void cargarJugadores() {
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
	public static void reglas() {
		int salir = 0;
		do {
			System.out.println("El juego consiste en...\nPrecione '1' para salir");
			salir = sc.nextInt();
		} while (salir != 1);
		pantallaInicio();
	}

	private static void elegirProvincias(int alQueLeTocaJugar) {
		System.out.println("le toca jugar al jugar: " + alQueLeTocaJugar);
		System.out.println("Elegi 1 de estas provincias: ");
		for (int i = 0; i < nuevoJuego.getProvincias().length; i++) {
			System.out.print(" " + (i + 1) + "-" + nuevoJuego.getProvincias()[i]);
		}
		int provincia = 0;
		for (int i = 1; i < 4; i++) {
			do {
				provincia = ingresarEnteroConMensaje("elegi una provincia valida:");
			} while (!siEligioBienElNumero(provincia));
			// if (metodo de si se puede agregar provincia) imprima:
			if (nuevoJuego.asignarProvincias(provincia, alQueLeTocaJugar)) {
				System.out.println("La provincia ha sido agregada: " + nuevoJuego.getProvincias()[(provincia - 1)]);
			} else {
				System.out.println(
						"La provincia: " + nuevoJuego.getProvincias()[(provincia - 1)] + " no se ha podido ingresar");
			}
		}

	}

	private static boolean siEligioBienElNumero(int provincia) {
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
