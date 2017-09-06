package PageObjects.Maxit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functionalLibrary.Global.CommonUtils;
import functionalLibrary.Global.ManageDriver;

public class SearchPage_POM {
	//WebDriver driver;
	WebDriver driver = ManageDriver.getManageDriver().getDriver();
	WebDriverWait wait = new WebDriverWait(driver,30);
	//Variables declaration:
    
//    @FindBy(how=How.XPATH,using="//*[contains(@class,'navMenuBullet') and contains(@text,'Search')]")
	@FindBy(how=How.XPATH,using="//a[@href ='/Modules/Accounts/Maxit/Search/enter.php']")
    private WebElement lnk_Search;
    
    @FindBy(how=How.ID,using="initialView")
    private WebElement lst_View;
    
    @FindBy(how=How.ID,using="account")
    private WebElement txtbx_Account;
    
    @FindBy(how=How.ID,using="securityIDType")
    private WebElement lst_SecIDType;
   
    @FindBy(how=How.ID,using="securityID")
    private WebElement txtbx_SecurityID;
    
    @FindBy(how=How.XPATH,using="//*[@id=\"maxitSearchForm\"]/div[6]/div/input")
    private WebElement btn_Submit;

//    @FindBy(how=How.XPATH,using=".//*[@class='x-panel-header x-unselectable']")
    @FindBy(how=How.XPATH,using=".//*[@class='x-panel-header-text']")
    private WebElement ele_SearchPagTitle;   
    
    @FindBy(how=How.XPATH,using="//*[@class=\"ext-el-mask-msg x-mask-loading\"]")
    private WebElement ele_Loading; 
    
    @FindBy(how=How.XPATH,using="//*[@class=\"x-grid3-hd-row\"]")
    private WebElement ele_ResTbl_Header; 
    
    
//*********************************************************************************************************************  
    //Performing Actions on objects in Search Page:
//*********************************************************************************************************************   
  //###################################################################################################################################################################  
  //Function name		: VerifySearchLinkExists(WebDriver driver)
  //Class name		: SearchPage
  //Description 		: Function to verify if Search link exists
  //Parameters 		: driver object
  //Assumption		: None
  //Developer			: Kavitha Golla
  //###################################################################################################################################################################		
  	public boolean VerifySearchLinkExists(){
  		try{  			
  			if(CommonUtils.isElementPresent(lnk_Search)){
  				return true;	
  			}//End of IF Condition to check if lnk_Search element exists
  			return false;	
  		}//End of Try block
  		catch(Exception e){
  			System.out.println("Exception in <SearchPage_POM.VerifySearchLinkExists>: Search Link is not displayed in UI..!!!");
  			return false;
  		}
  	}//End of VerifySearchLinkExists Method	

