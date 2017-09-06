package functionalLibrary.Global;


import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;



public class CommonUtils {
	//static Logger log = Logger.getLogger(CommonUtils.class);
	public static Exception excep;

	
	public void fnExceptionHandling(Exception excep, WebElement strElement){
		if(excep.toString().toUpperCase().contains("NOSUCHELEMENTEXCEPTION")){
			fnNoSuchElementException(excep,strElement);			
		}//End of IF condition to see if the Exception=NoSuchElementException
		
	}//End of <Method: fnExceptionHandling>
	
	
	private void fnNoSuchElementException(Exception excep,WebElement strElement){
		System.out.println("Object not found in browser,Please check its properties in PageObjects Package. Web Object Name:"+strElement);
		
	}//End of <Method: fnNoSuchElementException>
	
//###################################################################################################################################################################  
//Function name		: isElementPresent(WebElement varElement)
//Class name		: CommonUtils
//Description 		: Generic function to check if the element exists
//Parameters 		: WebElement
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public static boolean isElementPresent(WebElement varElement){
		try{
			if(varElement.isDisplayed()){
				return true;
				}
			return false;
		}//End of try block

		catch(Exception e){		
			return false;
		}//End of catch block	
	}//End of Method:isElementPresent
	
//###################################################################################################################################################################  
//Function name		: isAlertPresent(WebDriver driver)
//Class name		: CommonUtils
//Description 		: Generic function to check if an alert exists
//Parameters 		: 
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public static boolean isAlertPresent(WebDriver driver){
	    try {
	        Alert alert = driver.switchTo().alert();
	        String alertMessage = alert.getText();
	        System.out.println("Alert is present, Alert Message: "+alertMessage);
	        return true;
	    } // End of try block
	    catch (NoAlertPresentException e) {
	    	System.out.println("Alert is not present");
	        return false;
	    } // End of NoAlertPresentException catch block
	    
	    catch (Exception e) {
	    	System.out.println("Exception in <Method: isAlertPresent> <Class:CommonUtils>: Alert is not present");
	    	e.printStackTrace();
	        return false;
	    } // End of NoAlertPresentException catch block	    
	}//End of Method: isAlertPresent
	
//###################################################################################################################################################################  
//Function name		: acceptAlert(Alert alert)
//Class name		: CommonUtils
//Description 		: Generic function to accept if alert exists.
//Parameters 		: Alert alert
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public static boolean acceptAlert(WebDriver driver){
	    try {
	    	Alert alert = driver.switchTo().alert();
	        String alertMessage = alert.getText();
	        System.out.println("Alert is present, Alert Message: "+alertMessage);
	        alert.accept();
	        return true;
	    } // End of try block
	    catch (NoAlertPresentException e) {
	    	System.out.println("NoAlertPresentException in <Method: acceptAlert> <Class:CommonUtils>: Alert is not present");
	        return true;
	    } // End of NoAlertPresentException catch block
	    
	    catch (Exception e) {
	    	System.out.println("Exception in <Method: acceptAlert> <Class:CommonUtils>: Alert is not present");
	    	e.printStackTrace();
	        return false;
	    } // End of NoAlertPresentException catch block	    
	}//End of Method: acceptAlert
//###################################################################################################################################################################  
//Function name		: CaptureScreenshot(String strPath)
//Class name		: CommonUtils
//Description 		: Generic function to Capture Screenshot of the page
//Parameters 		: Path to save the screenshot
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public static void captureScreenshot(WebDriver driver,String strPath) throws Exception {
		if(strPath.isEmpty()){
			createFolder_NotExists("C:\\Selenium_Screenshots");
			strPath = "C:\\Selenium_Screenshots\\Scrrenshot1.jpeg";
			//System.out.println("Screenshot path is not given, so it will be captured to default location:"+strPath);
		}//End of IF condition to check strPath
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(strPath));
	}//End of Method: CaptureScreenshot	

