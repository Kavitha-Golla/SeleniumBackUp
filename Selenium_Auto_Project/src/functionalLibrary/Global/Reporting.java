package functionalLibrary.Global;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;


import functionalLibrary.Maxit.ConfigInputFile;
//import functionalLibrary.WM.ExcelPathsConstant_WM;
public class Reporting {
    
    //Reporting.ReportStep("filePath", "Passes/Fail", "Detail", "scrnShotPath");
    //###################################################################################################################################################################  
    //Function name     : reportStep()
    //Class name        : Reporting
    //Description       : 
    //Parameters        :
    //Assumption        : 
    //Developer         : Neelesh Vatsa
    //###################################################################################################################################################################   
    public void reportStep(int rowNum, String stepPassFailVal, String stepDetails, String scrnShotPath, String stepName, ITestContext context, WebDriver driver, String scrollStrategy, String sheetName, WebElement objElement) throws Exception{
        
        //1. Screenshot Step
        //Logic - If "scrnShotPath" parameter is empty then take a screenshot calling the default screenshot method. But if 
        // >> it has some value then use that path. Scenario where we want to add more details to screenshot using shutterbug tool
        // >> should be called before the report method n path should be passed.
        String imgPath = "";
        if(scrnShotPath == "" && stepPassFailVal.equals("Failed")){
            //take screenshot with basic details
            imgPath = CommonUtils.CaptureScreenshot(context, driver, stepName, stepDetails, scrollStrategy, objElement);
        }else if(!(scrnShotPath == "") && stepPassFailVal.equals("Failed")){
            imgPath = scrnShotPath;
        }
    
        //2. Writing to result Excel file step
        // Get the path of result file created in @beforeTest. attribute for the path is set there.
    
        String filePath = (String) context.getAttribute("resultPath");
        System.out.println("function: reportStep >> Result file path in report step = " + filePath);
        //access the file, workbook n get the sheet
        File readResult = new File(filePath);
        FileInputStream fin=new FileInputStream(readResult);
        XSSFWorkbook resultWorkBook = new XSSFWorkbook(fin);
        //
        XSSFSheet resultSheet;
        if(sheetName.equals("")){
            resultSheet= resultWorkBook.getSheetAt(0);
        }else{
            int sheetIndex = resultWorkBook.getSheetIndex(sheetName);
            resultSheet= resultWorkBook.getSheetAt(sheetIndex);
        }
        //get col num for 4 reporting columns that are at the end of result table
        XSSFRow rowCurrent = resultSheet.getRow(rowNum);
        int lastColNum = resultSheet.getRow(0).getPhysicalNumberOfCells();
        //last col no: is for screenshot,then Failed details, Passed detail and last Pas/Fail
        int colPassFail = lastColNum - 4;
        int colPassedDetails = lastColNum - 3;
        int colFailedDetails = lastColNum - 2;
        int colScreenshot = lastColNum - 1;
        //check if details cell is null which means no failed step has been added so far.
        // >> Add space to use it as string while concatenating the first failed result.. 
        Cell cellFailedDetails =resultSheet.getRow(rowNum).getCell(colFailedDetails);
        Cell cellPassedDetails =resultSheet.getRow(rowNum).getCell(colPassedDetails);
        if(cellFailedDetails == null){
               // add space in empty cell 
             resultSheet.getRow(rowNum).createCell(colFailedDetails).setCellValue(" ");
            
             resultSheet.getRow(rowNum).createCell(colScreenshot).setCellValue(" ");
        }
        if(cellPassedDetails == null){
             resultSheet.getRow(rowNum).createCell(colPassedDetails).setCellValue(" ");
        }
                 
         // 1) Set Passed and failed value
         //Logic - if its failed thn set fail in result file else if its passed then set to passed only if the existing screenshot 
         //cell value is null which means some step has failed in past.
        String screenshotCellData = resultSheet.getRow(rowNum).getCell(colScreenshot).getStringCellValue();
        
         if(stepPassFailVal.equalsIgnoreCase("Failed")){
             resultSheet.getRow(rowNum).createCell(colPassFail).setCellValue(stepPassFailVal);
         }else if(stepPassFailVal.equalsIgnoreCase("Passed")){ 
             if(screenshotCellData.equals(" ")){
                 resultSheet.getRow(rowNum).createCell(colPassFail).setCellValue(stepPassFailVal);   
             } 
         }
         //add passed details if step has passed 
         
         if(stepPassFailVal.equals("Passed")){
                String cellPasseddata = resultSheet.getRow(rowNum).getCell(colPassedDetails).getStringCellValue();
                String finalPassData = (cellPasseddata + "\n" + stepDetails).trim();
                resultSheet.getRow(rowNum).createCell(colPassedDetails).setCellValue(finalPassData);
                setResultFileFormat(rowCurrent, rowNum, colPassedDetails);
         }else if(stepPassFailVal.equals("Failed")){
            
            // 2) Set Details cell value
             // ----- Combine content for failure detail and screenshot
            String cellFaileddata = resultSheet.getRow(rowNum).getCell(colFailedDetails).getStringCellValue();
            String finalData = (cellFaileddata + "\n" + stepDetails).trim();
            resultSheet.getRow(rowNum).createCell(colFailedDetails).setCellValue(finalData);
            
            // 3) Set value for screenshot
            String finalImgData = (screenshotCellData + "\n" + imgPath).trim();
            resultSheet.getRow(rowNum).createCell(colScreenshot).setCellValue(finalImgData);
            
            // 4) Add hyperlinked text for img path
            String screenshotCellDataNew = (resultSheet.getRow(rowNum).getCell(colScreenshot).getStringCellValue()).trim();
            String[] cellImgArray = screenshotCellDataNew.split("[\\r\\n]+");
            int arrSize = cellImgArray.length;
            if(arrSize>=1){
                XSSFCell cell;
                cell = resultSheet.getRow(rowNum).createCell((short) (colScreenshot+arrSize));
                cell.setCellValue("Check screenshot" + (arrSize));
                setResultFileFormat(rowCurrent, rowNum, colScreenshot+arrSize);
                String imgPathFromArray = cellImgArray[arrSize-1];
                //Function call to set hyperlinks for screenshots
                setHyperlinkScreenShot(cell, imgPathFromArray); 
            }
             //Formatting Results related cell
             setResultFileFormat(rowCurrent, rowNum, colFailedDetails);
             setResultFileFormat(rowCurrent, rowNum, colScreenshot);
         }
         //save file
        FileOutputStream outFile1 =new FileOutputStream(new File(filePath));
        resultWorkBook.write(outFile1); 
        outFile1.flush();
        outFile1.close();
        resultWorkBook.close();
        
        }
        
