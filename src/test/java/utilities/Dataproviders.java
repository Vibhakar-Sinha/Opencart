package utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Dataproviders {

    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {
        String path=".\\testData\\LoginTestData.xlsx";

        ExcelUtility xlUtil=new ExcelUtility(path);

        int totalRows=xlUtil.getRowCount("Sheet1");
        int totalCols= xlUtil.getCellCount("Sheet1",1);

        String [][]loginData=new String[totalRows][totalCols];

        for(int i=1;i<=totalRows;i++){
            for(int j=0;j<totalCols;j++){
                loginData[i-1][j]=xlUtil.getCellData("Sheet1",i,j);
                //System.out.println(loginData[i-1][j]);
            }
        }
        return loginData;
    }
}