//###################################################################################################################################################################  
//Function name		: CaptureScreenshot(ITestContext context, WebDriver driver, String strStepName, String stepDetail, String strScrollStgy, WebElement objElement)
//Class name		: CommonUtils
//Description 		: Generic function to Capture Screenshot of the page
//Parameters 		: ITestContext to read testNG.xml parameters
//					: Driver object
//					: strStepName
//					: step details summary
//					: scroll strategy (horizontal/vertical/both directions)
//					: Webelement object to highlight the object in screenshot	
//Assumption		: None
//Developer			: Neelesh Vatsa
//###################################################################################################################################################################	
	public static String CaptureScreenshot(ITestContext context, WebDriver driver, String strStepName, String stepDetail, String strScrollStgy, WebElement objElement) throws Exception {
        String strProductName = context.getCurrentXmlTest().getParameter("productName");
        String strClientName = context.getCurrentXmlTest().getParameter("clientName");
        String strScriptName = context.getCurrentXmlTest().getParameter("scriptName");
        String strEnviName = context.getCurrentXmlTest().getParameter("environment");
        
        Format formatter = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
        Date date = new Date();
        String imgName = strStepName + "_Error_" + (formatter.format(date));
        
        String imgPath = "C:/SE_Reports/" + strProductName + "/" + strEnviName + "/" + strClientName + "/" + strScriptName + "/Screenshots";
        strScrollStgy = strScrollStgy.toUpperCase();
        if(objElement==null){
            switch (strScrollStgy){
            case "HORIZONTALLY":
                Shutterbug.shootPage(driver, ScrollStrategy.HORIZONTALLY).withTitle(stepDetail + new Date()).withName(imgName).save(imgPath);
                break;
            case "VERTICALLY":
                Shutterbug.shootPage(driver, ScrollStrategy.VERTICALLY).withTitle(stepDetail + new Date()).withName(imgName).save(imgPath);
                break;
            case "BOTH_DIRECTIONS":
                Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).withTitle(stepDetail + new Date()).withName(imgName).save(imgPath);
                break;
            default:
                Shutterbug.shootPage(driver).withTitle(stepDetail + new Date()).withName(imgName).save(imgPath);
                break;
            }
            
        }else{
            switch (strScrollStgy){
            case "HORIZONTALLY":
                Shutterbug.shootPage(driver, ScrollStrategy.HORIZONTALLY).highlight(objElement).withTitle(stepDetail + new Date()).withName(imgName).save(imgPath);
                break;
            case "VERTICALLY":
                Shutterbug.shootPage(driver, ScrollStrategy.VERTICALLY).highlight(objElement).withTitle(stepDetail + new Date()).withName(imgName).save(imgPath);
                break;
            case "BOTH_DIRECTIONS":
                Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).highlight(objElement).withTitle(stepDetail + new Date()).withName(imgName).save(imgPath);
                break;
            default:
                Shutterbug.shootPage(driver).highlight(objElement).withTitle(stepDetail + new Date()).withName(imgName).save(imgPath);
                break;
            }
            
        }
        
        String fullImgPath = imgPath + "/" + imgName + ".png";
        
        return fullImgPath;

        
	}//End of Method: CaptureScreenshot	[Method overloading]
//###################################################################################################################################################################  
//Function name		: loggerScreenshot_Path(String strPath)
//Class name		: CommonUtils
//Description 		: Generic function to place screenshot path in logger html file.
//Parameters 		: Screenshot path
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public static String loggerScreenshot_Path(String strPath) throws Exception {
		if(strPath.isEmpty()){
			//log.error("Screenshot path is empty");
			return "";
		}
		return strPath;
		        
	}//End of Method: loggerScreenshot_Path		
	
	
