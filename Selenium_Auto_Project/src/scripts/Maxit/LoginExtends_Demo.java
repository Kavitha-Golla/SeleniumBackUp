package scripts.Maxit;

import functionalLibrary.Global.CommonUtils;
import functionalLibrary.Global.DataTable;
import functionalLibrary.Global.ManageDriver;
import functionalLibrary.Maxit.Login;
import functionalLibrary.Maxit.SearchPageFns;
import functionalLibrary.Maxit.ConfigInputFile;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageObjects.Maxit.SearchPage_Results;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;


public class LoginExtends_Demo {
	SoftAssert softAssert = new SoftAssert();
	WebDriver driver;
	boolean strRetStatus;	
	
	@Parameters({"browser", "clientName", "environment"})
	@BeforeTest(description = "BeforeTest: Login_Method")
	public void BaseMethod(String strBrowser, String strClient, String strEnvi,ITestContext context  ) throws Exception{
		CommonUtils.startTestCaseLog("LoginExtends_Demo");	
		Login objLogin = new Login(strBrowser); //Create an object for Class "Login"}		
		strRetStatus = objLogin.fnLogin(strEnvi,strBrowser, strClient,context);						
		Assert.assertTrue(strRetStatus,"Login Failed, Please Check..!!");
		driver = ManageDriver.getManageDriver().getDriver();
		String strCheck = ConfigInputFile.getInputFIlePath("StepUp_Update_DataValidation", strEnvi, strClient);
		Assert.assertNotNull(strCheck,"Input File config failed, please check..!!");
		strRetStatus = true;	
	}//End of "BaseMethod" method
	
	
	
	@Test(description = "PreReq_CheckPoint Method- to Verify Login/Input data config Success status")
	public void PreReq_CheckPoint(){
		try{
			if(!strRetStatus){
			softAssert.assertTrue(strRetStatus, "Testing depends on PreReq_CheckPoint( )");				
			}
			else{
				System.out.println("Testing depends on PreReq_CheckPoint( )within else condition");
				throw new SkipException("Skip exception in PreReq_CheckPoint");
			}
		}
		catch(Exception e){
			
		}
	}//End of InputData_ConfigMethod
	
	
	@Test(description = "Search_Input",dependsOnMethods = "PreReq_CheckPoint") //set up Input data config excel file) 
	 public void fnTest() throws Exception {
		long id = Thread.currentThread().getId();
		driver = ManageDriver.getManageDriver().getDriver();
		  System.out.println("Thread ID:"+id+" - fnTest: This is @TEST annotation inside fnTestMethod1");
		  System.out.println("Thread ID:"+id+" - fnTest: Callling the ConfigInputFile fn");
		  //ManageDriver.getManageDriver().quit();
		  //String excelPath = configInputFile.getInputFIlePath("StepUp_Update_DataValidation","QA","STR");
		  String excelPath = "C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\TestData\\Maxit_TestData\\str_StepUp_Update_DataValidation.xls";
		  System.out.println("Excel Path is :"+excelPath);
		  DataTable.setExcelFile(excelPath);
		  String currentDir = System.getProperty("user.dir");
		  //String currentDir = System.getProperty("java.class.path");
	        System.out.println("Current dir using System:" +currentDir);
		 
	        //Within DataValidation:
	        if(DataTable.setSheet("Ledger")){			  		 
		  int intDTRows =  DataTable.getRowCount();
		  System.out.println("Total DT Rows in Ledger:"+intDTRows);

		  for(int intDTRowIndex = 1 ; intDTRowIndex<=intDTRows;intDTRowIndex++){
		  
		
		  if(DataTable.getCellData(intDTRowIndex, "To_Execute").equalsIgnoreCase("Y")){
			  System.out.println("Executing row#"+intDTRowIndex+" in Ledger.");

			  //fn call Search_SearchResult_DataValidation(intDTRowIndex, strViewName, strDTSheetName )
			  //read input values
			  String strAcct = DataTable.getCellData(intDTRowIndex, "Account_No").toString() ;
			  String strSecType = DataTable.getCellData(intDTRowIndex, "Sec_Type").toString() ;
			  String strSecValue = DataTable.getCellData(intDTRowIndex, "Sec_Value").toString() ;
			  String strRowCount = DataTable.getCellData(intDTRowIndex, "Row_Count").toString() ;
			  String strViewPage = "Ledger";
//			  if(!SearchPageFns.Search_NavigatePage("Ledger")){
//				  System.out.println("Return type of <SearchPageFns.Search_NavigatePage> from <> is false"); 
//			  }
			  
			  if(!SearchPageFns.Search_Input(strViewPage,strAcct, strSecType, strSecValue)){
				  System.out.println("Return type of <SearchPageFns.Search_Input> from <> is false");
			  }
//			  WebDriverWait wait = new WebDriverWait(driver,120);
//			  wait.until(ExpectedConditions.elementToBeClickable(By.id("ext-gen43")));
			  Thread.sleep(10000);
			  
//			  if(!SearchPage_Results.searchRes_NumOfRows_app(strViewPage)){
//				  System.out.println("Return type of <SearchPage_Results.searchRes_NumOfRows_app> from <> is false");
//			  }
			  
			  //call searchInput fn
			  //verify row count to check No Results returned else click on + for Ledger and take row count			  
			  // compare row counts
			  //read actual from UI & compare with expected
		  }
		  else{
			  System.out.println("Skipping row#"+intDTRowIndex+" in Ledger,as To_Execute=N");
			  //throw new SkipException("To_Execute=N for row# "+iLoop+" , so skipping to this row");			  			 
		  }
		  //SearchPageFns.SearchResult_DataValidation(intDTRowIndex, strViewName, strDTSheetName);
		  
		  //UI_Login("chrome", "STR", "QA");
		  //DataTable.setExcelFile(excelPath);
		  
		  }//End of for loop to iterate thru different rows
		  
		  }//End of IF condition to check  DataTable.setSheet

		
	}//End of fnTest()
//	@Test //set up Input data config excel file) 
//	 public void fnTestMethod2() throws Exception {
//		long id = Thread.currentThread().getId();
//		  System.out.println("Thread ID:"+id+" - fnTestMethod2:This is @TEST annotation inside fnTestMethod2");
//		  System.out.println("Thread ID:"+id+" - fnTestMethod2:Callling the ConfigInputFile fn");
//		  String excelPath = configInputFile.getInputFIlePath("StepUp_Update_DataValidation","QA","STR");
//		  System.out.println("Excel Path is :"+excelPath);
//		  DataTable.setExcelFile(excelPath);
//		  
//		  
//		  
//	  }

  
  
//  @BeforeTest
//  public void beforeTest() {
//	  System.out.println("This is @BeforeTest annotation");
//	  
//  }

  @AfterTest
  public void afterTest() {
	  try {
		  CommonUtils.endTestCaseLog("LoginExtends_Demo");
	  System.out.println("This is @AfterTest annotation");	  
		//ManageDriver.getManageDriver().quit();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

}