  //###################################################################################################################################################################  
  //Function name		: ClickSearchLink(WebDriver driver)
  //Class name		: SearchPage
  //Description 		: Function to Navigate to Search Page
  //Parameters 		: driver object
  //Assumption		: None
  //Developer			: Kavitha Golla
  //###################################################################################################################################################################		
  	public boolean ClickSearchLink(){
  		try{
  			if(CommonUtils.isElementPresent(lnk_Search)){
  				lnk_Search.click();
  				return true;	
  			}//End of IF Condition to check if lnk_Search element exists
//			String strScreenshotPath = CommonUtils.CaptureScreenshot(context, driver, "Verify UserName Exists", "Verifying User Name field exists in Login page", "", objLoginPage.txt_UserName);
//			System.out.println("Screenshot path is:"+strScreenshotPath);
  			return false;	
  		}//End of Try block
  		catch(Exception e){
  			System.out.println("Exception in <SearchPage_POM.ClickSearchLink>: Search Link is not displayed in UI..!!!");
  			e.printStackTrace();
  			return false;
  		}
  	}//End of ClickSearchLink Method	
  		
  	
//###################################################################################################################################################################  
//Function name		: VerifyViewList()
//Class name		: SearchPage
//Description 		: Function to verify the View list dropdown in Search Page
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public boolean VerifyViewList(){ 
		List<String> actViewList = new ArrayList<String>();
		ArrayList<String> expViewList = new ArrayList<String>();
		expViewList.addAll(Arrays.asList("Account","Open/Closed","Security Xref","Pos Recon","Trx Error","Raw Trades","Ledger","Unrealized","Realized","No Cost Basis","Audit Trail"));
		//System.out.println("Expected View List is : "+expViewList.toString());		
		
		List<WebElement> ViewList = lst_View.findElements(By.tagName("option"));
		for (WebElement option : ViewList) {
			actViewList.add(option.getText());
		}//End of FOR loop to read View List from Application
		//System.out.println("Actual View List is : "+actViewList.toString());
		
		if(!actViewList.toString().equals(expViewList.toString())){
			System.out.println("Checkpoint_Fail:Verifying View weblist in Search Page.Expected View List = "+expViewList.toString()+"; Actual View List = "+actViewList.toString());
			return false;
		}//End of IF condition to check Expected View List & Actual View List
		return true;
}//End of VerifyViewList method    	  	
  	
//###################################################################################################################################################################  
//Function name		: VerifySearchPageTitle(WebDriver driver)
//Class name		: SearchPage
//Description 		: Function to that the page is Navigated to Search Page
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
  	public boolean VerifySearchPageTitle(){
  		try{
  			
  			if(CommonUtils.isElementPresent(ele_SearchPagTitle)){
  				if(ele_SearchPagTitle.getAttribute("textContent").trim().equals("Search Criteria")){
  					return true;
  				}
  				else{
  					System.out.println("Search Page title is not correct, Expected:Search Criteria and Actual:"+ele_SearchPagTitle.getText());
  					return false;
  				}
  			}//End of IF Condition to check if ele_SearchPagTitle element exists
  			return false;	
  		}//End of Try block
  		catch(Exception e){
  			System.out.println("Exception in <SearchPage_POM.VerifySearchPageTitle>: Search Page is not displayed in UI..!!!");
  			return false;
  		}
  	}//End of VerifySearchPageTitle Method	
  
  //###################################################################################################################################################################  
  //Function name		: SelectViewPage()
  //Class name			: SearchPage_POM
  //Description 		: Function to select the page view from "View List"
  //Parameters 			: 
  //Assumption			: None
  //Developer			: Kavitha Golla
  //###################################################################################################################################################################		
    	public boolean SelectViewPage(String strViewPage){
    		try{
    			if(!VerifyViewList()){    				
    				return false;
    			}
    			List<WebElement> ViewList = lst_View.findElements(By.tagName("option"));
    			for (WebElement option : ViewList) {
    				if(option.getText().equals(strViewPage)){
    					System.out.println("Navigating to Search Page View : "+strViewPage.toString());
    					option.click();    					
    					return true;
    				}    				
    			}//End of FOR loop to read View List from Application
    			return false;	
    		}//End of Try block
    		catch(Exception e){
    			System.out.println("Exception in <SearchPage_POM.SelectViewPage>: Search Page is not displayed in UI..!!!");
    			return false;
    		}
    	}//End of SelectViewPage Method  	
 
