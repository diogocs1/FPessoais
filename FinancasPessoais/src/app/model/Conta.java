package app.model;

import java.util.ArrayList;
import java.util.Date;

public class Conta {
	private int id;
	private ArrayList<Acao> historico = new ArrayList<Acao>();
	private Usuario pessoa;
	private String tipo;
	private String conta;
	private String banco;
	private double saldo;
	
	public Conta(Usuario pessoa ,String banco, String conta, String tipo, double saldo) {
		setPessoa(pessoa);
		setBanco(banco);
		setConta(conta);
		setTipo(tipo);
		depositar(saldo);
		addAcao(new Acao(new Date(), "Nova conta criada"));
	}
	public Conta(int id,Usuario pessoa ,String banco, String conta, String tipo, double saldo) {
		setId(id);
		setPessoa(pessoa);
		setBanco(banco);
		setConta(conta);
		setTipo(tipo);
		depositar(saldo);
		addAcao(new Acao(new Date(), "Nova conta criada"));
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
	public ArrayList<Acao> getHistorico() {
		return historico;
	}
	public void addAcao(Acao acao) {
		this.historico.add(acao);
	}
	public void setHistorico (ArrayList<Acao> hist){
		this.historico = hist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ID: "+ id + " Num: " + conta;
	}
	public String toHistorico (){
		return "ID: "+id+" Num: "+conta+" Saldo: "+saldo;
	}
	
}