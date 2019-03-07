package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class Controller implements Initializable {
    @FXML
    int n  = 0;
    static int m = 0;
    public static ArrayList<String> states = new ArrayList <String>();	//** state list
    public static ArrayList<String> emotions = new ArrayList <String>();	//**  emotions list
    //String observData ;		//** store users emotion sequence
    static int status [] = new int [100];
    static double start_p [] = new double [100];
    boolean flag = false;
    
    @FXML private Label header;
    @FXML private Label stateCounter;
    @FXML private Label emoCounter;
    
    @FXML
    private TextField stateName;
    @FXML private TextField emojiName;
    
    /**
     * Configure the Table
     * 
     * */
    
    @FXML private TableView<application.TableView>tableview;
	@FXML private TableColumn<application.TableView, String> state1;
	//@FXML private TableColumn<application.TableView, String> starting_s1;
	@FXML private TableColumn<application.TableView, Double> probability;
    
	@FXML private TextField ss;
	//@FXML private TextField ss1;
	@FXML private TextField sp;
	
	@FXML
	public void ChangedCellss (CellEditEvent edittedCell)
	{
		application.TableView collSelected  = tableview.getSelectionModel().getSelectedItem();
		collSelected.setState2(edittedCell.getNewValue().toString());
	}
	
	/*
	@FXML
	public void ChangedCellss1 (CellEditEvent edittedCell)
	{
		application.TableView collSelected  = tableview.getSelectionModel().getSelectedItem();
		collSelected.setState2(edittedCell.getNewValue().toString());
	}
	*/
	
	@FXML
	public void ChangedCellsp (CellEditEvent edittedCell)
	{
		application.TableView collSelected  = tableview.getSelectionModel().getSelectedItem();
		collSelected.setProbability(Double.valueOf((String) edittedCell.getNewValue()));
	}

    
    @FXML
    public void AddState(ActionEvent event) {
    	String state = stateName.getText().toString();
        states.add(state);
      //  states.add("kim");
        status[n] = n;
        n++;
       // System.out.println(states);
        stateCounter.setText(String.valueOf(n));
        stateName.setText("");
       // flag = true;
    }

    public void addEmo (ActionEvent event) {
    	String emo = emojiName.getText();
        emotions.add(emo);
        m++;
        emoCounter.setText(String.valueOf(m));
        emojiName.setText("");
        //System.out.println(emotions);
    }
    
    @FXML
    public void gotoTableview(ActionEvent event) throws IOException
    {
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("Sample2.fxml"));
    	Scene tableViewScene = new Scene (tableViewParent);
    	
    	Stage window1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window1.setScene(tableViewScene);
    	window1.show();
    }

    @FXML
	public void newRowPushed1(ActionEvent event)
	{
		application.TableView addRow = new application.TableView (ss.getText(),Double.valueOf(sp.getText()));
		
		tableview.getItems().add(addRow);
		
		//ArrayList<String> states = new ArrayList <String> ();
		
		//states = c.getList();
		
		int i = states.indexOf(ss.getText());
		
		//trans_p.add(Double.valueOf(p.getText()));
		start_p [i] = Double.valueOf(sp.getText());
		//System.out.println(start_p [i]);
		ss.setText("");
		sp.setText("");
	}
    
    /**
     * Initialize the Controller class
     * 
     * */
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	state1.setCellValueFactory(new PropertyValueFactory<application.TableView,String>("state1"));
		//starting_s1.setCellValueFactory(new PropertyValueFactory<application.TableView,String>("starting_s1"));
		probability.setCellValueFactory(new PropertyValueFactory<application.TableView,Double>("probability"));

		//load dummy data
		//tableview.setItems(getTable());
		
		tableview.setEditable(true);
		
		state1.setCellFactory(TextFieldTableCell.forTableColumn());
		//starting_s1.setCellFactory(TextFieldTableCell.forTableColumn());
		//probability.setCellFactory(TextFieldTableCell.forTableColumn().toString());
		
	}
	
    /**
     * this method will return a List of TableView object
     * */
    
    /*
    public ObservableList<application.TableView> getTable()
	{
		ObservableList<application.TableView> table = FXCollections.observableArrayList();
		//ArrayList<String> states = new ArrayList <String> ();
		//System.out.println(states);
		//states.add("s");
		for (String s1 : states)
		{
			//System.out.println(s1);
			table.add (new application.TableView(s1, 0.0));
		}
		
		//table.add (new application.TableView("rr", 0.0));
		System.out.println(table);
		return table;
	}
    */
    
    public void setTrans_p()
    {
    	
    }
    @FXML
    
    public ArrayList<String> getList1() {
    	//states.add("ss");
    	//System.out.println(states);
        return emotions;
    }
    
    
    public ArrayList<String> getList() {
    	//states.add("ss");
    	//System.out.println(states);
        return states;
    }
    
    public void setEmit_p()
    {

    }



    public void setObservData()
    {

    }
    
    
}

