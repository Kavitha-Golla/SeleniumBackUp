package functionalLibrary.Global;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class DataTable_Backup {
	
	private static Workbook excelWBook;
	private static Sheet excelWSheet;	 
	private static Cell cellObj;
	private static Row rowObj;
	private static HashMap<String,Integer> colList;
	public static DataFormatter dataformat = new DataFormatter();
	
	private static HashMap<Long, Workbook> threadExcelMap = null;
	
//#####################################################################################################################################################
//Function name		: setExcelFile(String Path,String SheetName)
//Class name		: Datatable
//Description 		: This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
//Parameters 		: Path - Excel workbook path
//					: SheetName - Sheet name	
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: Datatable.setExcelFile("H:\\Kavitha\\Workspace\\Selenium_WM_Auto\\TestData\\DataSheets\\WM_Registration_Pos_Case.xlsx", "Global");
//#####################################################################################################################################################
public static void setExcelFile(String Path,String SheetName) throws Exception {
		try {
//			long id = Thread.currentThread().getId();
//			if (threadExcelMap != null){
//				excelWBook = threadExcelMap.get(id);
//			System.out.println("for thread id:"+id+" the excel workbook is:");
//			}
//			else
//				threadExcelMap = new HashMap<Long, Workbook>();	
		setExcelFile(Path);
		setSheet(SheetName);
		//threadExcelMap.put(id, excelWBook);
		} catch (Exception e){
			excelWBook = null;
			excelWSheet = null;
			throw (e);
		}

} //End of "setExcelFile" method[Method overloading]

//#####################################################################################################################################################
//Function name		: setExcelFile(String Path)
//Class name		: Datatable
//Description 		: This method is to set the File path and to open the Excel file, Pass Excel Path as Arguments to this method
//Parameters 		: Path - Excel wokbook path
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: Datatable.setExcelFile("H:\\Kavitha\\Workspace\\Selenium_WM_Auto\\TestData\\DataSheets\\WM_Registration_Pos_Case.xlsx");

//	NOTE: Difference between above & this method is arguments, method definition is different.
//#####################################################################################################################################################
public static void setExcelFile(String Path) throws Exception {
		try {
				// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
		    //Find the file extension by splitting file name in substring  and getting only extension name
		    String fileExtensionName = Path.substring(Path.indexOf("."));
	
		    //Check condition if the file is xlsx file
		    if(fileExtensionName.equals(".xlsx")){
		    	excelWBook = new XSSFWorkbook(ExcelFile);
		    }
	
		    //Check condition if the file is xls file
		    else if(fileExtensionName.equals(".xls")){
		    	excelWBook = new HSSFWorkbook(ExcelFile);
		    }

		}//End of Try block
		catch (Exception e){
			excelWBook = null;
			throw (e);
		}

} //End of "setExcelFile" method[Method overloading]

//################################################################################################################################################################### 
//Function name		: setSheet(String SheetName)
//Class name		: Datatable
//Description 		: This method to set Sheet from the workbook
//Parameters 		: SheetName - Sheet name
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: Datatable.setSheet("Global");
//################################################################################################################################################################### 
public static boolean setSheet(String SheetName) {
	excelWSheet = null;
	try {
		int noOfSheets = excelWBook.getNumberOfSheets();
		for(int i=0;i<noOfSheets ; i++){
			if(excelWBook.getSheetName(i).equalsIgnoreCase(SheetName)){
				excelWSheet = excelWBook.getSheet(SheetName);
				setColumnList(excelWSheet);
				return true;		
			}			
		}
			System.out.println("<Class:DataTable><Method: setSheet>: Given Sheet Name:"+SheetName+" does not exists, please check");
			excelWSheet = null;
			return false;
	}//End of try block
	catch (Exception e){
		System.out.println("<Class:DataTable><Method: setSheet>: Given Sheet Name:"+SheetName+" does not exists, please check");
		excelWSheet = null;
		e.printStackTrace();
		return false;
	}//End of Catch block

} //End of "setSheet" method

//###################################################################################################################################################################  
//Function name		: setCellData(String Result,  int RowNum, int ColNum)
//Class name		: Datatable
//Description 		: This method is to write in the Excel cell, Row num and Col num are the parameters
//Parameters 		: Result - Value to be written to Excelsheet
//					  RowNum - Row Number
//					  ColNum - column Number
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: Datatable.setCellData("Pass",2,10);
//################################################################################################################################################################### 
@SuppressWarnings({"deprecation", "static-access" })
public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

		try{
			rowObj  = excelWSheet.getRow(RowNum);
			cellObj = rowObj.getCell(ColNum, rowObj.RETURN_BLANK_AS_NULL);
			if (cellObj == null) {	
				cellObj = rowObj.createCell(ColNum);	
				cellObj.setCellValue(Result);	
				} 
			else {
					cellObj.setCellValue(Result);
				}
				// Constant variables Test Data path and Test Data file name
				FileOutputStream fileOut = new FileOutputStream("C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_Auto_Project\\TestData\\DataSheets\\Output_Results\\" + "TestResults.xlsx");				
				excelWBook.write(fileOut);
				//fileOut.flush();
				fileOut.close();
			}catch(Exception e){
				throw (e);
			}

}// End of setCellData method[Method overloading]
//###################################################################################################################################################################  
//Function name		: setCellData(String Result,  int RowNum, String ColName)
//Class name		: Datatable
//Description 		: This method is to write in the Excel cell, Row num and Col Name are the parameters
//Parameters 		: Result - Value to be written to Excelsheet
//					  RowNum - Row Number
//					  ColName - column Name				  
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: Datatable.setCellData("Pass",2,10);
//################################################################################################################################################################### 
@SuppressWarnings({ "static-access", "deprecation" })
public static void setCellData(String strResult,  int RowNum, String strColName) throws Exception	{

		try{
			int colIndex = getColumnIndex(strColName);
			if(colIndex==-1){
				System.out.println("<METHOD: setCellData><CLASS: DataTable> - There is no column in DataSheet with columnName : ["+ strColName +"]");
			}//End of IF condition to check colIndex
			rowObj  = excelWSheet.getRow(RowNum);
			cellObj = rowObj.getCell(colIndex, rowObj.RETURN_BLANK_AS_NULL);
				
			if (cellObj == null) {	
				cellObj = rowObj.createCell(colIndex);	
				cellObj.setCellValue(strResult);	
				} 
			else {
					cellObj.setCellValue(strResult);
				}
				// Constant variables Test Data path and Test Data file name
				FileOutputStream fileOut = new FileOutputStream("C:\\SVN_Automation_New\\Trunk\\Selenium_Automation\\Selenium_WM_Auto\\TestData\\DataSheets\\Output_Results\\" + "TestResults.xlsx");
				//FileOutputStream fileOut = new FileOutputStream(util.getGlobalProperty("dtRegistration_PosCase_Output") + "TestResults.xlsx");
				excelWBook.write(fileOut);
				//fileOut.flush();
				fileOut.close();
			}catch(Exception e){

				throw (e);
			}

}// End of setCellData method[Method overloading]

