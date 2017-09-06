package functionalLibrary.Maxit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import PageObjects.Maxit.*;
import functionalLibrary.Global.CommonUtils;
import functionalLibrary.Global.ManageDriver;

public class SearchPageFns {
	WebDriver driver = ManageDriver.getManageDriver().getDriver();
	 SearchPage_POM SearchPage = PageFactory.initElements(driver, SearchPage_POM.class);
	 SearchPage_Results SearchPageResults = PageFactory.initElements(driver, SearchPage_Results.class);
	public  boolean Search_NavigatePage(String strViewPage){
		try{
			if(!SearchPage.ClickSearchLink()){

				return false;
			}
			if(!SearchPage.VerifySearchPageTitle()){
				return false;
			}
			
			if(!SearchPage.SelectViewPage(strViewPage)){
				System.out.println("Please Input correct View page parameter.");
				return false;
			}//End of IF Condition to Select View Page
			return true;
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <SearchPageFns.Search_NavigatePage>: Unable to Navigate to Search Page:"+strViewPage+". Please Check..!!");			
			return false;
		}
				
	}//End of Search_NavigatePage method	
//*********************************************************************************************************************	
	public  boolean Search_Input(String strViewPage , String strAccount,String strSecIDType,String strSecValue ){		
		try{			
			if(!Search_NavigatePage(strViewPage)){
				return false;				
			}
			return SearchPage.SearchPage_Input(strAccount, strSecIDType, strSecValue);						
		}//End of Try block
		catch(Exception e){
			System.out.println("Exception in <SearchPageFns.Search_Input>: Search Page is not displayed in UI..!!!");
			return false;
		}
		
	}//End of Search_Input method	
//*********************************************************************************************************************		
	public  boolean SearchRes_RowCnt_Checkpoint(int expectedRowCount,String strViewPage){
		int sysRowCount = SearchPageResults.searchRes_NumOfRows_app(strViewPage);
		if(expectedRowCount != sysRowCount){
			System.out.println("<SearchPageFns.SearchRes_RowCnt_Checkpoint>: Expected Row Count = "+expectedRowCount+" and Actual Row Count = "+sysRowCount);
			System.out.println("Checkpoint : Row Count [Failed].Expected Row Count = "+expectedRowCount+" and Actual Row Count = "+sysRowCount);
			return false;
		}
		System.out.println("Checkpoint : Row Count [Passed].Expected Row Count = "+expectedRowCount+" and Actual Row Count = "+sysRowCount);
		return true;
	}//End of SearchRes_RowCnt_Checkpoint method
	
//*********************************************************************************************************************		
	public  String[] SearchRes_GetActualRowArray(String strColNames,int rowCnt){
		String[] actColList = strColNames.split(";");
		String [] aActualArr = new String[rowCnt];
		
		for(int i=1;i<=rowCnt;i++){
			String sActualValues ="";
			for(int iLoop=0;iLoop< actColList.length;iLoop++){
				String strColName = actColList[iLoop].trim();
				int colIndex = SearchPageResults.searchRes_GetColIndexByColName(strColName);
				String strColValue = SearchPageResults.searchRes_getColValue(i-1, colIndex) ;//fn to get Col Value from UI
				
				if(iLoop==actColList.length){
					sActualValues = (sActualValues.toString() + strColValue.toString()).trim();
				}
				else{
					sActualValues = (sActualValues.toString() + strColValue.toString() + ";").trim();
				}				
			}// End of FOR Loop to loop through ColumnsList
			//Format each row value, add it to the result array-list 
			if(sActualValues.endsWith(";")){
				sActualValues = StringUtils.left(sActualValues, sActualValues.length()-1);	
			}
				aActualArr[i - 1] = sActualValues;			
		}// End of FOR Loop to loop through RowCount in Search Results table
		
		
		return aActualArr;
	}//End of SearchRes_GetActualRowArray method	
	
//*********************************************************************************************************************
	public  void SearchResult_CompareArrays(String[] expArray, String[] actArray, String strColNames,String strViewPage, String strSearchValues){
		boolean blnFound=false;
		String strExpArrayVal="";
		
		for(String expArrayVal : expArray){
			strExpArrayVal = expArrayVal;
			for(int actArryLoop=0;actArryLoop<actArray.length;actArryLoop++){
				if(strExpArrayVal.trim().equals(actArray[actArryLoop].trim())){
					blnFound = true;
					break;
				}//End of IF condition to compare Each Expected value with Actual Array value
			}//For loop for each expectedArray value
			
			if(blnFound){
				System.out.println(strViewPage+": Compare row values for " + strSearchValues +"[Passed]. Expected Row Value = ["+ strExpArrayVal +"] as Actual Row Value.");
			}
			else{
				System.out.println(strViewPage+": Compare row values for " + strSearchValues +"[Failed]. Expected Row Value = ["+ strExpArrayVal +"] NOT as Actual Row Value.Column Names compared are ["+strColNames+"]. Please check screenshot.");
			}
		}//For loop for each expectedArray value
	}//End of SearchResult_CompareArrays method
	
//*********************************************************************************************************************		
	public  void SearchResult_DataValidation(String strViewPage,String To_Execute,String strAcct,String strSecType, String strSecValue,String strRowCount , String strColNames, String[] strExpRows ){
		String[] aActualArr;	
		String strSearchValues = "["+strAcct+";"+strSecType+";"+strSecValue+"]";
		
			if(this.Search_Input(strViewPage , strAcct , strSecType,strSecValue)){
				int expectedRowCount = Integer.parseInt(strRowCount);
				if(this.SearchRes_RowCnt_Checkpoint(expectedRowCount,strViewPage)){
					if(expectedRowCount!=0){
						aActualArr = this.SearchRes_GetActualRowArray(strColNames, expectedRowCount);					
						this.SearchResult_CompareArrays(strExpRows, aActualArr, strColNames, strViewPage, strSearchValues);
					}//Check for RowCount not zero to call comparison method.
				}//End of IF condition to check RowCount checkpoint
			}//End of IF condition to check Search_Input Checkpoint


		
		
		//return false;
	}//End of SearchResult_DataValidation method

//*********************************************************************************************************************

	
	
	

}//End of <Class: DataValidation>
