package xthreadanalyser;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

//Using MVC pattern


//this class will be the top UI element border pane.
public class View extends BorderPane{




	 private final Button button = new Button("Button");
//	 private Label threadlabel = new Label("");
	 private TextArea threadlabel = new TextArea();

	 private Label fullculprithreadlabel = new Label();
	 private List<Label> alertlist= new ArrayList<>();
	 private VBox vbox = new VBox();

	 private final Controller incontroller;

	 private static String scanfolderresult="";



	 private TabPane tabPane = new TabPane();

	// private Tab tab = new Tab();
	 private List<Tab> tablist= new ArrayList<>();

	 final ScrollPane sc = new ScrollPane();



	 	//We configure all UI element here:
	    public View(final Controller controller) {
	    	incontroller=controller;

	    	// below not working as not added to "this"
	    	//final Button button2 = new Button("Button2");



	    	// File menu - Open, Save, Exit
	    	MenuBar menuBar = new MenuBar();

	    	//important line adding menu to UI
	    	this.setTop(menuBar);

	        Menu fileMenu = new Menu("File");
	        MenuItem openMenuItem = new MenuItem("Scan Folder");
	        openMenuItem.setOnAction(actionEvent->controller.open(this));

	       // MenuItem saveMenuItem = new MenuItem("Save");
	        MenuItem exitMenuItem = new MenuItem("Exit");
	        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
	        //actionEvent -> Platform.exit()

	        //add the new menuitems to the fileMenu
	        //fileMenu.getItems().addAll(openMenuItem, saveMenuItem,new SeparatorMenuItem(), exitMenuItem);
	        fileMenu.getItems().addAll(openMenuItem,new SeparatorMenuItem(), exitMenuItem);

	        menuBar.getMenus().addAll(fileMenu);



	        Tab tab = new Tab();
            tab.setText("Welcome");
            tablist.add(tab);





	        ScrollPane scrollPane = new ScrollPane();
	        scrollPane.setFitToHeight(true);
	        scrollPane.setFitToWidth(true);
	        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

	        //this.setCenter(scrollPane);

	        this.setCenter(tabPane);
	        tabPane.getTabs().add(tablist.get(0));
	        tab.setContent(scrollPane);

	        scrollPane.setContent(vbox);

	        vbox.getChildren().add(threadlabel);

	        final WebView browser = new WebView();
	        final WebEngine webEngine = browser.getEngine();

	        vbox.getChildren().add(browser);

	       // webEngine.load("http://java2s.com");
	       //.load("welcome.html");
	        File f = new File("welcome.html");
	        webEngine.load(f.toURI().toString());


	        Separator separator1 = new Separator();

	        vbox.getChildren().add(separator1);

	        // adding list alert here





	        vbox.getChildren().add(fullculprithreadlabel);

	        this.getChildren().add(sc);







	    	button.setOnAction(event -> {
	            System.out.println("Button has been pressed.");

	        });
	       this.getChildren().addAll(button);

	    }



	    public Button getButton() {
	        return button;
	    }




	    public void setThreadLabel(String in){
	    	scanfolderresult=scanfolderresult+"\n"+"********"+"\n"+in;
	    	threadlabel.setText(scanfolderresult);
	    	System.out.println(in);

	    }


	    public void setFullCulpriThreadLabel(String in){
	    	fullculprithreadlabel.setText(in);

	    }


	    // setting all alert labels
	    public void setAlertList(List<PRPCAlert> inalertlist){


	    	 for(PRPCAlert alert : inalertlist) {

	    		 Label temp= new Label();
	 	    	 temp.setText(alert.toString());
	 	    	 alertlist.add(temp);

		        }
	    	 for(Label alertlist : alertlist) {

		        	 vbox.getChildren().add(alertlist);


		        }


	    }


	    //populating all tabs
	    public void populateTabs(List<ThreadDumpAggregate> listaggregate )
	    {

	    	for(ThreadDumpAggregate aggregate:listaggregate)	{


	    		Tab tab = new Tab();
	            //tab.setText("Tabo");
	    		tab.setText(aggregate.getThreadDump().getTimestamp());

	         //   tablist.add(tab);
	            this.setCenter(tabPane);

	            this.populatetab(tab, aggregate);




	    		}

	    }

