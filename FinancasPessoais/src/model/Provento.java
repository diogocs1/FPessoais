package model;

import javax.swing.JOptionPane;

public class Provento {
	private String titulo;
	private double valor;
	private String tipo;

	Provento (String titulo,double valor, String tipo){
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
}