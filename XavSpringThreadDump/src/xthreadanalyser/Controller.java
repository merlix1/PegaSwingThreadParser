package xthreadanalyser;


import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.layout.HBox;


public class Controller implements EventHandler {
    private final Stage primaryStage;
    private final View view = new View(this);
   // private ViewThreadPopup viewthreads=new ViewThreadPopup(this);
    
    private final xModel xmodel = new xModel(this);
   // private final View view = new View(this);
    
    //private PRPCThreadDump threaddump=new PRPCThreadDump();
    private PRPCThreadDump threaddump;
    private ThreadDumpAggregate threaddumpaggregate=new ThreadDumpAggregate();
    
    private List<ThreadDumpAggregate> listaggregate= new ArrayList<>();
    
    

    public Controller(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(final Event event) {
        final Object source = event.getSource();

        
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public View getView() {
        return view;
    }

	public Object open(View view) {
		// TODO Auto-generated method stub
		
		//open file
		//FileChooser fileChooser = new FileChooser();
		//File file = fileChooser.showOpenDialog(this.primaryStage);
        //if (file != null) {
         //   FileUtilities.openFile(file);
        //}
        
        //browse folder in OS:
		String resultfromscan= new String();
		
		
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = 
                directoryChooser.showDialog(this.primaryStage);
         
        if(selectedDirectory == null){
        	System.out.println("No Directory selected");
            //labelSelectedDirectory.setText("No Directory selected");
        }else{
        	System.out.println("Directory selected:"+selectedDirectory.getAbsolutePath());
        	try{
        		resultfromscan=FileUtilities.listSourceFiles(selectedDirectory.getAbsolutePath());
        		view.setThreadLabel(resultfromscan);
        	}
        	catch (IOException ex) {
 	           // I/O error encounted during the iteration, the cause is an IOException
 	           System.out.println("unable to open folder");
 	       }
            //labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
        }
        
        //clean data if we open a second folder:
        
        listaggregate=null;
        //view=null;
        //
        
		
		listaggregate=xmodel.open();
		//threaddumpaggregate=listaggregate.get(0);
	
		
		//view.setThreadLabel("The culprit requestor ID is :"+threaddumpaggregate.getThreadDump().getCulpritRequestorID());
		//view.setFullCulpriThreadLabel(threaddumpaggregate.getThreadDump().getCulpritThread().toString());
		//view.setAlertList(threaddumpaggregate.getPRPCAlerts());
		view.populateTabs(listaggregate);
		
		
	//	view.populateTabs(FileUtilities.getThreadDumps());
		
	
		
		
		return null;
	}
	
	public void threadspopup(ThreadDumpAggregate aggregate)
	{
	
	 ViewThreadPopup viewthreads=new ViewThreadPopup(this, aggregate);

	 
	
	}
	
	public void alertpopup(PRPCAlert alert)
	{
	 System.out.println("alert popup in controler called");
//	 ViewThreadPopup viewthreads=new ViewThreadPopup(this, aggregate);
//	 viewthreads.ViewThreadPopupRun(aggregate);
	 ViewAlert viewalert=new ViewAlert(this,alert);
	
	}
	
	
}