	    public void populatetab(Tab tab, ThreadDumpAggregate aggregate)
	    {


	        tabPane.getTabs().add(tab);


	        ScrollPane scrollPanetab = new ScrollPane();
	        scrollPanetab.setFitToHeight(true);
	        scrollPanetab.setFitToWidth(true);
	        scrollPanetab.setHbarPolicy(ScrollBarPolicy.NEVER);
	        scrollPanetab.setVbarPolicy(ScrollBarPolicy.ALWAYS);

	        VBox vboxtab = new VBox();
	        scrollPanetab.setContent(vboxtab);

	        /*
	        Label threadlabeltab = new Label();
	        vboxtab.getChildren().add(threadlabeltab);

	        threadlabeltab.setText(aggregate.getThreadDump().getCulpritThreadName());
	        */

	        HBox hbox=new HBox();

	        vboxtab.getChildren().add(hbox);

	        Button fullthreaddump = new Button("Thread Dump");
	        fullthreaddump.setOnAction(event -> {
	            System.out.println("Extracted Thread Dump Button has been pressed.");
	          //  controller.open(this); //aggregate ThreadDumpAggregate aggregate
	            incontroller.threadspopup(aggregate);


	        });



	        TextArea threadanalysis = new TextArea();
	        threadanalysis.setEditable(false);
	        threadanalysis.setWrapText(true);

	        threadanalysis.setText(aggregate.threadanalysis().toString());
	        threadanalysis.setMinHeight(110);
	        threadanalysis.setMinWidth(1280);

	       // threadanalysis
	        hbox.getChildren().add(fullthreaddump);
	        hbox.getChildren().add(threadanalysis);
	        hbox.setAlignment(Pos.CENTER);



	        	//	aggregate.getThreadDump().getCulpritThreadName());

	        Separator separator1 = new Separator();

	        vboxtab.getChildren().add(separator1);

	        //display culprit thread
	        //Label fullculprithreadlabeltab = new Label();
	        TextArea fullculprithreadlabeltab = new TextArea();
	       // fullculprithreadlabeltab.setPrefRowCount(30);
	        fullculprithreadlabeltab.setMinHeight(450);
	        fullculprithreadlabeltab.setEditable(false);

	        vboxtab.getChildren().add(fullculprithreadlabeltab);
	        fullculprithreadlabeltab.setText(aggregate.getThreadDump().getCulpritThread().toString());

	        //using Java FX table view
	        TableView<PRPCAlert> PRPCalerttable = new TableView<PRPCAlert>();
	        ObservableList<PRPCAlert> datos = FXCollections.observableArrayList();

	        //attempt to set color here




	        	/*PRPCalerttable.setRowFactory( row -> new TableRow<PRPCAlert>(){

	            @Override
	            public void updateItem(PRPCAlert item, boolean empty)
	            {
	                super.updateItem(item, empty);
	                //System.out.println("flaglevel:"+item.getFlaglevel());

	                /*
	                row.setOnMouseClicked(event -> {
		                    if (event.getClickCount() == 2 )
		                    {
		                       // MyType rowData = row.getItem();
		                        System.out.println(item);
		                        PRPCAlert alertclicked=this.getItem();
		                        incontroller.alertpopup(alertclicked);
		                    }
	                });

	                */
	        		/*
	                if (item == null || empty)
	                {
	                    setStyle("");
	                } else
	                {

	                	//System.out.println("flaglevel:"+item.getFlaglevel()+"alert in interval"+item.getRawData());
	                	//Now 'item' has all the info of the Person in this row
	                    	if (item.getFlaglevel()==1)
	                    	{
	                    		setStyle("-fx-background-color: yellow");
	                    	}
	                    	else if (item.getFlaglevel()==2)
	                    	{
	                    		setStyle("-fx-background-color: red");
	                    	}
	                    	else {
	                            setStyle("");
	                        }

	                }
	            }

	        });
	    */


	        // alternative approach

	        PRPCalerttable.setRowFactory( tv -> {
	            TableRow<PRPCAlert> row = new TableRow<PRPCAlert>()
	            		{

	            						@Override
							            public void updateItem(PRPCAlert item, boolean empty)
							            {
							                super.updateItem(item, empty);

							                if (item == null || empty)
							                {
							                    setStyle("");
							                } else
							                {

							                	//System.out.println("flaglevel:"+item.getFlaglevel()+"alert in interval"+item.getRawData());
							                	//Now 'item' has all the info of the Person in this row
							                    	if (item.getFlaglevel()==1)
							                    	{
							                    		setStyle("-fx-background-color: yellow");
							                    	}
							                    	else if (item.getFlaglevel()==2)
							                    	{
							                    		setStyle("-fx-background-color: red");
							                    	}
							                    	else {
							                            setStyle("");
							                        }

							                }
							            }

	            		}
	            		;

	            row.setOnMouseClicked(event -> {
							                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
							                	PRPCAlert rowData = row.getItem();
							                    System.out.println(rowData);
							                    incontroller.alertpopup(rowData);
							                }
	            						});





	            return row ;
	        });




