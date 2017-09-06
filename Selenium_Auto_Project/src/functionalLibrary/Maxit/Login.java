package functionalLibrary.Maxit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;

import functionalLibrary.Global.*;
import PageObjects.Maxit.*;


public class Login {
	static Logger log = Logger.getLogger(Login.class);
	WebDriver driver;
	ManageDriver manageDriver;
	DataTable dt;
	LoginPage objLoginPage;
	
	public Login() throws Exception{		
		manageDriver = ManageDriver.getManageDriver();
		driver = manageDriver.getDriver();
		dt = new DataTable();
		objLoginPage = new LoginPage();
	}//End of Login method

	public Login(String strBrowser) throws Exception{		
		manageDriver = ManageDriver.getManageDriver();
		dt = new DataTable();
		objLoginPage = new LoginPage();
		//manageDriver.initializeDriver(strBrowser);
		//driver = manageDriver.getDriver();
	}	
//###################################################################################################################################################################  
//Function name		: fnLogin(String strEnvironment,String strBrowser, String strClient)
//Class name		: Login
//Description 		: Use this function to Invoke the browser and login to application
//Parameters 		: N/A
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public boolean fnLogin(String strEnvironment,String strBrowser, String strClient,ITestContext context)
	{		
		try{
			//Check for browser type and create an object:
			if(strEnvironment.isEmpty() || strBrowser.isEmpty() ||strClient.isEmpty() )
			{
				System.out.println("<METHOD: fnLogin><CLASS: Login> - strEnvironment/strBrowser/strClient is empty");
				log.info("<METHOD: fnLogin><CLASS: Login> - strEnvironment/strBrowser/strClient is empty");
				return false;
			}
			
			dt.setExcelFile("C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\TestData\\Sites_v1.xlsx",strEnvironment);
			int intLoginRowCnt = dt.getRowCount();
			for(int i=1;i<=intLoginRowCnt;i++){			
				if(dt.getCellData(i, "Client").toString().equals(strClient.toString()))
				{					
					if(dt.getCellData(i, "SetProxy").toString().toUpperCase().equals("YES")){
						manageDriver.initializeDriver(strBrowser,"setProxy_ON");						
					}
					else{
						manageDriver.initializeDriver(strBrowser,"setProxy_OFF");					
					}
					driver = manageDriver.getDriver();
					//manageDriver.initializeDriver(strBrowser);
					String strURL=dt.getCellData(i, "URL");
					//System.out.println("URL for client: "+strClient+" is "+strURL);
					log.info("URL for client: "+strClient+" is "+strURL);
					if(dt.getCellData(i, "LoginType").equals("SSO")){						
						strURL=strURL.replace("http://", "");
						strURL=strURL.replace("https://", "");					
						String password = dt.getCellData(i, "Password");
						if(password.contains("@")){
							password = password.replace("@", "%40").toString();																				
						}//End of IF for password containing "@" character
						strURL = "https://"+dt.getCellData(i, "UserName")+":"+password+"@"+strURL;
						//System.out.println(strURL);
						log.info("URL for client: "+strClient+" is "+strURL);
					}//End of IF condition to check if Login Type=SSOTrust
						
					driver.get(strURL);						
					Thread.sleep(1000);
					driver.manage().window().maximize();
					switch (dt.getCellData(i, "LoginType").toUpperCase()) {
						case "NONSSO":
							return fnLogin_NonSSO_v1(i,context);
						case "SSO":
							return fnLogin_SSO(i,context);
						case "SITEMINDER":
							return fnLogin_SiteMinder(i,context); 
						default:
							return false;
						
							
					}//End of Switch condition for Login type	
				}//End of IF to check client name						
			}//End of for loop for total number of rows
	
			return false;
		}//End of Try block
		catch (Exception e){
			e.printStackTrace();
			return false;
		}//End of catch block

	} // End of "fnLogin" method
		
//###################################################################################################################################################################  
//Function name		: fnLogin_NonSSO_v1(int intDTLogin_Row)
//Class name		: Login
//Description 		: Generic function to login to Maxit NonSSO Clients
//Parameters 		: N/A
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	private boolean fnLogin_NonSSO_v1(int intDTLogin_Row,ITestContext context){
		//ITestContext context;
		String strUserName="",strPassword="", strClient ;
		//Read values from LoginSheet
		try {					
			strUserName = dt.getCellData(intDTLogin_Row, "UserName");
			strPassword = dt.getCellData(intDTLogin_Row, "Password");
			strClient = dt.getCellData(intDTLogin_Row, "Client");
			if((strUserName.equals("Column not found")||strUserName.equals("")) || (strPassword.equals("Column not found")||strPassword.equals("")) || (strClient.equals("Column not found")||strClient.equals("")) ){
				log.error("Exiting fnLogin_NonSSO as the credentials are not read from excel due to 'Column not found/other exception',please check..!!Client:"+strClient+";Login ID:"+strUserName+";Password:"+strPassword);
				return false;
			}//End of IF condition to check if Credentials are retrieved.
			log.info("Logging into Maxit client(NonSSO):"+strClient+";LoginID:"+strUserName+";Password:"+strPassword);
			
			//NonSSO CLient: Verify & Enter User name
			if(objLoginPage.VerifyUserNameExists(driver)){
				CommonUtils.CaptureScreenshot(context, driver, "Verify UserName Exists", "Verifying User Name field exists in Login page", "", objLoginPage.txt_UserName);
				if(!objLoginPage.EnterUserName(driver,strUserName)){
					//CommonUtils.captureScreenshot(driver, "");
					String strScreenshotPath = CommonUtils.CaptureScreenshot(context, driver, "Verify UserName Exists", "Verifying User Name field exists in Login page", "", objLoginPage.txt_UserName);
					System.out.println("Screenshot path is:"+strScreenshotPath);
					//CommonUtils.loggerScreenshot_Path(strScreenshotPath);
					return false;
				}//End of if to check return value of "EnterUserName" method
			}//End of if to check return value of "VerifyUserNameExists" method

			//NonSSO CLient: Verify & Enter Password
			if(objLoginPage.VerifyPasswordExists(driver)){
				if(!objLoginPage.EnterPassword(driver,strPassword)){
					return false;
				}//End of if to check return value of "EnterPassword" method
			}//End of if to check return value of "VerifyPasswordExists" method

			//NonSSO CLient: Verify & Click on Login button
			if(objLoginPage.VerifyLoginExists(driver)){
				if(!objLoginPage.ClickLogin(driver)){
					return false;
				}//End of if to check return value of "ClickLogin" method
			}//End of if to check return value of "VerifyLoginExists" method
		
			//NonSSO CLient: Verify if Login is success by verifying SciVantage Logo is displayed.
			if(objLoginPage.VerifyScivanLogo(driver)){
				System.out.println("<METHOD: fnLogin_NonSSO_v1><CLASS: Login> - Login is to client "+strClient+" is successful");
				return true;
			}//End of IF condition to Verify if Login is success by verifying SciVantage Logo is displayed.
			else{
				System.out.println("<METHOD: fnLogin_NonSSO_v1><CLASS: Login> - Login is to client "+strClient+" is NOT successful..!!");
				String strScreenshotPath = CommonUtils.CaptureScreenshot(context, driver, "Login Unsuccessful", "Login Unsuccessful", "", null);
				return false;
			}	
			
		}//End of try Block
		
		catch (Exception e){
			System.out.println("<METHOD: fnLogin_NonSSO><CLASS: Login> - Exception, please check!!!!");	
			e.printStackTrace();			
			return false;
			
		}//End of Catch Block
	}//End of fnLogin_NonSSO_v1 method		

//###################################################################################################################################################################  
//Function name		: fnLogin_SSO(int intDTLogin_Row)
//Class name		: Login
//Description 		: Generic function to login to Maxit SSO Clients
//Parameters 		: N/A
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	private boolean fnLogin_SSO(int intDTLogin_Row,ITestContext context){
		String strUserName="",strPassword="", strClient ;
		//Read values from LoginSheet
		try {					
			strUserName = dt.getCellData(intDTLogin_Row, "UserName");
			strPassword = dt.getCellData(intDTLogin_Row, "Password");
			strClient = dt.getCellData(intDTLogin_Row, "Client");
			if((strUserName.equals("Column not found")||strUserName.equals("")) || (strPassword.equals("Column not found")||strPassword.equals("")) || (strClient.equals("Column not found")||strClient.equals("")) ){
				System.out.println("Exiting fnLogin_SSO as the credentials are not read from excel due to 'Column not found/other exception',please check..!! ");
				System.out.println("Client:"+strClient+";Login ID:"+strUserName+";Password:"+strPassword);
				return false;
			}//End of IF condition to check if Credentials are retrieved.
			System.out.println("Logging into Maxit client(SSO):"+strClient+";LoginID:"+strUserName+";Password:"+strPassword);
			
			if(!CommonUtils.acceptAlert(driver)){
				return false;
			}
			
			//SSO CLient: Verify & Enter User name in Single Sign-On Test Page
			if(objLoginPage.VerifyUserNameExists(driver)){
				if(!objLoginPage.EnterUserName(driver,"AutoTest")){
					return false;
				}//End of if to check return value of "EnterUserName" method
			}//End of if to check return value of "VerifyUserNameExists" method

			if(!objLoginPage.VerifySSO_CheckALL_Click(driver)){
					return false;
			}//End of if to check return value of "VerifyUserNameExists" method			
			
			if(objLoginPage.VerifyLoginExists(driver)){
				if(!objLoginPage.ClickLogin(driver)){
					return false;
				}//End of if to check return value of "ClickLogin" method
			}//End of if to check return value of "VerifyLoginExists" method
		
			//NonSSO CLient: Verify if Login is success by verifying SciVantage Logo is displayed.
			if(objLoginPage.VerifyScivanLogo(driver)){
				System.out.println("<METHOD: fnLogin_SSO><CLASS: Login> - Login is to client "+strClient+" is successful");
				return true;
			}//End of IF condition to Verify if Login is success by verifying SciVantage Logo is displayed.
			else{
				System.out.println("<METHOD: fnLogin_SSO><CLASS: Login> - Login is to client "+strClient+" is NOT successful..!!");	
				return false;
			}

		}//End of try Block		
		
		catch (Exception e){
			System.out.println("<METHOD: fnLogin_SSO><CLASS: Login> - Exception, please check!!!!");	
			e.printStackTrace();			
			return false;
			
		}//End of Catch Block
	}//End of fnLogin_SSO method	

//###################################################################################################################################################################  
//Function name		: fnLogin_SiteMinder(int intDTLogin_Row)
//Class name		: Login
//Description 		: Generic function to login to Maxit SiteMinder(Endeavour clients with Foxy Proxy) Clients
//Parameters 		: N/A
//Assumption		: These clients can be logged into Firefox only(List of Prod clients : EDJ,APX,CTR,FMS,ING,LEU,MSC,PPR,PPL,SFR,SFB,SCH,SFM,STC,SWN,WLF)
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	private boolean fnLogin_SiteMinder(int intDTLogin_Row, ITestContext context){
		String strUserName="",strPassword="", strClient ;
		//Read values from LoginSheet
		try {					
			strUserName = dt.getCellData(intDTLogin_Row, "UserName");
			strPassword = dt.getCellData(intDTLogin_Row, "Password");
			strClient = dt.getCellData(intDTLogin_Row, "Client");
			if((strUserName.equals("Column not found")||strUserName.equals("")) || (strPassword.equals("Column not found")||strPassword.equals("")) || (strClient.equals("Column not found")||strClient.equals("")) ){
				System.out.println("Exiting fnLogin_SiteMinder as the credentials are not read from excel due to 'Column not found/other exception',please check..!! ");
				System.out.println("Client:"+strClient+";Login ID:"+strUserName+";Password:"+strPassword);
				return false;
			}//End of IF condition to check if Credentials are retrieved.
			System.out.println("Logging into Maxit client(SiteMinder):"+strClient+";LoginID:"+strUserName+";Password:"+strPassword);
			
			if(!CommonUtils.acceptAlert(driver)){
				return false;
			}//Checkpoint to see if Alert exists and Accept it

			if(!objLoginPage.EnterUserID(driver,strUserName)){
				return false;
			}//End of if to check return value of "EnterUserID" method
			
			if(!objLoginPage.EnterUserPwrd(driver,strPassword)){
				return false;
			}//End of if to check return value of "EnterUserPwrd" method
		
			if(!objLoginPage.ClickEndeavourLogin(driver)){
				return false;				
			}//End of if to check return value of "ClickEndeavourLogin" method
		
			//SiteMinder CLient: Verify if Login is success by verifying SciVantage Logo is displayed.
			if(objLoginPage.VerifyScivanLogo(driver)){
				System.out.println("<METHOD: fnLogin_SiteMinder><CLASS: Login> - Login is to client "+strClient+" is successful");				
				return true;
			}//End of IF condition to Verify if Login is success by verifying SciVantage Logo is displayed.
			else{
				System.out.println("<METHOD: fnLogin_SiteMinder><CLASS: Login> - Login is to client "+strClient+" is NOT successful..!!");	
				return false;
			}

		}//End of try Block		
		
		catch (Exception e){
			System.out.println("<METHOD: fnLogin_SiteMinder><CLASS: Login> - Exception, please check!!!!");	
			e.printStackTrace();			
			return false;
			
		}//End of Catch Block
	}//End of fnLogin_SiteMinder method	
	
}//End of <Class: fnLogin>
