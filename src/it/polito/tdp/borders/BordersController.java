/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader
	
	@FXML // fx:id="connectedComponents"
    private Label connectedComponents; // Value injected by FXMLLoader

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		
		Long start = System.nanoTime();
		
		txtResult.clear();
		
		Integer year;
		
		try{
			
			year = Integer.parseInt(txtAnno.getText());
			
			if(year < 1816 || year > 2016){
				txtResult.setText("Insert a year in range 1816-2016.");
				return;
			}
			
		}catch(NumberFormatException e){
			
			txtResult.setText("ERROR: Insert a year.");
			return;
			
		}
		
		model.createGraph(year);
		
		Set<Country> nations = new HashSet<Country>();
		
		nations = model.getNations();
		
		for(Country c : nations){
			txtResult.appendText(c.printInfo()+"\n");
		}
		
		Integer numberOfConnectedComponents = model.getNumberOfConnectedComponents();
		
		connectedComponents.setText("The graph has "+numberOfConnectedComponents+" connected components.");
		
		Long stop = System.nanoTime();
		
		System.out.println("\nProcess returned in "+(stop-start)/1e9+" seconds.");
		
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
        assert connectedComponents != null : "fx:id=\"connectedComponents\" was not injected: check your FXML file 'Borders.fxml'.";
	}

	public void setModel(Model model) {
		
		this.model = model;
		
	}
}
