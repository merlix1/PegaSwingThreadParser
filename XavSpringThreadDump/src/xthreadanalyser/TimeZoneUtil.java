package xthreadanalyser;

public class TimeZoneUtil {

	
	public static String ShortToUTC(String timezone)
	{
		
		
		
				if(timezone.equals("ACDT")){timezone="UTC+10:30";}
				else if(timezone.equals("ACST")){timezone="UTC+09:30";}
				else if(timezone.equals("ACT")){timezone="UTC-05";}
				else if(timezone.equals("ACT")){timezone="UTC+06:30";}
				else if(timezone.equals("ADT")){timezone="UTC-03";}
				else if(timezone.equals("AEDT")){timezone="UTC+11";}
				else if(timezone.equals("AEST")){timezone="UTC+10";}
				else if(timezone.equals("AFT")){timezone="UTC+04:30";}
				else if(timezone.equals("AKDT")){timezone="UTC-08";}
				else if(timezone.equals("AKST")){timezone="UTC-09";}
				else if(timezone.equals("AMST")){timezone="UTC-03";}
				else if(timezone.equals("AMT")){timezone="UTC-04";}
				else if(timezone.equals("AMT")){timezone="UTC+04";}
				else if(timezone.equals("ART")){timezone="UTC-03";}
				else if(timezone.equals("AST")){timezone="UTC+03";}
				else if(timezone.equals("AST")){timezone="UTC-04";}
				else if(timezone.equals("AWST")){timezone="UTC+08";}
				else if(timezone.equals("AZOST")){timezone="UTC±00";}
				else if(timezone.equals("AZOT")){timezone="UTC-01";}
				else if(timezone.equals("AZT")){timezone="UTC+04";}
				else if(timezone.equals("BDT")){timezone="UTC+08";}
				else if(timezone.equals("BIOT")){timezone="UTC+06";}
				else if(timezone.equals("BIT")){timezone="UTC-12";}
				else if(timezone.equals("BOT")){timezone="UTC-04";}
				else if(timezone.equals("BRST")){timezone="UTC-02";}
				else if(timezone.equals("BRT")){timezone="UTC-03";}
				//else if(timezone.equals("BST")){timezone="UTC+06";}
				//else if(timezone.equals("BST")){timezone="UTC+11";}
				else if(timezone.equals("BST")){timezone="UTC+01";}
				else if(timezone.equals("BTT")){timezone="UTC+06";}
				else if(timezone.equals("CAT")){timezone="UTC+02";}
				else if(timezone.equals("CCT")){timezone="UTC+06:30";}
				else if(timezone.equals("CDT")){timezone="UTC-05";}
				else if(timezone.equals("CDT")){timezone="UTC-04";}
				else if(timezone.equals("CEST")){timezone="UTC+02";}
				else if(timezone.equals("CET")){timezone="UTC+01";}
				else if(timezone.equals("CHADT")){timezone="UTC+13:45";}
				else if(timezone.equals("CHAST")){timezone="UTC+12:45";}
				else if(timezone.equals("CHOT")){timezone="UTC+08";}
				else if(timezone.equals("CHOST")){timezone="UTC+09";}
				else if(timezone.equals("CHST")){timezone="UTC+10";}
				else if(timezone.equals("CHUT")){timezone="UTC+10";}
				else if(timezone.equals("CIST")){timezone="UTC-08";}
				else if(timezone.equals("CIT")){timezone="UTC+08";}
				else if(timezone.equals("CKT")){timezone="UTC-10";}
				else if(timezone.equals("CLST")){timezone="UTC-03";}
				else if(timezone.equals("CLT")){timezone="UTC-04";}
				else if(timezone.equals("COST")){timezone="UTC-04";}
				else if(timezone.equals("COT")){timezone="UTC-05";}
				else if(timezone.equals("CST")){timezone="UTC-06";}
				else if(timezone.equals("CST")){timezone="UTC+08";}
				else if(timezone.equals("ACST")){timezone="UTC+09:30";}
				else if(timezone.equals("ACDT")){timezone="UTC+10:30";}
				else if(timezone.equals("CST")){timezone="UTC-05";}
				else if(timezone.equals("CT")){timezone="UTC+08";}
				else if(timezone.equals("CVT")){timezone="UTC-01";}
				else if(timezone.equals("CWST")){timezone="UTC+08:45";}
				else if(timezone.equals("CXT")){timezone="UTC+07";}
				else if(timezone.equals("DAVT")){timezone="UTC+07";}
				else if(timezone.equals("DDUT")){timezone="UTC+10";}
				else if(timezone.equals("DFT")){timezone="UTC+01";}
				else if(timezone.equals("EASST")){timezone="UTC-05";}
				else if(timezone.equals("EAST")){timezone="UTC-06";}
				else if(timezone.equals("EAT")){timezone="UTC+03";}
				else if(timezone.equals("ECT")){timezone="UTC-04";}
				else if(timezone.equals("ECT")){timezone="UTC-05";}
				else if(timezone.equals("EDT")){timezone="UTC-04";}
				else if(timezone.equals("AEDT")){timezone="UTC+11";}
				else if(timezone.equals("EEST")){timezone="UTC+03";}
				else if(timezone.equals("EET")){timezone="UTC+02";}
				else if(timezone.equals("EGST")){timezone="UTC±00";}
				else if(timezone.equals("EGT")){timezone="UTC-01";}
				else if(timezone.equals("EIT")){timezone="UTC+09";}
				else if(timezone.equals("EST")){timezone="UTC-05";}
				else if(timezone.equals("AEST")){timezone="UTC+10";}
				else if(timezone.equals("FET")){timezone="UTC+03";}
				else if(timezone.equals("FJT")){timezone="UTC+12";}
				else if(timezone.equals("FKST")){timezone="UTC-03";}
				else if(timezone.equals("FKT")){timezone="UTC-04";}
				else if(timezone.equals("FNT")){timezone="UTC-02";}
				else if(timezone.equals("GALT")){timezone="UTC-06";}
				else if(timezone.equals("GAMT")){timezone="UTC-09";}
				else if(timezone.equals("GET")){timezone="UTC+04";}
				else if(timezone.equals("GFT")){timezone="UTC-03";}
				else if(timezone.equals("GILT")){timezone="UTC+12";}
				else if(timezone.equals("GIT")){timezone="UTC-09";}
				else if(timezone.equals("GMT")){timezone="UTC±00";}
				else if(timezone.equals("GST")){timezone="UTC-02";}
				else if(timezone.equals("GST")){timezone="UTC+04";}
				else if(timezone.equals("GYT")){timezone="UTC-04";}
				else if(timezone.equals("HADT")){timezone="UTC-09";}
				else if(timezone.equals("HAEC")){timezone="UTC+02";}
				else if(timezone.equals("HAST")){timezone="UTC-10";}
				else if(timezone.equals("HKT")){timezone="UTC+08";}
				else if(timezone.equals("HMT")){timezone="UTC+05";}
				else if(timezone.equals("HOVST")){timezone="UTC+08";}
				else if(timezone.equals("HOVT")){timezone="UTC+07";}
				else if(timezone.equals("ICT")){timezone="UTC+07";}
				else if(timezone.equals("IDT")){timezone="UTC+03";}
				else if(timezone.equals("IOT")){timezone="UTC+03";}
				else if(timezone.equals("IRDT")){timezone="UTC+04:30";}
				else if(timezone.equals("IRKT")){timezone="UTC+08";}
				else if(timezone.equals("IRST")){timezone="UTC+03:30";}
				else if(timezone.equals("IST")){timezone="UTC+05:30";}
				else if(timezone.equals("IST")){timezone="UTC+01";}
				else if(timezone.equals("IST")){timezone="UTC+02";}
				else if(timezone.equals("JST")){timezone="UTC+09";}
				else if(timezone.equals("KGT")){timezone="UTC+06";}
				else if(timezone.equals("KOST")){timezone="UTC+11";}
				else if(timezone.equals("KRAT")){timezone="UTC+07";}
				else if(timezone.equals("KST")){timezone="UTC+09";}
				else if(timezone.equals("LHST")){timezone="UTC+10:30";}
				else if(timezone.equals("LHST")){timezone="UTC+11";}
				else if(timezone.equals("LINT")){timezone="UTC+14";}
				else if(timezone.equals("MAGT")){timezone="UTC+12";}
				else if(timezone.equals("MART")){timezone="UTC-09:30";}
				else if(timezone.equals("MAWT")){timezone="UTC+05";}
				else if(timezone.equals("MDT")){timezone="UTC-06";}
				else if(timezone.equals("MET")){timezone="UTC+01";}
				else if(timezone.equals("MEST")){timezone="UTC+02";}
				else if(timezone.equals("MHT")){timezone="UTC+12";}
				else if(timezone.equals("MIST")){timezone="UTC+11";}
				else if(timezone.equals("MIT")){timezone="UTC-09:30";}
				else if(timezone.equals("MMT")){timezone="UTC+06:30";}
				else if(timezone.equals("MSK")){timezone="UTC+03";}
				else if(timezone.equals("MST")){timezone="UTC+08";}
				else if(timezone.equals("MST")){timezone="UTC-07";}
				else if(timezone.equals("MUT")){timezone="UTC+04";}
				else if(timezone.equals("MVT")){timezone="UTC+05";}
				else if(timezone.equals("MYT")){timezone="UTC+08";}
				else if(timezone.equals("NCT")){timezone="UTC+11";}
				else if(timezone.equals("NDT")){timezone="UTC-02:30";}
				else if(timezone.equals("NFT")){timezone="UTC+11";}
				else if(timezone.equals("NPT")){timezone="UTC+05:45";}
				else if(timezone.equals("NST")){timezone="UTC-03:30";}
				else if(timezone.equals("NT")){timezone="UTC-03:30";}
				else if(timezone.equals("NUT")){timezone="UTC-11";}
				else if(timezone.equals("NZDT")){timezone="UTC+13";}
				else if(timezone.equals("NZST")){timezone="UTC+12";}
				else if(timezone.equals("OMST")){timezone="UTC+06";}
				else if(timezone.equals("ORAT")){timezone="UTC+05";}
				else if(timezone.equals("PDT")){timezone="UTC-07";}
				else if(timezone.equals("PET")){timezone="UTC-05";}
				else if(timezone.equals("PETT")){timezone="UTC+12";}
				else if(timezone.equals("PGT")){timezone="UTC+10";}
				else if(timezone.equals("PHOT")){timezone="UTC+13";}
				else if(timezone.equals("PHT")){timezone="UTC+08";}
				else if(timezone.equals("PKT")){timezone="UTC+05";}
				else if(timezone.equals("PMDT")){timezone="UTC-02";}
				else if(timezone.equals("PMST")){timezone="UTC-03";}
				else if(timezone.equals("PONT")){timezone="UTC+11";}
				else if(timezone.equals("PST")){timezone="UTC-08";}
				else if(timezone.equals("PST")){timezone="UTC+08";}
				else if(timezone.equals("PYST")){timezone="UTC-03";}
				else if(timezone.equals("PYT")){timezone="UTC-04";}
				else if(timezone.equals("RET")){timezone="UTC+04";}
				else if(timezone.equals("ROTT")){timezone="UTC-03";}
				else if(timezone.equals("SAKT")){timezone="UTC+11";}
				else if(timezone.equals("SAMT")){timezone="UTC+04";}
				else if(timezone.equals("SAST")){timezone="UTC+02";}
				else if(timezone.equals("SBT")){timezone="UTC+11";}
				else if(timezone.equals("SCT")){timezone="UTC+04";}
				else if(timezone.equals("SGT")){timezone="UTC+08";}
				else if(timezone.equals("SLST")){timezone="UTC+05:30";}
				else if(timezone.equals("SRET")){timezone="UTC+11";}
				else if(timezone.equals("SRT")){timezone="UTC-03";}
				else if(timezone.equals("SST")){timezone="UTC-11";}
				else if(timezone.equals("SST")){timezone="UTC+08";}
				else if(timezone.equals("SYOT")){timezone="UTC+03";}
				else if(timezone.equals("TAHT")){timezone="UTC-10";}
				else if(timezone.equals("THA")){timezone="UTC+07";}
				else if(timezone.equals("TFT")){timezone="UTC+05";}
				else if(timezone.equals("TJT")){timezone="UTC+05";}
				else if(timezone.equals("TKT")){timezone="UTC+13";}
				else if(timezone.equals("TLT")){timezone="UTC+09";}
				else if(timezone.equals("TMT")){timezone="UTC+05";}
				else if(timezone.equals("TRT")){timezone="UTC+03";}
				else if(timezone.equals("TOT")){timezone="UTC+13";}
				else if(timezone.equals("TVT")){timezone="UTC+12";}
				else if(timezone.equals("ULAST")){timezone="UTC+09";}
				else if(timezone.equals("ULAT")){timezone="UTC+08";}
				else if(timezone.equals("USZ1")){timezone="UTC+02";}
				else if(timezone.equals("UTC")){timezone="UTC+00";}
				else if(timezone.equals("UYST")){timezone="UTC-02";}
				else if(timezone.equals("UYT")){timezone="UTC-03";}
				else if(timezone.equals("UZT")){timezone="UTC+05";}
				else if(timezone.equals("VET")){timezone="UTC-04";}
				else if(timezone.equals("VLAT")){timezone="UTC+10";}
				else if(timezone.equals("VOLT")){timezone="UTC+04";}
				else if(timezone.equals("VOST")){timezone="UTC+06";}
				else if(timezone.equals("VUT")){timezone="UTC+11";}
				else if(timezone.equals("WAKT")){timezone="UTC+12";}
				else if(timezone.equals("WAST")){timezone="UTC+02";}
				else if(timezone.equals("WAT")){timezone="UTC+01";}
				else if(timezone.equals("WEST")){timezone="UTC+01";}
				else if(timezone.equals("WET")){timezone="UTC±00";}
				else if(timezone.equals("WIT")){timezone="UTC+07";}
				else if(timezone.equals("WST")){timezone="UTC+08";}
				else if(timezone.equals("YAKT")){timezone="UTC+09";}
				else if(timezone.equals("YEKT")){timezone="UTC+05";}

		
		
		
		
		return timezone;
		
	}
	
	
	
	
}
