package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableView {
	private SimpleStringProperty state1;
	private SimpleStringProperty state2;
	private SimpleStringProperty emoji;
	private SimpleDoubleProperty probability;
	public TableView(String state1, String state2, Double probability) {
		super();
		//System.out.println("Called");
		this.state1 = new SimpleStringProperty (state1);
		this.state2 = new SimpleStringProperty (state2);
		this.probability = new SimpleDoubleProperty (probability);
	}
	
	public TableView(String state1, Double probability) {
		// TODO Auto-generated constructor stub
		//System.out.println("called");
		this.state1 = new SimpleStringProperty (state1);
		this.probability = new SimpleDoubleProperty (probability);

	}
	public String getState1() {
		return state1.get();
	}
	public void setState1(String state1) {
		this.state1 = new SimpleStringProperty(state1);
	}
	public String getState2() {
		return state2.get();
	}
	public void setState2(String state2) {
		this.state2 = new SimpleStringProperty(state2);
	}
	public String getEmoji() {
		return emoji.get();
	}
	public void setEmoji(String emoji) {
		this.emoji = new SimpleStringProperty (emoji);
	}
	public Double getProbability() {
		return probability.get();
	}
	public void setProbability(Double probability) {
		this.probability.set(probability);
	}
	
}
