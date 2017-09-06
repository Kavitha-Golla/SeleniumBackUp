package PageObjects.Maxit;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functionalLibrary.Global.CommonUtils;
import functionalLibrary.Global.ManageDriver;

public class SearchPage_Results {
	//static WebDriver driver;
	WebDriver driver = ManageDriver.getManageDriver().getDriver();
	WebDriverWait wait;
	//Variables declaration:
    
    @FindBy(how=How.XPATH,using="//*[@class=\"x-grid3-hd-row\"]")
    private WebElement ele_ResTable_Header; 
    
    @FindBy(how=How.XPATH,using="//*[@class=\"x-grid3-body\"]")
    private WebElement ele_ResultsTable; 
    
    @FindBy(how=How.XPATH,using="//*[@class=\"ux-maximgb-tg-elbow-active ux-maximgb-tg-elbow-plus\"]")
    private List<WebElement> ele_LedgerClickPlus; 

    @FindBy(how=How.XPATH,using="//*[@class=\"ux-maximgb-tg-elbow-active ux-maximgb-tg-elbow-end-plus\"]")
    private  List<WebElement> ele_LedgerClickPlusEnd; 
    
    @FindBy(how=How.XPATH,using="//*[@class=\"ux-maximgb-tg-elbow-active ux-maximgb-tg-elbow-minus\"]")
    private List<WebElement> ele_LedgerClickMinus; 
    
    @FindBy(how=How.XPATH,using="//*[@class=\"ux-maximgb-tg-elbow-active ux-maximgb-tg-elbow-end-minus\"]")
    private List<WebElement> ele_LedgerClickEndMinus; 
    
