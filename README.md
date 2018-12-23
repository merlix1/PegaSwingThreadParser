# PegaSwingThreadParser

Note 1: This project is using Swing and JavaFX (well, mostly JavaFX but some weird performance issue made me use Swing for some views).

Note 2: No Spring api used here.

Note 3: If you import this project in Eclipse with Java 8, follow these steps afterwward:
Go to project properties->build path->library tab-> expand JRE system library-> You should see "no rules"->add following rule:
Accessible: javafx/**


Also called UTSOR: Unable To Synchonize On Requestor.

 

Here is a beta utility  to scan a folder for PegaRules logs for any "unable to synchronize on requestor" message and then link them with relevant alert in PegaRULES-ALERT logs.


The selling point is that it does all the filtering for you so you only the alert associated to the same requestor and thread which caused the "unable to synchonize on requestor" message.

The goal there was to straight away identify culprits and save time

Note: this needs JAVA 8 and should work with PRPC 7.1 and above.


In order to run it, unzip version14.zip in a folder and click on “UTSORsmasher1-4.jar”.

Then do File->Scan Folder and select a folder which contains Pega logs and Pega alert logs.

If it contains a “unable to synchronize on requestor” then this should be processed.

 

If you have any SR with such message "unable to synchronize on requestor"  then I would recommend to test this tool.
