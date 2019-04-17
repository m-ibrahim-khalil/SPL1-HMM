package application;

import java.io.IOException;
import java.net.URL;
//import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ControllerStateEmission implements Initializable{

	@FXML
	ScrollPane scrolpane;
	@FXML
	VBox vbox;
	@FXML
	HBox hbox;
	@FXML
	TextField textField;
	@FXML
	Label label;
	
	public HBox createHbox (String s)
	{
		hbox = new HBox();
		label = new Label();
		hbox = new HBox();
		label = new Label();
		label.prefWidth(200);
		label.prefHeight(50);
		label.setTextFill(Color.web("#0076a3"));
		label.setFont(new Font("Arial",24));
		label.setWrapText(true);
		label.setText(s);
		hbox.getChildren().add(label);
		textField = new TextField("");
		hbox.getChildren().add(textField);
		
		return hbox;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scrolpane.setContent(vbox);
		for (int i = 0; i < Variable.nState; i++)
		{
			for (int j = 0; j < Variable.nEmission; j++)
			{
				vbox.getChildren().add(createHbox(Variable.states.get(i) +" -> "+ Variable.emotions.get(j)));
			}
			
		}
		
	}
	
	@FXML
	public void buttonProcess (ActionEvent event) throws IOException
	{
		int i = 0,j = 0;
		ObservableList<Node> list = vbox.getChildren();
		for(Object o: list) {
			//System.out.println(((TextField)((HBox) o).getChildren().get(1)).getText());
			Variable.emissionProbability[i][j] = Double.parseDouble(((TextField)((HBox) o).getChildren().get(1)).getText().toString());
			((TextField)((HBox) o).getChildren().get(1)).setText("");
			
			if (j == (Variable.nEmission - 1))
			{
				j = 0;
				i++;
			}
			
			else j++;
		}
			
	}

	
	public void gotoHome (ActionEvent event) throws IOException
    {
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("view/ConstructHmm.fxml"));
    	Scene tableViewScene = new Scene (tableViewParent);
    	
    	Stage window1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window1.setScene(tableViewScene);
    	window1.show();
    }
    
	@FXML
	public void gotoStartingState(ActionEvent event) throws IOException
    {
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("view/StartingState.fxml"));
    	Scene tableViewScene = new Scene (tableViewParent);
    	
    	Stage window1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window1.setScene(tableViewScene);
    	window1.show();
    }
	
	@FXML
	public void gotoStateTransition(ActionEvent event) throws IOException
    {
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("view/StateTransition.fxml"));
    	Scene tableViewScene = new Scene (tableViewParent);
    	
    	Stage window1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window1.setScene(tableViewScene);
    	window1.show();
    }
	
	@FXML
	public void gotoStateEmission (ActionEvent event) throws IOException
    {
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("view/StateEmission.fxml"));
    	Scene tableViewScene = new Scene (tableViewParent);
    	
    	Stage window1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window1.setScene(tableViewScene);
    	window1.show();
    }
	
	
}
