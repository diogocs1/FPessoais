package app.model;

import javax.swing.JOptionPane;

public class Gasto {
	private int id;
	private Conta conta;
	private Usuario pessoa;
	private String titulo;
	private String descricao;
	private String vencimento;
	private String status;
	private int prioridade;
	private double valor;
	private CreditCard cartao;

	public Gasto (Conta conta, String titulo, String descricao, String vencimento, int prioridade, double valor){
		setPessoa(conta.getPessoa());
		setConta(conta);
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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