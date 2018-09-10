package xthreadanalyser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//this class represent a thread dump found in Pega logs

public class PRPCThreadDump {

	// whole thread dump
	//private StringBuilder threaddump= new StringBuilder();
	private String threaddump= new String();

	// whole culprit thread stack
	private StringBuilder culpritthread=  new StringBuilder();

	// culprit thread name
	private String culpritthreadname=  new String();

	// culprit requestorID
	private String culpritrequestorID= new String();

	//last line of thread dump containing usuful info
	private String lastline= new String();

	private String firstline= new String();

	private String timestamp= new String();


	private String timezone= "";

	private String filename= new String();




	//constructor


	public PRPCThreadDump(StringBuilder inthreadump, String infirsline, String inlastline)
	{
		threaddump=inthreadump.toString();
		lastline=inlastline;
		firstline=infirsline;
		this.setCulpritRequestorID(inlastline);
		this.setCulpritThreadName(inlastline);
		this.setCulpritThread();
		this.setTimestamp();


		//.out.println(inthreadump);
	}


	/*public void setThreadDump(StringBuilder in)
	{

		threaddump= in;


	}*/


	//this is not good, 2 similar method, need to remove the below one
	public StringBuilder getThreadDump1()
	{
		StringBuilder tmp=new StringBuilder(threaddump);

		return  tmp;

	}

	public String getThreadDump()
	{


		return  threaddump;

	}




/*
	public void setCulpritThread(StringBuilder in)
	{

		culpritthread= in;

	}
	*/

	// this returns the whole culprit stack trace
	// Note: the PRPCThreadDump needs to have identified the culpritthreadname
	// contract: culpritthreadname and threaddump must be already populated

	private void setCulpritThread()
	{

		//StringBuilder culpritthread= this.getThreadDump();

		//String culpritthreadname= this.getCulpritThreadName();

		boolean inthread=false;


		//String culpritthreadlines[] = culpritthread.toString().split("\n");
		String culpritthreadlines[] = threaddump.toString().split("\n");
		//System.out.println("are we here?");

		for(String s: culpritthreadlines){

			//System.out.println("mouaa"+culpritthread.toString());

			if(inthread==true){
				//searching for end of thread
				String patternendthread = "\"";
				Pattern pattern = Pattern.compile(patternendthread);
				Matcher matcher = pattern.matcher(s);
							if (matcher.find()){


											inthread=false;
											//System.out.println("found culprot thread: " + culpritthread);

											return;
												}
							else
							{
								culpritthread.append(s);
								culpritthread.append("\n");

							}

					}


			if(inthread==false){
				// searching for beginning of thread

					//too restrictive
					String patternculpritthread = "\""+culpritthreadname+"\"";

					//too permissive thread identification to cover tomcat and weblogic difference. Example:
					// tomcat: (finally locked by = ajp-bio-8009-exec-192)
					// weblogic: (finally locked by = [STUCK] ExecuteThread: '34' for queue: 'weblogic.kernel.Default (self-tuning)')
					//String patternculpritthread = "\""+culpritthreadname;


					// need to not take (finally locked by = ajp-bio-8009-exec-192) instead of (finally locked by = ajp-bio-8009-exec-19) for example


				//causing 	Unclosed character class near index 		"[\D*"
				//String patternculpritthread = "\""+culpritthreadname+"\\D*"+"\"";

					//Pattern pattern = Pattern.compile(patternculpritthread);

					// we need this as oterwise if thread contains "[" it is a mess
					Pattern pattern = Pattern.compile(patternculpritthread, Pattern.LITERAL);

					Matcher matcher = pattern.matcher(s);
					if (matcher.find()){
	        						//System.out.println("found culprit thread: " + matcher.group());
	        						// culpritthread=matcher.group(1);
	        						//culpritthread=new StringBuilder(matcher.group());
	        						culpritthread.append(s);
	        						inthread=true;
	        						}

					}






			}




		return;




	}



	public StringBuilder getCulpritThread()
	{

		return  culpritthread;

	}


	//culprit thread extracted from form: "finally locked by = http-bio-443-exec-61)"
	private void setCulpritThreadName(String inlastline)
	{
		String culpritthread= new String();


		//String patternCulpritThreadName = "finally locked by = (.+?)\\)";

		// get full thread + theoratically ")"
		//String patternCulpritThreadName = "finally locked by = (.+?)";
		String patternCulpritThreadName = "finally locked by = (.++)$";
		//remove ")"
		//patternCulpritThreadName = patternCulpritThreadName.substring(0, patternCulpritThreadName.length() - 1);


		Pattern pattern = Pattern.compile(patternCulpritThreadName);
        Matcher matcher = pattern.matcher(inlastline);
        if (matcher.find()){
        culpritthread=matcher.group(1);
        }

        //removing the extra ")" at the end of the line
        if(culpritthread.length()>1)
        {
        culpritthread = culpritthread.substring(0, culpritthread.length() - 1);
        }

		culpritthreadname= culpritthread;
		System.out.println("culprit thread:"+culpritthreadname);

	}

	public String getCulpritThreadName()
	{



		return  culpritthreadname;

	}



	//culprit thread extracted from form: "finally locked by = http-bio-443-exec-61)"
	private void setCulpritRequestorID(String inlastline)
	{


		String requestorID= new String();
		String patternRequestorID = ".[0-9A-F]{32}";


		Pattern pattern = Pattern.compile(patternRequestorID);
        Matcher matcher = pattern.matcher(inlastline);
        if (matcher.find()){
        System.out.println("found requestorID: " + matcher.group());
        requestorID=matcher.group();
        }

		//return requestorID;


		culpritrequestorID= requestorID;

	}

	public String getCulpritRequestorID()
	{

		return  culpritrequestorID;

	}


	private void setTimestamp()
	{
		//String firstline= threaddump.toString().split("\\n");

		timestamp=firstline.substring(0, 23);
		System.out.println("timestamp extracted"+timestamp);
	}

	//it is a timestamp without timezone
	public String getTimestamp()
	{
		return 	timestamp;
	}


	public void setTimezone(String intimezone)
	{
		timezone=intimezone;
	}

	public String getTimezone()
	{
		return 	timezone;
	}

	public void setFilename(String infilename)
	{
		filename=infilename;
	}

	public String getFilename()
	{
		return 	filename;
	}







}
