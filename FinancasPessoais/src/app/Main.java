package app;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	/*
	 * Herança de Application de JavaFX (javafx.application.Application)
	 * Implementa o método start() de Application que inicializa a aplicação JavaFX (@Override -> Reescrito)
	 * Possui atributos estáticos para a fácil manipulação da cena, sem a necessidade de composição entre as outras classes
	 * 		cena ---> Carrega a cena principal no Stage (palco) primaryStage
	 * 		root, cadastro, itens, inicio ---> Containers que contém os elementos de cada cena (Arquivos FXML)
	 */
	static Scene cena;
	static BorderPane root;
	static AnchorPane cadastro;
	static AnchorPane itens;
	static AnchorPane inicio;

	@Override
	public void start(Stage primaryStage) {
		/*
		 * Instancia a cena a adiciona o cotainer que contém um BorderPane e a Barra de menus
		 */
		try {
			root = (BorderPane)FXMLLoader.load(Main.class.getResource("style/Border.fxml"));
			itens = (AnchorPane) FXMLLoader.load(Main.class.getResource("style/Login.fxml"));
			cadastro = (AnchorPane) FXMLLoader.load(Main.class.getResource("style/Cadastro.fxml") );
			inicio = (AnchorPane) FXMLLoader.load(Main.class.getResource("style/Home.fxml") );
			Scene cena = new Scene(root);
			// Carrega o arquivo de folhas de estilo (CSS) na cena usando o método .toExternalForm()
			cena.getStylesheets().add(Main.class.getResource("style/style.css").toExternalForm());
			primaryStage.setScene(cena);
			primaryStage.setTitle("FinançasPessoais 1.0");
			primaryStage.show();
			// Carrega o método que mostra a tela de login no início do programa
			login();
		} catch(Exception e) {
			e.getMessage();
		}		
	}
	public static void login () {
		/*
		 * Carrega a tela de login dentro do root (BorderPane)
		 */
		root.setCenter(null);
		root.setCenter(itens);

	}
	public static void cadastro () {
		/*
		 * Carrega a tela de cadastro dentro do root (BorderPane)
		 */
		root.setCenter(null);
		root.setCenter(cadastro);
	}
	public static void inicio () {
		/*
		 * Carrega a tela inicial dentro do root (BorderPane)
		 */
		root.setCenter(null);;
		root.setCenter(inicio);
	}
	public static void main(String[] args) {
		/*
		 * Método usado apenas em caso de erros no método start para inicializar.
		 */
		launch(args);
	}
}
