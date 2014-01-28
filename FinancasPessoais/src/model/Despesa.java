package model;

import javax.swing.JOptionPane;

public class Despesa {
	private int id;
	private String titulo;
	private String descricao;
	private String vencimento;
	private int prioridade;
	private double valor;
	private CreditCard cartao;

	Despesa (String titulo, String descricao, String vencimento, int prioridade, double valor){
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setVencimento(vencimento);
		this.setPrioridade(prioridade);
		this.setValor(valor);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		if (prioridade >= 0 && prioridade < 4) this.prioridade = prioridade;
		else erro(1);
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		if (valor >= 0) this.valor = valor;
		else erro(2);
	}
	public void erro (int id) {
		JOptionPane.showMessageDialog(null, (id + ": Valor Inválido!"));
	}

	public CreditCard getCartao() {
		return cartao;
	}

	public void setCartao(CreditCard cartao) {
		this.cartao = cartao;
	}
}