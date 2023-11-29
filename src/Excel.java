import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

class Excel {
      String projectsFile;
      String stagesFile;
      String stagesDetailedFile;

    Excel(String projectsFile, String stagesFile, String stagesDetaildFile) {

        this.projectsFile = projectsFile;
        this.stagesFile = stagesFile;
        this.stagesDetailedFile = stagesDetaildFile;
    }

    private   ArrayList readFile(String filename) throws IOException {

        try {
            FileInputStream fis = new FileInputStream(new File(filename));
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            HSSFSheet sheet = wb.getSheetAt(0);

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();

            ArrayList rowsList = new ArrayList();
            ArrayList colsList = new ArrayList();

            for (int r = 1; r <= rows; r++) {

                colsList = new ArrayList();
                HSSFRow row = sheet.getRow(r);

                for (int c = 0; c < cols; c++) {
                    HSSFCell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                    if (cell == null) {
                        colsList.add(null);

                    } else if (cell.getCellType() == CellType.STRING) {
                        if (IsDate(cell.getStringCellValue())) {
                            colsList.add(CreateDate(cell.getStringCellValue()));

                        } else {
                            colsList.add(cell.getStringCellValue());
                        }
                    } else if (DateUtil.isCellDateFormatted(cell)) {
                        colsList.add(new Date(DateUtil.getJavaDate(cell.getNumericCellValue()).getTime()));

                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        colsList.add(cell.getNumericCellValue());

                    } else {
                        throw new Exception("invalid cell type: " + cell.getCellType());
                    }
                }
                rowsList.add(colsList);

            }
            return rowsList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private   Boolean IsDate(String date) {

        if (date.contains(":"))
            return true;
        return false;
    }

    private   Date CreateDate(String date) throws Exception {

        if (date.contains(":")) {
            return new Date(Integer.parseInt(date.substring(6, 10))-1900,
                    Integer.parseInt(date.substring(3, 5))-1,
                    Integer.parseInt(date.substring(0, 2)));
        } else {
            throw new Exception("invalid date format" + date);
        }
    }

    public   ArrayList getProjects() throws IOException {

        ArrayList<ArrayList> array = readFile(projectsFile);

        for (int i = 0; i < array.size(); i++) {
            // // array.get(i).remove(3); //remove startdate
            // // array.get(i).remove(3); //remove enddate
            array.get(i).remove(5); //remove Customer
            array.get(i).remove(5); //remove Currency
        }
        return array;
    }

    public   ArrayList getStagesMerged() throws IOException {
        ArrayList<ArrayList> s = readFile(stagesFile);
        ArrayList<ArrayList> sd = readFile(stagesDetailedFile);

        for (int i = 0; i < s.size(); i++) {
            sd.get(i).remove(3); //remove time col
            sd.get(i).remove(3); //remove time language key
            sd.get(i).add(s.get(i).get(5));//add New value
            if (s.get(i).get(6) == null || ((Double) s.get(i).get(6) < (Double) s.get(i).get(5))) {
                sd.get(i).add(Boolean.TRUE); // True  this means normal change
            } else {
                sd.get(i).add(Boolean.FALSE); // False being returned from the higher stage and needs to be clarified
            }
        }
        return sd;
    }

}