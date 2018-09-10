package xthreadanalyser;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class ViewAlert extends JFrame {

    //private JButton saveCloseBtn = new JButton("Save Changes and Close");
    //private JButton closeButton = new JButton("Exit Without Saving");
    private JFrame frame=new JFrame("Alert details");
    private JTextArea textArea = new JTextArea();
    private PRPCAlert inalert;
    private String AlertVersion;

    List<String> listalertdetails;
    private String html;
    private String PALData;
    private String TraceList;

    private String StackTrace;
    private String ParameterPage;




    public ViewAlert(final Controller controller,PRPCAlert alert)
    {
    	inalert=alert;
    	preprocessing();
    	panels();
     }

    private void panels(){


          // rightPanel = new JPanel(new GridLayout(15,0,10,10));
          //rightPanel.setBorder(new EmptyBorder(15, 5, 5, 10));

          JTabbedPane tabbedPane = new JTabbedPane();

         // JComponent panel1 = makeTextPanel("Panel #1");

          //("Tab 1", icon, panel1,"Does nothing");


          //Alert Detail Panel definition
          JPanel alertdetailpanel = new JPanel();
          this.alertdetailpanel(alertdetailpanel);



          //Alert PAL data Panel definition
          JPanel paldatapanel = new JPanel();
          this.PALDataTab(paldatapanel);


        //Alert trace list Panel definition
          JPanel tracelistpanel = new JPanel();
          this.traceListTab(tracelistpanel);

        //Alert stack trace Panel definition
          JPanel stacktracepanel = new JPanel();
          this.stackTrace(stacktracepanel);

          //Alert parameter [age Panel definition
          JPanel parampagepanel = new JPanel();
          this.parameterPage(parampagepanel);

          // Raw Text Panel definition
          JPanel rawpanel = new JPanel(new GridLayout(1,1));
          rawpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
          textArea.setText(inalert.getRawData());
          textArea.setEditable(false);

          JScrollPane scrollBarForTextArea=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
          //scrollBarForTextArea.
          rawpanel.add(scrollBarForTextArea);



          tabbedPane.addTab("Alert Details", alertdetailpanel);
          tabbedPane.addTab("PAL data", paldatapanel);
          tabbedPane.addTab("Trace List", tracelistpanel);
          tabbedPane.addTab("Stack Trace", stacktracepanel);
          tabbedPane.addTab("Parameter Page", parampagepanel);
          tabbedPane.addTab("Raw Text", rawpanel);
          frame.add(tabbedPane);

          //frame.add(panel);
         // frame.getContentPane().add(rightPanel,BorderLayout.EAST);
         // rightPanel.add(saveCloseBtn);
         // rightPanel.add(closeButton);

           frame.setSize(1200, 700);
           frame.setVisible(true);
           frame.setLocationRelativeTo(null);

    		}

    	//details panel built here
    	private void alertdetailpanel(JPanel alertdetailpanel)
    	{




	       // scrollPane.setFitToHeight(true);
	       // scrollPane.setFitToWidth(true);
	       // scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	       // scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

    		JEditorPane editorPane = new JEditorPane();
    		editorPane.setEditable(false);
    		editorPane.setContentType("text/html");
    		editorPane.setText(html);
    		//scrollPane.setContent(editorPane);

    		JScrollPane scrollPane = new JScrollPane(editorPane);


    		alertdetailpanel.setLayout(new BoxLayout(alertdetailpanel, BoxLayout.PAGE_AXIS));

    		JPanel toppanel = new JPanel();
    		toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.LINE_AXIS));







   // 		JPanel righttoppanel = new JPanel();
   // 		JTextField textField2 = new JTextField();
    //		textField2.setText("asded");
    //		righttoppanel.add(textField2);

    		//toppanel.add(lefttoppanel);
    		//toppanel.add(righttoppanel);
    		toppanel.add(scrollPane);


    //		JPanel bottompanel = new JPanel();
   // 		JTextField textField3 = new JTextField();
    //		textField3.setText("assaas");
   // 		bottompanel.add(textField3);

    		alertdetailpanel.add(toppanel);
    		//alertdetailpanel.add(bottompanel);




    	}

    	private void preprocessing()
    	{
    		System.out.println(inalert.getRawData());

    		String[] allalertdetails =inalert.getRawData().split("\\*");

    		List<String> listalertdetails = new ArrayList<String>(allalertdetails.length);

    		for (String s : allalertdetails) {
    			listalertdetails.add(s);
    		}


    		//listalertdetails.re
    		AlertVersion=listalertdetails.get(1);


    		if(AlertVersion.equals("7"))
    		{

    			ParameterPage=listalertdetails.get(30);
    			listalertdetails.remove(30);
    			System.out.println("ParameterPage is:"+ParameterPage);

    			StackTrace=listalertdetails.get(19);
    			listalertdetails.remove(19);
    			System.out.println("StackTrace is:"+StackTrace);

    			PALData=listalertdetails.get(24);
    			listalertdetails.remove(24);
    			TraceList=listalertdetails.get(23);
    			System.out.println("tracelist is:"+TraceList);
    			listalertdetails.remove(23);


    		}


    		if(AlertVersion.equals("8"))
    		{

    			ParameterPage=listalertdetails.get(35);
    			listalertdetails.remove(35);
    			System.out.println("ParameterPage is:"+ParameterPage);

    			StackTrace=listalertdetails.get(34);
    			listalertdetails.remove(34);
    			System.out.println("StackTrace is:"+StackTrace);

    			PALData=listalertdetails.get(29);
    			listalertdetails.remove(29);
    			TraceList=listalertdetails.get(28);
    			System.out.println("tracelist is:"+TraceList);
    			listalertdetails.remove(28);


    		}

    	//	List<String> listalertdetailsreverse= Lists.reve
    		Collections.reverse(listalertdetails);

    		StringBuilder buf = new StringBuilder();
    		buf.append("<html>" +
    				"<style>table{border: 1px solid black;border-spacing: 0px;font-family: Courier New;border-collapse: collapse;}</style>"+
    		           "<body>" +
    		           "<table cellpadding=0 cellspacing=0>"
    		           /*+
    		           "<tr>" +
    		           "<th>Interviewe</th>" +
    		           "<th>Timing1</th>" +
    		           "<th>Timing2</th>" +
    		           "</tr>"*/);
    		for (int i = 0; i < listalertdetails.size(); i++) {
    		    buf.append("<tr><td>")
    		       .append(getTitleLabel(i))
    		       .append("</td><td>")
    		       .append(listalertdetails.get(i))
    		       .append("</td><tr>");

    		}
    		buf.append("</table>" +
    		           "</body>" +
    		           "</html>");
    		this.html = buf.toString();

    		//System.out.println(html);


    	}

    	private String getTitleLabel(int i)
    	{
    		String title="toto";

    		if(AlertVersion.equals("7"))
			{
			switch (i){

			case 0: title="MESSAGE";
			break;
			case 1: title="STEPPAGENAME";
			break;
			case 2: title="STEPPAGECLASS";
			break;
			case 3: title="PRIMARYPAGENAME";
			break;
			case 4: title="PRIMARYPAGECLASS";
			break;
			case 5: title="LASTSTEP";
			break;
			case 6: title="FIRSTACTIVITY";
			break;
			case 7: title="LASTINPUT";
			break;
			case 8: title="STACK";
			break;
			case 9: title="LOGGER";
			break;
			case 10: title="PEGATHREAD";
			break;
			case 11: title="THREAD";
			break;
			case 12: title="ALERTNUMBER";
			break;
			case 13: title="INTERACTIONNUMBER";
			break;
			case 14: title="PERSONALRULESETYN";
			break;
			case 15: title="ENCODEDRULESET";
			break;
			case 16: title="RULEAPPNAMEANDVERSION";
			break;
			case 17: title="WORKPOOL";
			break;
			case 18: title="USERID";
			break;
			case 19: title="REQUESTORID";
			break;
			case 20: title="TENANTIDHASH";
			break;
			case 21: title="TENANTID";
			break;
			case 22: title="NODEID";
			break;
			case 23: title="THRESHOLDKPI";
			break;
			case 24: title="OBSERVEDKPI";
			break;
			case 25: title="MESSAGEID";
			break;
			case 26: title="VERSION";
			break;
			case 27: title="TIMESTAMP";
			break;


			}



			}



    		if(AlertVersion.equals("8"))
    				{
    				switch (i){

    				case 0: title="MESSAGE";
    				break;
    				case 1: title="STEPPAGENAME";
    				break;
    				case 2: title="STEPPAGECLASS";
    				break;
    				case 3: title="PRIMARYPAGENAME";
    				break;
    				case 4: title="PRIMARYPAGECLASS";
    				break;
    				case 5: title="FUTURE4";
    				break;
    				case 6: title="FUTURE3";
    				break;
    				case 7: title="FUTURE2";
    				break;
    				case 8: title="FUTURE1";
    				break;
    				case 9: title="LASTSTEP";
    				break;
    				case 10: title="FIRSTACTIVITY";
    				break;
    				case 11: title="LASTINPUT";
    				break;
    				case 12: title="STACK";
    				break;
    				case 13: title="LOGGER";
    				break;
    				case 14: title="PEGATHREAD";
    				break;
    				case 15: title="THREAD";
    				break;
    				case 16: title="ALERTNUMBER";
    				break;
    				case 17: title="CORRELATIONID";
    				break;
    				case 18: title="INTERACTIONNUMBER";
    				break;
    				case 19: title="PERSONALRULESETYN";
    				break;
    				case 20: title="ENCODEDRULESET";
    				break;
    				case 21: title="RULEAPPNAMEANDVERSION";
    				break;
    				case 22: title="WORKPOOL";
    				break;
    				case 23: title="USERID";
    				break;
    				case 24: title="REQUESTORID";
    				break;
    				case 25: title="TENANTIDHASH";
    				break;
    				case 26: title="TENANTID";
    				break;
    				case 27: title="NODEID";
    				break;
    				case 28: title="THRESHOLDKPI";
    				break;
    				case 29: title="OBSERVEDKPI";
    				break;
    				case 30: title="MESSAGEID";
    				break;
    				case 31: title="VERSION";
    				break;
    				case 32: title="TIMESTAMP";
    				break;


    				}



    				}


    		return title;
    	}

    	private void PALDataTab(JPanel paldatapanel){
    		//System.out.println("PAL data:"+PALData);
    		String[] paldataarray =PALData.split(";");

    		List<String> listpaldata = new ArrayList<String>(paldataarray.length);
    		for (String s : paldataarray) {
    			listpaldata.add(s);
    		}

    		StringBuilder buf = new StringBuilder();
    		buf.append("<html>" +
    		           "<body>" +
    		           "<table>"
    		           /*+
    		           "<tr>" +
    		           "<th>Interviewe</th>" +
    		           "<th>Timing1</th>" +
    		           "<th>Timing2</th>" +
    		           "</tr>"*/);
    		for (int i = 0; i < listpaldata.size(); i++) {
    			System.out.println("stuff"+listpaldata.get(i));
    		    buf.append("<tr><td>")
    		       .append(listpaldata.get(i))
    		       .append("</td><tr>");

    		}
    		buf.append("</table>" +
    		           "</body>" +
    		           "</html>");

    		PALData=buf.toString();
    		System.out.println("PAL data as HTML:"+PALData);

    		JEditorPane editorPane = new JEditorPane();
    		editorPane.setEditable(false);
    		editorPane.setContentType("text/html");
    		editorPane.setText(PALData);


    		//scrollPane.setContent(editorPane);

    		JScrollPane scrollPane = new JScrollPane(editorPane);
    		paldatapanel.setLayout(new BoxLayout(paldatapanel, BoxLayout.PAGE_AXIS));

    		paldatapanel.add(scrollPane);

    	}

    	private void traceListTab(JPanel tracelistpanel){
    		System.out.println("TraceList data:"+TraceList);

    	}


    	private void stackTrace(JPanel stacktracepanel){
    		System.out.println("StackTrace data:"+StackTrace);
    		String[] stacktracearray =StackTrace.split(";");

    		List<String> stacktracelist = new ArrayList<String>(stacktracearray.length);
    		for (String s : stacktracearray) {
    			stacktracelist.add(s);
    		}

    		StringBuilder buf = new StringBuilder();
    		buf.append("<html>" +
    		           "<body>" +
    		           "<table>"
    		           /*+
    		           "<tr>" +
    		           "<th>Interviewe</th>" +
    		           "<th>Timing1</th>" +
    		           "<th>Timing2</th>" +
    		           "</tr>"*/);
    		for (int i = 0; i < stacktracelist.size(); i++) {
    			//System.out.println("stuff"+stacktracelist.get(i));
    		    buf.append("<tr><td>")
    		       .append(stacktracelist.get(i))
    		       .append("</td><tr>");

    		}
    		buf.append("</table>" +
    		           "</body>" +
    		           "</html>");

    		StackTrace=buf.toString();
    		System.out.println("StackTrace data as HTML:"+StackTrace);

    		JEditorPane editorPane = new JEditorPane();
    		editorPane.setEditable(false);
    		editorPane.setContentType("text/html");
    		editorPane.setText(StackTrace);
    		//scrollPane.setContent(editorPane);

    		JScrollPane scrollPane = new JScrollPane(editorPane);
    		stacktracepanel.setLayout(new BoxLayout(stacktracepanel, BoxLayout.PAGE_AXIS));

    		stacktracepanel.add(scrollPane);


    	}




    	private void parameterPage(JPanel parampagepanel){
    		//System.out.println("param page data:"+ParameterPage);
    		String[] parameterpagearray =ParameterPage.split(";");

    		List<String> parameterpage = new ArrayList<String>(parameterpagearray.length);
    		for (String s : parameterpagearray) {
    			parameterpage.add(s);
    		}

    		StringBuilder buf = new StringBuilder();
    		buf.append("<html>" +
    		           "<body>" +
    		           "<table>"
    		           /*+
    		           "<tr>" +
    		           "<th>Interviewe</th>" +
    		           "<th>Timing1</th>" +
    		           "<th>Timing2</th>" +
    		           "</tr>"*/);
    		for (int i = 0; i < parameterpage.size(); i++) {
    			//System.out.println("parameterpage stuff"+parameterpage.get(i));
    		    buf.append("<tr><td>")
    		       .append(parameterpage.get(i))
    		       .append("</td><tr>");

    		}
    		buf.append("</table>" +
    		           "</body>" +
    		           "</html>");

    		ParameterPage=buf.toString();
    		//System.out.println("PAL data as HTML:"+PALData);

    		JEditorPane editorPane = new JEditorPane();
    		editorPane.setEditable(false);
    		editorPane.setContentType("text/html");
    		editorPane.setText(ParameterPage);
    		//scrollPane.setContent(editorPane);

    		JScrollPane scrollPane = new JScrollPane(editorPane);
    		parampagepanel.setLayout(new BoxLayout(parampagepanel, BoxLayout.PAGE_AXIS));

    		parampagepanel.add(scrollPane);

    	}


}
