/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import static input.InputReader.excel;
import static input.InputReader.Domainio;
import static sample.FXMLController.*;
import static sample.Progress.*;
//import static sample.FXMLController.project;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import sample.FXMLController;

/**
 *
 * @author pravin.a
 */

public class OutputWriter {

    public static String sheet_Location = "";
    public static String folder = location;
    public static Sheet st;
    public static Workbook wb = null;
    public static PrintWriter writer = null;


    public static int ava = 0;




    public static void appendToFile(Exception e) {

        String aa = folder;
        aa = aa + "exception.txt";
        FileWriter fstream = null;
        try {
            fstream = new FileWriter(aa, true);
        } catch (IOException ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        BufferedWriter out = new BufferedWriter(fstream);
        PrintWriter pWriter = new PrintWriter(out, true);
        e.printStackTrace(pWriter);

    }


    public static void appendHarProblem(String str) {

        String aa = folder;
        aa = aa + "HAR Issue.txt";
        if (ava == 0) {
            ava++;

            try {
                writer = new PrintWriter(aa, "UTF-8");
            } catch (Exception ex) {
                OutputWriter.appendToFile(ex);
                ex.printStackTrace();
            }

        }
        writer.println(str);

    }



    private static void copyRow(HSSFWorkbook workbook, HSSFSheet worksheet, int sourceRowNum, int destinationRowNum) {
        // Get the source / new row
        HSSFRow newRow = null;

        newRow = worksheet.getRow(destinationRowNum);

        HSSFRow sourceRow = worksheet.getRow(sourceRowNum);

        // If the row exist in destination, push down all rows by 1 else create a new row
        if (newRow != null) {
            worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
        } else {
            newRow = worksheet.createRow(destinationRowNum);
        }

        // Loop through source columns to add to new row
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            // Grab a copy of the old/new cell
            HSSFCell oldCell = sourceRow.getCell(i);
            HSSFCell newCell = newRow.createCell(i);

            // If the old cell is null jump to next cell
            if (oldCell == null) {
                newCell = null;
                continue;
            }

            // Copy style from old cell and apply to new cell
            HSSFCellStyle newCellStyle = workbook.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            ;
            newCell.setCellStyle(newCellStyle);

            // If there is a cell comment, copy
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // If there is a cell hyperlink, copy
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // Set the cell data type
            newCell.setCellType(oldCell.getCellType());

            // Set the cell data value
            switch (oldCell.getCellType()) {
                case Cell.CELL_TYPE_BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
            }
        }
    }

    public void create() throws FileNotFoundException, IOException {
        System.out.println("Location:"+folder);
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excel));
        String aa = folder;

        aa = aa + "sheet.xls";

        sheet_Location = aa;
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        copyRow(workbook, sheet, 0, 1);
        FileOutputStream out = new FileOutputStream(sheet_Location);
        workbook.write(out);
        out.close();

        wb = new HSSFWorkbook(new FileInputStream(sheet_Location));

        st = wb.getSheetAt(0);
        if (st == null) {
            st = wb.createSheet("Sheet1");
        }

        for (int i = 0; i <= 10; i++) {
            System.out.println("4");
            String get;
            Row row = st.getRow(i);

            Cell c = row.getCell(1);
            try {
                get = c.getStringCellValue();

            } catch (Exception e) {
                continue;
            }
            if (get.contains("Tested Date:")) {

                Row row1 = st.getRow(i);
                Cell ce1l = row1.createCell(3);
                ce1l.setCellValue(time.e());
            }
            if (get.contains("Project Name:")) {

                Row row2 = st.getRow(i);
                Cell ce12 = row2.createCell(3);
                // ce12.setCellValue(domain);
                ce12.setCellValue(FXMLController.project1);
            }
            if (get.contains("Environment:")) {

                Row row3 = st.getRow(i);
                Cell ce13 = row3.createCell(3);
                ce13.setCellValue(environ);
            }
        }
    }

    public void LoadingView(String Lable, String Process) {
        urlLable = Lable;
        processLable = Process;
    }

    public void complete() throws FileNotFoundException, IOException {

        urlLable = "Writing in file " + sheet_Location;
        processLable = "In Process";
        System.out.println("out");

        wb.write(new FileOutputStream(sheet_Location));
        writer.close();

        loadTxt = Double.valueOf(totalUrl);
        loadBar = Double.valueOf(totalUrl);

        System.out.println("writed");

        urlLable = "Completed";
        processLable = "Completed";
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = null;
                alert = new Alert(Alert.AlertType.INFORMATION, "Completed", ButtonType.OK);
                alert.setTitle("Completed");
                alert.setHeaderText("Test Finished");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("/StyleSheet/style.css").toExternalForm());
                dialogPane.getStyleClass().add("myDialog");
              alert.show();
            }});

    }

    public void com() throws FileNotFoundException, IOException {

        System.out.println("out");

        wb.write(new FileOutputStream(sheet_Location));

        System.out.println("writed");

    }
}
