package xthreadanalyser;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


/*  VERY POOR PERFORMANCE OF JAVAFX WITH LARGE TEXT, SEE CODE IN SWING LATER BELOW WICH IS MUCH FASTER AND CONSUMME FAR LESS MEMORY
public class ViewThreadPopup extends BorderPane {
	
	
	//final Stage primaryStage=null;
	
	private Controller incontroller=null;
	private Scene scene=null;
	private VBox vbox = new VBox();
	//JFrame frame = new JFrame("Test");
	
	public ViewThreadPopup(final Controller controller,ThreadDumpAggregate aggregate) 
	{
		incontroller=controller;
		
		
		Scene scene = new Scene(this,400,400);
		
		Stage stage = new Stage();
        stage.setTitle("Full Thread Dump");
        
        
        
       ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
       //TextArea fullthreads = new TextArea();
       // Label fullthreads = new Label();
        
        Text fullthreads = new Text();
       // fullthreads.setMinHeight(450);
       // fullthreads.setEditable(false);
        fullthreads.setText(aggregate.getThreadDump().getThreadDump().toString());
        
        vbox.getChildren().add(fullthreads);
        
       // scrollPane.setContent(fullthreads);
        
        
        
        scrollPane.setContent(vbox);
        
        
        
        
        
        
        
       // this.setCenter(vbox);
        this.setCenter(scrollPane);
        
        
        stage.setScene(scene);
        stage.show();
		
	}
}*/
///////////////////////////////////

//SWING IMPLEMENTATION: MUCH BETTER PERFORMANCE
public class ViewThreadPopup extends JFrame {
	 
	    //private JButton saveCloseBtn = new JButton("Save Changes and Close");
	    //private JButton closeButton = new JButton("Exit Without Saving");
	    private JFrame frame=new JFrame("Thread Dump");
	    private JTextArea textArea = new JTextArea();



	    public ViewThreadPopup(final Controller controller,ThreadDumpAggregate aggregate) 
	    {
	         panels(aggregate);
	     }        

	    private void panels(ThreadDumpAggregate aggregate){        
	          JPanel panel = new JPanel(new GridLayout(1,1));
	          panel.setBorder(new EmptyBorder(5, 5, 5, 5));
	          // rightPanel = new JPanel(new GridLayout(15,0,10,10));
	          //rightPanel.setBorder(new EmptyBorder(15, 5, 5, 10));
	          
	          textArea.setText(aggregate.getThreadDump().getThreadDump());
	          textArea.setEditable(false);
	          
	          JScrollPane scrollBarForTextArea=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	          //scrollBarForTextArea.
	          panel.add(scrollBarForTextArea); 
	          frame.add(panel);
	         // frame.getContentPane().add(rightPanel,BorderLayout.EAST);
	         // rightPanel.add(saveCloseBtn);
	         // rightPanel.add(closeButton);

	           frame.setSize(1200, 700);
	           frame.setVisible(true);   
	           frame.setLocationRelativeTo(null);

	}
		
	
}