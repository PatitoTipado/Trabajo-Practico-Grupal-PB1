package ar.edu.unlam.pb1.interfaz;

import ar.edu.unlam.pb1.dominio.Triviador;
import java.util.Random;
import java.util.Scanner;

public class InterfaceTriviador {

	private static Triviador nuevoJuego = new Triviador();
	private static Scanner sc = new Scanner(System.in);
	static Random r1 = new Random();

	public static void main(String args[]) {

		System.out.println("TRIVIADOR!\nBienvenido");
		pantallaInicio();
		elegirProvincias();

	}
//	public static void inicioConPregunta() {
//		
//	}

	public static void inicio() {
		int numJugador = 0;
		System.out.println("jugador " + numJugador + "elige un numero 1 o 2");
		int numero = r1.nextInt(2) + 1;
		int num1 = sc.nextInt();
		if (numero == num1) {
			System.out.println("Elegis primero");
		} else {
			System.out.println("Elegis segundo");
		}
		// deberia devolverte que jugador le toca
	}

	// faltan agregar reglas
	public static void reglas() {
		int salir;
		do {
			System.out.println("El juego consiste en...\nPrecione '1' para salir");
			salir = sc.nextInt();
		} while (salir != 1);
		pantallaInicio();
	}

	// desarrollar todos los metodos
	public static void pantallaInicio() {
		System.out.println("\nElija una opción:\n1:Jugar 2:Reglas 3:Salir");
		int opcion = sc.nextInt();
		switch (opcion) {
		case 1:
			inicio();
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
	}

	public static void elegirProvincias() {
		System.out.println("Elegi 3 de estas provincias: ");
		for (int i = 0; i < nuevoJuego.getProvincias().length; i++) {
			System.out.print(" " + (i + 1) + "-" + nuevoJuego.getProvincias()[i]);
		}
		int provincia = 0;
		for (int i = 1; i < 4; i++) {
			provincia = sc.nextInt();
			// if (metodo de si se puede agregar provincia) imprima:
			System.out.println("La provincia ha sido agregada: " + nuevoJuego.getProvincias()[(provincia - 1)]);
		}
	}
}
