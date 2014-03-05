package app.controllers;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Conta;

public class HomeController {
	// Por fazer...
	
	private Main main;
	
	@FXML
	private ListView<Conta> lista1;
	
	@FXML
	public void initialize () {
		//Por fazer...
		lista1.setContextMenu(null);
	}

	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	
}
