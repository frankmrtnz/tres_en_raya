import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TresEnRaya {

	private static String JUGADOR = "jugador";
	private static String CPU = "cpu";
	private static ArrayList<Integer> posicionesDJugador = new ArrayList<Integer>();
	private static ArrayList<Integer> posicionesDCpu = new ArrayList<Integer>();

	public static void main(String[] args) {

		char[][] tableroDJuego = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' },
				{ ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

		pintarTableroDJuego(tableroDJuego);

		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Introduza una posición (1-9)");
			int jugadorPosicion = scanner.nextInt();

			while (posicionesDJugador.contains(jugadorPosicion) || posicionesDCpu.contains(jugadorPosicion)) {
				System.out.println("Posición ocupada! Introduza otra posición");
				jugadorPosicion = scanner.nextInt();
			}

			colocarPieza(tableroDJuego, jugadorPosicion, JUGADOR);

			String resultado = comprobarGanador();
			if (resultado.length() > 0) {
				System.out.println(resultado);
				break;
			}

			Random rand = new Random();
			int cpuPosicion = rand.nextInt(9) + 1;
			while (posicionesDJugador.contains(cpuPosicion) || posicionesDCpu.contains(cpuPosicion)) {
				cpuPosicion = rand.nextInt(9) + 1;
			}
			colocarPieza(tableroDJuego, cpuPosicion, CPU);

			pintarTableroDJuego(tableroDJuego);

			resultado = comprobarGanador();
			if (resultado.length() > 0) {
				System.out.println(resultado);
				break;
			}

		}

	}

	public static String comprobarGanador() {
		List filaArriba = Arrays.asList(1, 2, 3);
		List filaMedio = Arrays.asList(4, 5, 6);
		List filaAbajo = Arrays.asList(7, 8, 9);
		List columnaIzquierda = Arrays.asList(1, 4, 7);
		List columnaMedio = Arrays.asList(2, 5, 8);
		List columnaDerecha = Arrays.asList(3, 6, 9);
		List cruzada1 = Arrays.asList(1, 5, 9);
		List cruzada2 = Arrays.asList(7, 5, 3);

		List<List> ganando = new ArrayList<List>();
		ganando.add(filaArriba);
		ganando.add(filaMedio);
		ganando.add(filaAbajo);
		ganando.add(columnaIzquierda);
		ganando.add(columnaMedio);
		ganando.add(columnaDerecha);
		ganando.add(cruzada1);
		ganando.add(cruzada2);

		for (List list : ganando) {
			if (posicionesDJugador.containsAll(list)) {
				return "Enhorabuena, has ganado!";
			}
			if (posicionesDCpu.containsAll(list)) {
				return "La CPU ganó! Lo siento :(";
			}
			if (posicionesDJugador.size() + posicionesDCpu.size() == 9) {
				return "EMPATE!";
			}
		}

		return "";
	}

	public static void pintarTableroDJuego(char[][] tableroDJuego) {
		for (char[] fila : tableroDJuego) {
			for (char c : fila) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void colocarPieza(char[][] tableroDJuego, int posicion, String usuario) {
		char symbol = 'X';

		if (usuario.equals("jugador")) {
			symbol = 'X';
			posicionesDJugador.add(posicion);
		}
		if (usuario.equals("cpu")) {
			symbol = 'O';
			posicionesDCpu.add(posicion);
		}

		switch (posicion) {
		case 1:
			tableroDJuego[0][0] = symbol;
			break;
		case 2:
			tableroDJuego[0][2] = symbol;
			break;
		case 3:
			tableroDJuego[0][4] = symbol;
			break;
		case 4:
			tableroDJuego[2][0] = symbol;
			break;
		case 5:
			tableroDJuego[2][2] = symbol;
			break;
		case 6:
			tableroDJuego[2][4] = symbol;
			break;
		case 7:
			tableroDJuego[4][0] = symbol;
			break;
		case 8:
			tableroDJuego[4][2] = symbol;
			break;
		case 9:
			tableroDJuego[4][4] = symbol;
			break;
		default:
			break;
		}
	}

}
