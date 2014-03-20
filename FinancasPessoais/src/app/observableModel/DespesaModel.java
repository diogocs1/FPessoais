package app.observableModel;

import app.model.Despesa;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DespesaModel {
	private final SimpleStringProperty descricao;
	private final SimpleStringProperty vencimento;
	private final SimpleDoubleProperty valor;
	private Despesa despesa1;
	
	public DespesaModel(Despesa despesa) {
		setDespesa1(despesa);
		descricao = new SimpleStringProperty(despesa.getDescricao() +" " +despesa.getStatus());
		vencimento = new SimpleStringProperty(despesa.getVencimento());
		valor = new SimpleDoubleProperty(despesa.getValor());
	}

	public Despesa getDespesa1() {
		return despesa1;
	}
	public void setDespesa1(Despesa despesa1) {
		this.despesa1 = despesa1;
	}
	public double getValor() {
		return valor.get();
	}
	public String getVencimento() {
		return vencimento.get();
	}
	public String getDescricao() {
		return descricao.get();
	}
}
