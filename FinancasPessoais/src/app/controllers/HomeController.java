package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Main;
import app.jdbc.DadosConta;
import app.logica.Cadastro;
import app.model.Acao;
import app.model.Conta;
import app.observableModel.ContaModel;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	//Instância Principal
	private Main main;
	
	/*************************************
	 * Cadastro de contas
	 *************************************/
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
		


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Coloca os itens na tabela
		atualizaTabelaContas();
		
		/*************************************
		 * Cadastro de contas
		 *************************************/
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
						atualizaTabelaContas();
					}
				}
			});
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
	}
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
			// Define os valores para cada coluna
			contaCol.setCellValueFactory(
					new PropertyValueFactory<ContaModel, String>("conta")
					);
			saldoCol.setCellValueFactory(
					new PropertyValueFactory<ContaModel, Double>("saldo")
					);
			tabelaContas.setItems(listaContas);
			
			for (Conta conta : contas) {
				listaContas.add(new ContaModel(conta));
			}
		} catch (SQLException e) {
			Dialogs.showErrorDialog(null, "Problemas no banco de dados! \n \n" + e.getMessage());
		}
	}
	public void atualizaTabelaHist (Conta conta) {
		System.out.println("tabela histórico");
		// Obtém o histórico de ações
		ArrayList<Acao> acoes = conta.getHistorico();
		System.out.println(acoes.get(0).getDescricao());
		// Inicializa o ObservableList
		listaAcoes = FXCollections.observableArrayList();
		hDataCol.setCellValueFactory(
				new PropertyValueFactory<HistoricoModel, String>("data")
				);
		hDescricaoCol.setCellValueFactory(
				new PropertyValueFactory<HistoricoModel, String>("descricao")
				);
		tabelaHistorico.setItems(listaAcoes);
		
		for (Acao acao: acoes){
			listaAcoes.add(new HistoricoModel(acao));
		}
	}
	/*************************************
	 * Cadastro de contas - FIM
	 *************************************/
	
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public Stage getNovaJanelaConta (){
		return this.novaJanelaConta;
	}
}
