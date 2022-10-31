package pack;

import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public class Tombola {

	static boolean ambo = false;
	static boolean terna = false;
	static boolean quanterna = false;
	static boolean cinquina = false;

	public static void main(String[] args) {
		/*
		 * Creazione di UML-Activity Diagram-Flowchart del gioco della tombola Dopo aver
		 * creato la prima parte, programmare il gioco(traccia libera)
		 */
		Random random = new Random();
		
		System.out.println("Quanti giocatori vogliono partecipare(1-5): ");
		int NumGiocatori = new Scanner(System.in).nextInt();
		if (NumGiocatori < 1 || NumGiocatori > 5) {
			System.out.println("(1-5)!!!!!!!!!!!!!!!!!!!!!!");
			System.exit(-1);// se sei stupido, esci dal gioco :)
		}

		String[] NomiGiocatori = new String[NumGiocatori];

		for (int i = 0; i <= NumGiocatori - 1; i++) {// assegna nomi ai gicoatori
			System.out.println("inserisci il nome del giocatore" + (i + 1) + " : ");
			NomiGiocatori[i] = new Scanner(System.in).next();
		}

		int[][] M1 = null;// [[Numeri casuali cartella1], [Numeri casuali cartella2] ....
		int[][] M2 = null;
		int[][] M3 = null;
		int[][] M4 = null;
		int[][] M5 = null;
		int[] M1Contatore = null;// [cartella1,cartella 2,...
		int[] M2Contatore = null;
		int[] M3Contatore = null;
		int[] M4Contatore = null;
		int[] M5Contatore = null;

		for (int i = 0; i < NumGiocatori; i++) { // fa scegliere quante cartelle prendere
			System.out.println("scegli una quantità di cartelle tra 1 e 5: ");
			int N = new Scanner(System.in).nextInt();

			// Prima Rompi coglioni e ci secca fare lo switch

			if (!(N >= 1 && N <= 5)) { // verifica quante cartelle scegli
				System.out.println("Numero cartelle invalido");
				i--;
			} else if (i == 0) {// genera cartelle per il primo giocatore
				System.out.println("Primo giocatore ha scelto <" + N + "> cartelle");
				M1 = GeneraCartella(N);
				M1Contatore = new int[N];

			} else if (i == 1) {
				System.out.println("Secondo giocatore ha scelto <" + N + "> cartelle");
				M2 = GeneraCartella(N);
				M2Contatore = new int[N];

			} else if (i == 2) {
				System.out.println("Secondo giocatore ha scelto <" + N + "> cartelle");
				M3 = GeneraCartella(N);
				M3Contatore = new int[N];

			} else if (i == 3) {
				System.out.println("Secondo giocatore ha scelto <" + N + "> cartelle");
				M4 = GeneraCartella(N);
				M4Contatore = new int[N];

			} else {
				System.out.println("Secondo giocatore ha scelto <" + N + "> cartelle");
				M5 = GeneraCartella(N);
				M5Contatore = new int[N];

			}
		}
		int[] listaUsciti = new int[99];
		int var = 0;
		int ContatoreUscite = 0;

		boolean Tombola = false;
		while (!Tombola) {// for infinito
			var = GeneraNumero(listaUsciti);// restituisce un numero 1-99
			System.out.println("Numero dalla Tombolo è:" + var);

			listaUsciti[ContatoreUscite] = var;
			ContatoreUscite++;

			switch (NumGiocatori) {
			// Verifica se qualcuno ha il numero nella cartella

			case 1:
				M1Contatore = VerificaVincete(var, M1, M1Contatore);
				break;
			case 2:
				M1Contatore = VerificaVincete(var, M1, M1Contatore);
				M2Contatore = VerificaVincete(var, M2, M2Contatore);
				break;
			case 3:
				M1Contatore = VerificaVincete(var, M1, M1Contatore);
				M2Contatore = VerificaVincete(var, M2, M2Contatore);
				M3Contatore = VerificaVincete(var, M3, M3Contatore);
				break;
			case 4:
				M1Contatore = VerificaVincete(var, M1, M1Contatore);
				M2Contatore = VerificaVincete(var, M2, M2Contatore);
				M3Contatore = VerificaVincete(var, M3, M3Contatore);
				M4Contatore = VerificaVincete(var, M4, M4Contatore);
				break;
			case 5:
				M1Contatore = VerificaVincete(var, M1, M1Contatore);
				M2Contatore = VerificaVincete(var, M2, M2Contatore);
				M3Contatore = VerificaVincete(var, M3, M3Contatore);
				M4Contatore = VerificaVincete(var, M4, M4Contatore);
				M5Contatore = VerificaVincete(var, M5, M5Contatore);
				break;

			}

			//

			for (int i = 0; i <= NumGiocatori - 1; i++) {
				switch (i) {// gicoatore 1

				case 0:
					Tombola = VerificaGiocatore(M1Contatore, NomiGiocatori[i]);
					break;

				case 1:
					Tombola = VerificaGiocatore(M2Contatore, NomiGiocatori[i]);
					break;

				case 2:
					Tombola = VerificaGiocatore(M3Contatore, NomiGiocatori[i]);
					break;

				case 3:
					Tombola = VerificaGiocatore(M4Contatore, NomiGiocatori[i]);
					break;

				case 4:
					Tombola = VerificaGiocatore(M5Contatore, NomiGiocatori[i]);
					break;

				}

				if (Tombola)
					break;
			}

		}
		System.out.println("Numeri estratti " + ContatoreUscite);
		System.out.println("Gioco Finito!");
	}

	public static boolean VerificaGiocatore(int[] contatore, String nomi) {

		boolean Tombola = false;

		for (int g : contatore) {
			switch (g) {
			case 2:
				if (!ambo) {
					System.out.println(nomi + " Ha un cartella con Ambo");
					ambo = true;
				}
				break;
			case 3:
				if (!terna) {
					System.out.println(nomi + " Ha un cartella con terna");
					terna = true;
				}
				break;
			case 4:
				if (!quanterna) {
					System.out.println(nomi + " Ha un cartella con quanterna");
					quanterna = true;
				}
				break;
			case 5:
				if (!cinquina) {
					System.out.println(nomi + " Ha un cartella con cinquina");
					cinquina = true;
				}
				break;
			case 10:
				if (!Tombola) {
					System.out.println(nomi + " Ha fatto Tombola!!!!!!!!!!!!!!");
					Tombola = true;
				}
				break;
			}
		}
		return Tombola;

	}

	public static int[] VerificaVincete(int Num, int[][] m, int[] cont) {// NrTombola, Mcartelle,contatoreVincete
		for (int i = 0; i < m.length; i++) {// cartella
			for (int j = 0; j < 10; j++) {
				if (m[i][j] == Num) {
					cont[i] += 1;
					break;
				}
			}
		}
		return cont;

	}

	public static int GeneraNumero(int[] lista) {
		Random random = new Random();
		int casuale = random.nextInt(1, 100);
		boolean presente = false;
		while (!presente) {// genera un numero che non è uscito prima
			for (int i : lista) {
				if (i == casuale) {
					presente = true;
					break;
				}
			}
			if (!presente) {
				return casuale;
			} else {
				casuale = random.nextInt(1, 100);
				presente = false;
			}

		}
		return casuale;
	}

	public static int[][] GeneraCartella(int n) {
		Random random = new Random();
		int lunghezza = 10;
		boolean presente = false;
		int casuale = 0;
		int[][] m = new int[n][lunghezza];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < lunghezza; j++) {// 3
				casuale = random.nextInt(12) + 1;
				while (!presente) {

					for (int k = 0; k < lunghezza; k++) {// ricerca di un valore ugual all'interno della cartella
						if (casuale == m[i][k]) {
							presente = true;
							break;

						}

					}
					if (presente == false) {// aggiunge il numero se non è presente
						m[i][j] = casuale;

						break;// esci nel while
					} else {// presente==true
						casuale = random.nextInt(12) + 1;
						presente = false;
					}
				}
				presente = false;
			}
		}
		return m;

	}
}
