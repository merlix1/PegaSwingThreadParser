package xthreadanalyser;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;



//this class represent 1 thread dump along with all associated alerts
public class ThreadDumpAggregate {


	//PRPCThreadDump threaddump= new PRPCThreadDump();
	PRPCThreadDump threaddump;
	List<PRPCAlert> listsuspectalert= new ArrayList<>();

	// culprit thread name
	private String culpritthreadname=  new String();
	// culprit requestorID
	private String culpritrequestorID= new String();


	private StringBuilder threadanalysis= new StringBuilder();

	//used to identify interval during thread hang:
	private ZonedDateTime startissue;
	private ZonedDateTime endissue;



	ThreadDumpAggregate(){



	}


	public PRPCThreadDump getThreadDump(){

		return threaddump;

	}


	public void setThreadDump(PRPCThreadDump inthreaddump){
		threaddump= inthreaddump;
		}


   public List<PRPCAlert> getPRPCAlerts(){

		return listsuspectalert;

	}

   public void setPRPCAlerts(List<PRPCAlert> listalert){
	   listsuspectalert= listalert;
		}


	// analysis code
   //contract: all properties of this instance should be already populated
	public StringBuilder threadanalysis()
	{

		//list of alert measuring a time:
    	List<String> listtimealert= new ArrayList<>();
    	listtimealert.add("PEGA0001");
    	listtimealert.add("PEGA0005");
    	listtimealert.add("PEGA0006");
    	listtimealert.add("PEGA0007");
    	listtimealert.add("PEGA0011");
    	listtimealert.add("PEGA0012");
    	listtimealert.add("PEGA0013");
    	listtimealert.add("PEGA0014");
    	listtimealert.add("PEGA0015");
    	listtimealert.add("PEGA0019");
    	listtimealert.add("PEGA0020");
    	listtimealert.add("PEGA0037");
    	listtimealert.add("PEGA0038");
    	listtimealert.add("PEGA0058");
    	listtimealert.add("PEGA0059");
    	listtimealert.add("PEGA0062");
    	listtimealert.add("PEGA0063");
    	listtimealert.add("PEGA0065");
    	listtimealert.add("PEGA0074");
    	listtimealert.add("PEGA0075");
    	listtimealert.add("PEGA0077");
    	listtimealert.add("PEGA0081");


		// do we need to check if there are 0 alert in listsuspectalert???
		if((threaddump==null)||(listsuspectalert==null) )
		{
			//shall we throw some exception here?
			threadanalysis.append("Something is wrong");


			return threadanalysis;}


				//convert to GMT  sample:2017-03-29 03:27:29,042
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
				LocalDateTime ldt = LocalDateTime.parse(this.threaddump.getTimestamp(), format);


		        //timezone in Pegarules
				String tmpzone=this.threaddump.getTimezone();



				tmpzone=TimeZoneUtil.ShortToUTC(tmpzone);
				System.out.println("UTC Conversion to:"+tmpzone);


		        ZonedDateTime klDateTime = ldt.atZone(ZoneId.of(tmpzone));
		    //    System.out.println("Depart : " + format.format(klDateTime));

		        //GMT
		        endissue = klDateTime.withZoneSameInstant(ZoneId.of("GMT"));
		       // System.out.println("Arrive : " + format.format(gmtDateTime));

		        // start of issue 120 sec -> 2min in GMT
		        startissue=endissue.minusMinutes(2);


		        threadanalysis.append("Thread dump on file:                                                  "+this.threaddump.getFilename()+"       ");
				threadanalysis.append("       Generated at: "+this.threaddump.getTimestamp()+" "+this.threaddump.getTimezone());
				threadanalysis.append(" -> "+format.format(endissue)+" GMT"+"\"\n");
				if(this.listsuspectalert.size()!=0){
					threadanalysis.append("Dectected relevant alert on PegaRULES-ALERT file:  "+this.listsuspectalert.get(0).getFilename()+"\"\n");
				}

				threadanalysis.append("*** Thread hanging since: "+format.format(startissue)+"\"\n");


				//threadanalysis.append("User name: \""+this.threaddump.+"\"\n");

		        //now we need to identify and flag PRPC alert during interval and with relevant KPI


		        for(PRPCAlert alert: listsuspectalert)
		        {
		        	//flag all alerts during the 2 min interval:
		        	ZonedDateTime tmpalert=alert.getGmttimealert();

		        	if((tmpalert.compareTo(startissue)>0)&&(tmpalert.compareTo(endissue)<0))
		        	{
		        		// set flag to level 1
		        		alert.setFlaglevel(1);
		        		//System.out.println("alert in interval"+alert.getRawData());
		        	}


		        	//flag relevant   alert  alert.getMessageid()=="PEGA0001"



		        	//if(alert.getMessageid().equals("PEGA0001")||alert.getMessageid().equals("PEGA0005"))

		        	//Checking alerts
		         	if(listtimealert.contains(alert.getMessageid()))
		         			{

		         			String tmpstartalert;

		         			//only consider alert > 1000 ms
		         			if(alert.getObservedkpi().length()>3){
		         				tmpstartalert=alert.getObservedkpi().substring(0,alert.getObservedkpi().length()-3);

		         			}
		         			else
		         			{
		         				tmpstartalert="0";
		         			}
		         			//  +1 to compensate for truncation
		         			Integer tmpintstartalert=Integer.parseInt(tmpstartalert)+1;
		         				if((tmpalert.compareTo(endissue)>0)&&(tmpalert.minusSeconds(tmpintstartalert).compareTo(startissue)<0))
		         						{
		         						alert.setFlaglevel(2);

		         						if(alert.getMessageid().equals("PEGA0005"))
			         						{
		         							threadanalysis.append("High confidence the issue is caused by following SQL query:\n"+alert.getMessage()+"\n");
		         							threadanalysis.append(" Check PEGA0005 alert at "+alert.getTimestamp()+" for more details\n");

			         						}
		         						if(alert.getMessageid().equals("PEGA0001"))
		         						{
		         							threadanalysis.append(" Recommendation: Check PEGA0001 alert at"+alert.getTimestamp());

		         						}


		         						}




		         			}




		        }










		return threadanalysis;

	}


}
