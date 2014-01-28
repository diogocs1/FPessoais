package model;

public class Conta {
	private String agencia;
	private String tipo;
	private String conta;
	private String banco;
	private double saldo;
	
	Conta(String banco, String agencia, String conta, String tipo) {
		this.setBanco(banco);
		this.setAgencia(agencia);
		this.setConta(conta);
		this.setTipo(tipo);
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public double getSaldo() {
		return saldo;
	}

	public void depositar(double saldo) {
		this.saldo += saldo;
	}
}