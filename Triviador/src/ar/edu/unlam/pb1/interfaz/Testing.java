package ar.edu.unlam.pb1.interfaz;

import java.util.*;
import ar.edu.unlam.pb1.dominio.*;

public class Testing {
	private static String[] interfazGrafica = new String[10];
	private static final int POSICION_INSERTADA = 4;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\t\t\t  *  " + "\t\t\t  *  ");
		System.out.println("\t\t\t  *  " + "\t\t\t  *  ");
		System.out.println("\t\t\t  *  " + "\t\t\t  *  ");
		System.out.println("\t\t\t*****" + "\t\t\t*****");
		System.out.println("\t\t\t  *  " + "\t\t\t  *  ");

		String mensaje = "show messaje";
		String rojo = "\u001B[31m";
		String verde = "\u001B[32m";
		String amarillo = "\u001B[33m";
		String resetearColor = "\"\u001B[0m\""; // this con escapeo

		// escapeo java, para que la comilla no te lo tome como unicode, si no como lo
		// que viene a continuacion \"\"

		System.out.println(rojo + "Este texto es rojo" + resetearColor);
		System.out.println(verde + "Este texto es verde" + resetearColor);
		System.out.println(amarillo + "Este texto es amarillo" + resetearColor);

		System.out.println();

		System.out.println(mensaje);

		mensaje = String.valueOf(verde) + " show";
		Personajes rombs = new Personajes();
		String rombo = rombs.personajeRombo();
		String espada = rombs.personajeEspada();

		System.out.println(rombo + "\n" + espada);
		for (int k = 0; k < 5; k++) {
			System.out.print("Ingresa el tamaño del rombo (debe ser un número impar): ");
			int num = scanner.nextInt();

			if (num % 2 == 0) {
				System.out.println("El número ingresado no es impar. Por favor, ingresa un número impar.");
				// si es numero impar aumentar num
			}

			// para un triangulo
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num - i - 1; j++) {
					System.out.print(rojo + " ");
				}
				for (int j = 0; j < 2 * i + 1; j++) {
					System.out.print(verde + "*");
				}
				System.out.println();
			}

			// esta es la parte inferior del rombo
			for (int i = num - 2; i >= 0; i--) {
				for (int j = 0; j < num - i - 1; j++) {
					System.out.print(amarillo + " ");
				}
				for (int j = 0; j < 2 * i + 1; j++) {
					System.out.print(verde + "*");
				}
				System.out.println();
			}

		}
		scanner.close();
	}

	public static void imprimirArray(String queImprimis) {
		for (int i = 0; i < interfazGrafica(queImprimis).length; i++) {
			System.out.println("\t\t\t" + interfazGrafica(queImprimis)[i]);
		}
	}

	public static String[] interfazGrafica(String provinciaMostrar) {
		// llenar el array
		llenarElArrayDeAsteriscos();
		// 25 es la mitad del array quiero insertar una palabra alli
		insertarPalabraEnLaInterfaz(provinciaMostrar);
		// y eliminar las puntas hasta que la cantidad sea igual al de la palabra
		extraerLasPuntasDelArray(provinciaMostrar);

		return interfazGrafica;
	}

	private static void extraerLasPuntasDelArray(String provinciaMostrar) {
		int indice = 1;
		while (indice < (provinciaMostrar.length() + 1)) {
			if (indice % 2 == 0) {
				interfazGrafica[POSICION_INSERTADA] = interfazGrafica[POSICION_INSERTADA].substring(1);
			} else {
				interfazGrafica[POSICION_INSERTADA] = interfazGrafica[POSICION_INSERTADA].substring(0,
						(interfazGrafica[POSICION_INSERTADA].length() - 1));
			}
			indice++;
		}
	}

	private static void insertarPalabraEnLaInterfaz(String provincia) {
		// agarramos la palabra en una variable auxiliar
		String auxiliar = interfazGrafica[POSICION_INSERTADA];
		// posicion de insercion de la palabra en cuestion
		Integer posicionDeInsercionDePalabra = 25;
		// dividimos la palabra en dos partes
		String parteInicial = auxiliar.substring(0, posicionDeInsercionDePalabra);
		String parteFinal = auxiliar.substring(posicionDeInsercionDePalabra);
		// devolvemos el resultado de el junte de la cadena total
		interfazGrafica[POSICION_INSERTADA] = parteInicial + provincia + parteFinal;
	}

	private static void llenarElArrayDeAsteriscos() {
		for (int i = 0; i < interfazGrafica.length; i++) {
			interfazGrafica[i] = "**************************************************";
		}
	}
}
