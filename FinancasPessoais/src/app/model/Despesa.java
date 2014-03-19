package app.model;

public class Despesa {
	private int id;
	private Conta conta;
	private Usuario pessoa;
	private String descricao;
	private String vencimento;
	private String status;
	private String prioridade;
	private double valor;

	public Despesa() {
	}
	public Despesa (Usuario pessoa, String descricao, String vencimento, String prioridade,String status, double valor) throws IllegalArgumentException{
		setPessoa(pessoa);
		setPessoa(conta.getPessoa());
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

	public void setVencimento(String vencimento) throws IllegalArgumentException{
		String[] formataVencimento = vencimento.split("/");
		int[] data = new int[3];
		for (int i = 0; i < formataVencimento.length; i++){
			data[i] = Integer.parseInt(formataVencimento[i]);
		}
		if (data[0] <= 31 && data[1] <= 12 && data[2] > 2000){
			if (data[1] == 2 && data[0] > 29){
				throw new IllegalArgumentException("Data Inválida");
			}else if (data[0] <= 30 && (data[1] % 2 == 0 && data[1] < 7 || data[1] % 2 != 0 && data[1] > 8) ){
				this.vencimento = vencimento;
			}else if (data[0] <= 31 && ! (data[1] % 2 == 0 && data[1] < 7 || data[1] % 2 != 0 && data[1] > 8) ){
				this.vencimento = vencimento;
			}else{
				throw new IllegalArgumentException("Data Inválida");
			}
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