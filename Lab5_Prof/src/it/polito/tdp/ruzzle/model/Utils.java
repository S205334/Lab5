package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.polito.tdp.ruzzle.dao.WordDAO;

public class Utils {

	/*
	 * Calcola i vicini data una posizione pos. Esclude le posizioni in
	 * currentPositions (per non ripassare sulle lettere già usate).
	 */
	public static List<Position> getNeighbours(Position pos, List<Position> currentPositions) {

		List<Position> neighbours = new ArrayList<Position>();

		// Se sono al primo livello della ricorsione aggiungi tutte le celle
		// della matrice
		if (pos.getX() == -1 && pos.getY() == -1) {
			for (int i = 0; i < Model.ruzzleMatrixDim; i++)
				for (int j = 0; j < Model.ruzzleMatrixDim; j++)
					neighbours.add(new Position(i, j));
			return neighbours;
		}

		// Altrimenti cerca tutti i vicini per la posizione specificata
		for (int i = pos.getX() - 1; i < pos.getX() + 1; i++)
			for (int j = pos.getY() - 1; j < pos.getY() + 1; j++)
				if (i >= 0 && j >= 0 && i < Model.ruzzleMatrixDim && j < Model.ruzzleMatrixDim) {
					Position p = new Position(i, j);
					if (!currentPositions.contains(p)) {
						neighbours.add(p);
					}
				}

		return neighbours;
	}

	/*
	 * Controlla se la sequenza è promettente
	 */
	public static boolean checkSequence(List<Character> word) {

		StringBuilder st = new StringBuilder();
		for (Character c : word) {
			st.append(c);
		}
		// System.out.println(st.toString());

		WordDAO wd = new WordDAO();

		// Controlla solo la stringa iniziale
		return wd.searchSequence(st.toString());
	}

	/*
	 * Controlla se la parola è presente nel dizionario
	 */
	public static boolean checkWord(List<Character> word) {

		StringBuilder st = new StringBuilder();
		for (Character c : word) {
			st.append(c);
		}
		// System.out.println(st.toString());

		WordDAO wd = new WordDAO();

		// Controlla l'intera parola
		return wd.searchWord(st.toString());
	}

	/*
	 * Funzione di Debug per la stampa della matrice.
	 */
	public static void printMatrix(char[][] ruzzleMatrix) {
		for (int i = 0; i < Model.ruzzleMatrixDim; i++) {
			for (int j = 0; j < Model.ruzzleMatrixDim; j++) {
				System.out.print(ruzzleMatrix[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	/*
	 * Funzione per la generazione di caratteri semplice
	 */
	public static char getSimpleRandomChar() {
		Random random = new Random();

		List<Character> notItalian = new ArrayList<Character>();
		notItalian.add(Character.valueOf('j'));
		notItalian.add(Character.valueOf('k'));
		notItalian.add(Character.valueOf('w'));
		notItalian.add(Character.valueOf('x'));
		notItalian.add(Character.valueOf('y'));

		char randomChar;

		do {
			randomChar = (char) (random.nextInt(26) + 'a');
		} while (notItalian.contains(Character.valueOf(randomChar)));

		return randomChar;
	}

	/*
	 * Funzione per la generazione di caratteri più complessa
	 */
	public static char getComplexRandomChar() {

		// Implemento il meccanismo della roulette wheel
		float sum = 0;
		Map<Character, Float> alphabet = getItalianAlphabet();

		// Sommo tutte le probabilità.
		for (Character c : alphabet.keySet()) {
			sum += alphabet.get(c);
		}

		// Genero un numero casuale tra 0 e sum
		Random random = new Random();
		float randomFloat = random.nextFloat() * sum;

		sum = 0;
		char randomChar = 'a';

		// Find the char that corresponds to the sum.
		for (Character c : alphabet.keySet()) {
			randomChar = c;
			if (sum < randomFloat)
				sum += alphabet.get(c);
			else
				break;
		}

		return randomChar;
	}

	/*
	 * Ogni lettera in italiano ha una frequenza diversa. Quelle più frequenti
	 * ha senso che abbiamo una probabilità maggiore di essere scelte.
	 */
	private static Map<Character, Float> getItalianAlphabet() {
		Map<Character, Float> alphabet = new HashMap<Character, Float>();

		alphabet.put(Character.valueOf('a'), (float) 11.74);
		alphabet.put(Character.valueOf('b'), (float) 0.92);
		alphabet.put(Character.valueOf('c'), (float) 4.50);
		alphabet.put(Character.valueOf('d'), (float) 3.7);
		alphabet.put(Character.valueOf('e'), (float) 11.7);
		alphabet.put(Character.valueOf('f'), (float) 0.95);
		alphabet.put(Character.valueOf('g'), (float) 1.64);
		alphabet.put(Character.valueOf('h'), (float) 1.54);
		alphabet.put(Character.valueOf('i'), (float) 11.28);
		alphabet.put(Character.valueOf('l'), (float) 6.51);
		alphabet.put(Character.valueOf('m'), (float) 2.51);
		alphabet.put(Character.valueOf('n'), (float) 6.88);
		alphabet.put(Character.valueOf('o'), (float) 9.83);
		alphabet.put(Character.valueOf('p'), (float) 3.05);
		alphabet.put(Character.valueOf('q'), (float) 0.51);
		alphabet.put(Character.valueOf('r'), (float) 6.37);
		alphabet.put(Character.valueOf('s'), (float) 4.98);
		alphabet.put(Character.valueOf('t'), (float) 5.62);
		alphabet.put(Character.valueOf('u'), (float) 3.01);
		alphabet.put(Character.valueOf('v'), (float) 2.10);
		alphabet.put(Character.valueOf('z'), (float) 0.49);

		return Collections.unmodifiableMap(alphabet);
	}
}