//###################################################################################################################################################################  
//Function name		: fileExists(String strPath)
//Class name		: CommonUtils
//Description 		: Generic function to verify if the file or directory exists
//Parameters 		: Path of the file
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public static boolean fileExists(String strPath) throws Exception {
		if(strPath.trim()== null || strPath.trim().isEmpty() || strPath.trim().equals("")){
			System.out.println("<class:CommonUtils><Method:fileExists>	Path passed to this methos is NULL, please check!");
			//log.info("<class:CommonUtils><Method:fileExists>	Path passed to this methos is NULL, please check!");			
			return false;
		}
		File f = new File(strPath);
		if (f.exists()) {
		    return true;
		} else {
			System.out.println("File "+strPath+" does NOT exists in the directory.Please check!");
			return false;
		}
	}// End of Method: fileExists

//###################################################################################################################################################################  
//Function name		: createFolder_NotExists(String strPath)
//Class name		: CommonUtils
//Description 		: Generic function to verify if the file or directory exists
//Parameters 		: Path of the file
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public static boolean createFolder_NotExists(String strPath) throws Exception {
		if(strPath.trim()== null || strPath.trim().isEmpty() || strPath.trim().equals("")){
			System.out.println("<class:CommonUtils><Method: createFolder_NotExists>	Path passed to this method is NULL, please check!");			
			return false;
		}
		File f = new File(strPath);
		if (f.exists()) {		    
		    return true;
		} else {			
			System.out.println("File "+strPath+" does NOT exists in the directory.Please check!");
			f.mkdir();
			System.out.println("Folder "+strPath+" is created.");
			return true;
		}

	}// End of Method: createFolder_NotExists	
	