  //###################################################################################################################################################################  
  //Function name		: SearchPage_Input()
  //Class name			: SearchPage_POM
  //Description 		: Function to input data into Search Page
  //Parameters 			: 
  //Assumption			: None
  //Developer			: Kavitha Golla
  //###################################################################################################################################################################		
	public boolean SearchPage_Input(String strAccount,String strSecIDType,String strSecValue){
		try{
			boolean blnSecTypeCheck = false;
			txtbx_Account.sendKeys(strAccount);
			List<WebElement> SecType_List = lst_SecIDType.findElements(By.tagName("option"));
			for (WebElement option : SecType_List) {
				if(option.getText().equals(strSecIDType)){
					//System.out.println("Selecting SecIDTYpe : "+strSecIDType.toString());
					option.click();
					blnSecTypeCheck=true;
					break;
				}
			}//End of FOR loop
			if(!blnSecTypeCheck){
				for (WebElement option : SecType_List) {
					if(option.getText().equals("Security No")){
						System.out.println("Selecting default SecIDType as Security No");
						option.click();
						blnSecTypeCheck=true;
						break;
					}
				}//End of FOR loop
			}//End of IF condition to select default SecIDTpe= Security ID

			txtbx_SecurityID.sendKeys(strSecValue);
			Thread.sleep(5000);			
			btn_Submit.click();
			Thread.sleep(5000);
			//driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
			wait.until(ExpectedConditions.visibilityOf(ele_ResTbl_Header));
			System.out.println("Checkpoint : Search Page Navigation - [Passed]");
			return true;
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <SearchPage_POM.SearchPage_Input>: Search Page is not displayed in UI..!!!");
			System.out.println("Checkpoint : Search Page Navigation - [Failed]");
			e.printStackTrace();
			return false;
		}
	}//End of SearchPage_Input Method      	
   	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
  //###################################################################################################################################################################  
  //Function name	: VerifySearchLinkExists(WebDriver driver)
  //Class name		: SearchPage
  //Description 	: Function to verify if Search link exists
  //Parameters 		: driver object
  //Assumption		: None
  //Developer		: Kavitha Golla
  //###################################################################################################################################################################		
//	public static boolean VerifySearchLinkExists(WebDriver driver) throws Exception{ 
//		try{
//			lnk_Search = driver.findElement(By.partialLinkText("Search")); //xpath : //*[@id="navMenu"]/ul/li[3]/ul/li[2]/a
//			if(lnk_Search.isDisplayed()){
//				lnk_Search.click();
//				return true;
//			}
//			else{
//				System.out.println("SearchPage.lnk_Search():(Search link is not displayed in browser.");
//				return false;
//			}//End of Else condition
//		}//End of Try block
//		catch(Exception e){
//			return false;
//		}
//	}//End of lnk_Search method
	
////*********************************************************************************************************************
//	public static boolean list_View(WebDriver driver,String strViewPage){ 
//		element = driver.findElement(By.id("initialView"));
//		WebElement select = driver.findElement(By.id("initialView"));
//		List<WebElement> ViewList = select.findElements(By.tagName("option"));
//		for (WebElement option : ViewList) {
//			if(strViewPage.equals(option.getText().trim())){
//			 option.click(); 
//			 System.out.println("View dropdown in Search Page is selected to: "+strViewPage);
//			 return true;
//			}//End of IF condition		
//			else {
//				System.out.println("SearchPage->listView(). There is no element as "+strViewPage+" under View dropdown in Search Page");
//				return false;
//			}//End of Else condition
//		}//End of For loop to loop through each element in the View web list.	
//		return false;
//	}//End of list_View method	
//	
////*********************************************************************************************************************
////	public WebElement txtbx_Account(WebDriver driver){ 
////		element = driver.findElement(By.id("account")); 
////		return element;
////	}//End of txtbx_Account method		
//
////*********************************************************************************************************************
//	public static boolean list_SecType(WebDriver driver,String strSecType){ 
//		WebElement select = driver.findElement(By.id("securityIDType"));
//		List<WebElement> SecTypeList = select.findElements(By.tagName("option"));
//		for (WebElement option : SecTypeList) {
//			if(strSecType.equals(option.getText().trim())){
//				option.click();   
//				System.out.println("SecType dropdown in Search Page is selected to: "+strSecType);				
//			}//End of IF condition
//			else {
//				System.out.println("SearchPage->list_SecType(). There is no element as "+strSecType+" under SecurityID dropdown in Search Page");
//				return false;				
//			}//End of Else condition
//			}//End of For loop to loop through each element in the SecType web list.
//		return false;
//	}//End of list_SecType method

//*********************************************************************************************************************	
	
	
	
//###########################<< End of Objects identification >>######################################################################################################
//###########################<< Actions on Objects >>######################################################################################################

	
	
	
//#################################################################################################################################
}//End of <Class: SearchPage>
