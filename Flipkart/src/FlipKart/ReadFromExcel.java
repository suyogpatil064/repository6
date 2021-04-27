package FlipKart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel extends Base{
	public static String[][] readExcel(String filePath, String fileName, String sheetName) throws IOException {

		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook dataWorkbook = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			dataWorkbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of HSSFWorkbook class

			dataWorkbook = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name

		Sheet dataSheet = dataWorkbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = dataSheet.getLastRowNum() - dataSheet.getFirstRowNum();
		//System.out.println("rowCount= " + rowCount);
		// Create a loop over all the rows of excel file to read it
		
		Row row1 = dataSheet.getRow(0);
		dataArray =new String[rowCount][row1.getLastCellNum()];
		
		for (int i = 1; i < rowCount+1; i++) {

			Row row = dataSheet.getRow(i);
			//System.out.println("i= "+ i);
			// Create a loop to print cell values in a row
			
			for (int j = 0; j < row.getLastCellNum(); j++) {
				//System.out.println("j= "+ j);
				
				//System.out.println("columns= " + row.getLastCellNum());
				// Print Excel data in console
				try {
					dataArray[i-1][j]= row.getCell(j).getStringCellValue();
					//System.out.println("value= " + dataArray[i-1][j]);
				} catch (IllegalStateException e) {
					double d = row.getCell(j).getNumericCellValue();
					int value = (int) d;
					String s = String.valueOf(value);
					dataArray[i-1][j] = s;
					//System.out.println(dataArray[i-1][j]);
				}
			}
		}
		return dataArray;

	}
}