	        //end attempt


	        for (PRPCAlert o : aggregate.getPRPCAlerts()) {
	            datos.add(o);
	        }

	        TableColumn timestampCol = new TableColumn("Time Stamp");
	        timestampCol.setMinWidth(200);
	       // timestampCol.
	        timestampCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("timestamp"));

	        TableColumn messageidCol = new TableColumn("MessageId");
	        messageidCol.setMinWidth(100);
	        messageidCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("messageid"));


	        TableColumn observedkpiCol = new TableColumn("Observed KPI");
	        observedkpiCol.setMinWidth(100);
	        observedkpiCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("observedkpi"));

	        TableColumn thresholdkpiCol = new TableColumn("Threshold KPI");
	        thresholdkpiCol.setMinWidth(100);
	        thresholdkpiCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("thresholdkpi"));



	        /* same data being displayed on all rows, removing from display
	        TableColumn nodeidCol = new TableColumn("Node id");
	        nodeidCol.setMinWidth(230);
	        nodeidCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("nodeid"));

	        TableColumn requestoridCol = new TableColumn("Requestor id");
	        requestoridCol.setMinWidth(250);
	        requestoridCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("requestorid"));


	        TableColumn useridCol = new TableColumn("User id");
	        useridCol.setMinWidth(100);
	        useridCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("userid"));
	        */

	        TableColumn threadCol = new TableColumn("Java thread");
	        threadCol.setMinWidth(150);
	        threadCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("thread"));


	        TableColumn pegathreadCol = new TableColumn("Pega Thread");
	        pegathreadCol.setMinWidth(100);
	        pegathreadCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("pegathread"));

	        TableColumn loggerCol = new TableColumn("Logger");
	        loggerCol.setMinWidth(100);
	        loggerCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("logger"));

	        TableColumn stackCol = new TableColumn("Stack");
	        stackCol.setMinWidth(100);
	        stackCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("stack"));

	        TableColumn lastinputCol = new TableColumn("Last Input");
	        lastinputCol.setMinWidth(200);
	        lastinputCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("lastinput"));

	        TableColumn firstactivityCol = new TableColumn("First Activity");
	        firstactivityCol.setMinWidth(200);
	        firstactivityCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("firstactivity"));

	        TableColumn laststepCol = new TableColumn("Last Step");
	        laststepCol.setMinWidth(200);
	        laststepCol.setCellValueFactory(new PropertyValueFactory<PRPCAlert, String>("laststep"));











	        PRPCalerttable.setItems(datos);
	        PRPCalerttable.getColumns().addAll(timestampCol, messageidCol,observedkpiCol,thresholdkpiCol,loggerCol,lastinputCol,firstactivityCol,laststepCol);




	        vboxtab.getChildren().add(PRPCalerttable);



	    	 tab.setContent(scrollPanetab);



	    }

/*	    public void fullthreaddumppopup()
	    {

	    	Popup popup = new Popup();
	        popup.setX(300);
	        popup.setY(200);
	        popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
	       // popup.show(primaryStage);

	    }

	  */





}
