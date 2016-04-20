package model;

import java.util.ArrayList;
import java.util.List;
import db.DictionaryDAO;

public class RuzzleModel {
	
	private int n = 16;
	private Griglia gr = new Griglia();;
	private List<WordSet> solutions;
	
	public RuzzleModel() {}

	public List<WordSet> resolveRuzzle() {
		
		solutions = new ArrayList<WordSet>();
		
		for(int k = 0; k < n; k++) {
			Pos s = gr.getPosizioni().get(k);
			List<Pos> path = new ArrayList<Pos>();
			path.add(s);
			cercaParole( gr.get(s), s, path);
		}
		
		return solutions;
	}
	
	public List<WordSet> getSolutions() {
		return solutions;
	}
	
	public List<String> getGriglia() {
		List<String> caratteri = new ArrayList<>();	
		String alpha = "abcdefghilmnopqrstuvz";
		
		for(int i = 0; i < n; i++) {
			int rand = (int) (Math.random()*21);
			Pos p = gr.getPosizioni().get(i);
			String c = String.valueOf(alpha.charAt(rand));
			
			caratteri.add(c);
			gr.set(p, c);
		}
		
		return caratteri;
	}	
	
	
	public void cercaParole(String w, Pos p, List<Pos> path) {

		Pos s;
		
		if(isAWord(w) && w.length() > 1 && !solutions.equals(w)) {
			solutions.add(new WordSet(w, path));
			System.out.println(solutions.get(solutions.size()-1).toString());
			
		}
			// alto
			if (p.getRiga() > 1) {
				s = new Pos (p.getRiga()-1, p.getCol());
				w += gr.get(s);
				if(isCorrect(w) && !path.equals(s)) {
					path.add(s);
					cercaParole( w, s, path);
					path.remove(s);
				}
				
				w = w.substring(0, w.length()-1);
			}
			
			// basso
			if (p.getRiga() < 4) {
				s = new Pos (p.getRiga()+1, p.getCol());
				w += gr.get(s);
				if(isCorrect(w) && !path.equals(s)) {
					path.add(s);
					cercaParole( w, s, path);
					path.remove(s);
				}
				
				w = w.substring(0, w.length()-1);
			}
			
			// destra
			if (p.getCol() < 4) {
				s = new Pos (p.getRiga(), p.getCol()+1);
				w += gr.get(s);
				if(isCorrect(w) && !path.equals(s)) {
					path.add(s);
					cercaParole( w, s, path);
					path.remove(s);
				}
				
				w = w.substring(0, w.length()-1);
			}
			
			// sinistra
			if (p.getCol() > 1) {
				s = new Pos (p.getRiga(), p.getCol()-1);
				w += gr.get(s);
				if(isCorrect(w) && !path.equals(s)) {
					path.add(s);
					cercaParole( w, s, path);
					path.remove(s);
				}
				
				w = w.substring(0, w.length()-1);
			}
			
			// obliquo alto sx
			if (p.getCol() > 1 && p.getRiga() > 1 ) {
				s = new Pos (p.getRiga()-1, p.getCol()-1);
				w += gr.get(s);
				if(isCorrect(w) && !path.equals(s)) {
					path.add(s);
					cercaParole( w, s, path);
					path.remove(s);
				}
				
				w = w.substring(0, w.length()-1);
			}
			
			// obliquo alto dx
			if (p.getCol() < 4 && p.getRiga() > 1 ) {
				s = new Pos (p.getRiga()-1, p.getCol()+1);
				w += gr.get(s);
				if(isCorrect(w) && !path.equals(s)) {
					path.add(s);
					cercaParole( w, s, path);
					path.remove(s);
				}
				
				w = w.substring(0, w.length()-1);
			}
			
			// obliquo basso dx
			if (p.getCol() < 4 && p.getRiga() < 4 ) {
				s = new Pos (p.getRiga()+1, p.getCol()+1);
				w += gr.get(s);
				if(isCorrect(w) && !path.equals(s)) {
					path.add(s);
					cercaParole( w, s, path);
					path.remove(s);
				}
				
				w = w.substring(0, w.length()-1);
			}
			
			// obliquo basso sx
			if (p.getCol() > 1 && p.getRiga() < 4 ) {
				s = new Pos (p.getRiga()+1, p.getCol()-1);
				w += gr.get(s);
				if(isCorrect(w) && !path.equals(s)) {
					path.add(s);
					cercaParole( w, s, path);
					path.remove(s);
				}
				
				w = w.substring(0, w.length()-1);
			}
		}		
	
	
	public boolean isAWord(String w) {
		DictionaryDAO dao = new DictionaryDAO();
		return dao.isAWord(w);	
	}
	
	public boolean isCorrect(String w) {
		DictionaryDAO dao = new DictionaryDAO();
		return dao.isCorrect(w);	
	}
	
	//public static void main(String[] args) {
	//	GeneraModel model = new GeneraModel();
	//	model.creaGriglia();
	//}


}
