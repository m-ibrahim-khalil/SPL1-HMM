package application;

import java.io.IOException;
import java.net.URL;
//import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    PieChart pieChart;
    @FXML
    AnchorPane anchorPane;

	public HBox createHbox (String s)
	{
		hbox = new HBox();
		hbox.setSpacing(10);
		label = new Label();
		label.setPrefHeight(50);
		label.setPrefWidth(175);
		label.setTextFill(Color.web("#0076a3"));
		label.setFont(new Font("Arial",24));
		label.setWrapText(true);
		label.setText(s);
		hbox.getChildren().add(label);
		hbox.getChildren().add(new TextField());

		return hbox;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		int i,j;
		scrolpane.setContent(vbox);

		for ( i = 0; i < Variable.nState; i++)
		{
			for ( j = 0; j < Variable.nEmission; j++)
			{
				vbox.getChildren().add(createHbox(Variable.states.get(i) +" -> "+ Variable.emotions.get(j)));
			}
			
		}

		pieChart = new PieChart();
		int n = Variable.nState;
		int m = Variable.nEmission;

		PieChart.Data [][] slice = new PieChart.Data[n][m];
		for (i = 0; i < n; i++)
		{
			for (j = 0; j < m; j++)
			{
				String s = Variable.states.get(i) + "->" + Variable.emotions.get(j);
				double probability = Variable.emissionProbability[i][j];
				slice [i][j] = new PieChart.Data(s,probability);
				pieChart.getData().add(slice[i][j]);
			}

		}
		final Label caption = new Label("");
		caption.setTextFill(Color.WHITE);
		caption.setStyle("-fx-font: 12 arial;");


		for (final PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());

					caption.setText(String.valueOf(data.getPieValue()));
				}
			});
		}
		pieChart.setClockwise(true);
		pieChart.setLabelLineLength(10);
		pieChart.setLabelsVisible(true);
		pieChart.setStartAngle(90);
		pieChart.setTitle("Emission probability");
		pieChart.setLegendSide(Side.BOTTOM);
		pieChart.setLabelsVisible(true);
		pieChart.setPrefSize(400, 300);

		anchorPane.getChildren().addAll(pieChart,caption);

		
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

		pieChart = new PieChart();
		int n = Variable.nState;
		int m = Variable.nEmission;

		PieChart.Data [][] slice = new PieChart.Data[n][m];
		for (i = 0; i < n; i++)
		{
			for (j = 0; j < m; j++)
			{
				String s = Variable.states.get(i) + "->" + Variable.emotions.get(j);
				double probability = Variable.emissionProbability[i][j];
				slice [i][j] = new PieChart.Data(s,probability);
				pieChart.getData().add(slice[i][j]);
			}

		}
		final Label caption = new Label("");
		caption.setTextFill(Color.WHITE);
		caption.setStyle("-fx-font: 12 arial;");


		for (final PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());

					caption.setText(String.valueOf(data.getPieValue()));
				}
			});
		}
		pieChart.setClockwise(true);
		pieChart.setLabelLineLength(10);
		pieChart.setLabelsVisible(true);
		pieChart.setStartAngle(90);
		pieChart.setTitle("Emission probability");
		pieChart.setLegendSide(Side.BOTTOM);
		pieChart.setLabelsVisible(true);
		pieChart.setPrefSize(400, 300);

		anchorPane.getChildren().addAll(pieChart,caption);


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
