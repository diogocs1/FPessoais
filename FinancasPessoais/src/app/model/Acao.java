package app.model;

import java.util.Date;


public class Acao {
	private int id;
	private String data;
	private String descricao;
	
	public Acao(Date data, String descricao) {
		setData(data);
		setDescricao(descricao);
	}
	public Acao(int id, Date data, String descricao) {
		setId(id);
		setData(data);
		setDescricao(descricao);
	}
	public Acao(int id, String data, String descricao) {
		setId(id);
		setData(data);
		setDescricao(descricao);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data.toString();
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
