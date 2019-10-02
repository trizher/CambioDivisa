package dad.javafx.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisaApp extends Application {

	private TextField origenText, destinoText;
	private ComboBox<Divisa> origenModeCombo, destinoModeCombo;
	private Button cambiarButton;
	private Divisa[] divisas = {new Divisa("Euro", 1.0), new Divisa("Libra", 0.89), new Divisa("Dolar", 1.09), new Divisa("Yen", 117.67)};
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		origenText = new TextField("0");
		origenText.setMaxWidth(50);
		
		destinoText = new TextField("0");
		destinoText.setMaxWidth(50);
		destinoText.setEditable(false);
		
		origenModeCombo = new ComboBox<Divisa>();
		origenModeCombo.getItems().addAll(divisas);
		origenModeCombo.setMinWidth(120);
		origenModeCombo.getSelectionModel().selectFirst();
		
		destinoModeCombo = new ComboBox<Divisa>();
		destinoModeCombo.getItems().addAll(divisas);
		destinoModeCombo.setPromptText("Divisa Destino");
		destinoModeCombo.setMinWidth(120);
		destinoModeCombo.getSelectionModel().selectFirst();
		
		cambiarButton = new Button("Cambiar");
		cambiarButton.setDefaultButton(true);
		cambiarButton.setOnAction(e -> onCambiarButtonAction(e));
		
		HBox origenBox = new HBox(5, origenText, origenModeCombo);
		origenBox.setAlignment(Pos.CENTER);

		
		HBox destinoBox = new HBox(5, destinoText, destinoModeCombo);
		destinoBox.setAlignment(Pos.CENTER);
		
		HBox botonBox = new HBox(5, cambiarButton);
		botonBox.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5, origenBox, destinoBox, botonBox);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private void onCambiarButtonAction(ActionEvent e) {
		Divisa origen = origenModeCombo.getSelectionModel().getSelectedItem();
		Divisa destino = destinoModeCombo.getSelectionModel().getSelectedItem();
		Double cantidad = 0.0;
		try {
			cantidad = Double.parseDouble(origenText.getText());
		} catch(Exception ex) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("¡Error!");
			alert.setContentText("Se ha introducido un dato incorrecto");
			
			alert.showAndWait();
		}
		destinoText.setText(destino.fromEuro(origen.toEuro(cantidad)).toString());
		
	}

	public static void main(String[] args) {
		launch();

	}

}
