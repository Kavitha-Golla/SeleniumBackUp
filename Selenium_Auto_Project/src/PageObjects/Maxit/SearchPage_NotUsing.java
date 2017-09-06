package PageObjects.Maxit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functionalLibrary.Global.CommonUtils;

public class SearchPage_NotUsing {
	//Variables declaration:
	//private static WebElement element = null;    
    private static WebElement lnk_Search;
    private static WebElement lst_View;
    private static WebElement txtbx_Account;
    private static WebElement lst_SecIDType;
    private static WebElement txtbx_SecurityID;
    private static WebElement btn_Submit;
    private static WebElement ele_SearchPagTitle;
    
   
    
//*********************************************************************************************************************  
    //Identify Objects in Search Page:
//*********************************************************************************************************************    
    private static void lnk_Search(WebDriver driver){
    	//lnk_Search = driver.findElement(By.partialLinkText("Search")); //xpath : //*[@id="navMenu"]/ul/li[3]/ul/li[2]/a
    	lnk_Search = driver.findElement(By.xpath("//*[contains(@class,'navMenuBullet') and contains(@text,'Search')]"));
    	//*[@id="navMenu"]/ul/li[3]/ul/li[2]/a		 
    	//xpath : //*[@id="navMenu"]/ul/li[3]/ul/li[2]/a
    	//navMenuBullet
    }//End if <Method:lnk_Search>
    
    private static void lst_View(WebDriver driver){
    	lst_View = driver.findElement(By.id("initialView"));
    }//End of <Method:lst_View>
    
    private static void txtbx_Account(WebDriver driver){
    	txtbx_Account = driver.findElement(By.id("account"));
    }//End of <Method: txtbx_Account> 
    
    private static void lst_SecIDType(WebDriver driver){
    	lst_SecIDType = driver.findElement(By.id("securityIDType"));
    }//End of <Method: lst_SecIDType>
    
    private static void txtbx_SecurityID(WebDriver driver){
    	txtbx_SecurityID = driver.findElement(By.id("securityID"));
    }//End of <Method: txtbx_SecurityID>     
    
    private static void btn_Submit(WebDriver driver){
    	btn_Submit = driver.findElement(By.xpath("//*[@id=\"maxitSearchForm\"]/div[6]/div/input"));
    }//End of <Method: btn_Submit>      

    private static boolean ele_SearchPagTitle(WebDriver driver){
    	ele_SearchPagTitle = driver.findElement(By.xpath(".//*[@class='x-panel-header x-unselectable']"));
    	if(ele_SearchPagTitle.getText().equals("Search Criteria")){
    		return true;
    	}
    	return false;
    }//End of <Method: ele_SearchPagTitle> 
    
    
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
  	public static boolean VerifySearchLinkExists(WebDriver driver){
  		try{
  			if(lnk_Search==null){					
  			lnk_Search(driver);
  			}//End of IF condition to check lnk_Search element
  			
  			if(CommonUtils.isElementPresent(lnk_Search)){
  				return true;	
  			}//End of IF Condition to check if lnk_Search element exists
  			return false;	
  		}//End of Try block
  		catch(Exception e){
  			System.out.println("Exception in <LoginPage.VerifySearchLinkExists>: Search Link is not displayed in UI..!!!");
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
  	public static boolean ClickSearchLink(WebDriver driver){
  		try{
  			if(lnk_Search==null){					
  			lnk_Search(driver);
  			}//End of IF condition to check lnk_Search element
  			
  			if(CommonUtils.isElementPresent(lnk_Search)){
  				lnk_Search.click();
  				return true;	
  			}//End of IF Condition to check if lnk_Search element exists
  			return false;	
  		}//End of Try block
  		catch(Exception e){
  			System.out.println("Exception in <LoginPage.ClickSearchLink>: Search Link is not displayed in UI..!!!");
  			return false;
  		}
  	}//End of ClickSearchLink Method	
  		
  	
//###################################################################################################################################################################  
//Function name		: VerifyViewList(WebDriver driver)
//Class name		: SearchPage
//Description 		: Function to verify the View list dropdown in Search Page
//Parameters 		: driver object
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public static void VerifyViewList(WebDriver driver){ 
		List<String> actViewList = new ArrayList<String>();
		ArrayList<String> expViewList = new ArrayList<String>();
		expViewList.addAll(Arrays.asList("Account","Open/Closed","Security Xref","Pos Recon","Trx Error","Raw Trades","Ledger","Unrealized","Realized","No Cost Basis","Audit Trail"));
		System.out.println("Expected View List is : "+expViewList.toString());		
		
		if(lst_View==null){
			lst_View(driver);
		}//End of IF condition to check lst_View element
		
		List<WebElement> ViewList = lst_View.findElements(By.tagName("option"));
		for (WebElement option : ViewList) {
			actViewList.add(option.getText());
		}//End of FOR loop to read View List from Application
		System.out.println("Actual View List is : "+actViewList.toString());
		
		if(!actViewList.toString().equals(expViewList.toString())){
			System.out.println("Checkpoint_Fail:Verifying View weblist in Search Page.Expected View List = "+expViewList.toString()+"; Actual View List = "+actViewList.toString());			
		}//End of IF condition to check Expected View List & Actual View List

}//End of VerifyViewList method    	  	
  	
  //###################################################################################################################################################################  
  //Function name		: VerifySearchPageTitle(WebDriver driver)
  //Class name		: SearchPage
  //Description 		: Function to that the page is Navigated to Search Page
  //Parameters 		: driver object
  //Assumption		: None
  //Developer			: Kavitha Golla
  //###################################################################################################################################################################		
  	public static boolean VerifySearchPageTitle(WebDriver driver){
  		try{
  			if(ele_SearchPagTitle==null){					
  			ele_SearchPagTitle(driver);
  			}//End of IF condition to check ele_SearchPagTitle element
  			
  			if(CommonUtils.isElementPresent(ele_SearchPagTitle)){
  				if(ele_SearchPagTitle.getText().equals("Search Criteria")){
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
  			System.out.println("Exception in <LoginPage.VerifySearchPageTitle>: Search Page is not displayed in UI..!!!");
  			return false;
  		}
  	}//End of VerifySearchPageTitle Method	
  	
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
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
////	public static WebElement txtbx_Account(WebDriver driver){ 
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
