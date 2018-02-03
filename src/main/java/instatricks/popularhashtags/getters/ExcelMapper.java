package instatricks.popularhashtags.getters;


import instatricks.popularhashtags.MostPopularHashTagsGetter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelMapper {

    public void mapToExcelFile(List<String> tags, String filePath){
        Workbook workbook =  new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        putTagsToCells(tags, sheet);

        try(FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath))){
            workbook.write(fileOutputStream);
            workbook.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    private void putTagsToCells(List<String> tags, Sheet sheet) {
        for (int i = 0; i < tags.size(); i++) {
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            String tag = tags.get(i);
            String[] splitedTag = tag.split(" ");
            cell.setCellValue(splitedTag[0]);
            String rating = splitedTag[1];
            rating = rating.replace("(", "");
            rating = rating.replace(")", "");
            rating = rating.replace(",", "");
            row.createCell(1).setCellValue(rating);
        }
    }


}
