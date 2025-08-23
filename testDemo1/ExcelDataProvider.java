package Excel;



import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "getdata")
    public Object[][] getdata() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\deept\\Downloads\\DataProvider.1 (1) (1).xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("Sheet1");

        int rowCount = sheet.getPhysicalNumberOfRows(); // all rows
        int totalRows = Math.min(2, rowCount - 1); // pick only 2 rows after header

        Object[][] data = new Object[totalRows][2];

        for (int i = 0; i < totalRows; i++) {
            data[i][0] = sheet.getRow(i + 1).getCell(0).getStringCellValue(); // username
            data[i][1] = sheet.getRow(i + 1).getCell(1).getStringCellValue(); // password
        }

        wb.close();
        fis.close();
        return data;
    }
}

  