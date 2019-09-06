package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class will help you to read data from excel sheet
 */
public class ExcelReader {

	public FileInputStream fis=null; 

	private XSSFWorkbook workbook=null;
	private XSSFSheet worksheet=null;
	private XSSFRow row=null; 
	private  XSSFCell cell=null;
	String testData = EnvironmentManager.getTestDataSheetName();

	/**
	 * Constructor will create a instance for file and for workbook
	 * 
	 */
	public ExcelReader(){

		File file=new File(EnvironmentManager.getDataFromExcelPath());
		try {
			fis=new FileInputStream(file);
			workbook=new  XSSFWorkbook(fis);
			worksheet=workbook.getSheet(testData);
			TestLogger.fileInfo(file.toString());
		}catch (Exception e) {
			TestLogger.errorMessage(e.getMessage());		}
	}

	/**
	 * This method will return the total number of row used in the excel 
	 * 
	 * @return return total number of used rows
	 */
	public int getRowCount(){

		try {
			if(worksheet.getLastRowNum()!=0){
				return (worksheet.getLastRowNum());
			}
		}catch (Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}
		return 0;
	}


	/**
	 * Based on the row number and column name method will return the particular cell value
	 *
	 * @param rowNum:Mention the row number  
	 * @param columnName:Mention the column name  
	 * 
	 * @return Cell value in string
	 */
	public String getCellData(int rowNum, String columnName){
		String cellText=null;
		try{
			int colNum=0;
			if(rowNum<0){
				return "";
			}
			row=worksheet.getRow(0);
			for(int rowCount=0;rowCount<row.getLastCellNum();rowCount++){

				if(row.getCell(rowCount).getStringCellValue().trim().equals(columnName.trim())){
					colNum=rowCount;
				}
			}
			if(colNum== -1){
				return "";
			}
			row = worksheet.getRow(rowNum);
			cell=row.getCell(colNum);
			if(cell==null){
				return "";
			}
			CellType cellType = cell.getCellType();
			if(cellType==CellType.STRING){
				return cell.getStringCellValue();
			}
			else if(cellType==CellType.NUMERIC ||cellType==CellType.FORMULA)
			{
				cellText=String.valueOf(cell.getNumericCellValue());
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					double d=cell.getNumericCellValue();
					Calendar cal=Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText=(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText=cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+1+"/"+cellText;
				}
				return cellText;
			}
			else if (cellType==CellType.BLANK){
				cellText= "";}
			else{
				return String.valueOf(cell.getBooleanCellValue());
			}
		}catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
		TestLogger.testMessage(cellText);
		return cellText;
	}

	/**
	 * Based on the row number it will return the particular row data in a list
	 * 
	 * @param rowNum:Mention the row number 
	 * 
	 * @return row data in in list
	 */
	public List<String> getRowData(int rowNum){

		List<String> rowList=new  ArrayList<>();
		try{
			row=worksheet.getRow(rowNum);
			for(int rowCount=0;rowCount<row.getLastCellNum();rowCount++){
				cell=row.getCell(rowCount);
				String cellData="";
				CellType cellType = cell.getCellType();
				if(cellType==CellType.STRING) {
					cellData=cell.getStringCellValue();
				}
				else if(cellType==CellType.NUMERIC ||cellType==CellType.FORMULA){
					cellData=String.valueOf(cell.getNumericCellValue());
					if(HSSFDateUtil.isCellDateFormatted(cell)){
						double d=cell.getNumericCellValue();
						Calendar cal=Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						cellData=(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellData=cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+1+"/"+cellData;
					}
				}
				else if (cellType==CellType.BLANK){
					cellData="";
				}
				else{
					cellData=String.valueOf(cell.getBooleanCellValue());
				}
				rowList.add(cellData);
			}
		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());

		}
		TestLogger.testMessage(rowList.toString());
		return rowList;
	}

