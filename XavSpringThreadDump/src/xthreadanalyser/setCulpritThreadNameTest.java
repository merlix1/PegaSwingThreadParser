package xthreadanalyser;

import static org.junit.Assert.*;

import org.junit.Test;

public class setCulpritThreadNameTest {

	@Test
	public void testsetCulpritThreadNameNoResult() {

		StringBuilder inthreadump= new StringBuilder();
		String infirsline= new String();
		infirsline="2018-07-05 00:02:48,367 [fault (self-tuning)'] [  STANDARD] [                    ] [       MCCM:01.01.01] (          internal.async.Agent) INFO    - System date: Thu Jul 05 00:02:48 CEST";
		String inlastline= new String();
		inlastline="2018-07-05 00:02:48,367 [fault (self-tuning)'] [  STANDARD] [                    ] [       MCCM:01.01.01] (          internal.async.Agent) INFO    - System date: Thu Jul 05 00:02:48 CEST";
		//(StringBuilder inthreadump, String infirsline, String inlastline)

		PRPCThreadDump tdump= new PRPCThreadDump(inthreadump,infirsline,inlastline);

		tdump.getCulpritThreadName();
		String result=tdump.getCulpritThreadName();
		assertEquals(result,"");
	}

	@Test
	public void testsetCulpritThreadNameResultTomcat1() {

		StringBuilder inthreadump= new StringBuilder();
		String infirsline= new String();
		infirsline="2018-07-05 00:02:48,367 [fault (self-tuning)'] [  STANDARD] [                    ] [       MCCM:01.01.01] (          internal.async.Agent) INFO    - System date: Thu Jul 05 00:02:48 CEST";
		String inlastline= new String();
		inlastline="com.pega.pegarules.pub.context.RequestorLockException: Unable to synchronize on requestor HB7B2DB5A2435892A76AA652B86184BC2 within 120 seconds: (thisThread = ajp-bio-8009-exec-156) (originally locked by = ajp-bio-8009-exec-182) (finally locked by = ajp-bio-8009-exec-124)";
		//(StringBuilder inthreadump, String infirsline, String inlastline)

		PRPCThreadDump tdump= new PRPCThreadDump(inthreadump,infirsline,inlastline);

		tdump.getCulpritThreadName();
		String result=tdump.getCulpritThreadName();
		assertEquals(result,"ajp-bio-8009-exec-124");
	}


	@Test
	public void testsetCulpritThreadNameResultWeblogic1() {

		StringBuilder inthreadump= new StringBuilder();
		String infirsline= new String();
		infirsline="2018-07-05 00:02:48,367 [fault (self-tuning)'] [  STANDARD] [                    ] [       MCCM:01.01.01] (          internal.async.Agent) INFO    - System date: Thu Jul 05 00:02:48 CEST";
		String inlastline= new String();
		inlastline="com.pega.pegarules.pub.context.RequestorLockException: Unable to synchronize on requestor H12B711A026F0ED40FDBD16DD1BC37C87 within 120 seconds: (thisThread = [ACTIVE] ExecuteThread: '30' for queue: 'weblogic.kernel.Default (self-tuning)') (originally locked by = [ACTIVE] ExecuteThread: '31' for queue: 'weblogic.kernel.Default (self-tuning)') (finally locked by = [ACTIVE] ExecuteThread: '31' for queue: 'weblogic.kernel.Default (self-tuning)')";

		PRPCThreadDump tdump= new PRPCThreadDump(inthreadump,infirsline,inlastline);

		tdump.getCulpritThreadName();
		String result=tdump.getCulpritThreadName();
		assertEquals(result,"[ACTIVE] ExecuteThread: '31' for queue: 'weblogic.kernel.Default (self-tuning)'");
	}




}
