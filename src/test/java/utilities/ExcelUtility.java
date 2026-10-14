package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    public FileInputStream filein;
    public FileOutputStream fileout;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    String path;
    public CellStyle style;

    public ExcelUtility(String path){
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException {
        filein=new FileInputStream(path);
        workbook=new XSSFWorkbook(filein);
        sheet=workbook.getSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        workbook.close();
        filein.close();
        return rowCount;
    }

    public int getCellCount(String sheetName,int rowNum) throws IOException {
        filein=new FileInputStream(path);
        workbook=new XSSFWorkbook(filein);
        sheet=workbook.getSheet(sheetName);
        int cellCount=sheet.getRow(rowNum).getLastCellNum();
        workbook.close();
        filein.close();
        return cellCount;

    }
    public String getCellData(String sheetName,int rowNum, int cellNum) throws IOException {
        filein=new FileInputStream(path);
        workbook=new XSSFWorkbook(filein);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.getCell(cellNum);

        String data;
        DataFormatter formater=new DataFormatter();
        try{

            data=formater.formatCellValue(cell);
        } catch (Exception e) {
            data="";
        }

        workbook.close();
        filein.close();
        return data;
    }
    public void setCellData(String sheetName,int rowNum,int colNum, String data) throws IOException {

        File xlfile=new File(path);

        if(!xlfile.exists()){ //If file not exist then create new file
            fileout=new FileOutputStream(path);
            workbook=new XSSFWorkbook();
            workbook.write(fileout);
        }
        filein=new FileInputStream(path);
        workbook=new XSSFWorkbook(filein);

        if(workbook.getSheetIndex(sheetName)==-1){ //If sheet not exists then create new Sheet
            workbook.createSheet(sheetName);
        }
        sheet=workbook.getSheet(sheetName);

        if(sheet.getRow(rowNum)==null){
            sheet.createRow(rowNum);
        }
        row=sheet.getRow(rowNum);

        cell=row.createCell(colNum);
        cell.setCellValue(data);

        fileout=new FileOutputStream(path);
        workbook.write(fileout);
        workbook.close();
        filein.close();
        fileout.close();

    }

    public void fillGreenColor(String sheetName,int rowNum,int colNum) throws IOException {
        filein=new FileInputStream(path);
        workbook=new XSSFWorkbook(filein);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.getCell(colNum);

        style=workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fileout=new FileOutputStream(path);
        workbook.write(fileout);

        workbook.close();
        filein.close();
        fileout.close();
    }

    public void fillRedColor(String sheetName,int rowNum,int colNum) throws IOException {
        filein=new FileInputStream(path);
        workbook=new XSSFWorkbook(filein);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.getCell(colNum);

        style=workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fileout=new FileOutputStream(path);
        workbook.write(fileout);

        workbook.close();
        filein.close();
        fileout.close();
    }

}
