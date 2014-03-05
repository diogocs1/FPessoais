package app;


import app.controllers.CadastroController;
import app.controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * HeranÃ§a de Application de JavaFX (javafx.application.Application)
 * Implementa o mÃ©todo start() de Application que inicializa a aplicaÃ§Ã£o JavaFX (@Override -> Reescrito)
 * Possui atributos estÃ¡ticos para a fÃ¡cil manipulaÃ§Ã£o da cena, sem a necessidade de composiÃ§Ã£o entre as outras classes
 * 		cena ---> Carrega a cena principal no Stage (palco) primaryStage
 * 		root, cadastro, itens, inicio ---> Containers que contÃ©m os elementos de cada cena (Arquivos FXML)
 */
public class Main extends Application {
	private BorderPane root;
	
	private FXMLLoader loaderCadastro;
	private AnchorPane cadastro;
	private CadastroController controllerCadastro;

	/**
	 * Instancia a cena a adiciona o cotainer que contÃ©m um BorderPane e a Barra de menus
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader rootLoader = new FXMLLoader(Main.class.getResource("view/Border.fxml"));
			root = (BorderPane) rootLoader.load();
			Scene cena = new Scene(root);
			primaryStage.setScene(cena);
			primaryStage.setTitle("Finanças Pessoais 1.0");
			primaryStage.show();
			login();
		} catch(Exception e) {
			Dialogs.showErrorDialog(primaryStage, "Não foi possível iniciar\n" + e.getMessage());
		}		
	}
	
	
	public void login () {
		/**
		 * Carrega a tela de login dentro do root (BorderPane)
		 * Passa a instância atual da classe Main para o atributo main do
		 * LoginController
		 */
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Login.fxml"));
			AnchorPane login = (AnchorPane) loader.load();
			root.setCenter(login);
			
			LoginController cont = loader.getController();
			cont.setMain(this);
		}catch (Exception e){
			Dialogs.showErrorDialog(null, "Não é possível carregar a tela inicial!\n" + e.getMessage());
		}
	}
	public void cadastro () {
		/**
		 * Carrega a tela de cadastro dentro do root (BorderPane)
		 * Passa a instância atual da classe Main para o atributo main do
		 * CadastroController
		 */
		try{
			 if (loaderCadastro == null && cadastro == null){
				 loaderCadastro = new FXMLLoader(Main.class.getResource("view/Cadastro.fxml"));
				 cadastro = (AnchorPane) loaderCadastro.load();
				 
				 controllerCadastro = loaderCadastro.getController();
				 controllerCadastro.setMain(this);
			 }
			 root.setCenter(cadastro);			 
		}catch (Exception e) {
			Dialogs.showErrorDialog(null, "Erro\n \n" + e.getMessage());
		}
	}
//	public static void inicio () {
//		/**
//		 * Carrega a tela inicial dentro do root (BorderPane)
//		 */
//		root.setCenter(null);;
//		root.setCenter(inicio);
//	}
	public static void main(String[] args) {
		/**
		 * Método usado apenas em caso de erros no mÃ©todo start para inicializar.
		 */
		launch(args);
	}
}
