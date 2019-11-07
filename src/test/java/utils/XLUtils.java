package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtils {
    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow rows;
    public static XSSFCell cell;


    public static int getRowCount(String xlfile, String xlsheet) throws IOException {
        fis=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fis);
        ws=wb.getSheet(xlsheet);
        int getCount = ws.getLastRowNum();
        wb.close();
        fis.close();
        return  getCount;
    }
    public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException{
        fis = new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fis);
        ws=wb.getSheet(xlsheet);
        rows=ws.getRow(rownum);
        int cellCount=rows.getLastCellNum();
        wb.close();
        fis.close();
        return cellCount;
    }

    public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
        fis = new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fis);
        ws=wb.getSheet(xlsheet);
        rows=ws.getRow(rownum);
        cell=rows.getCell(colnum);
        String data;
        DataFormatter formatter = new DataFormatter();
        String cellData=formatter.formatCellValue(cell);
        wb.close();
        fis.close();
        return cellData;
    }

    public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException {
        fis = new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fis);
        ws=wb.getSheet(xlsheet);
        rows=ws.getRow(rownum);
        cell=rows.createCell(colnum);
        cell.setCellValue(data);
        fos=new FileOutputStream(xlfile);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }
}
