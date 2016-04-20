package it.polito.tdp.ruzzle.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.ruzzle.model.Model;
import it.polito.tdp.ruzzle.model.Position;
import it.polito.tdp.ruzzle.model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RuzzleController {

	Model model = new Model();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	Label errorLbl;

	List<Label> labels = new ArrayList<Label>();

	@FXML
	private Label lbl2;

	@FXML
	private Label lbl0;

	@FXML
	private Label lbl1;

	@FXML
	private Label lbl3;

	@FXML
	private Label lbl4;

	@FXML
	private Label lbl5;

	@FXML
	private Label lbl6;

	@FXML
	private Label lbl7;

	@FXML
	private Label lbl8;

	@FXML
	private Label lbl9;

	@FXML
	private Label lbl10;

	@FXML
	private Label lbl11;

	@FXML
	private Label lbl12;

	@FXML
	private Label lbl13;

	@FXML
	private Label lbl14;

	@FXML
	private Label lbl15;

	@FXML
	private Button btnRandomRuzzle;

	@FXML
	private ListView<String> listView;

	@FXML
	void initialize() {
		assert lbl2 != null : "fx:id=\"lbl2\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl0 != null : "fx:id=\"lbl0\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl1 != null : "fx:id=\"lbl1\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl3 != null : "fx:id=\"lbl3\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl4 != null : "fx:id=\"lbl4\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl5 != null : "fx:id=\"lbl5\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl6 != null : "fx:id=\"lbl6\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl7 != null : "fx:id=\"lbl7\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl8 != null : "fx:id=\"lbl8\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl9 != null : "fx:id=\"lbl9\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl10 != null : "fx:id=\"lbl10\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl11 != null : "fx:id=\"lbl11\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl12 != null : "fx:id=\"lbl12\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl13 != null : "fx:id=\"lbl13\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl14 != null : "fx:id=\"lbl14\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert lbl15 != null : "fx:id=\"lbl15\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert errorLbl != null : "fx:id=\"errorLbl\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert btnRandomRuzzle != null : "fx:id=\"btnRandomRuzzle\" was not injected: check your FXML file 'Ruzzle.fxml'.";
		assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'Ruzzle.fxml'.";

		labels.add(lbl0);
		labels.add(lbl1);
		labels.add(lbl2);
		labels.add(lbl3);
		labels.add(lbl4);
		labels.add(lbl5);
		labels.add(lbl6);
		labels.add(lbl7);
		labels.add(lbl8);
		labels.add(lbl9);
		labels.add(lbl10);
		labels.add(lbl11);
		labels.add(lbl12);
		labels.add(lbl13);
		labels.add(lbl14);
		labels.add(lbl15);
		
		for (Label l : labels)
			l.setStyle("-fx-background-color: green; -fx-label-padding: 10px;"
					+ "-fx-font-size: 18px; -fx-text-fill: white; -fx-background-radius: 10px");
	}

	@FXML
	void doGenerateRandomRuzzle(ActionEvent event) {

		// Crea una matrice Model.ruzzleMatrixDim x Model.ruzzleMatrixDim
		char[][] matrix = model.generateRandomMatrix();

		// Update dell'interfaccia grafica
		for (int i = 0; i < Model.ruzzleMatrixDim; i++) {
			for (int j = 0; j < Model.ruzzleMatrixDim; j++) {
				labels.get(i * Model.ruzzleMatrixDim + j).setText(String.valueOf(matrix[i][j]).toUpperCase());
			}
		}

		// Reset colore label interfaccia grafica
		for (Label l : labels)
			l.setStyle("-fx-background-color: green; -fx-label-padding: 10px;"
					+ "-fx-font-size: 18px; -fx-text-fill: white; -fx-background-radius: 10px");
		
		// Svuota la lista da visualizzare.
		listView.getItems().clear();

		try {
			errorLbl.setText("Sto risolvendo ruzzle");
			// Risolvi Ruzzle
			model.solveRuzzle();
			errorLbl.setText("Finito");

		} catch (RuntimeException e) {
			errorLbl.setText("Errore nella connessione al database");
		}

		// Ottieni la lista delle parole
		List<Word> solutions = model.getSolutions();

		for (Word w : solutions) {
			listView.getItems().add(w.getString());
		}
	}

	@FXML
	void doShow(ActionEvent event) {

		int index = listView.getSelectionModel().getSelectedIndex();

		if (index >= 0) {

			Word word = model.getSolutions().get(index);
			List<Position> positions = word.getPositions();

			for (Label l : labels)
				l.setStyle("-fx-background-color: green; -fx-label-padding: 10px;"
						+ "-fx-font-size: 18px; -fx-text-fill: white; -fx-background-radius: 10px");

			for (Position p : positions) {
				labels.get(p.getX() * Model.ruzzleMatrixDim + p.getY())
				.setStyle("-fx-background-color: red; -fx-label-padding: 10px;"
						+ "-fx-font-size: 18px; -fx-text-fill: white; -fx-background-radius: 10px");
			}

			errorLbl.setText("");

		} else {
			
			if (listView.getItems().size() == 0) {
				errorLbl.setText("Genera una nuova soluzione!");
			} else {
				errorLbl.setText("Seleziona una parola dalla lista!");
			}
		}
	}

}
