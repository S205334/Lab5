package model;

import java.util.List;

public class WordSet {
	private String word;
	private List<Pos> posizione;
	
	public WordSet(String word, List<Pos> posizione) {
		this.word = word;
		this.posizione = posizione;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Pos> getPosizione() {
		return posizione;
	}

	public void setPosizione(List<Pos> posizione) {
		this.posizione = posizione;
	}
	
	@Override
	public String toString() {
		return "[characters=" + word + ", positions=" + posizione + "]\n";
	}
}