//###################################################################################################################################################################  
//Function name		: startTestCaseLog(String strTestCaseName, ITestContext context)
//Class name		: CommonUtils
//Description 		: Generic function to create a start of Test case execution format for log
//Parameters 		: Test Case Name
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	 public static void startTestCaseLog(String strTestCaseName,ITestContext context){	
		 long id = Thread.currentThread().getId();
		 System.out.println("****************************************************************************************");		 
		 System.out.println("****************************************************************************************");		 
		 System.out.println("$$$$$$$$$$$$$$$$$$$$$                 "+strTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");	
		 System.out.println("$$$$$$$$$$$$$$$$$$$$$                Browser Thread ID:"+id+"    $$$$$$$$$$$$$$$$$$$$$$$$$");				
		 System.out.println("****************************************************************************************");		 
		 System.out.println("****************************************************************************************");	
		 String strClient = context.getCurrentXmlTest().getParameter("clientName");
		 String strEnv = context.getCurrentXmlTest().getParameter("environment");
		 String strProduct = context.getCurrentXmlTest().getParameter("productName");
		 String strBrowser = context.getCurrentXmlTest().getParameter("browser");
		 String strScriptName = context.getCurrentXmlTest().getParameter("scriptName");
		 		 		 
		 //log.info("Test Execution started: Product="+strProduct+";ClientName="+strClient+";Environment="+strEnv+";Browser="+strBrowser+";Script Name="+strScriptName);
		 System.out.println("Test Execution started: Product="+strProduct+";ClientName="+strClient+";Environment="+strEnv+";Browser="+strBrowser+";Script Name="+strScriptName);
		 
		}//End of startTestCaseLog method.

	//###################################################################################################################################################################  
	//Function name		: startTestCaseLog(String strTestCaseName)
	//Class name		: CommonUtils
	//Description 		: Generic function to create a start of Test case execution format for log
	//Parameters 		: Test Case Name
	//Assumption		: None
	//Developer			: Kavitha Golla
	//###################################################################################################################################################################	
		 public static void startTestCaseLog(String strTestCaseName){
			 
			 ITestContext context = GlobalObjectsFactory.getGlobalObjects().getiTestContext();
			 if (context == null){
				 System.out.println("ITestContext Object is not initialized yet!!!");
			 }
			 long id = Thread.currentThread().getId();
			 System.out.println("****************************************************************************************");		 
			 System.out.println("****************************************************************************************");		 
			 System.out.println("$$$$$$$$$$$$$$$$$$$$$                 "+strTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");	
			 System.out.println("$$$$$$$$$$$$$$$$$$$$$                Browser Thread ID:"+id+"    $$$$$$$$$$$$$$$$$$$$$$$$$");				
			 System.out.println("****************************************************************************************");		 
			 System.out.println("****************************************************************************************");	
			 String strClient = context.getCurrentXmlTest().getParameter("clientName");
			 String strEnv = context.getCurrentXmlTest().getParameter("environment");
			 String strProduct = context.getCurrentXmlTest().getParameter("productName");
			 String strBrowser = context.getCurrentXmlTest().getParameter("browser");
			 String strScriptName = context.getCurrentXmlTest().getParameter("scriptName");
			 		 		 
			 //log.info("Test Execution started: Product="+strProduct+";ClientName="+strClient+";Environment="+strEnv+";Browser="+strBrowser+";Script Name="+strScriptName);
			 System.out.println("Test Execution started: Product="+strProduct+";ClientName="+strClient+";Environment="+strEnv+";Browser="+strBrowser+";Script Name="+strScriptName);
			 
			}//End of startTestCaseLog method.
 
//###################################################################################################################################################################  
//Function name		: endTestCaseLog(String strTestCaseName)
//Class name		: CommonUtils
//Description 		: Generic function to create a start of Test case execution format for log
//Parameters 		: Test Case Name
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public static void endTestCaseLog(String strTestCaseName,ITestContext context){	
			long id = Thread.currentThread().getId();
			
			System.out.println("****************************************************************************************");		 
			System.out.println("****************************************************************************************");		 
			System.out.println("$$$$$$$$$$$$$$$$$$$$$                 "+strTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");	
			System.out.println("$$$$$$$$$$$$$$$$$$$$$      Browser Thread ID:"+id+" is closed.   $$$$$$$$$$$$$$$$$$$$$$$$$");				
			System.out.println("****************************************************************************************");		 
			System.out.println("**************************	"+"-E---N---D-"+"	***************************************");	
		 	 
			 String strClient = context.getCurrentXmlTest().getParameter("clientName");
			 String strEnv = context.getCurrentXmlTest().getParameter("environment");
			 String strProduct = context.getCurrentXmlTest().getParameter("productName");
			 String strBrowser = context.getCurrentXmlTest().getParameter("browser");
			 String strScriptName = context.getCurrentXmlTest().getParameter("scriptName");
			 		 		 
			 System.out.println("Test Execution Complete: Product="+strProduct+";ClientName="+strClient+";Environment="+strEnv+";Browser="+strBrowser+";Script Name="+strScriptName);
			 			
		}//End of endTestCaseLog method. 
 
//###################################################################################################################################################################  
//Function name     : contextGetScriptName(ITestContext context)
//Class name        : CommonUtils
//Description       : 
//Parameters        : 
//Assumption        : None
//Developer         : Neelesh Vatsa
//###################################################################################################################################################################   
    
	public static String contextGetParameterName(ITestContext context, String strParameterName){
	    String strParamName = "";
	     switch (strParameterName) {
	         case "scriptName":
	             strParamName = context.getCurrentXmlTest().getParameter("scriptName");
	             break;
	         case "clientName":
	             strParamName = context.getCurrentXmlTest().getParameter("clientName");
	             break;
	         case "productName":
	             strParamName = context.getCurrentXmlTest().getParameter("productName");
	             break;
	         case "browser":
	             strParamName = context.getCurrentXmlTest().getParameter("browser");
	             break;
	         case "environment":
	             strParamName = context.getCurrentXmlTest().getParameter("environment");
	             break;
	         default:
	             throw new IllegalArgumentException("Invalid parameter name: " + strParameterName);
	     }
	     return strParameterName;
	} 
    
	
}//End of <Class:CommonUtils>
