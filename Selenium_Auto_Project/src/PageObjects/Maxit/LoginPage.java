package PageObjects.Maxit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import functionalLibrary.Global.CommonUtils;

public class LoginPage {

	private static Logger log = Logger.getLogger("LoginPage_v1.class");
    public WebElement txt_UserName;
    private WebElement txtbx_Password;
    private WebElement btn_Login;
    private WebElement lnk_Logout;
    private WebElement img_ScivanLogo;
    private WebElement btn_CheckUncheckALL;
    private List<WebElement> chkbox_SSO_ALL;
    
    //For SiteMinder Clients:
    private WebElement txt_UserID;
    private WebElement txtbx_UserPwrd;
    private WebElement btn_Endeavor_Login;
    
//    private WebDriver driver;
//    {
//    	driver = ManageDriver.getManageDriver().getDriver();
//    }
      
	private void txtbx_LoginId(WebDriver driver){ 
		txt_UserName = driver.findElement(By.id("loginId"));
		//log.debug("txtbx_LoginId method is completed");
	}//End of txtbx_UserName method
	 
	private void txtbx_Password(WebDriver driver){
		txtbx_Password = driver.findElement(By.id("password"));
	}//End of txtbx_Password method
	
	 private void btn_Login(WebDriver driver){
		 btn_Login = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/input"));
	 }//End of btn_Login method
	 
	private void lnk_Logout(WebDriver driver){ 
		lnk_Logout = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[1]/a"));
	}//End of lnk_Logut method
	
	private void img_ScivanLogo(WebDriver driver){ 
		img_ScivanLogo = driver.findElement(By.xpath("//*[@id=\"header\"]/div/img"));
	}//End of lnk_Logut method

	//SSO Clients objects:
	private void btn_CheckUncheckALL(WebDriver driver){
		btn_CheckUncheckALL = driver.findElement(By.id("checkUncheckAll"));
	}
	
	private void chkbox_SSO_ALL(WebDriver driver){
		chkbox_SSO_ALL = driver.findElements(By.xpath("//*[@id='functions']//*[@type='checkbox']"));
	}
	
	//SiteMinder:
	private void txt_UserID(WebDriver driver){ 
		txt_UserID = driver.findElement(By.id("inp_username"));
	}//End of txt_UserID method
	 
	private void txtbx_UserPwrd(WebDriver driver){
		txtbx_UserPwrd = driver.findElement(By.id("inp_password"));
	}//End of txtbx_UserPwrd method
	
