package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Word {
	private List<Character> characters = new ArrayList<Character>();
	private List<Position> positions = new ArrayList<Position>();

	public List<Character> getCharacters() {
		return characters;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public String getString() {
		StringBuilder sb = new StringBuilder();
		for (Character c : characters) {
			sb.append(c);
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "[characters=" + characters + ", positions=" + positions + "]\n";
	}

}