//################################################################################################################################################################### 
//Function name		: getRowCount() 
//Class name		: Datatable
//Description 		: This function returns the total number of used rows in datasheet 
//Parameters 		: N/A
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: Datatable.getRowCount();
//################################################################################################################################################################### 
public static int getRowCount(){
	try {
	return excelWSheet.getLastRowNum();
	}
	
	catch(Exception e){
		System.out.println("There is an exception in <Class: Datatable><Method: getRowCount>, please check..!!");		
		e.printStackTrace();
		return 0;
		} // End of Catch block
	
	} //End of "getRowCount" method


//###################################################################################################################################################################  
//Function name		: getCellData(int RowNum, int ColNum)
//Class name		: Datatable
//Description 		: This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num (starts with 0)
//Parameters 		: RowNum - Row Number
//					  ColNum - column Number
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: Datatable.getCellData(2,10);
//################################################################################################################################################################### 
public static String getCellData(int RowNum, int ColNum) throws Exception{
		try{
			cellObj = excelWSheet.getRow(RowNum).getCell(ColNum);
			//String CellData = cellObj.getStringCellValue();
			String CellData = dataformat.formatCellValue(cellObj);
			return CellData;
			}
		catch (Exception e){
			return"";
			}
}//End of "getCellData" method[Method overloading]

