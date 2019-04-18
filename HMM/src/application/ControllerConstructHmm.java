package application;

//import java.beans.Transient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Hmm.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class ControllerConstructHmm implements Initializable{
	String s = "";
	boolean flag = true;
	int n = Variable.nState;
	@FXML
    private TextField stateName;
    @FXML 
    private TextField emojiName;
    @FXML 
    private Label input;
    @FXML
    private TextField probability;
    @FXML
    private Label containOperationName;
	@FXML
	private ComboBox <String> observeData;
	@FXML 
    private Label output;
	@FXML
	private TreeView <String> treeview;
	
	TreeItem<String> rootNode = new TreeItem<>("NODE ");
	TreeItem<String> root1 = new TreeItem<>("States");
	TreeItem<String> root2 = new TreeItem<>("Emissions");
	
	@FXML
	public void addState (ActionEvent event) throws IOException
	{
		String state = stateName.getText().toString();
        Variable.states.add(state);
        stateName.setText(null);
        root1.setExpanded(true);
        root1.getChildren().add(new TreeItem<>(state));
        Variable.intStates[n] = n;
        n++;
        Variable.nState++;
	}
	
	public void addEmission (ActionEvent event) throws IOException
	{
		String emo = emojiName.getText();
		Variable.emotions.add(emo);
        observeData.getItems().add(emo);
        emojiName.setText(null);
		root2.setExpanded(true);
        root2.getChildren().add(new TreeItem<>(emo));
        Variable.nEmission++;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		s = "";
		treeView();
		
		for (String emo : Variable.emotions)
		{
			observeData.getItems().add(emo);
		}
		
		for (String obs : Variable.observations)
		{
			s += obs + " ";
			//System.out.println(s);
		}
		
		input.setText(s);
	}
	
	public void treeView ()
	{
		rootNode.getChildren().add(root1);
		rootNode.getChildren().add(root2);
        treeview.setRoot(rootNode);	
	}
	
	public void addObserveData (ActionEvent event)
	{
		s += observeData.getValue().toString() + " " ;
		input.setText(s);
		Variable.observations.add(observeData.getValue().toString());
		Variable.nObservations++;
		System.out.println(Variable.nObservations);
	}
	
	
	public void calculateMostLikelyHiddenStateSequence (ActionEvent event) throws IOException
	{
		String os = "";
		output.setText(os);
		
		containOperationName.setText("Most Likely Hidden State Sequence");
		
		int[] result = Viterbi.compute(Variable.getIntEmission(), Variable.getIntstates(),
				Variable.startProbability, Variable.getTransitionProbability(), 
				Variable.emissionProbability,Variable.nState, Variable.nObservations);
		
		for (int i : result) {
			os += Variable.states.get(i) + " ";

		}

        output.setText(os);

		s = "";
		Variable.observations.clear();

	}

	public void estimateCurrentHiddenState (ActionEvent event) throws IOException
	{
		containOperationName.setText("Most Likely Current Hidden State");

		int[] result = Viterbi.compute(Variable.getIntEmission(), Variable.getIntstates(),
				Variable.startProbability, Variable.getTransitionProbability(),
				Variable.emissionProbability,Variable.nState, Variable.nObservations);

		int n = result.length - 1;
		int k = result[n];
		//System.out.println(n + " " + k + " " + Variable.states.get(k) );
		output.setText(Variable.states.get(k));
		
	}
	
	public void probabilityOfOvserveSequenceThrowThisModel (ActionEvent event) throws IOException
	{
		containOperationName.setText("Probability of Emision Sequence Throw This Model");
	}
	
	public void EstimateNextHiddenState (ActionEvent event) throws IOException
	{
		containOperationName.setText("Most Likely Next Hidden State");
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
