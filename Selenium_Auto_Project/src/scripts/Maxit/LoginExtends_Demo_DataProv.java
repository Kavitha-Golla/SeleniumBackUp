package scripts.Maxit;

import functionalLibrary.Global.CommonUtils;
import functionalLibrary.Global.ManageDriver;
import functionalLibrary.Maxit.DataVal_DataProviders;
import functionalLibrary.Maxit.Login;
import functionalLibrary.Maxit.SearchPageFns;
import functionalLibrary.Maxit.ConfigInputFile;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;


public class LoginExtends_Demo_DataProv {
	SoftAssert softAssert = new SoftAssert();
	WebDriver driver;
	boolean strRetStatus;	
	SearchPageFns objSearchPageFns;
	
	
	@Parameters({"browser", "clientName", "environment" , "dataTableName"})
	@BeforeTest(description = "BeforeTest: Login_Method")
	public void BaseMethod(String strBrowser, String strClient, String strEnvi , String strscriptName, ITestContext context ) throws Exception{
//		long id = Thread.currentThread().getId();
		CommonUtils.startTestCaseLog("LoginExtends_Demo",context);
		Login objLogin = new Login(strBrowser); //Create an object for Class "Login"}		
		strRetStatus = objLogin.fnLogin(strEnvi,strBrowser, strClient,context);						
		Assert.assertTrue(strRetStatus,"Login Failed, Please Check..!!");
		driver = ManageDriver.getManageDriver().getDriver();
		String strCheck = ConfigInputFile.getInputFIlePath(strscriptName, strEnvi, strClient);
		Assert.assertNotNull(strCheck,"Input File config failed, please check..!!");
		strRetStatus = true;	
	}//End of "BaseMethod" method
	
	
	
	@Test(description = "PreReq_CheckPoint Method- to Verify Login/Input data config Success status")
	public void PreReq_CheckPoint(){
		try{
			if(strRetStatus){
			softAssert.assertTrue(strRetStatus, "@TEST: PreReq_CheckPoint, dependent on @BeforeTest annotation.");				
			}
			else{
				throw new SkipException("Fail exception in PreReq_CheckPoint");
			}
		}//End of Try block
		catch(Exception e){
			System.out.println("@TEST:PreReq_CheckPoint[Failed], Login/Input file config is not done.");
		}//End of Catch block
	}//End of PreReq_CheckPoint
	
	
	@Test(description = "Ledger_DataValidation",dependsOnMethods = "PreReq_CheckPoint",dataProvider = "Ledger",dataProviderClass = DataVal_DataProviders.class)
	 public void SearchResult_DataVal_Ledger(String strViewPage,String To_Execute,String strAcct,String strSecType, String strSecValue,String strRowCount , String strColNames, String[] strExpRows ) throws Exception {
		driver = ManageDriver.getManageDriver().getDriver();		
		objSearchPageFns = new SearchPageFns();
		
		if(To_Execute.equalsIgnoreCase("Y")){
			objSearchPageFns.SearchResult_DataValidation(strViewPage, To_Execute, strAcct, strSecType, strSecValue, strRowCount, strColNames, strExpRows);			
		}//End of IF condition to check To_Execute=Y/N
		else{
			throw new SkipException("To_Execute=N, so skipping the row from DataValidation.");
		}//End of ELSE condition to check To_Execute=Y/N
		        
	}//End of SearchResult_DataVal_Ledger()
	
//	@Test(description = "RGL_DataValidation",dependsOnMethods = "PreReq_CheckPoint",dataProvider = "Realized",dataProviderClass = DataVal_DataProviders.class)
//	 public void SearchResult_DataVal_RGL(String strViewPage,String To_Execute,String strAcct,String strSecType, String strSecValue,String strRowCount , String strColNames, String[] strExpRows ) throws Exception {
//		driver = ManageDriver.getManageDriver().getDriver();		
//		
//		if(To_Execute.equalsIgnoreCase("Y")){
//			SearchPageFns.SearchResult_DataValidation(strViewPage, To_Execute, strAcct, strSecType, strSecValue, strRowCount, strColNames, strExpRows);			
//		}//End of IF condition to check To_Execute=Y/N
//		else{
//			throw new SkipException("To_Execute=N, so skipping the row from DataValidation.");
//		}//End of ELSE condition to check To_Execute=Y/N
//		        
//	}//End of SearchResult_DataVal_RGL()
	
//	@Test(description = "UGL_DataValidation",dependsOnMethods = "PreReq_CheckPoint",dataProvider = "Unrealized",dataProviderClass = DataVal_DataProviders.class)
//	 public void SearchResult_DataVal_UGL(String strViewPage,String To_Execute,String strAcct,String strSecType, String strSecValue,String strRowCount , String strColNames, String[] strExpRows ) throws Exception {
//		driver = ManageDriver.getManageDriver().getDriver();				
//		if(To_Execute.equalsIgnoreCase("Y")){
//			SearchPageFns.SearchResult_DataValidation(strViewPage, To_Execute, strAcct, strSecType, strSecValue, strRowCount, strColNames, strExpRows);			
//		}//End of IF condition to check To_Execute=Y/N
//		else{
//			throw new SkipException("To_Execute=N, so skipping the row from DataValidation.");
//		}//End of ELSE condition to check To_Execute=Y/N
//		        
//	}//End of SearchResult_DataVal_UGL()	
	
	
  @AfterTest
  public void afterTest(ITestContext context) {
	  try {
		  CommonUtils.endTestCaseLog("LoginExtends_Demo",context);
	  System.out.println("This is @AfterTest annotation");	  
		//ManageDriver.getManageDriver().quit();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

}
