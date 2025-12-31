package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static Map<String, String> getDataByCT(String ct) {

        String path = PropertiesReader.getRequired("data.excel.path");
        String sheetName = PropertiesReader.getRequired("data.excel.sheet");

        Map<String, String> data = new HashMap<>();

        try (InputStream is = ExcelReader.class
                .getClassLoader()
                .getResourceAsStream(path)) {

            if (is == null) {
                throw new RuntimeException("Excel não encontrado: " + path);
            }

            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Aba não encontrada: " + sheetName);
            }

            Row header = sheet.getRow(0);
            if (header == null) {
                throw new RuntimeException("Header do Excel não encontrado");
            }

            boolean found = false;
            DataFormatter formatter = new DataFormatter();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Cell ctCell = row.getCell(0);
                String ctValue = formatter.formatCellValue(ctCell);

                if (ctValue.equalsIgnoreCase(ct)) {
                    found = true;

                    for (int i = 1; i < header.getLastCellNum(); i++) {
                        String key = formatter.formatCellValue(header.getCell(i));
                        String value = formatter.formatCellValue(row.getCell(i));

                        data.put(key, value);
                    }
                    break;
                }
            }

            workbook.close();

            if (!found) {
                throw new RuntimeException("CT não encontrado no Excel: " + ct);
            }

            return data;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler Excel", e);
        }
    }
}