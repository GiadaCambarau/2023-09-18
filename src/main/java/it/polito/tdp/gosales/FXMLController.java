package it.polito.tdp.gosales;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.gosales.model.Model;
import it.polito.tdp.gosales.model.Retailers;
import it.polito.tdp.gosales.model.Volume;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnRicorsione;

    @FXML
    private Button btnVolumi;

    @FXML
    private ComboBox<Integer> cmbAnno;

    @FXML
    private ComboBox<String> cmbNazione;
    
    @FXML
    private TextField txtN;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaVolumi(ActionEvent event) {
    	List<Volume> volumi = model.calcolaVolume();
    	
    	for (Volume v: volumi) {
    		txtResult.appendText(v+"\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String nazione = cmbNazione.getValue();
    	if (nazione.compareTo("")==0) {
    		txtResult.setText("Seleziona una nazione");
    		return;
    	}
    	int anno = cmbAnno.getValue();
    	if (anno == 0) {
    		txtResult.setText("Seleziona un anno");
    		return;
    	}
    	model.creaGrafo(nazione, anno);
    	txtResult.appendText("#Vertici: "+ model.getV()+"\n");
    	txtResult.appendText("#Archi: "+ model.getA()+"\n");

    }

    @FXML
    void doRicorsione(ActionEvent event) {
    	String input = txtN.getText();
    	if (input.compareTo("")==0) {
    		txtResult.setText("Inserisci un numero");
    		return;
    	}
    	int n =0;
    	try {
    		n = Integer.parseInt(input);
    	}catch(NumberFormatException e ) {
    		txtResult.setText("Inserisci un numero intero");
    		return;
    	}
    	if (n<2) {
    		txtResult.setText("Il numero deve essere maggiore o uguale a 2");
    		return;
    	}
    	
    }

    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicorsione != null : "fx:id=\"btnRicorsione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVolumi != null : "fx:id=\"btnVolumi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbNazione != null : "fx:id=\"cmbNazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbAnno.getItems().add(2015);
    	cmbAnno.getItems().add(2016);
    	cmbAnno.getItems().add(2017);
    	cmbAnno.getItems().add(2018);
    	cmbNazione.getItems().addAll(model.getNazioni());
    }

}
