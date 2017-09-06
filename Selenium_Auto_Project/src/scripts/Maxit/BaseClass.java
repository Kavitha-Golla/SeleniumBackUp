package scripts.Maxit;

import functionalLibrary.Global.CommonUtils;
import functionalLibrary.Global.GlobalObjectsFactory;
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


public class BaseClass {
	SoftAssert softAssert = new SoftAssert();
	WebDriver driver;
	boolean strRetStatus;

	
	@Parameters({"browser", "clientName", "environment" , "dataTableName"})
	@BeforeTest(description = "BaseClass: Login_Method")
	public void BaseMethod(String strBrowser, String strClient, String strEnvi , String strscriptName, ITestContext context ){
		try{
			
			GlobalObjectsFactory.getGlobalObjects().setiTestContext(context);
			
		CommonUtils.startTestCaseLog("BaseClass->BaseMethod for Login",context);
		Login objLogin = new Login(strBrowser); //Create an object for Class "Login"}		
		strRetStatus = objLogin.fnLogin(strEnvi,strBrowser, strClient,context);						
		Assert.assertTrue(strRetStatus,"Login Failed, Please Check..!!");
		driver = ManageDriver.getManageDriver().getDriver();
		String strCheck = ConfigInputFile.getInputFIlePath(strscriptName, strEnvi, strClient);
		Assert.assertNotNull(strCheck,"Input File config failed, please check..!!");
		strRetStatus = true;	
		}
		catch(Exception e){
			System.out.println("Exception in BaseClass->BaseMethod in @BeforeTest method..!!");
			e.printStackTrace();
		}
	}//End of "BaseMethod" method
	

	@Test(description = "PreReq_CheckPoint Method- to Verify Login/Input data config Success status",enabled = true)
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
	
	
	@AfterTest
		public void afterTest(ITestContext context) {
		  try {
			  CommonUtils.endTestCaseLog("BaseClass->afterTest Method",context);
			  //System.out.println("This is @AfterTest annotation");	  
			  //ManageDriver.getManageDriver().quit();
		  } catch (Exception e) {
			  System.out.println("Exception in BaseClass->afterTest in @AfterTest method..!!");
			  e.printStackTrace();
			}//End of Catch block
	  }//End of afterTest Annotation method.

}