    @FindBy(how=How.XPATH,using="//*[@class=\"ext-el-mask-msg x-mask-loading\"]")
    private List<WebElement> ele_Loading; 
    
    
//*********************************************************************************************************************  
    //Performing Actions on objects in Search Page Results:
//*********************************************************************************************************************   
  //###################################################################################################################################################################  
  //Function name		: searchRes_NumOfRows_app(String strViewPage)
  //Class name			: SearchPage_Results
  //Description 		: Function to read number of records retrieved from search result
  //Parameters 			: Search Page name
  //Assumption			: None
  //Developer			: Kavitha Golla
  //###################################################################################################################################################################		
  	public int searchRes_NumOfRows_app(String strViewPage){
  		try{  	
  			wait = new WebDriverWait(driver,30);
  			wait.until(ExpectedConditions.visibilityOf(ele_ResultsTable));
  			
  			if(!CommonUtils.isElementPresent(ele_ResultsTable)){
  				System.out.println("Search_Results Table is not displayed,please check..!!");
  				return 0;	
  			}//End of IF Condition to check if ele_ResultsTable element exists
  			
  			List<WebElement> app_rows = ele_ResultsTable.findElements(By.tagName("tr"));
  			if(strViewPage.equalsIgnoreCase("LEDGER")){
  				
  				List<WebElement> clickPlus = ele_LedgerClickPlus;
  				List<WebElement> clickPlusEnd = ele_LedgerClickPlusEnd;
  				if(clickPlus.size()>0){
  	  				for (WebElement clickRow : clickPlus) {
  	  					clickRow.click();  	  				
  	  					wait.until(ExpectedConditions.visibilityOfAllElements(ele_LedgerClickMinus));  	  					
  	  				}//End of FOR loop to click on PLUS in Ledger page Application  					
  				}
  				if(clickPlusEnd.size()>0){
  	  				for (WebElement clickRowEnd : clickPlusEnd) {
  	  					clickRowEnd.click();
  	  					wait.until(ExpectedConditions.visibilityOfAllElements(ele_LedgerClickEndMinus));  	 
  	  				}//End of FOR loop to click on PLUS in Ledger page Application 					
  				}  				
	  			//System.out.println("Total number of rows="+app_rows.size()); 			
  			
  			}//End of IF Condition to check if search page=LEDGER
  			Thread.sleep(5000);
  			return app_rows.size();  			
  			
  		}//End of Try block
  		catch(Exception e){
  			System.out.println("Exception in <SearchPage_Results.searchRes_NumOfRows_app>: Search Results Table is not displayed in UI..!!!");
  			e.printStackTrace();
  			return 0;
  		}
  	}//End of searchRes_NumOfRows_app Method	

 
//###################################################################################################################################################################  
//Function name			: searchRes_GetColIndexByColName(String strColName)
//Class name			: SearchPage_Results
//Description 			: Function to read number of records retrieved from search result
//Parameters 			: driver object
//Assumption			: None
//Developer				: Kavitha Golla
//###################################################################################################################################################################		
	public int searchRes_GetColIndexByColName(String strColName){
  		try{  			
  			if(!CommonUtils.isElementPresent(ele_ResTable_Header)){
  				System.out.println("Search_Results Table Header is not displayed,please check..!!");
  				return 0;	
  			}//End of IF Condition to check if ele_ResTable_Header element exists
  			
  			List<WebElement> getCols = ele_ResTable_Header.findElements(By.tagName("td"));
  			//System.out.println("<Class:SearchPage_Results><Method:searchRes_GetColIndexByColName>: Total number of columns in Results table"+getCols.size());
  			
  			int iColLoop = 0;
  			for (WebElement eachCol : getCols) {  		
  				//System.out.println("iColLoop column number is : "+iColLoop);
  				//if (eachCol.getText().equalsIgnoreCase(strColName)){
  				if (eachCol.getAttribute("textContent").trim().equalsIgnoreCase(strColName)){
  					//String strColClass = eachCol.getAttribute("class").trim();
  					//Integer colIndex = Integer.parseInt(StringUtils.substringAfterLast(strColClass, "-"));  					
  					Integer colIndex = iColLoop;
  					//System.out.println("Column Name:"+eachCol.getAttribute("textContent").trim()+" and class:"+eachCol.getAttribute("class")+"and colIndex:"+colIndex);  					
  					return colIndex;
  				}//End of IF condition to check column name 
  				iColLoop++;
  			}//End of FOR loop to get Column Index
  			
  			System.out.println("<SearchPage_Results><Method:searchRes_GetColIndexByColName>: Search Results Table HEADER, Expected Column Name:"+strColName+" is not displayed in UI..!!!");
  			return -1;
  		}//End of Try block
  		catch (NumberFormatException e) {
  			System.out.println("NumberFormatException in <SearchPage_Results><Method:searchRes_GetColIndexByColName>: Column Index for column name: "+strColName+" is not Integer");
  			return -1;
  		}
  		catch(Exception e){
  			System.out.println("Exception in <SearchPage_Results><Method:searchRes_GetColIndexByColName>: Search Results Table HEADER is not displayed in UI..!!!");
  			return -1;
  		}  		
		
	}//End of searchRes_GetColIndexByColName method
  	
  //###################################################################################################################################################################  
  //Function name		: searchRes_getColValue(int rowNumber,int colIndex)
  //Class name			: SearchPage_Results
  //Description 		: Function to get column value for a given column index
  //Parameters 			: 
  //Assumption			: None
  //Developer			: Kavitha Golla
  //###################################################################################################################################################################		
	public String searchRes_getColValue(int rowNumber,int colIndex){
  		try{  			
  			if(!CommonUtils.isElementPresent(ele_ResultsTable)){
  				System.out.println("Search_Results Table is not displayed,please check..!!");
  				return null;	
  			}//End of IF Condition to check if ele_ResultsTable element exists
  			
  			List<WebElement> app_rows = ele_ResultsTable.findElements(By.tagName("tr"));
  			List<WebElement> app_cols = app_rows.get(rowNumber).findElements(By.tagName("td"));
  			String strColValue = app_cols.get(colIndex).getAttribute("textContent");
  			return strColValue;
  			
  		}//End of Try block
  		catch(Exception e){
  			System.out.println("Exception in <SearchPage_Results.searchRes_getColValue>: Search Results Table is not displayed in UI..!!!");
  			return "";
  		}
	}// End of searchRes_getColValue method.
	
	
	
//###########################<< End of Objects identification >>######################################################################################################
//###########################<< Actions on Objects >>######################################################################################################

	
	
	
//#################################################################################################################################
}//End of <Class: SearchPage_Results>
