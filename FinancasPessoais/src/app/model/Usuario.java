package app.model;


public class Usuario {
	private int id;
	private String nome;
	private String senha;
	private String nascimento;
	
	
	public Usuario (String nome, String senha, String nascimento) {
		setNome(nome);
		setSenha(senha);
		setNascimento(nascimento);
	}
	public Usuario (int id, String nome, String senha, String nascimento) {
		setId(id);
		setNome(nome);
		setSenha(senha);
		setNascimento(nascimento);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	@Override
	public String toString() {
		return this.nome;
	}
}
