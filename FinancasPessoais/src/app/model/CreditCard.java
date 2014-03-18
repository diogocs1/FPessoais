package app.model;

import javax.swing.JOptionPane;

public class CreditCard {
	private Gasto gasto;
	private String operadora;
	private String numero;
	private String vencimento;
	private double limite;
	
	CreditCard (Gasto gasto, String op, String num, String venc, double limite){
		setGasto(gasto);
		this.setOperadora(op);
		this.setNumero(num);
		this.setVencimento(venc);
		this.setLimite(limite);
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		if (limite > 0)	this.limite = limite;
		else JOptionPane.showMessageDialog(null, "Valor inválido");
	}
	@Override
	public String toString() {
		return ("Cartão: " + this.operadora + "\n" + "Número: " + this.numero);
	}

	public Gasto getGasto() {
		return gasto;
	}

	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}
}
