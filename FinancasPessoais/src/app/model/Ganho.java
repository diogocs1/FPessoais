package app.model;

import javax.swing.JOptionPane;

public class Ganho {
	private Conta conta;
	private Usuario pessoa;
	private String titulo;
	private double valor;
	private String tipo;

	Ganho (Conta conta ,String titulo,double valor, String tipo){
		setPessoa(conta.getPessoa());
		setConta(conta);
		this.setTitulo(titulo);
		this.setValor(valor);
		this.setTipo(tipo);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		if (valor > 0) this.valor = valor;
		else JOptionPane.showMessageDialog(null, "Valor inválido");
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Usuario getPessoa() {
		return pessoa;
	}

	public void setPessoa(Usuario pessoa) {
		this.pessoa = pessoa;
	}
}