	 private void btn_Endeavor_Login(WebDriver driver){
		 btn_Endeavor_Login = driver.findElement(By.id("inp_submit"));
	 }//End of btn_Endeavor_Login method
	
//*********************************************************************************************************************************************************	
		//Performing Actions
//*********************************************************************************************************************************************************
//###################################################################################################################################################################  
//Function name		: VerifyUserNameExists(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to verify if UserName element exists
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public boolean VerifyUserNameExists(WebDriver driver){
		try{
			//if(txt_UserName==null)
			{					
			txtbx_LoginId(driver);
			}//End of IF condition to check txt_UserName element
			
			if(CommonUtils.isElementPresent(txt_UserName)){
				return true;	
			}//End of IF Condition to check if txt_UserName element exists
			return false;	
		}//End of Try block
		catch(Exception e){
			log.error("Exception in <LoginPage.VerifyUserNameExists>: UserName field is not displayed in UI..!!!");
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}//End of VerifyUserNameExists Method	
	
//###################################################################################################################################################################  
//Function name		: EnterUserName(WebDriver driver,String strUserName)
//Class name		: LoginPage
//Description 		: Function to Input value to UserName field
//Parameters 		: driver object & Input value(User Name)
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################
	public boolean EnterUserName(WebDriver driver,String strUserName){
		try{
			//if(txt_UserName==null)
			{		
			txtbx_LoginId(driver);
			}//End of IF condition to check txt_UserName element
			
			if(CommonUtils.isElementPresent(txt_UserName)){
				if(strUserName.isEmpty()){
					System.out.println("UserName value is empty, please check..!!");
					return false;
				}//End of IF condition to check if Userame is empty
				txt_UserName.sendKeys(strUserName);		
				return true;
			}//End of IF Condition to check if txt_UserName element exists
			return false;
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.EnterUserName>: UserName field is not displayed in UI..!!!");
			return false;
		}
	}//End of EnterUserName Method

//###################################################################################################################################################################  
//Function name		: VerifyPasswordExists(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to verify if Password element exists
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public boolean VerifyPasswordExists(WebDriver driver){
		try{
			//if(txtbx_Password==null)
			{					
			txtbx_Password(driver);
			}//End of IF condition to check txtbx_Password element
			
			if(CommonUtils.isElementPresent(txtbx_Password)){
				return true;	
			}//End of IF Condition to check if txtbx_Password element exists
			return false;	
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.VerifyPasswordExists>: Password field is not displayed in UI..!!!");
			return false;
		}
	}//End of VerifyPasswordExists Method	
	
//###################################################################################################################################################################  
//Function name		: EnterPassword(WebDriver driver,String strPassword)
//Class name		: LoginPage
//Description 		: Function to Input value to Password field
//Parameters 		: driver object & Input value(Password)
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################
	public boolean EnterPassword(WebDriver driver,String strPassword){
		try{
			//if(txtbx_Password==null)
			{		
			txtbx_Password(driver);
			}//End of IF condition to check txtbx_Password element
			
			if(CommonUtils.isElementPresent(txtbx_Password)){
				if(strPassword.isEmpty()){
					System.out.println("Password value is empty, please check..!!");	
					return false;
				}//End of IF condition to check if Password is empty
				txtbx_Password.sendKeys(strPassword);
				return true;
			}//End of IF Condition to check if txtbx_Password element exists
			return false;	
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.EnterPassword>: Password field is not displayed in UI..!!!");
			return false;
		}
	}//End of EnterPassword Method	

//###################################################################################################################################################################  
//Function name		: ClickLogin(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to Click on LogOut link
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public boolean ClickLogin(WebDriver driver){
		try{
			//if(btn_Login==null)
			{			
				btn_Login(driver);
			}//End of IF condition to check btn_Login element
			
			if(CommonUtils.isElementPresent(btn_Login)){
				btn_Login.click();
				return true;
			}//End of IF Condition to check if btn_Login element exists
			else{
				System.out.println("Login button is NOT displayed..!!");
				return false;
			}//End of ELSE condition if element is not displayed
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.ClickLogin>: Login button is not found/displayed..!!!");
			return false;
		}
	}//End of ClickLogin Method

//###################################################################################################################################################################  
//Function name		: VerifyLoginExists(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to verify if Login button is displayed
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public boolean VerifyLoginExists(WebDriver driver) throws Exception{
		try{
			//if(btn_Login==null)
			{		
				btn_Login(driver);
			}//End of IF condition to check btn_Login element
			
			if(CommonUtils.isElementPresent(btn_Login)){
				return true;
			}//End of IF Condition to check if btn_Login element exists
			else{
				System.out.println("Login Button is NOT displayed..!!");				
				CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Login.jpeg");
				return false;
			}//End of ELSE condition if element is not displayed
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.VerifyLoginExists>:Login button is not found/displayed..!!!");
			CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Login.jpeg");
			return false;
		}
	}//End of VerifyLoginExists Method	
	
	
//###################################################################################################################################################################  
//Function name		: ClickLogOut(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to Click on LogOut link
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public boolean ClickLogOut(WebDriver driver){
		try{
			//if(lnk_Logout==null)
			{			
				lnk_Logout(driver);
			}//End of IF condition to check lnk_Logout element
			
			if(CommonUtils.isElementPresent(lnk_Logout)){
				lnk_Logout.click();
				return true;
			}//End of IF Condition to check if lnk_Logout element exists
			else{
				System.out.println("LogOut link is NOT displayed..!!");
				return false;
			}//End of ELSE condition if element is not displayed
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.ClickLogOut>: Logout Link not found/displayed..!!!");
			return false;
		}
	}//End of ClickLogOut Method

//###################################################################################################################################################################  
//Function name		: VerifyLogOut(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to verify if Logout link is displayed, to confirm Login Success
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public boolean VerifyLogOut(WebDriver driver) throws Exception{
		try{
			//if(lnk_Logout==null)
			{		
				lnk_Logout(driver);
			}//End of IF condition to check lnk_Logout element
			
			if(CommonUtils.isElementPresent(lnk_Logout)){
				return true;
			}//End of IF Condition to check if lnk_Logout element exists
			else{
				System.out.println("LogOut link is NOT displayed..!!");				
				CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Scrrenshot1.jpeg");
				return false;
			}//End of ELSE condition if element is not displayed
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.VerifyLogOut>:Logout Link is not found/displayed..!!!");
			CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Scrrenshot1.jpeg");
			return false;
		}
	}//End of VerifyLogOut Method	

//###################################################################################################################################################################  
//Function name		: VerifyScivanLogo(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to verify if Scivantage Logo is displayed, to confirm Login Success
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public boolean VerifyScivanLogo(WebDriver driver) throws Exception{
		try{
			//if(lnk_Logout==null)
			{		
				img_ScivanLogo(driver);
			}//End of IF condition to check lnk_Logout element
			
			if(CommonUtils.isElementPresent(img_ScivanLogo)){
				return true;
			}//End of IF Condition to check if lnk_Logout element exists
			else{
				System.out.println("Scivantage Logo image is NOT displayed..!!");				
				CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Scrrenshot1.jpeg");
				return false;
			}//End of ELSE condition if element is not displayed
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.VerifyScivanLogo>:Scivantage Logo is not found/displayed..!!!");
			CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Scrrenshot1.jpeg");
			return false;
		}
	}//End of VerifyScivanLogo Method		
	
//###################################################################################################################################################################  
//Function name		: VerifySSO_CheckALL(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to verify if Check/Uncheck ALL button is displayed in Single Sign-On Test page(Applicable only for SSO clients)
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public boolean VerifySSO_CheckALL_Click(WebDriver driver) throws Exception{
		try{
			btn_CheckUncheckALL(driver);			
			if(CommonUtils.isElementPresent(btn_CheckUncheckALL)){
				if(btn_CheckUncheckALL.isEnabled()){
					btn_CheckUncheckALL.click();
					//verify if clicked all checkboxes
					return VerifySSO_CheckALLSuccess(driver);					
				}
				else{
					System.out.println("Check/Uncheck All button in Single Sign-On Test page is NOT enabled..!!");				
					CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Scrrenshot1.jpeg");
					return false;
				}
			}//End of IF Condition to check if lnk_Logout element exists
			else{
				System.out.println("Check/Uncheck All button in Single Sign-On Test page is NOT displayed..!!");				
				CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Scrrenshot1.jpeg");
				return false;
			}//End of ELSE condition if element is not displayed
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.VerifySSO_CheckALL>:Check/Uncheck All button in Single Sign-On Test page is not found/displayed..!!!");
			CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Scrrenshot1.jpeg");
			return false;
		}
	}//End of VerifySSO_CheckALL_Click Method		
	
//###################################################################################################################################################################  
//Function name		: VerifySSO_CheckALLSuccess(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to verify if Check/Uncheck ALL button is clicked successfully in Single Sign-On Test page(Applicable only for SSO clients)
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public boolean VerifySSO_CheckALLSuccess(WebDriver driver) throws Exception{
		try{
			chkbox_SSO_ALL(driver);
			if(CommonUtils.isElementPresent(btn_CheckUncheckALL)){							
				List<WebElement> ele_CheckAll = chkbox_SSO_ALL;
				for(WebElement eachCheckBox : ele_CheckAll){
					if(!eachCheckBox.isSelected()){
						System.out.println("Checkbox: "+eachCheckBox.getText()+" is not selected, so selecting now");				
						eachCheckBox.click();
					}//End of IF condition to verify if "Check box" is not selected already
				}//End of FOR loop to loop thru each Check box	
			return true;
			}//End of IF condition to check if Element exists
			else{
				System.out.println("Exception in <LoginPage.VerifySSO_CheckALLSuccess>:Check/Uncheck All button in Single Sign-On Test page is not found/displayed..!!!");
				CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Scrrenshot1.jpeg");
				return false;
			}
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.VerifySSO_CheckALLSuccess>:Check/Uncheck All button in Single Sign-On Test page is not found/displayed..!!!");
			CommonUtils.captureScreenshot(driver, "C:\\Users\\kavitha.golla\\Selenium_Screenshots\\Scrrenshot1.jpeg");
			return false;
		}
	}//End of VerifySSO_CheckALLSuccess Method	
	
	
//###################################################################################################################################################################  
//Function name		: EnterUserID(WebDriver driver,String strUserName)
//Class name		: LoginPage
//Description 		: Function to Input value to UserID field
//Parameters 		: driver object & Input value(User Name)
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################
	public boolean EnterUserID(WebDriver driver,String strUserName){
		try{	
			txt_UserID(driver);			
			if(CommonUtils.isElementPresent(txt_UserID)){
				if(strUserName.isEmpty()){
					System.out.println("UserName value is empty, please check..!!");
					return false;
				}//End of IF condition to check if Userame is empty
				txt_UserID.sendKeys(strUserName);		
				return true;
			}//End of IF Condition to check if txt_UserID element exists
			return false;
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.EnterUserID>: UserID field is not displayed in UI..!!!");
			return false;
		}
	}//End of EnterUserID Method	
	
	
//###################################################################################################################################################################  
//Function name		: EnterUserPwrd(WebDriver driver,String strPassword)
//Class name		: LoginPage
//Description 		: Function to Input value to Password field
//Parameters 		: driver object & Input value(Password)
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################
	public boolean EnterUserPwrd(WebDriver driver,String strPassword){
		try{	
			txtbx_UserPwrd(driver);			
			if(CommonUtils.isElementPresent(txtbx_UserPwrd)){
				if(strPassword.isEmpty()){
					System.out.println("Password value is empty, please check..!!");
					return false;
				}//End of IF condition to check if Password is empty
				txtbx_UserPwrd.sendKeys(strPassword);		
				return true;
			}//End of IF Condition to check if txtbx_UserPwrd element exists
			return false;
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.EnterUserPwrd>: UserID field is not displayed in UI..!!!");
			return false;
		}
	}//End of EnterUserPwrd Method		
	
//###################################################################################################################################################################  
//Function name		: ClickEndeavourLogin(WebDriver driver)
//Class name		: LoginPage
//Description 		: Function to Click on Login button
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################	
	public boolean ClickEndeavourLogin(WebDriver driver){
		try{		
			btn_Endeavor_Login(driver);						
			if(CommonUtils.isElementPresent(btn_Endeavor_Login)){
				btn_Endeavor_Login.click();
				return true;
			}//End of IF Condition to check if btn_Login element exists
			else{
				System.out.println("Login button is NOT displayed..!!");
				return false;
			}//End of ELSE condition if element is not displayed
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <LoginPage.ClickEndeavourLogin>: Login button is not found/displayed..!!!");
			return false;
		}
	}//End of ClickEndeavourLogin Method



}//End of <class:LoginPage>
