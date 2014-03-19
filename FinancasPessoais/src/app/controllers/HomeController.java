package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Main;
import app.jdbc.DadosConta;
import app.jdbc.DadosDespesa;
import app.logica.Cadastro;
import app.logica.Calcula;
import app.logica.Normaliza;
import app.model.Acao;
import app.model.Conta;
import app.model.Despesa;
import app.observableModel.ContaModel;
import app.observableModel.DespesaModel;
import app.observableModel.HistoricoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	//Instância Principal
	private Main main;
	
	/*************************************
	 * Cadastro de contas
	 *************************************/
		// Painel superior direito:
		@FXML
		private Label saldoTotal;
		@FXML
		private Label debitoTotal;
		@FXML
		private Label saldoPrevisto;
		
		// Tab principal de contas
		@FXML
		private TableView<ContaModel> tabelaContas;
		@FXML
		private TableColumn<ContaModel, String> contaCol;
		@FXML
		private TableColumn<ContaModel, Double> saldoCol;
		private ObservableList<ContaModel> listaContas;
		@FXML
		private Button novaConta;
		@FXML
		private Button editaConta;
		@FXML
		private Button removerConta;
		// Janela de cadastro de contas
		private Stage novaJanelaConta;
		
		//Detalhes de contas
		@FXML
		private Label titularDt;
		@FXML
		private Label numeroDt;
		@FXML
		private Label bancoDt;
		@FXML
		private Label saldoDt;
		
		// Tabela de Histórico
		@FXML
		private TableView<HistoricoModel> tabelaHistorico;
		@FXML
		private TableColumn<HistoricoModel, String> hDataCol;
		@FXML
		private TableColumn<HistoricoModel, String> hDescricaoCol;
		private ObservableList<HistoricoModel> listaAcoes;
		
		//Opções de conta
		@FXML
		private Button btDepositar;
		@FXML
		private Button btSacar;
		@FXML
		private Pane entraValor;
		@FXML
		private TextField tfValor;
		
		/*************************************************
		 * Tela de despesas
		 *************************************************/
		private Stage novaJanelaDespesas;
		@FXML
		private TableView<DespesaModel> tabelaDespesas;
		@FXML
		private TableColumn<DespesaModel, String> colunaDescricao;
		@FXML
		private TableColumn<DespesaModel, String> colunaVencimento;
		@FXML
		private TableColumn<DespesaModel, String> colunaValor;
		// Botões
		@FXML
		private Button btNovaDespesa;
		@FXML
		private Button btRemoveDespesa;
		@FXML
		private Button btPagar;
		
	/*
	 * Initialize - INICIO
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Define os valores para cada coluna de Contas
		contaCol.setCellValueFactory(
				new PropertyValueFactory<ContaModel, String>("conta")
				);
		saldoCol.setCellValueFactory(
				new PropertyValueFactory<ContaModel, Double>("saldo")
				);
		// Colunas de histórico
		hDataCol.setCellValueFactory(
				new PropertyValueFactory<HistoricoModel, String>("data")
				);
		hDescricaoCol.setCellValueFactory(
				new PropertyValueFactory<HistoricoModel, String>("descricao")
				);
		// Colunas de despesas
		colunaDescricao.setCellValueFactory(
				new PropertyValueFactory<DespesaModel, String>("descricao")
				);
		colunaVencimento.setCellValueFactory(
				new PropertyValueFactory<DespesaModel, String>("vencimento")
				);
		colunaValor.setCellValueFactory(
				new PropertyValueFactory<DespesaModel, String>("valor")
				);

		// Atualiza painel superior
		atualizaSaldoTotal();
		atualizaDebitoTotal();
		atualizaSaldoPrevisto();
		/*************************************
		 * Cadastro de contas
		 *************************************/
		//Torna o campo valor invisível
		entraValor.setVisible(false);
		
			novaConta.setOnAction(new EventHandler<ActionEvent>(){
				public void handle (ActionEvent evt){
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CadastroConta.fxml"));
						AnchorPane novaConta = (AnchorPane) loader.load();
						CadastroContaController controller = loader.getController();
						controller.setMain(main);
						novaJanelaConta = new Stage();
						Scene cena = new Scene(novaConta);
						novaJanelaConta.setTitle("Nova Conta");
						novaJanelaConta.initModality(Modality.WINDOW_MODAL);
						novaJanelaConta.initOwner(main.getPrimaryStage());
						novaJanelaConta.setScene(cena);
						novaJanelaConta.show();
					} catch (IOException e) {
						Dialogs.showErrorDialog(null, "Não foi possível criar uma nova conta! \n \n" + e.getMessage());
					}
				}
			});
			//Editar conta
			editaConta.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle (ActionEvent evt){
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CadastroConta.fxml"));
						AnchorPane novaConta = (AnchorPane) loader.load();
						CadastroContaController controller = loader.getController();
						controller.setMain(main);
						Conta editar = tabelaContas.getSelectionModel().getSelectedItem().getContaObj();
						controller.editaConta(editar);
						novaJanelaConta = new Stage();
						Scene cena = new Scene(novaConta);
						novaJanelaConta.setTitle("Nova Conta");
						novaJanelaConta.initModality(Modality.WINDOW_MODAL);
						novaJanelaConta.initOwner(main.getPrimaryStage());
						novaJanelaConta.setScene(cena);
						novaJanelaConta.show();
					} catch (IOException e) {
						Dialogs.showErrorDialog(null, "Não foi possível editar esta conta! \n \n" + e.getMessage());
					}
				}
			});
			//Apaga Conta
			removerConta.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle (ActionEvent evt){
					DialogResponse i = Dialogs.showConfirmDialog(main.getPrimaryStage(), "Tem certeza de que deseja remover?");
					if (i == DialogResponse.YES){
						Cadastro.removeConta(
								tabelaContas.getSelectionModel().getSelectedItem().getContaObj()
						);
						tabelaContas.getSelectionModel().getSelectedItem().setContaObj(null);
						atualizaTabelaContas();
						atualizaSaldoPrevisto();
						atualizaSaldoTotal();
						tabelaHistorico.setItems(null);
					}
				}
			});
			// Identifica os cliques na tabela
			tabelaContas.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent arg0) {
					try{
						// Obtém a conta selecionada
						Conta contaAtual = tabelaContas.getSelectionModel().getSelectedItem().getContaObj();
						// Mostra os detalhes e atualiza a tabela do Histórico
						detalhesConta(contaAtual);
						atualizaTabelaHist(contaAtual);
					}catch (NullPointerException e){
						e.getMessage();
					}
				}
			});
			// Botão depositar
			btDepositar.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if ( !(entraValor.isVisible() ) ){
						entraValor.setVisible(true);
						tfValor.setText("");
					}else{
						try {
							Double valor = Normaliza.normalizaValor(tfValor.getText());
							Conta contaAtual = tabelaContas.getSelectionModel().getSelectedItem().getContaObj();
							atualizaTabelaContas();
							Cadastro.depositaValor(contaAtual, valor);
							entraValor.setVisible(false);
							atualizaSaldoTotal();
						} catch (NullPointerException e) {
							Dialogs.showErrorDialog(main.getPrimaryStage(), "Selecione uma conta e preencha o valor");
							entraValor.setVisible(false);
						} catch (SQLException e) {
							Dialogs.showErrorDialog(main.getPrimaryStage(), "Problema no banco de dados! \n \n"+"Erro: "+e.getSQLState());
						}
						atualizaTabelaContas();
						entraValor.setVisible(false);
					}
				}
			});
			// Botão sacar
			btSacar.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if ( !(entraValor.isVisible() ) ){
						entraValor.setVisible(true);
						tfValor.setText("");
					}else{
						try {
							Double valor = Normaliza.normalizaValor(tfValor.getText());
							Conta contaAtual = tabelaContas.getSelectionModel().getSelectedItem().getContaObj();
							Cadastro.sacaValor(contaAtual, valor);
							atualizaTabelaContas();
							entraValor.setVisible(false);
							atualizaSaldoTotal();
						} catch (SQLException e) {
							Dialogs.showErrorDialog(main.getPrimaryStage(), "Problema no banco de dados! \n \n" + e.getSQLState());
						} catch (IllegalArgumentException e){
							Dialogs.showErrorDialog(main.getPrimaryStage(), e.getMessage());
						} catch (NullPointerException e){
							Dialogs.showErrorDialog(main.getPrimaryStage(), "Selecione uma conta e preencha o valor");
							entraValor.setVisible(false);
						}
					}
				}
			});
			/*************************************
			 * Cadastro de contas - FIM
			 *************************************/
			/*************************************
			 * Cadastro de despesas - INICIO
			 *************************************/
			btNovaDespesa.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CadastroDespesa.fxml"));
						AnchorPane novaDespesa = (AnchorPane) loader.load();
						CadastroDespesaController controller = loader.getController();
						controller.setMain(main);
						novaJanelaDespesas = new Stage();
						Scene cena = new Scene(novaDespesa);
						novaJanelaDespesas.setTitle("Nova Despesa");
						novaJanelaDespesas.initModality(Modality.WINDOW_MODAL);
						novaJanelaDespesas.initOwner(main.getPrimaryStage());
						novaJanelaDespesas.setScene(cena);
						novaJanelaDespesas.show();
					} catch (IOException e) {
						Dialogs.showErrorDialog(main.getPrimaryStage(), "Problemas ao abrir cadastro de despesas! \n \n" + e.getMessage());
					}
				}
			});
			btRemoveDespesa.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					DialogResponse i = Dialogs.showConfirmDialog(main.getPrimaryStage(), "Tem certeza de que deseja remover?");
					if (i == DialogResponse.YES){
						Cadastro.removeDespesa(
								tabelaDespesas.getSelectionModel().getSelectedItem().getDespesa1()
						);
						tabelaDespesas.getSelectionModel().getSelectedItem().setDespesa1(null);
						atualizaSaldoTotal();
						atualizaTabelaDespesas();
						atualizaDebitoTotal();
						atualizaSaldoPrevisto();
					}
				}
			});
	}
	/*
	 * Initialize - FIM
	 */
	/*************************************
	 * Cadastro de contas
	 *  - Métodos de tabelas
	 *************************************/
	
	public void detalhesConta (Conta conta){
		titularDt.setText(conta.getPessoa().getNome());
		numeroDt.setText(conta.getConta());
		bancoDt.setText(conta.getBanco());
		saldoDt.setText(String.valueOf(conta.getSaldo()));
	}
	public void atualizaTabelaContas (){
		try {
			ArrayList<Conta> contas = new DadosConta().getContas();
			// Inicializa o atributo ObservableList
			listaContas = FXCollections.observableArrayList();
			// Lista apenas as contas do usuário atual
			for (Conta conta : contas) {
				if (conta.getPessoa().getId() == main.getUser().getId()){
					listaContas.add(new ContaModel(conta));
				}
			}
			if (listaContas.isEmpty()){
				tabelaContas.setItems(null);
			}else{
				tabelaContas.setItems(listaContas);
			}
		} catch (SQLException e) {
			Dialogs.showErrorDialog(null, "Problemas no banco de dados! \n \n" + e.getMessage());
		} catch (NullPointerException e){
			e.printStackTrace();
		}
	}
	public void atualizaTabelaHist (Conta conta) {
		// Obtém o histórico de ações
		ArrayList<Acao> acoes = conta.getHistorico();
		// Inicializa o ObservableList
		listaAcoes = FXCollections.observableArrayList();
		tabelaHistorico.setItems(listaAcoes);
		
		for (Acao acao: acoes){
			listaAcoes.add(new HistoricoModel(acao));
		}
		
	}
	/*************************************
	 * Cadastro de contas - FIM
	 *************************************/
	public void atualizaSaldoTotal (){
		saldoTotal.setText(Calcula.somaSaldo());
	}
	public void atualizaDebitoTotal (){
		debitoTotal.setText(Calcula.somaDespesas());
	}
	public void atualizaSaldoPrevisto (){
		saldoPrevisto.setText(Calcula.saldoPrevisto());
	}
	/*************************************
	 * Cadastro de Despesas - INICIO
	 *************************************/
	public void atualizaTabelaDespesas(){
		try {
			ArrayList<Despesa> despesas = new DadosDespesa().getDespesas();
			ObservableList<DespesaModel> listaDespesas = FXCollections.observableArrayList();
			for (Despesa despesa:despesas){
				if (despesa.getPessoa().getId() == main.getUser().getId()){
					listaDespesas.add(new DespesaModel(despesa));
				}
			}
			if (listaDespesas.isEmpty()){
				tabelaDespesas.setItems(null);
			}else{
				tabelaDespesas.setItems(listaDespesas);
			}
		} catch (SQLException e) {
			Dialogs.showErrorDialog(main.getPrimaryStage(), "Problema ao carregar a tabela de despesas! \n \n" + e.getSQLState());
		}

	}
	
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public Stage getNovaJanelaConta (){
		return this.novaJanelaConta;
	}
	public Stage getNovaJanelaDespesas() {
		return novaJanelaDespesas;
	}
	public void setNovaJanelaDespesas(Stage novaJanelaDespesas) {
		this.novaJanelaDespesas = novaJanelaDespesas;
	}
}