	/**
	 * Based on the row name it will return the particular row data in a list
	 * 
	 * @param rowName:Mention the row name 
	 * 
	 * @return row data in list
	 */
	public List<String> getRowData(String rowName){

		List<String> rowList=new  ArrayList<>();
		try{

			for(int rowCount=0;rowCount<getRowCount();rowCount++){
				row=worksheet.getRow(rowCount);
				cell=row.getCell(0);
				String cellData="";
				CellType cellType = cell.getCellType();
				if(cellType==CellType.STRING) {
					cellData=cell.getStringCellValue();
				}
				else if(cellType==CellType.NUMERIC ||cellType==CellType.FORMULA){
					cellData=String.valueOf(cell.getNumericCellValue());

					if(HSSFDateUtil.isCellDateFormatted(cell)){
						double d=cell.getNumericCellValue();
						Calendar cal=Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						cellData=(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellData=cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+1+"/"+cellData;
					}
				}
				else if (cellType==CellType.BLANK){
					cellData="";
				}
				else{
					cellData=String.valueOf(cell.getBooleanCellValue());
				}
				if(cellData.equals(rowName)) {
					rowList= getRowData(rowCount);
				}
			}

		}
		catch(Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
		return rowList;
	}

	/**
	 * This method will read the particular row data as key value pair form excel sheet
	 *  
	 *  @param: rowName
	 *           
	 * @return row data in map          
	 */	
	public Map<String, List<String>> getData(String rowName){

		Map<String, List<String>> xlRowData = new LinkedHashMap<String, List<String>>(); 
		try {
			List<String> rowData = getRowData(rowName);
			String key=rowData.get(0);
			ArrayList<String> values=new ArrayList<String>();
			for(int rowCount=1;rowCount<rowData.size();rowCount++) {
				values.add(rowData.get(rowCount));
			}
			xlRowData.put(key, values);
		}catch (Exception e){
			TestLogger.errorMessage(e.getMessage());
		}
		TestLogger.testMessage(xlRowData.toString());
		return xlRowData;
	}


	/**
	 * This method will read the range of data form excel sheet 
	 * 
	 * @param RangeName:   No.of columns names
	 * 
	 * @return data in map
	 */
	public Map<String, List<String>> getRangeData(String RangeName) throws IOException {

		int count = 0;
		Row r = null;
		int row = 0;
		int col = 0;
		Name rangeName = null;

		Map<String, List<String>> xlData = new LinkedHashMap<String, List<String>>(); 
		String headVal = null;
		try {
			if (workbook != null) {
				rangeName = workbook.getName(RangeName);

			}
			AreaReference aref = new AreaReference(rangeName.getRefersToFormula(),SpreadsheetVersion.EXCEL2007);
			CellReference[] crefs = (CellReference[]) aref.getAllReferencedCells();

			for (col = aref.getFirstCell().getCol(); col <= aref.getLastCell().getCol(); col++) {
				ArrayList<String> xlList = new ArrayList<String>();
				XSSFSheet s = workbook.getSheet(crefs[col].getSheetName());
				for (row = aref.getFirstCell().getRow(); row <= aref.getLastCell().getRow(); row++) {
					if (s.getRow(row) != null) {
						r = s.getRow(row);

						if (r.getCell(crefs[count].getCol()) != null) {

							if (row == aref.getFirstCell().getRow()) {
								headVal = r.getCell(crefs[count].getCol()).toString();

							} else {
								if (r.getCell(crefs[count].getCol()).getCellType() == CellType.NUMERIC) {
									xlList.add(String.valueOf(r.getCell(crefs[count].getCol())));
								} else
									xlList.add(r.getCell(crefs[count].getCol()).getStringCellValue());
							}
						}

						else {
							xlList.add("");
						}
					}
				}
				xlData.put(headVal, xlList);
				count++;
			}
		} catch (Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}
		TestLogger.testMessage(xlData.toString());
		return xlData;
	}


	/**
	 * This method will read the range of data form excel sheet 
	 * 
	 * @param RangeName: Range name of the selected rows and columns
	 * 
	 * @return data in list
	 *
	 */
	public List<String> getListRangeData(String RangeName) {

		int count = 0;
		Row r = null;
		int row = 0;
		int col = 0;
		Name rangeName = null;
		ArrayList<String> xlList = new ArrayList<String>();
		try {
			if (workbook != null) {

				rangeName = workbook.getName(RangeName);
			}
			AreaReference aref = new AreaReference(rangeName.getRefersToFormula(), SpreadsheetVersion.EXCEL2007);
			CellReference[] crefs = (CellReference[]) aref.getAllReferencedCells();

			for (col = aref.getFirstCell().getCol(); col <= aref.getLastCell().getCol(); col++) {

				XSSFSheet s = workbook.getSheet(crefs[col].getSheetName());
				for (row = aref.getFirstCell().getRow(); row <= aref.getLastCell().getRow(); row++) {
					if (s.getRow(row) != null) {
						r = s.getRow(row);

						if (r.getCell(crefs[count].getCol()) != null)
						{

							if (r.getCell(crefs[count].getCol()).getCellType() == CellType.NUMERIC) {
								xlList.add(String.valueOf(r.getCell(crefs[count].getCol())));
							} else
								xlList.add(r.getCell(crefs[count].getCol()).getStringCellValue());

						}

						else {
							xlList.add("");
						}
					}
				}
				count++;
			}
		} catch (Exception e) {
			TestLogger.errorMessage(e.getMessage());
		}
		TestLogger.testMessage(xlList.toString());
		return xlList;
		
	}


}
