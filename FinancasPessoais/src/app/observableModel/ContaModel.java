package app.observableModel;

import app.model.Conta;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ContaModel {
	private final SimpleStringProperty conta;
	private final SimpleDoubleProperty saldo;
	private Conta contaObj;
	
	public ContaModel(Conta conta) {
		setContaObj(conta);
		this.conta = new SimpleStringProperty(conta.toString());
		this.saldo = new SimpleDoubleProperty(conta.getSaldo());
	}
	
	
	public String getConta() {
		return conta.get();
	}
	public void setConta (Conta conta){
		this.conta.set(conta.toString());
	}
	public double getSaldo() {
		return saldo.get();
	}
	public void setSaldo (Conta conta){
		this.saldo.set(conta.getSaldo());
	}
	public Conta getContaObj() {
		return contaObj;
	}
	public void setContaObj(Conta contaObj) {
		this.contaObj = contaObj;
	}
}
