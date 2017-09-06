package functionalLibrary.Maxit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import functionalLibrary.Global.ManageDriver;

public class DataVal_DataProviders {
	WebDriver driver = ManageDriver.getManageDriver().getDriver();
	ConfigInputFile objConfigInputFile = new ConfigInputFile();
	
//###################################################################################################################################################################  
//Function name		: Read_Ledger(ITestContext context)
//Class name		: DataVal_DataProviders
//Description 		: Generic method to read data from Ledger sheet for data Validation Data Provider
//Parameters 		: context: ITestContest to read testNG.xml parameters
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	@DataProvider(name="Ledger")
	public Object[][] Read_Ledger(ITestContext context)throws Exception{
		String strScriptName = context.getCurrentXmlTest().getParameter("scriptName");
		String strEnv = context.getCurrentXmlTest().getParameter("environment");
		String strClient = context.getCurrentXmlTest().getParameter("clientName");
		
	    String excelPath = ConfigInputFile.getInputFIlePath(strScriptName,strEnv,strClient);
//		String excelPath = "C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\TestData\\Maxit_TestData\\edj_StandardBasicProcessing_DataValidation.xls";	  
		System.out.println("Excel Path is :"+excelPath);
		return(objConfigInputFile.getData_DataValidation(excelPath, "Ledger","Ledger"));
	  
	}//End of DataProvider "Read_Ledger"

//###################################################################################################################################################################  
//Function name		: Read_RGL(ITestContext context)
//Class name		: DataVal_DataProviders
//Description 		: Generic method to read data from RGL sheet for data Validation Data Provider
//Parameters 		: context: ITestContest to read testNG.xml parameters
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################			
	@DataProvider(name="Realized")
	public Object[][] Read_RGL(ITestContext context)throws Exception{
		String strScriptName = context.getCurrentXmlTest().getParameter("scriptName");
		String strEnv = context.getCurrentXmlTest().getParameter("environment");
		String strClient = context.getCurrentXmlTest().getParameter("clientName");
		
	    String excelPath = ConfigInputFile.getInputFIlePath(strScriptName,strEnv,strClient);
//		String excelPath = "C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\TestData\\Maxit_TestData\\edj_StandardBasicProcessing_DataValidation.xls";	  
		System.out.println("Excel Path is :"+excelPath);
	  return(objConfigInputFile.getData_DataValidation(excelPath, "Realized","Realized"));	  
	}//End of DataProvider "Read_RGL"

//###################################################################################################################################################################  
//Function name		: Read_UGL(ITestContext context)
//Class name		: DataVal_DataProviders
//Description 		: Generic method to read data from UGL sheet for data Validation Data Provider
//Parameters 		: context: ITestContest to read testNG.xml parameters
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	@DataProvider(name="Unrealized")
	public Object[][] Read_UGL(ITestContext context)throws Exception{
		String strScriptName = context.getCurrentXmlTest().getParameter("scriptName");
		String strEnv = context.getCurrentXmlTest().getParameter("environment");
		String strClient = context.getCurrentXmlTest().getParameter("clientName");
		
	    String excelPath = ConfigInputFile.getInputFIlePath(strScriptName,strEnv,strClient);
//		String excelPath = "C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\TestData\\Maxit_TestData\\edj_StandardBasicProcessing_DataValidation.xls";	  
		System.out.println("Excel Path is :"+excelPath);
	  return(objConfigInputFile.getData_DataValidation(excelPath, "Unrealized","Unrealized"));	  
	}//End of DataProvider "Read_UGL"	

//###################################################################################################################################################################  
//Function name		: Read_OpenClosed(ITestContext context)
//Class name		: DataVal_DataProviders
//Description 		: Generic method to read data from Open/Closed sheet for data Validation Data Provider
//Parameters 		: context: ITestContest to read testNG.xml parameters
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	@DataProvider(name="OpenClosed")
	public Object[][] Read_OpenClosed(ITestContext context)throws Exception{
		String strScriptName = context.getCurrentXmlTest().getParameter("scriptName");
		String strEnv = context.getCurrentXmlTest().getParameter("environment");
		String strClient = context.getCurrentXmlTest().getParameter("clientName");
		
	    String excelPath = ConfigInputFile.getInputFIlePath(strScriptName,strEnv,strClient);
//		String excelPath = "C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\TestData\\Maxit_TestData\\edj_StandardBasicProcessing_DataValidation.xls";	  
		System.out.println("Excel Path is :"+excelPath);
	  return(objConfigInputFile.getData_DataValidation(excelPath, "OpenClosed","Open/Closed"));
	  
	}//End of DataProvider "Read_OpenClosed"	
	
//###################################################################################################################################################################  
//Function name		: Read_RawTrades(ITestContext context)
//Class name		: DataVal_DataProviders
//Description 		: Generic method to read data from Raw_Trades sheet for data Validation Data Provider
//Parameters 		: context: ITestContest to read testNG.xml parameters
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	@DataProvider(name="RawTrades")
	public Object[][] Read_RawTrades(ITestContext context)throws Exception{
		String strScriptName = context.getCurrentXmlTest().getParameter("scriptName");
		String strEnv = context.getCurrentXmlTest().getParameter("environment");
		String strClient = context.getCurrentXmlTest().getParameter("clientName");
		
	    String excelPath = ConfigInputFile.getInputFIlePath(strScriptName,strEnv,strClient);
//		String excelPath = "C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\TestData\\Maxit_TestData\\edj_StandardBasicProcessing_DataValidation.xls";	  
		System.out.println("Excel Path is :"+excelPath);
	  return(objConfigInputFile.getData_DataValidation(excelPath, "RawTrades","Raw Trades"));
	  
	}//End of DataProvider "Read_RawTrades"	
	
	
//###################################################################################################################################################################		
}//End of DataVal_DataProviders
//###################################################################################################################################################################	
