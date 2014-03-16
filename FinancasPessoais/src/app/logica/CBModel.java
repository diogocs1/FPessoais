package app.logica;

import java.util.ArrayList;

import javafx.scene.control.SingleSelectionModel;

public class CBModel extends SingleSelectionModel<String> {
	private ArrayList<String> model;

	@Override
	protected int getItemCount() {
		return model.size();
	}

	@Override
	protected String getModelItem(int index) {
		return model.get(index);
	}
	
	public void setModel (ArrayList<String> lista){
		model = lista;
	}

}
