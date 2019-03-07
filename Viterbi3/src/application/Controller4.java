package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller4 {
	
	//public  ArrayList<String> emotions = new ArrayList <String>();
	//public  ArrayList<String> states = new ArrayList <String>();
	
	@FXML private TextField input;
	@FXML private Label output;
	int obs [];
	Controller con = new Controller();
	//ControllerTableview con1 = new ControllerTableview();
	//Controller3 con2 = new Controller3();
	
	
	@FXML 
	public void generateButtonPushed (ActionEvent event)
	{
		//** obs array store the sequence of
	    //** emoji corresponding the emotions list

		String observData = input.getText().toString();
		
	    String [] observations = observData.split(" ");
	    int c = 0;
	    
	    
	    obs = new int [observations.length];

	    //**split obserbdate by space separator and push them in observations array 		 
	    
	    //System.out.println(Controller.emotions);
		for (String i : observations)
	    {
	        //System.out.println(con.m);
	
	        for (int j = 0; j < con.m; j++)
	        {
	            if (i.equals(Controller.emotions.get(j)) )
	            {
	                obs [c] = j;
	                c++;
	                break;
	            }
	        }
	    }
		
		
		//System.out.println(obs [2]);
		//System.out.println(obs [3]);
		
		//System.out.println(Controller.status[1]);
		//System.out.println(Controller.start_p[1]);
		//System.out.println(ControllerTableview.trans_p[0][0]);
		//System.out.println(Controller3.emit_p[0][1]);
		calculate ();
		
		input.setText("");
	}
	
	public void calculate() {
		
		 String s = "";
        int[] result = Viterbi.compute(obs, Controller.status, Controller.start_p, ControllerTableview.trans_p, Controller3.emit_p);
        System.out.println(result.length);
        for (int i : result)
        {
             s += Controller.states.get(i) + " ";
             //System.out.println(s);
        }
        output.setText(s);
	
}
}
