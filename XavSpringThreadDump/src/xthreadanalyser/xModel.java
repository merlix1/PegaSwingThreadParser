package xthreadanalyser;

import java.util.ArrayList;
import java.util.List;

public class xModel {

	public xModel(final Controller controller){





	}

	public static List<ThreadDumpAggregate> open() {
		// TODO Auto-generated method stub
		System.out.println("xav: open menu has been clicked");
		
		List<ThreadDumpAggregate> listag= new ArrayList<>();
				
		listag=FileUtilities.getListAggregate();

		
		
		//return threaddumpaggregate;
		return listag;
		
		



	}




}
