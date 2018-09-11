package xthreadanalyser;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtilities {

	//list of PegaRules log file
	private static List<Path> pegarules = new ArrayList<>();
	//list of PegaRules ALERT log file
	private static List<Path> pegarulesalert = new ArrayList<>();


	// read a Pega logs file and extract all thread dumps
	//public static PRPCThreadDump getThreadDump()
	public static List<PRPCThreadDump> getThreadDumps()

	{


		List<PRPCThreadDump> threaddumps=new ArrayList<>();

		StringBuilder buffer = new StringBuilder();




			for(Path pathpegarules: pegarules)
				{

				buffer.setLength(0);
				BufferedReader reader=null;
				try {
						String line;
						String firstline=new String();
			//			BufferedReader reader = new BufferedReader(new FileReader("PegaRULES-2017-Apr-12.log"));
						reader = new BufferedReader(new FileReader(pathpegarules.toString()));

							boolean capture= false;

						String timezone= new String();
						boolean capturetimezone=false;
						PRPCThreadDump tempthreaddump = null;

							while ((line = reader.readLine()) != null)
							{

										//we need to capture the timezone
										if((!capturetimezone)&&line.contains("Total memory:"))
												{
											   timezone=setTimezone(line);
												capturetimezone=true;
												}
										//order is important...
										if(capture)
										 {
											 buffer.append(line);
											 buffer.append("\n");

											 //below, second thread dump found so 1rst didn't end with "unable to sync..."
											 //removing first thread dump
											 if(line.contains("Thread Dump Starts"))
											 {
												 capture= false;
												 buffer.setLength(0);
												 System.out.println("detectiooooooooooon");
												 continue;


											 }
										 }
										 if(line.contains("Thread Dump Starts"))
										 {
											 capture= true;
											 firstline=line;
											 buffer.append(line);
											 buffer.append("\n");

										 }

								 // the line found here will contain the culprit thread name and the culprit requestor ID and the culprit thread
										 if(line.contains("Unable to synchronize on requestor")&& capture)
										 {
											 //parameter line used below correspond to last line
											 tempthreaddump= new PRPCThreadDump(buffer, firstline,line);
											 tempthreaddump.setFilename(pathpegarules.getFileName().toString().toUpperCase());
											 capture= false;
											 threaddumps.add(tempthreaddump);
											 buffer.setLength(0);
										 }

							}


							 for( PRPCThreadDump  coco:threaddumps )
							 {

								 //not setting timezone if already set
								 if((coco.getTimezone()==null)||(coco.getTimezone()==""))
								 {
									 coco.setTimezone(timezone);
								 }

							 }

						}
						catch (IOException ex)
						{
				        ex.printStackTrace(System.err);
						}
					finally{

								try {

								reader.close();
								} catch (IOException ex) {

									ex.printStackTrace();

								}
							}


				}




		return threaddumps;

	}



	//retrieve all alerts in all Pega Alert log file given a requestorid and java thread name
	public static List<PRPCAlert> getListSuspectAlerts(String requestorid, String threadname)
	{
		List<PRPCAlert> listsuspectalert= new ArrayList<>();
		String filterbyrequestorid= requestorid;


		try {

					for(Path pathalert: pegarulesalert)
					{
						String line;
						//BufferedReader reader = new BufferedReader(new FileReader("PEGARULES-ALERT-2017-APR-12.LOG"));
						BufferedReader reader = new BufferedReader(new FileReader(pathalert.toString()));

						while ((line = reader.readLine()) != null)
						{
							if((line.contains(filterbyrequestorid))&&(line.contains(threadname)))
							 {
								// get alert
								PRPCAlert alert= new PRPCAlert(line);
								alert.setFilename(pathalert.getFileName().toString().toUpperCase());

							//	listsuspectalert.add(alert);
							//	System.out.println("alert found: " + alert.toString());
								listsuspectalert.add(alert);
								
								
								
							 }

						}
					}

		} catch (IOException ex) {
	        ex.printStackTrace(System.err);
	    }


		return listsuspectalert;

	}


	//soon to be main activity to get all data from logs
	public static List<ThreadDumpAggregate> getListAggregate()
	{

		List<ThreadDumpAggregate> listaggregate= new ArrayList<>();

		for(PRPCThreadDump prpcthreadump:FileUtilities.getThreadDumps())
		{
			ThreadDumpAggregate tmpaggregate= new ThreadDumpAggregate();

			// first we get the tread dump
			tmpaggregate.setThreadDump(prpcthreadump);


			//second we get alerts
			List<PRPCAlert> listsuspectalert= new ArrayList<>();
			//prpcthreadump.getCulpritThreadName()
			listsuspectalert=FileUtilities.getListSuspectAlerts(prpcthreadump.getCulpritRequestorID(),prpcthreadump.getCulpritThreadName());
			tmpaggregate.setPRPCAlerts(listsuspectalert);

			// call threadanalysishere

			listaggregate.add(tmpaggregate);


		}




		return listaggregate;

	}


	//extract timezone used for Pega logs
	private static String setTimezone(String inline)
		{
		String timezone=new String() ;

		//02:51:45 EDT 2017 Total memory
		String patterntimezone = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9]\\s(\\S+)\\s";


		Pattern pattern = Pattern.compile(patterntimezone);
        Matcher matcher = pattern.matcher(inline);
        if (matcher.find()){
        timezone=matcher.group(1);
        }

        System.out.println("timezoneis:"+timezone);
		return timezone;

		}


	public static void openFile(File file) {
		Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException ex) {
           System.out.println("Error opening file");
        }
    }

	public static String listSourceFiles(String sdir) throws IOException {
			String filesfound="";
			boolean isalertfile=false;
			boolean ispegalogfile=false;

			Path dir=Paths.get(sdir);
	       List<Path> result = new ArrayList<>();
	       try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
	           for (Path entry: stream) {

	               result.add(entry);



	               String tmpuppercase;
	               tmpuppercase=entry.getFileName().toString().toUpperCase();


	               String spatternpegarules = "PEGARULES";
	               String spatternpegarulesalert = "ALERT";

	             //get List of PegaRules Alert log file

	               Pattern patternpegarules = Pattern.compile(spatternpegarules);
	               Pattern patternalert = Pattern.compile(spatternpegarulesalert);

	       		   Matcher matcherpegarules = patternpegarules.matcher(tmpuppercase);
	               Matcher matcheralert = patternalert.matcher(tmpuppercase);

	               Boolean isalert= false;


	       			// try find alert log first
	       			if (matcheralert.find())
	       				{
	       				if(matcherpegarules.find())
	       					{
	       					pegarulesalert.add(entry);
	       					isalert=true;
	       					System.out.println("Pegarules Alert files detected:"+tmpuppercase);
	       					filesfound=filesfound+"Pegarules Alert files detected:"+tmpuppercase+"\n";
	       					//filesfound.append("Pegarules Alert files detected:"+tmpuppercase+"\n");
	       					isalertfile=true;

	       					}
	       				}


	               //get List of PegaRules log file

	       			//Pattern pattern = Pattern.compile(patternpegarules);
	       			//Matcher matcher = pattern.matcher(tmpuppercase);
	       			if ((isalert==false)&&matcherpegarules.find())
	       				{
	       				pegarules.add(entry);
	       				//timezone=matcher.group(1);
	       				System.out.println("Pegarules files detected:"+tmpuppercase);
	       				filesfound=filesfound+"Pegarules files detected:"+tmpuppercase+"\n";
	       				//filesfound.concat("Pegarules files detected:"+tmpuppercase+"\n");
	       				ispegalogfile=true;

	       				}


	           }
	       } catch (DirectoryIteratorException ex) {
	           // I/O error encounted during the iteration, the cause is an IOException
	           throw ex.getCause();
	       }

	       if((!ispegalogfile)||(!isalertfile))
	       {
	    	   filesfound=filesfound+"No logs detected!"+"\n";
	    	   //filesfound.concat("No logs detected!"+"\n");

	       }
	       System.out.println("filesfound is"+filesfound);
	       return filesfound;

	   }
}
