package functionalLibrary.Maxit;

import org.apache.log4j.Logger;

import functionalLibrary.Global.CommonUtils;
import functionalLibrary.Global.DataTable;


public class ConfigInputFile {
	static Logger log = Logger.getLogger(ConfigInputFile.class);
	static String excelRelativePath = "C:\\SVN_Automation_New\\Trunk\\Maxit\\Admin\\QTP_BetaVersion\\00_Data Table\\";
	static String excelAbsPath;
	DataTable dt = new DataTable();
	
//###################################################################################################################################################################  
//Function name		: getInputFIlePath(String scriptName, String strEnvi, String strClient)
//Class name		: configInputFile
//Description 		: Generic function to configure input file dynamically during runtime based on scriptName
//Parameters 		: ScriptName: Name of the script
//					: Environment( QA/PROD)
//					: Client name
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################		
	public static String getInputFIlePath(String scriptName, String strEnvi, String strClient){
		try{
		if(scriptName.trim().isEmpty()||strEnvi.trim().isEmpty()||strClient.trim().isEmpty()){
			log.info("<Class:configInputFile><Method:getInputFIlePath>	One/all of Input arguements are empty.Arugements(ScriptName:"+scriptName+";Environment:"+strEnvi+";ClientName:"+strClient+". Please Check.!");
			return null;
		}//End of IF to check if the parameters are empty
		

			switch(strEnvi.trim().toUpperCase()){
			case "QA":
				excelAbsPath = excelRelativePath+"QA_Site\\"+strClient.trim()+"\\"+strClient.trim().toLowerCase()+"_"+scriptName.trim()+".xls";				
				if(CommonUtils.fileExists(excelAbsPath)){
					log.info("Setting Input data Config File, Path - "+excelAbsPath);
					return excelAbsPath;
				}//Checking for .xls file
				
				excelAbsPath = excelRelativePath+"QA_Site\\"+strClient.trim()+"\\"+strClient.trim().toLowerCase()+"_"+scriptName.trim()+".xlsx";
				if(CommonUtils.fileExists(excelAbsPath)){
					log.info("Setting Input data Config File, Path - "+excelAbsPath);
					return excelAbsPath;
				}//Checking for .xlsx file				
				return null;			
			case "PROD":
				excelAbsPath = excelRelativePath+"Production_Site\\"+strClient.trim()+"\\"+strClient.trim().toLowerCase()+"_"+scriptName.trim();
				if(CommonUtils.fileExists(excelAbsPath+".xls") ||CommonUtils.fileExists(excelAbsPath+".xlsx") ){
					log.info("Setting Input data Config File, Path - "+excelAbsPath);
					return excelAbsPath;
				}//Checking for .xls file
				
				excelAbsPath = excelRelativePath+"Production_Site\\"+strClient.trim()+"\\"+strClient.trim().toLowerCase()+"_"+scriptName.trim();
				if(CommonUtils.fileExists(excelAbsPath+".xls") ||CommonUtils.fileExists(excelAbsPath+".xlsx") ){
					log.info("Setting Input data Config File, Path - "+excelAbsPath);
					return excelAbsPath;
				}//Checking for .xlsx file				
				return null;
			default:
				log.info("<Class:configInputFile><Method:getInputFIlePath>	Error: strEnv argument passed is not QA/PROD, Please check..!");			
				return null;
			}//End of Switch case
		}//End of try block
		catch(Exception e){
			log.info("Exception in <Class:configInputFile><Method:getInputFIlePath> Exception Message" + e.getMessage() );
			return null;
		}

		
	}//End of getInputFIlePath method

//###################################################################################################################################################################  
//Function name		: getData_DataValidation(String strExcelPath,String strSheetName, String strViewPage)
//Class name		: configInputFile
//Description 		: Generic function to configure input file dynamically during runtime based on scriptName
//Parameters 		: strExcelPath: Input excel file path
//					: strSheetName: Name of the sheet
//					: strViewPage : Search View page name
//Assumption		: None
//Developer			: Kavitha Golla
//###################################################################################################################################################################			
	public Object[][] getData_DataValidation(String strExcelPath,String strSheetName, String strViewPage) throws Exception{
		Object[][] dpArray = null;
		dt.setExcelFile(strExcelPath);
        if(dt.setSheet(strSheetName)){			  		 
        	int intDTRows = dt.getRowCount();
        	dpArray = new Object[intDTRows][8];
        	
        	for(int intDTRowIndex = 1 ; intDTRowIndex<=intDTRows;intDTRowIndex++){	  

        		  String To_Execute = dt.getCellData(intDTRowIndex, "To_Execute").toString() ;
        		  if(To_Execute=="" || To_Execute.isEmpty()){
        			  return(dpArray);
        		  }
				  String strAcct = dt.getCellData(intDTRowIndex, "Account_No").toString() ;
				  String strSecType = dt.getCellData(intDTRowIndex, "Sec_Type").toString() ;
				  String strSecValue = dt.getCellData(intDTRowIndex, "Sec_Value").toString() ;
				  String strRowCount = dt.getCellData(intDTRowIndex, "Row_Count").toString() ;
				  String strColNames = dt.getCellData(intDTRowIndex, "Column_Names").toString() ;
				  dpArray[intDTRowIndex-1][0]=strViewPage;
				  dpArray[intDTRowIndex-1][1]=To_Execute;
				  dpArray[intDTRowIndex-1][2]=strAcct;
				  dpArray[intDTRowIndex-1][3]=strSecType;
				  dpArray[intDTRowIndex-1][4]=strSecValue;
				  dpArray[intDTRowIndex-1][5]=strRowCount;
				  dpArray[intDTRowIndex-1][6]=strColNames;
				  
				  //Reading Expected rows
				  int rowCnt = Integer.parseInt(strRowCount);
				  String[] expRows = new String[rowCnt];
				  if(rowCnt>0){
					  for(int readExpRow = 1;readExpRow<=rowCnt;readExpRow++){
						  expRows[readExpRow-1]= (dt.getCellData(intDTRowIndex, "Row"+readExpRow).toString());						  
					  }//End of for loop to read Expected rows from Input sheet
					  dpArray[intDTRowIndex-1][7]=expRows;
				  }//End of IF condition to check ExpectedRow count in data sheet>0
				  
				  else {
					  dpArray[intDTRowIndex-1][7] = null;
				  }//End of ELSE condition to check ExpectedRow count in data sheet>0				  				  

			  }//End of for loop to iterate thru different rows	
	  }//End of IF condition to check  DataTable.setSheet

		return(dpArray);
	}//End of getData_DataValidation Method
//###################################################################################################################################################################	
}//							End of <configInputFile> class
//###################################################################################################################################################################
