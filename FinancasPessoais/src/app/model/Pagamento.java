package app.model;

import app.logica.Verifica;

public class Pagamento {
	private int id;
	private Despesa despesa;
	private Conta conta;
	private double valor;
	private String data;
	
	public Pagamento(Conta conta,Despesa despesa, double valor, String data) throws IllegalArgumentException{
		setConta(conta);
		setValor(valor);
		setData(data);
		setDespesa(despesa);
	}
	public Pagamento(Despesa despesa, double valor, String data) throws IllegalArgumentException{
		setValor(valor);
		setData(data);
		setDespesa(despesa);
	}


	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) throws IllegalArgumentException{
		if (Verifica.validaData(data)){
			this.data = data;
		}
	}
	public Despesa getDespesa() {
		return despesa;
	}
	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
