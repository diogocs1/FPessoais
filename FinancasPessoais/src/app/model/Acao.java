package app.model;

import java.util.Date;

public class Acao {
	private int id;
	private Date data;
	private String descricao;
	
	public Acao(Date data, String descricao) {
		setData(data);
		setDescricao(descricao);
	}
	public Acao(int id, Date dara, String descricao) {
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
