package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Griglia {

	private Map<Pos, String> griglia;
	private List<Pos> posizioni;
	private int N = 4;
	
	
	public Griglia() {
		super();
		this.griglia = new HashMap<>();
		this.posizioni = new ArrayList<>();
		
		for( int riga = 1 ; riga <= N; riga++ ) 
			for( int col = 1; col <= N; col++ ) 
				posizioni.add(new Pos(riga, col));
	}
	
	public List<Pos> getPosizioni() {
		return posizioni;
	}

	public String get(Pos p) {
		return griglia.get(p);
	}

	public void set(Pos p, String c) {
		if( griglia.get(p)==null )
			griglia.put(p, c) ;
		else
			throw new RuntimeException("Casella già occupata") ;
	}	
	
	
}
