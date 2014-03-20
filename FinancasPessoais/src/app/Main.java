package app;


import app.controllers.CadastroController;
import app.controllers.HomeController;
import app.controllers.LoginController;
import app.model.Usuario;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * Herança de Application de JavaFX (javafx.application.Application)
 * Implementa o método start() de Application que inicializa a aplicação JavaFX (@Override -> Reescrito)
 * Possui atributos estáticos para a fácil manipulação da cena, sem a necessidade de composição entre as outras classes
 * 		cena ---> Carrega a cena principal no Stage (palco) primaryStage
 * 		root, cadastro, itens, inicio ---> Containers que contém os elementos de cada cena (Arquivos FXML)
 */
public class Main extends Application {
	private Stage primaryStage;
	// Armazena o usuário atual
	private Usuario user;
	
	private BorderPane root;
	
	private AnchorPane cadastro;
	private CadastroController controllerCadastro;
	
	private AnchorPane login;
	private LoginController controllerLogin;
	
	private AnchorPane home;
	private HomeController controllerHome;

	/**
	 * Instancia a cena a adiciona o cotainer que contém um BorderPane e a Barra de menus
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader rootLoader = new FXMLLoader(Main.class.getResource("view/Border.fxml"));
			root = (BorderPane) rootLoader.load();
			Scene cena = new Scene(root);
			this.primaryStage = primaryStage;
			this.primaryStage.setScene(cena);
			this.primaryStage.setTitle("Finanças Pessoais 1.0");
			this.primaryStage.show();
			login();
		} catch(Exception e) {
			Dialogs.showErrorDialog(primaryStage, "Não foi possível iniciar\n" + e.getMessage());
		}		
	}
	
	
	public void login () {
		/**
		 * Carrega a tela de login dentro do root (BorderPane)
		 * Passa a inst�ncia atual da classe Main para o atributo main do
		 * LoginController
		 */
		try {
			if (login == null){
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Login.fxml"));
				login = (AnchorPane) loader.load();
				controllerLogin = loader.getController();
				controllerLogin.setMain(this);
			}
			root.setCenter(login);
		}catch (Exception e){
			Dialogs.showErrorDialog(null, "N�o � poss�vel carregar a tela inicial!\n" + e.getMessage());
		}
	}
	public void cadastro () {
		/**
		 * Carrega a tela de cadastro dentro do root (BorderPane)
		 * Passa a inst�ncia atual da classe Main para o atributo main do
		 * CadastroController
		 */
		try{
			 if (cadastro == null){
				 FXMLLoader loaderCadastro = new FXMLLoader(Main.class.getResource("view/Cadastro.fxml"));
				 cadastro = (AnchorPane) loaderCadastro.load();
				 
				 controllerCadastro = loaderCadastro.getController();
				 controllerCadastro.setMain(this);
			 }
			 root.setCenter(cadastro);			 
		}catch (Exception e) {
			Dialogs.showErrorDialog(null, "Erro\n \n" + e.getMessage());
		}
	}
	public void inicio () {
		/**
		 * Carrega a tela inicial dentro do root (BorderPane)
		 */
		try {
			if (home == null){
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Home.fxml"));
				home = (AnchorPane) loader.load();
				controllerHome = loader.getController();
				controllerHome.setMain(this);
				//Coloca os itens na tabela
				controllerHome.atualizaTabelaDespesas();
				controllerHome.atualizaTabelaContas();
				controllerHome.atualizaInicio();
			}
			root.setCenter(home);
		}catch (Exception e){
			Dialogs.showErrorDialog(null, "Não é possível exibir a tela inicial\n \n" + e.getMessage());
		}
	}
	public static void main(String[] args) {
		/*
		 * Método usado apenas em caso de erros no método start para inicializar.
		 */
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public HomeController getControllerHome() {
		return controllerHome;
	}
}
