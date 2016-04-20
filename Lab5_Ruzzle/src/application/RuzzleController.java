/**
 * Sample Skeleton for 'Ruzzle.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import model.Pos;
import model.RuzzleModel;
import model.WordSet;

public class RuzzleController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listParole"
    private ListView<String> listParole; // Value injected by FXMLLoader

    @FXML // fx:id="btGenera"
    private Button btGenera; // Value injected by FXMLLoader
    
    @FXML // fx:id="prgBar"
    private ProgressBar prgBar; // Value injected by FXMLLoader

    @FXML // fx:id="L11"
    private Label L11; // Value injected by FXMLLoader

    @FXML // fx:id="L2"
    private Label L2; // Value injected by FXMLLoader

    @FXML // fx:id="L3"
    private Label L3; // Value injected by FXMLLoader

    @FXML // fx:id="L4"
    private Label L4; // Value injected by FXMLLoader

    @FXML // fx:id="L5"
    private Label L5; // Value injected by FXMLLoader

    @FXML // fx:id="L6"
    private Label L6; // Value injected by FXMLLoader

    @FXML // fx:id="L7"
    private Label L7; // Value injected by FXMLLoader

    @FXML // fx:id="L8"
    private Label L8; // Value injected by FXMLLoader

    @FXML // fx:id="L9"
    private Label L9; // Value injected by FXMLLoader

    @FXML // fx:id="L1"
    private Label L1; // Value injected by FXMLLoader

    @FXML // fx:id="L10"
    private Label L10; // Value injected by FXMLLoader

    @FXML // fx:id="L13"
    private Label L13; // Value injected by FXMLLoader

    @FXML // fx:id="L14"
    private Label L14; // Value injected by FXMLLoader

    @FXML // fx:id="L15"
    private Label L15; // Value injected by FXMLLoader

    @FXML // fx:id="L12"
    private Label L12; // Value injected by FXMLLoader

    @FXML // fx:id="L16"
    private Label L16; // Value injected by FXMLLoader

	private List<Label> labels = new ArrayList<>();
	private RuzzleModel model = new RuzzleModel();
	private List<WordSet> solutions;

    @FXML
    void doGenera(ActionEvent event) {
    	
    	listParole.getItems().clear();
    	
    	// genero la griglia
    	List<String> lettere = model.getGriglia();
    	
    	for(int i = 0; i<16 ; i++)
    		labels.get(i).setText(lettere.get(i).toUpperCase());
    	
    	// avvio la ricerca
    	Task<List<WordSet>> task = new Task<List<WordSet>>() {
    		
			@Override
			protected List<WordSet> call() throws Exception {
				
		    	updateProgress(-1, -1);
		    	btGenera.setDisable(true);
		    	solutions = model.resolveRuzzle();
		    	updateProgress(1, 1);
		    		    			    	
		    	return solutions;
			}
    	} ;
    	
    	task.setOnSucceeded( new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent event) {
				btGenera.setDisable(false);
				
				for(WordSet w : solutions)
		    		listParole.getItems().add(w.getWord());
				
			}
		} );
    	
    	prgBar.progressProperty().bind(task.progressProperty());
       	
    	Thread th = new Thread(task) ;
    	th.setDaemon(true);
    	th.start();
    } 
     
    @FXML
    void doView(MouseEvent event)  {
    	
    	int index = listParole.getSelectionModel().getSelectedIndex();
    	
    	for(int riga = 0; riga < 4; riga++)
    		for(int col = 0; col < 4; col++)
    			if (solutions.get(index).getPosizione().contains(new Pos(riga+1,col+1)))
					labels.get((riga*4+col)).setStyle("-fx-background-color: red;");
    			else
    				labels.get((riga*4+col)).setStyle("-fx-background-color: green;");
    }
    		
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert listParole != null : "fx:id=\"listParole\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert btGenera != null : "fx:id=\"btGenera\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L11 != null : "fx:id=\"L11\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L2 != null : "fx:id=\"L2\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L3 != null : "fx:id=\"L3\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L4 != null : "fx:id=\"L4\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L5 != null : "fx:id=\"L5\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L6 != null : "fx:id=\"L6\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L7 != null : "fx:id=\"L7\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L8 != null : "fx:id=\"L8\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L9 != null : "fx:id=\"L9\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L1 != null : "fx:id=\"L1\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L10 != null : "fx:id=\"L10\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L13 != null : "fx:id=\"L13\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L14 != null : "fx:id=\"L14\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L15 != null : "fx:id=\"L15\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L12 != null : "fx:id=\"L12\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert L16 != null : "fx:id=\"L16\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert prgBar != null : "fx:id=\"prgBar\" was not injected: check your FXML file 'Ruzzle.fxml'.";

        // inserisco labels in una lista
        labels.add(L1);
        labels.add(L2);
        labels.add(L3);
        labels.add(L4);
        labels.add(L5);
        labels.add(L6);
        labels.add(L7);
        labels.add(L8);
        labels.add(L9);
        labels.add(L10);
        labels.add(L11);
        labels.add(L12);
        labels.add(L13);
        labels.add(L14);
        labels.add(L15);
        labels.add(L16);
        
        
    }
}


