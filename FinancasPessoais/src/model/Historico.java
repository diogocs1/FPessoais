package model;

import java.util.Date;

public class Historico {
	private int id;
	private String titulo;
	private Date data;
	private String descricao;
	
	public Historico(String titulo, Date data, String descricao) {
		setTitulo(titulo);
		setData(data);
		setDescricao(descricao);
	}
	public Historico(int id, String titulo, Date dara, String descricao) {
		setId(id);
		setTitulo(titulo);
		setData(data);
		setDescricao(descricao);
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
