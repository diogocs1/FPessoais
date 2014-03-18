package app.observableModel;

import app.model.Acao;
import javafx.beans.property.SimpleStringProperty;

public class HistoricoModel {
	private final SimpleStringProperty data;
	private final SimpleStringProperty descricao;
	private final Acao acao;
	
	public HistoricoModel (Acao acao){
		this.acao = acao;
		data = new SimpleStringProperty(acao.getData());
		descricao = new SimpleStringProperty(acao.getDescricao());
	}

	
	public SimpleStringProperty getData() {
		return data;
	}
	public SimpleStringProperty getDescricao() {
		return descricao;
	}
	public Acao getAcao() {
		return acao;
	}
}