        //###################################################################################################################################################################  
        //Function name     : reportCreateResultFile()
        //Class name        : Report file creation
        //Description       : 
        //Parameters        :
        //Assumption        : 
        //Developer         : Neelesh Vatsa
        //###################################################################################################################################################################   
            
        public String reportCreateResultFile(String strScriptname, String strEnvi, String strClient, String productName) throws IOException {
        	String xlTestDataPath;
            //check if u can save this file in new location
        	if(productName.equalsIgnoreCase("Maxit")){
            xlTestDataPath = ConfigInputFile.getInputFIlePath(strScriptname, strEnvi, strClient);
        	}
        	else{
        		//Change this once WM "ExcelPathsConstant_WM" package is committed to SVN
        		xlTestDataPath = ""; //ExcelPathsConstant_WM.getExcelDatasheetPath(strScriptname, strEnvi, strClient);
        	}
            System.out.println("Method - reportCreateResultFile >> path of excel file read n copied to result version excel - " + xlTestDataPath);
            //Use this path to fetch data sheet and copy it to Result folder location
             // Specify the file path which you want to create or write
              File src=new File(xlTestDataPath);
              // Load the file
              FileInputStream fis=new FileInputStream(src);
               // load the workbook
            XSSFWorkbook wb=new XSSFWorkbook(fis);
            Format formatter = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
            Date date = new Date();
            String fileName = strScriptname + "Results_" + (formatter.format(date)) +".xlsx";
            System.out.println(fileName);
            String resultFilePath = ("C:/SE_Reports/" + productName + "/" + strEnvi +"/" + strClient +  "/" + strScriptname + "/" + fileName);
            FileOutputStream outFile =new FileOutputStream(new File(resultFilePath));
            wb.write(outFile);  
            outFile.flush();
            outFile.close();
            wb.close();
            
            
            //Open newly created result excel file and format it.
             File srcIn=new File(resultFilePath);
             FileInputStream fisIn=new FileInputStream(srcIn);
             XSSFWorkbook outWB = new XSSFWorkbook(fisIn);
             
              // get the sheet which you want to modify or create
                int sheetCount = outWB.getNumberOfSheets();
                //Add result col in all sheets
                for(int i = 0; i<=sheetCount-1; i++){
                    XSSFSheet sh1= outWB.getSheetAt(i);
                    //Create a new font and alter it.
                      XSSFFont font = outWB.createFont();
                      font.setFontHeightInPoints((short) 10);
                      font.setBold(true);
                      //font.setFontName("IMPACT");
                      font.setColor(HSSFColor.BLACK.index);
                      //Set font into style
                      XSSFCellStyle style = outWB.createCellStyle();
                      style.setFont(font);
                      
                    // here createCell and setCellvalue will set the value
                     int noOfColumns = sh1.getRow(0).getPhysicalNumberOfCells();
                     XSSFCell cellResult = sh1.getRow(0).createCell(noOfColumns);
                     XSSFCell cellPassedDetail = sh1.getRow(0).createCell(noOfColumns + 1);
                     XSSFCell cellFailedDetail = sh1.getRow(0).createCell(noOfColumns + 2);
                     XSSFCell cellScreenshot = sh1.getRow(0).createCell(noOfColumns + 3);
                     sh1.autoSizeColumn(noOfColumns + 2);
                     cellResult.setCellValue("Results");
                     cellResult.setCellStyle(style);
                     
                     cellPassedDetail.setCellValue("Passed Details");
                     cellPassedDetail.setCellStyle(style);
                     
                     cellFailedDetail.setCellValue("Failure Details");
                     cellFailedDetail.setCellStyle(style);
                     
                     cellScreenshot.setCellValue("ScreenshotPath");
                     cellScreenshot.setCellStyle(style);
                }
                 
             
             FileOutputStream fout=new FileOutputStream(resultFilePath);
             outWB.write(fout);
            fout.close();
            outWB.close();
            return resultFilePath;
            
        }
        //###################################################################################################################################################################  
        //Function name     : setHyperlinkScreenShot()
        //Class name        : hyperlin for screenshot imgs
        //Description       : 
        //Parameters        :
        //Assumption        : 
        //Developer         : Neelesh Vatsa
        //###################################################################################################################################################################   
                    
