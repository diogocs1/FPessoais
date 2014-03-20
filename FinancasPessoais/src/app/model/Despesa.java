package app.model;

import javafx.scene.control.Dialogs;
import app.logica.Verifica;

public class Despesa {
	private int id;
	private Usuario pessoa;
	private String descricao;
	private String vencimento;
	private String status;
	private String prioridade;
	private double valor;

	public Despesa (int id,Usuario pessoa, String descricao, String vencimento, String prioridade,String status, double valor) throws IllegalArgumentException{
		setId(id);
		setPessoa(pessoa);
		setDescricao(descricao);
		setVencimento(vencimento);
		setPrioridade(prioridade);
		setStatus(status);
		setValor(valor);
	}
	public Despesa (Usuario pessoa, String descricao, String vencimento, String prioridade,String status, double valor) throws IllegalArgumentException{
		setPessoa(pessoa);
		setDescricao(descricao);
		setVencimento(vencimento);
		setPrioridade(prioridade);
		setStatus(status);
		setValor(valor);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setVencimento(String vencimento){
		try{
			if (Verifica.validaData(vencimento)){
				this.vencimento = vencimento;
			}
		}catch (IllegalArgumentException e){
			Dialogs.showErrorDialog(null, "Data inválida!");
		}
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) throws IllegalArgumentException{
		if (valor >= 0){
			this.valor = valor;
		}else{
			throw new IllegalArgumentException("Valor inválido!");
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getPessoa() {
		return pessoa;
	}

	public void setPessoa(Usuario pessoa) {
		this.pessoa = pessoa;
	}
	@Override
	public String toString() {
		return vencimento + " : "+ descricao;
	}
}