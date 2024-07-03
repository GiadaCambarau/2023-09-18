package it.polito.tdp.gosales.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		model.creaGrafo("France", 2015);
		model.calcolaVolume();
		//model.calcolaPercorso(4);
	}

}
