package scripts.Maxit;

import functionalLibrary.Global.CommonUtils;
import functionalLibrary.Global.GlobalObjectsFactory;
import functionalLibrary.Global.ManageDriver;
import functionalLibrary.Maxit.SearchPageFns;
import functionalLibrary.Maxit.DataVal_DataProviders;

import org.testng.annotations.Test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;


public class DataValidation_Flow extends BaseClass {
	WebDriver driver;
	boolean strRetStatus;
	SearchPageFns objSearchPageFns;
	
	Logger log = Logger.getLogger(DataValidation_Flow.class);	
	
	
	@Test(description = "Ledger",dependsOnMethods = "PreReq_CheckPoint",dataProvider = "Ledger",dataProviderClass = DataVal_DataProviders.class)
	 public void Ledger(String strViewPage,String To_Execute,String strAcct,String strSecType, String strSecValue,String strRowCount , String strColNames, String[] strExpRows ) throws Exception {
		
		driver = ManageDriver.getManageDriver().getDriver();
		objSearchPageFns = new SearchPageFns();
		
		//CommonUtils.startTestCaseLog("DataValidation_Flow->Ledger");
		
		if(To_Execute.equalsIgnoreCase("Y")){
			objSearchPageFns.SearchResult_DataValidation(strViewPage, To_Execute, strAcct, strSecType, strSecValue, strRowCount, strColNames, strExpRows);			
		}//End of IF condition to check To_Execute=Y/N
		else{
			throw new SkipException("To_Execute=N, so skipping the row from DataValidation.");
		}//End of ELSE condition to check To_Execute=Y/N
		        
	}//End of SearchResult_DataVal_Ledger()

	@Test(description = "Realized",dependsOnMethods = "PreReq_CheckPoint",dataProvider = "Realized",dataProviderClass = DataVal_DataProviders.class)
	 public void Realized(String strViewPage,String To_Execute,String strAcct,String strSecType, String strSecValue,String strRowCount , String strColNames, String[] strExpRows ) throws Exception {
		driver = ManageDriver.getManageDriver().getDriver();		
		objSearchPageFns = new SearchPageFns();
		
		if(To_Execute.equalsIgnoreCase("Y")){
			objSearchPageFns.SearchResult_DataValidation(strViewPage, To_Execute, strAcct, strSecType, strSecValue, strRowCount, strColNames, strExpRows);			
		}//End of IF condition to check To_Execute=Y/N
		else{
			throw new SkipException("To_Execute=N, so skipping the row from DataValidation.");
		}//End of ELSE condition to check To_Execute=Y/N
		        
	}//End of Realized()	
	
	
	@Test(description = "Unrealized",dependsOnMethods = "PreReq_CheckPoint",dataProvider = "Unrealized",dataProviderClass = DataVal_DataProviders.class)
	 public void Unrealized(String strViewPage,String To_Execute,String strAcct,String strSecType, String strSecValue,String strRowCount , String strColNames, String[] strExpRows ) throws Exception {
		driver = ManageDriver.getManageDriver().getDriver();		
		objSearchPageFns = new SearchPageFns();
		
		if(To_Execute.equalsIgnoreCase("Y")){
			objSearchPageFns.SearchResult_DataValidation(strViewPage, To_Execute, strAcct, strSecType, strSecValue, strRowCount, strColNames, strExpRows);			
		}//End of IF condition to check To_Execute=Y/N
		else{
			throw new SkipException("To_Execute=N, so skipping the row from DataValidation.");
		}//End of ELSE condition to check To_Execute=Y/N
		        
	}//End of Unrealized()	
	

	@Test(description = "Open/Closed",dependsOnMethods = "PreReq_CheckPoint",dataProvider = "OpenClosed",dataProviderClass = DataVal_DataProviders.class)
	 public void OpenClosed(String strViewPage,String To_Execute,String strAcct,String strSecType, String strSecValue,String strRowCount , String strColNames, String[] strExpRows ) throws Exception {
		driver = ManageDriver.getManageDriver().getDriver();		
		objSearchPageFns = new SearchPageFns();
		
		if(To_Execute.equalsIgnoreCase("Y")){
			objSearchPageFns.SearchResult_DataValidation(strViewPage, To_Execute, strAcct, strSecType, strSecValue, strRowCount, strColNames, strExpRows);			
		}//End of IF condition to check To_Execute=Y/N
		else{
			throw new SkipException("To_Execute=N, so skipping the row from DataValidation.");
		}//End of ELSE condition to check To_Execute=Y/N
		        
	}//End of OpenClosed()	
	
	@Test(description = "Raw Trades",dependsOnMethods = "PreReq_CheckPoint",dataProvider = "RawTrades",dataProviderClass = DataVal_DataProviders.class)
	 public void RawTrades(String strViewPage,String To_Execute,String strAcct,String strSecType, String strSecValue,String strRowCount , String strColNames, String[] strExpRows ) throws Exception {
		driver = ManageDriver.getManageDriver().getDriver();		
		objSearchPageFns = new SearchPageFns();
		
		if(To_Execute.equalsIgnoreCase("Y")){
			objSearchPageFns.SearchResult_DataValidation(strViewPage, To_Execute, strAcct, strSecType, strSecValue, strRowCount, strColNames, strExpRows);			
		}//End of IF condition to check To_Execute=Y/N
		else{
			throw new SkipException("To_Execute=N, so skipping the row from DataValidation.");
		}//End of ELSE condition to check To_Execute=Y/N
		        
	}//End of RawTrades()	
	
	
}