//###################################################################################################################################################################  
//Function name		: getCellData(int intCellRow,String strColName) 
//Class name		: Datatable
//Description 		: This function returns the cell value by column_name from datasheet for given row/col_Name 
//Parameters 		: intCellRow : is the row number
//					  strColName : Column Name
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: Datatable.getCellData(1,"To_Execute") 
//################################################################################################################################################################### 
public static String getCellData(int intCellRow,String strColName) throws Exception{
	//System.out.println("Inside getCellData method");
	int colIndex = getColumnIndex(strColName);
	if(colIndex==-1){
		System.out.println("<METHOD: getCellData><CLASS: DataTable> - There is no column in DataSheet with columnName : ["+ strColName +"]");
		return "Column not found";
	}//End of IF condition to check colIndex
	
	cellObj = excelWSheet.getRow(intCellRow).getCell(colIndex);
	try{
		//String ColumnValue = cellObj.getStringCellValue().toString();
		String ColumnValue = dataformat.formatCellValue(cellObj);
		return ColumnValue;		
	}catch (NullPointerException e) {
		return "";
	}
		
}//End of getCellData method[Method overloading]

//###################################################################################################################################################################  
//Function name		: getCellData(int intCellRow,String strColName,String strSheetName) 
//Class name		: Datatable
//Description 		: This function returns the cell value by column_name from datasheet for given row/col_Name 
//Parameters 		: intCellRow : is the row number
//					  strColName : Column Name
//					  strSheetName : Sheet Name
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: Datatable.getCellData(1,"To_Execute","Global") 
//################################################################################################################################################################### 
public static String getCellData(int intCellRow,String strColName,String strSheetName) throws Exception{
	excelWSheet = excelWBook.getSheet(strSheetName);
	//System.out.println("Inside value method");
	int colIndex = getColumnIndex(strColName);
	if(colIndex==-1){
		System.out.println("<METHOD: getCellData><CLASS: DataTable> - There is no column in DataSheet with columnName : ["+ strColName +"]");
		return "Column not found";
	}//End of IF condition to check colIndex
	
	cellObj = excelWSheet.getRow(intCellRow).getCell(colIndex);
	try{
		//String ColumnValue = cellObj.getStringCellValue();
		String ColumnValue = dataformat.formatCellValue(cellObj);
		return ColumnValue;		
	}catch (NullPointerException e) {
		return "";
	}
	
}//End of "getCellData" method[Method overloading]

//###################################################################################################################################################################  
//Function name		: getColumnIndex(String strColName)
//Class name		: Datatable
//Description 		: This function returns the column index of the given column_name
//Parameters 		: strColName : Column Name
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: getColumnIndex("To_Execute")
//################################################################################################################################################################### 
public static Integer getColumnIndex(String strColName) throws Exception{
	try{
		int ret = colList.get(strColName);		
		return ret;
	}
	catch(Exception e){
		return -1;
	}
	
}//End of "getColumnIndex" method

//###################################################################################################################################################################  
//Function name		: setColumnList(XSSFSheet sheetObj)
//Class name		: Datatable
//Description 		: This function initializes ColList map (ColName;ColIndex) pair
//Parameters 		: N/A
//Assumption		: None
//Developer			: Kavitha Golla
//Example			: setColumnList(sheetObj)
//################################################################################################################################################################### 
private static void setColumnList(Sheet sheetObj) throws Exception{

	if(sheetObj!=null){
		colList=null;
		colList=new HashMap<String,Integer>();
		Row r = sheetObj.getRow(0);			
		for (int cn=0; cn < r.getLastCellNum(); cn++) {
			colList.put(r.getCell(cn).getStringCellValue(), cn);		
		}//End of for loop
	
	}//End of If condition to check"sheetObj==null"
}//End of "setColumnList" method




//############################ ***** End of Methods for class "Datatable" ******###############################################
}//End of Class	
//############################ ********************** END **********************###############################################



