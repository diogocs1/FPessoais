package model;

import java.util.Date;

public class Conta {
	private int id;
	private Historico historico;
	private Usuario pessoa;
	private String agencia;
	private String tipo;
	private String conta;
	private String banco;
	private double saldo;
	
	public Conta(Usuario pessoa ,String banco, String agencia, String conta, String tipo) {
		setPessoa(pessoa);
		setBanco(banco);
		setAgencia(agencia);
		setConta(conta);
		setTipo(tipo);
		setHistorico(new Historico("Nova Conta", new Date(), "Nova conta criada"));
	}
	public Conta(int id,Usuario pessoa ,String banco, String agencia, String conta, String tipo) {
		setId(id);
		setPessoa(pessoa);
		setBanco(banco);
		setAgencia(agencia);
		setConta(conta);
		setTipo(tipo);
		setHistorico(new Historico("Nova Conta", new Date(), "Nova conta criada"));
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
	public Usuario getPessoa() {
		return pessoa;
	}
	public void setPessoa(Usuario pessoa) {
		this.pessoa = pessoa;
	}
	public Historico getHistorico() {
		return historico;
	}
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}