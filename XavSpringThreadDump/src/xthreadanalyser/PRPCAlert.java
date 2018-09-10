package xthreadanalyser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class PRPCAlert {


	private String rawdata;

	private String timestamp;
	private String version;
	private String messageid;
	private String observedkpi;
	private Integer intobservedkpi;

	private String thresholdkpi;
	private String nodeid;
	private String requestorid;
	private String userid;
	private String thread;
	private String pegathread;
	private String logger;
	private String stack;
	private String lastinput;
	private String firstactivity;
	private String laststep;
	private String message;

	private String filename;

	private ZonedDateTime gmttimealert;

	//level of suspiciousness of the alert
	private Integer flaglevel;


	// we populate all alert properties in constructor
	PRPCAlert(String inrawdata){
		rawdata=inrawdata;


		flaglevel=0;

		//StringTokenizer stringTokenizer = new StringTokenizer(rawdata, "*");

		String[] fields = rawdata.split("\\*");

		//Attention: populating need to be done in order
		//ORDER IMPORTANT HERE

		/*mmm
		 this.setTimestamp(stringTokenizer.nextToken());

		this.setVersion(stringTokenizer.nextToken());

		System.out.println(version);
		if(version.equals("8"))
		{
			System.out.println("inside");
		this.setMessageid(stringTokenizer.nextToken());
		this.setObservedkpi(stringTokenizer.nextToken());
		this.setThresholdkpi(stringTokenizer.nextToken());
		this.setNodeid(stringTokenizer.nextToken());
		stringTokenizer.nextToken();
		stringTokenizer.nextToken();
		this.setRequestorid(stringTokenizer.nextToken());
		this.setUserid(stringTokenizer.nextToken());
		stringTokenizer.nextToken();
		stringTokenizer.nextToken();
		stringTokenizer.nextToken();
		stringTokenizer.nextToken();
		stringTokenizer.nextToken();
		stringTokenizer.nextToken();
		stringTokenizer.nextToken();
		this.setThread(stringTokenizer.nextToken());
		this.setPegathread(stringTokenizer.nextToken());
		this.setLogger(stringTokenizer.nextToken());
		stringTokenizer.nextToken();
		this.setLastinput(stringTokenizer.nextToken());
		this.setFirstactivity(stringTokenizer.nextToken());
		this.setLaststep(stringTokenizer.nextToken());
		System.out.println(stringTokenizer.nextToken());
		System.out.println(stringTokenizer.nextToken());
		System.out.println(stringTokenizer.nextToken());
		System.out.println(stringTokenizer.nextToken());
		System.out.println(stringTokenizer.nextToken());
		System.out.println(stringTokenizer.nextToken());
		System.out.println(stringTokenizer.nextToken());
		this.setMessage(stringTokenizer.nextToken());
		//stringTokenizer.nextToken();
		//stringTokenizer.nextToken();
		//stringTokenizer.nextToken();
		//stringTokenizer.nextToken();
		//this.setMessage(stringTokenizer.nextToken());
		}
		*/

		this.setTimestamp(fields[0]);
		this.setVersion(fields[1]);


		if(version.equals("7"))
		{
		this.setMessageid(fields[2]);
		this.setObservedkpi(fields[3]);
		this.setThresholdkpi(fields[4]);
		this.setNodeid(fields[5]);
		//fields[6];
		//fields[];
		this.setRequestorid(fields[8]);
		this.setUserid(fields[9]);
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		this.setThread(fields[16]);
		this.setPegathread(fields[17]);
		this.setLogger(fields[18]);
		//fields[0];
		this.setLastinput(fields[20]);
		this.setFirstactivity(fields[21]);
		this.setLaststep(fields[22]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//this.setMessage(fields[0]);
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		this.setMessage(fields[31]);
		}


		if(version.equals("8"))
		{
		this.setMessageid(fields[2]);
		this.setObservedkpi(fields[3]);
		this.setThresholdkpi(fields[4]);
		this.setNodeid(fields[5]);
		//fields[6];
		//fields[];
		this.setRequestorid(fields[8]);
		this.setUserid(fields[9]);
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		this.setThread(fields[17]);
		this.setPegathread(fields[18]);
		this.setLogger(fields[19]);
		//fields[0];
		this.setLastinput(fields[21]);
		this.setFirstactivity(fields[22]);
		this.setLaststep(fields[23]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//System.out.println(fields[0]);
		//this.setMessage(fields[0]);
		//fields[0];
		//fields[0];
		//fields[0];
		//fields[0];
		this.setMessage(fields[36]);
		}



	}


	public String toString(){

		String alerttostring=  timestamp+" "+messageid+"Pega Thread"+pegathread;

		return alerttostring;
	}


	public String getRawData(){

		return rawdata;
	}

	public void setRawData(String line){

		rawdata=line;
	}


	private void setTimestamp(String intimestamp)
	{
		timestamp= intimestamp;
		//while we are here...
		this.setGmttimealert(intimestamp.substring(0, 23));

	}
	public String getTimestamp()
	{
		return timestamp;
	}

	private void setVersion(String inversion)
	{
		version=inversion;


	}






	private void setMessageid(String inmessageid)
	{
		messageid= inmessageid;
	}
	public String getMessageid()
	{
		return messageid;
	}

	private void setGmttimealert(String timealert)
	{

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
		LocalDateTime ldt = LocalDateTime.parse(timealert, format);
		gmttimealert = ldt.atZone(ZoneId.of("GMT"));

	}

	public ZonedDateTime getGmttimealert()
	{


		return gmttimealert;
	}



	private void setObservedkpi(String inobservedkpi)
	{
		observedkpi= inobservedkpi;
		this.setIntObservedkpi(inobservedkpi);
	}
	public String getObservedkpi()
	{
		return observedkpi;
	}

	private void setIntObservedkpi(String inobservedkpi)
	{
		intobservedkpi= Integer.parseInt(inobservedkpi);;
	}
	public Integer getIntObservedkpi()
	{
		return intobservedkpi;
	}






	private void setThresholdkpi(String inthresholdkpi)
	{
		thresholdkpi= inthresholdkpi;
	}
	public String getThresholdkpi()
	{
		return thresholdkpi;
	}

	private void setNodeid(String innodeid)
	{
		nodeid= innodeid;
	}
	public String getNodeid()
	{
		return nodeid;
	}

	private void setRequestorid(String inrequestorid)
	{
		requestorid= inrequestorid;
	}
	public String getRequestorid()
	{
		return requestorid;
	}

	private void setUserid(String inuserid)
	{
		userid= inuserid;
	}
	public String getUserid()
	{
		return userid;
	}

	private void setThread(String inthread)
	{
		thread= inthread;
	}
	public String getThread()
	{
		return thread;
	}

	private void setPegathread(String inpegathread)
	{
		pegathread= inpegathread;
	}
	public String getPegathread()
	{
		return pegathread;
	}

	private void setLogger(String inlogger)
	{
		logger= inlogger;
	}
	public String getLogger()
	{
		return logger;
	}
	private void setStack(String instack)
	{
		stack= instack;
	}
	public String getStack()
	{
		return stack;
	}

	private void setLastinput(String inlastinput)
	{
		lastinput= inlastinput;
	}
	public String getLastinput()
	{
		return lastinput;
	}


	private void setFirstactivity(String infirstactivity)
	{
		firstactivity= infirstactivity;
	}
	public String getFirstactivity()
	{
		return firstactivity;
	}


	private void setLaststep(String inlaststep)
	{
		laststep= inlaststep;
	}
	public String getLaststep()
	{
		return laststep;
	}

	private void setMessage(String inmessage)
	{
		message= inmessage;
	}
	public String getMessage()
	{
		return message;
	}

	public void setFlaglevel(Integer in)
	{
		flaglevel=in;

	}
	public Integer getFlaglevel()
	{
		return flaglevel;

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
