package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

	public static boolean simpleRandom = false;

	public final static int ruzzleMatrixDim = 4;
	static List<Word> results = new ArrayList<Word>();
	char[][] ruzzleMatrix = new char[ruzzleMatrixDim][ruzzleMatrixDim];

	public List<Word> getSolutions() {

		return results;
	}

	public char[][] generateRandomMatrix() {

		randomRuzzleMatrix();
		Utils.printMatrix(ruzzleMatrix);

		return ruzzleMatrix;
	}

	/*
	 * Genera matrice in modo casuale
	 */
	public void randomRuzzleMatrix() {

		for (int i = 0; i < ruzzleMatrixDim; i++) {
			for (int j = 0; j < ruzzleMatrixDim; j++) {

				// Scegli quale metedo utilizzare per generare un carattere
				// Random
				if (simpleRandom) {
					ruzzleMatrix[i][j] = Utils.getSimpleRandomChar();
				} else {
					ruzzleMatrix[i][j] = Utils.getComplexRandomChar();
				}
			}
		}
	}

	/*
	 * Wrapper sulla funzione ricorsiva
	 */
	public List<Word> solveRuzzle() {

		// Delete previous solutions
		results.clear();

		// Initialization
		List<Position> currentPositions = new ArrayList<Position>();
		List<Character> currentWord = new ArrayList<Character>();

		recursiveRuzzleSolver(currentWord, currentPositions, new Position(-1, -1), 0);

		System.out.println(results.toString());

		return results;
	}

	/*
	 * Funzione ricorsiva
	 */
	private void recursiveRuzzleSolver(List<Character> currentWord, List<Position> currentPositions, Position pos,
			int level) {

		// If needed call the recursive function
		if (level > 2 && Utils.checkWord(currentWord)) {

			StringBuilder st = new StringBuilder();
			for (Character c : currentWord) {
				st.append(c);
			}
			System.out.println("Added: " + st.toString());

			Word word = new Word();
			word.getCharacters().addAll(currentWord);
			word.getPositions().addAll(currentPositions);
			results.add(word);
		}

		// Condizione di terminazione sul livello dell'albero della ricorsione
		if (level == ruzzleMatrixDim * ruzzleMatrixDim) {
			return;
		}

		// Ottieni i vicini per la posizione corrente
		List<Position> neighbours = Utils.getNeighbours(pos, currentPositions);

		// System.out.println(neighbours);

		for (Position p : neighbours) {

			// Add char
			currentWord.add(Character.valueOf(ruzzleMatrix[p.getX()][p.getY()]));
			currentPositions.add(p);

			if (Utils.checkSequence(currentWord)) {
				recursiveRuzzleSolver(currentWord, currentPositions, p, level + 1);
			}

			// Backtrack: remove char
			currentWord.remove(Character.valueOf(ruzzleMatrix[p.getX()][p.getY()]));
			currentPositions.remove(p);
		}

	}

}