        public void setHyperlinkScreenShot(XSSFCell cell, String FileAddress){
            XSSFWorkbook resultWorkBook=cell.getRow().getSheet().getWorkbook();
            //XSSFSheet resultSheet = cell.getRow().getSheet();
            CreationHelper createHelper = resultWorkBook.getCreationHelper();
            CellStyle hlink_style = resultWorkBook.createCellStyle();
            Font hlink_font = resultWorkBook.createFont();
            hlink_font.setUnderline(Font.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);
            Hyperlink hp = createHelper.createHyperlink(Hyperlink.LINK_FILE);
            //imgPath=imgPath.replace("\\", "/");
            hp.setAddress(FileAddress);
            cell.setHyperlink(hp);
            cell.setCellStyle(hlink_style);
            
        }
        //###################################################################################################################################################################  
        //Function name     : setHyperlinkScreenShot()
        //Class name        : hyperlin for screenshot imgs
        //Description       : 
        //Parameters        :
        //Assumption        : 
        //Developer         : Neelesh Vatsa
        //###################################################################################################################################################################   
                    
        public void setResultFileFormat(XSSFRow strCurrentRow, int rowNo, int colNum){
            //Formatting cell
            XSSFWorkbook resultWorkBook=strCurrentRow.getSheet().getWorkbook();
            XSSFSheet resultSheet = strCurrentRow.getSheet();
            CellStyle cs = resultWorkBook.createCellStyle();
            cs.setWrapText(true);
            resultSheet.getRow(rowNo).getCell(colNum).setCellStyle(cs);
            //resultSheet.getRow(rowNo).getCell(detailsColNum).setCellStyle(cs);
            //adjust column width to fit the content
            resultSheet.autoSizeColumn(colNum);
            //resultSheet.autoSizeColumn(detailsColNum);
            //auto adjust the hight of cell to accommodate multi line details
            strCurrentRow.setHeight((short)-1);
            
        }       
        
            
        
        
    
}