package com.base.liam;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;

/**
 * Created by chaochun.ccc on 2017-06-06.
 */
public class PoiTest {
  public static void main(String[] args) {
    testGetRow();
  }

  public static void generateKeyMap() {
    InputStream inputStream;
    Workbook workbook;
    File file;
    try {
      file = new File("/Users/liam/alibaba/biz/cpt接芳华/洗数据-字段对应（最新）.xlsx");
      inputStream = new FileInputStream(file);
      workbook = WorkbookFactory.create(inputStream);
      int numberOfSheets = workbook.getNumberOfSheets();
      for (int i = 0; i < numberOfSheets; i++) {
        Sheet sheet = workbook.getSheetAt(i);
        int lastRowNum = sheet.getLastRowNum();
        int begin = sheet.getFirstRowNum() + 1;

        Map<String, String> keyMap = Maps.newHashMap();

        int styleType = 0;
        for (int j = begin; j <= lastRowNum; j++) {
          Row row = sheet.getRow(j);
          Cell cell1 = row.getCell(1);
          styleType = (int) cell1.getNumericCellValue();
          Cell cell3 = row.getCell(3);
          Cell cell4 = row.getCell(4);
          if (cell3 == null || cell4 == null) {
            continue;
          }
          String fanghuaKey = cell4.getStringCellValue();
          String oldKey = cell3.getStringCellValue();
          if (keyMap.containsKey(fanghuaKey)) {
            String val = keyMap.get(fanghuaKey);
            String newVal = val + "," + oldKey;
            keyMap.put(fanghuaKey, newVal);
          } else {
            keyMap.put(fanghuaKey, oldKey);
          }
        }
//        System.out.println(JSON.toJSONString(keyMap));
        Joiner.MapJoiner mapJoiner = Joiner.on("\r\n").withKeyValueSeparator(":");
        String joinData = mapJoiner.join(keyMap);
        String rootPath = PoiTest.class.getResource("/").getPath();
        FileChannel channel = new FileOutputStream(rootPath + styleType + ".keymap").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.wrap(joinData.getBytes());
        channel.write(byteBuffer);
        channel.close();
        System.out.println(styleType + " -- " + joinData);
        System.out.println("------------------------------------------");
      }

    } catch (IOException | InvalidFormatException e) {
      e.printStackTrace();
    }
  }

  public static void testGetRow() {
    InputStream inputStream;
    Workbook workbook;
    File file;
    try {
      file = new File("/Users/liam/alibaba/biz/cpt接芳华/高级样式-多图轮播 (5).xlsx");
      inputStream = new FileInputStream(file);
      workbook = WorkbookFactory.create(inputStream);
      Sheet sheet = workbook.getSheetAt(0);
      int lastRowNum = sheet.getLastRowNum();
      System.out.println(sheet.getFirstRowNum());
      System.out.println(lastRowNum);
      int begin = sheet.getFirstRowNum() + 3;
      Map<String,String> content = Maps.newHashMap();
      for (int i = begin; i <= lastRowNum; i++) {
        Row row = sheet.getRow(i);
        Cell cell1 = row.getCell(1);
        String key = cell1.getStringCellValue();
        Cell cell3 = row.getCell(3);
        cell3.setCellType(CellType.STRING);
        String val = cell3.getStringCellValue();
        content.put(key, val);
      }
      System.out.println(JSON.toJSONString(content));
    } catch (IOException | InvalidFormatException e) {
      e.printStackTrace();
    }
  }

  public static void test2() {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet1 = workbook.createSheet("test");
    Font blobFont = workbook.createFont();
    blobFont.setBold(true);

    XSSFFont blueFont = (XSSFFont) workbook.createFont();
    blueFont.setBold(true);
    blueFont.setColor(IndexedColors.BLUE.getIndex());

    String title = Constants.IDEA_EXCEL_TITLE_PREFIX;
    XSSFRichTextString richTextString = new XSSFRichTextString(title);
    richTextString.append("我是蓝色测试", blueFont);

    sheet1.setDefaultColumnWidth(25);
    sheet1.setColumnWidth(4, 1000);
    sheet1.setAutobreaks(true);
    Row topRow = sheet1.createRow(0);

    CellStyle blobAlignCenterStyle = workbook.createCellStyle();
    blobAlignCenterStyle.setAlignment(HorizontalAlignment.CENTER);
    blobAlignCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    blobAlignCenterStyle.setFont(blobFont);
    blobAlignCenterStyle.setBorderBottom(BorderStyle.THIN);
    blobAlignCenterStyle.setBorderTop(BorderStyle.THIN);
    blobAlignCenterStyle.setBorderLeft(BorderStyle.THIN);
    blobAlignCenterStyle.setBorderRight(BorderStyle.THIN);

    CellStyle blobCenterBgYellowCenterStyle = workbook.createCellStyle();
    blobCenterBgYellowCenterStyle.setAlignment(HorizontalAlignment.CENTER);
    blobCenterBgYellowCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    blobCenterBgYellowCenterStyle.setFont(blobFont);
    blobCenterBgYellowCenterStyle.setWrapText(true);
    blobCenterBgYellowCenterStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    blobCenterBgYellowCenterStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    blobCenterBgYellowCenterStyle.setBorderLeft(BorderStyle.THIN);
    blobCenterBgYellowCenterStyle.setBorderRight(BorderStyle.THIN);
    blobCenterBgYellowCenterStyle.setBorderBottom(BorderStyle.THIN);

    CellStyle leftBgYellowCenterStyle = workbook.createCellStyle();
    leftBgYellowCenterStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    leftBgYellowCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    leftBgYellowCenterStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    leftBgYellowCenterStyle.setWrapText(true);
    leftBgYellowCenterStyle.setBorderLeft(BorderStyle.THIN);
    leftBgYellowCenterStyle.setBorderRight(BorderStyle.THIN);
    leftBgYellowCenterStyle.setBorderBottom(BorderStyle.THIN);

    CellStyle blobCenterBgBlue60CenterStyle1 = workbook.createCellStyle();
    blobCenterBgBlue60CenterStyle1.setFont(blobFont);
    blobCenterBgBlue60CenterStyle1.setAlignment(HorizontalAlignment.CENTER);
    blobCenterBgBlue60CenterStyle1.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
    blobCenterBgBlue60CenterStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    blobCenterBgBlue60CenterStyle1.setBorderBottom(BorderStyle.THIN);
    blobCenterBgBlue60CenterStyle1.setBorderLeft(BorderStyle.THIN);
    blobCenterBgBlue60CenterStyle1.setBorderRight(BorderStyle.THIN);

    CellStyle blobCenterBgBlue60CenterStyle2 = workbook.createCellStyle();
    blobCenterBgBlue60CenterStyle2.setFont(blobFont);
    blobCenterBgBlue60CenterStyle2.setAlignment(HorizontalAlignment.CENTER);
    blobCenterBgBlue60CenterStyle2.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
    blobCenterBgBlue60CenterStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    blobCenterBgBlue60CenterStyle2.setBorderBottom(BorderStyle.THIN);
    blobCenterBgBlue60CenterStyle2.setBorderRight(BorderStyle.THIN);

    CellStyle blobCenterBgGreenCenterStyle = workbook.createCellStyle();
    blobCenterBgGreenCenterStyle.setFont(blobFont);
    blobCenterBgGreenCenterStyle.setAlignment(HorizontalAlignment.CENTER);
    blobCenterBgGreenCenterStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
    blobCenterBgGreenCenterStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    blobCenterBgGreenCenterStyle.setBorderBottom(BorderStyle.THIN);
    blobCenterBgGreenCenterStyle.setBorderRight(BorderStyle.THIN);

    Cell cell = topRow.createCell(0);
    cell.setCellValue(richTextString);
    cell.setCellStyle(blobAlignCenterStyle);

    for (int i = 1; i <= 3; i++) {
      Cell tmpCell = topRow.createCell(i);
      tmpCell.setCellStyle(blobAlignCenterStyle);
      System.out.println(tmpCell);
    }
    sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

    Row tipRow = sheet1.createRow(1);
    tipRow.setHeightInPoints(150);
    Cell leftTips = tipRow.createCell(0);
    Cell rightTips = tipRow.createCell(1);
    leftTips.setCellValue(Constants.IDEA_EXCEL_LEFT_TIPS);
    rightTips.setCellValue(Constants.IDEA_EXCEL_RIGHT_TIPS);

    for (int i = 2; i <= 3; i++) {
      Cell tmpCell = tipRow.createCell(i);
      tmpCell.setCellStyle(leftBgYellowCenterStyle);
      System.out.println(tmpCell);
    }

    sheet1.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));
    leftTips.setCellStyle(blobCenterBgYellowCenterStyle);
    rightTips.setCellStyle(leftBgYellowCenterStyle);

    Row titleRow = sheet1.createRow(2);
    Cell moduleCell = titleRow.createCell(0);
    Cell elementCell = titleRow.createCell(1);
    Cell descCell = titleRow.createCell(2);
    Cell contentCell = titleRow.createCell(3);
    moduleCell.setCellValue(Constants.IDEA_EXCEL_TITLE_MODULE);
    moduleCell.setCellStyle(blobCenterBgBlue60CenterStyle1);
    elementCell.setCellValue(Constants.IDEA_EXCEL_TITLE_ELEMENT);
    elementCell.setCellStyle(blobCenterBgBlue60CenterStyle2);
    descCell.setCellValue(Constants.IDEA_EXCEL_TITLE_DESC);
    descCell.setCellStyle(blobCenterBgGreenCenterStyle);
    contentCell.setCellValue(Constants.IDEA_EXCEL_TITLE_CONTENT);
    contentCell.setCellStyle(blobCenterBgGreenCenterStyle);


    int beginRowIdx = Constants.IDEA_EXCEL_HEAD_ROW_COUNT;



    Map<String, List<List<String>>> data = Maps.newHashMap();
    List<String> expr1 = Lists.newArrayList("test1-1", "test1-fdafdfdasfdsafdsafdsafdsfadfsafdsafdsafdsfadsfdsafcdscfdsafcds", "test1-3", "test1-4");
    List<String> expr2 = Lists.newArrayList("test2-1", "test2-2", "test2-3", "test2-4");
    data.put("分组1", Lists.newArrayList(expr1, expr2));
    data.put("分组2", Lists.newArrayList(expr1, expr2));

    CellStyle blobCellStyle = workbook.createCellStyle();
    blobCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    blobCellStyle.setFont(blobFont);
    blobCellStyle.setBorderBottom(BorderStyle.THIN);
    blobCellStyle.setBorderRight(BorderStyle.THIN);

    CellStyle simpleCellStyle = workbook.createCellStyle();
    simpleCellStyle.setBorderBottom(BorderStyle.THIN);
    simpleCellStyle.setBorderRight(BorderStyle.THIN);
    simpleCellStyle.setWrapText(true);

    for (Map.Entry<String, List<List<String>>> entry : data.entrySet()) {
      int groupBegin = beginRowIdx;
      for (List<String> expr : entry.getValue()) {
        Row row = sheet1.createRow(beginRowIdx);
        Cell tmpElementCell = row.createCell(1);
        tmpElementCell.setCellStyle(simpleCellStyle);
        tmpElementCell.setCellValue(expr.get(0));
        tmpElementCell.setCellStyle(blobCellStyle);
        Cell tmpDescCell = row.createCell(2);
        tmpDescCell.setCellStyle(simpleCellStyle);
        tmpDescCell.setCellValue(expr.get(1));
        Cell tmpContentCell = row.createCell(3);
//        tmpContentCell.setCellStyle(setCellStyle);
        tmpContentCell.setCellStyle(simpleCellStyle);
        if (expr.get(2) != null) {
          tmpContentCell.setCellValue(expr.get(2));
        }
        beginRowIdx++;
        Cell titleCell = row.createCell(0);
        titleCell.setCellValue(entry.getKey());
        titleCell.setCellStyle(blobAlignCenterStyle);
      }
      for (int i = groupBegin + 1; i < beginRowIdx; i++) {
        Row groupRow = sheet1.getRow(i);
        Cell tmpCell = groupRow.createCell(0);
        tmpCell.setCellStyle(blobAlignCenterStyle);
      }
      sheet1.addMergedRegion(new CellRangeAddress(groupBegin, beginRowIdx - 1, 0, 0));
    }

    FileOutputStream outputStream;
    URL url;
    InputStream inputStream = null;
    try {
      url = new URL(
          "http://image-ad.sm.cn/admaterial/logo/170418582d404f7fcf4774ad63dd6a444d906c.jpg");
      outputStream = new FileOutputStream(new File("//Users/liam/test/rich3.xlsx"));
      inputStream = url.openStream();
      byte[] bytes = IOUtils.toByteArray(inputStream);
      int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
      inputStream.close();

      CreationHelper helper = workbook.getCreationHelper();
      // Create the drawing patriarch. This is the top level container for all shapes.
      Drawing drawing = sheet1.createDrawingPatriarch();

      // add a picture shape
      ClientAnchor anchor = helper.createClientAnchor();
      // set top-left corner of the picture,
      // subsequent call of Picture#resize() will operate relative to it
      anchor.setCol1(5);
      anchor.setRow1(1);
      Picture pict = drawing.createPicture(anchor, pictureIdx);

      // auto-size picture relative to its top-left corner
      pict.resize();

      workbook.write(outputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(workbook);
    }

  }

  public static void testWrite() {
    Workbook workbook = new XSSFWorkbook();

    Sheet sheet1 = workbook.createSheet("Sheet1");
    sheet1.autoSizeColumn(0);
    sheet1.autoSizeColumn(1);
    sheet1.autoSizeColumn(2);
    sheet1.autoSizeColumn(3);
    Row topRow = sheet1.createRow(0);
    // topRow.set
    String title = Constants.IDEA_EXCEL_TITLE_PREFIX + "test";

    CellStyle blobAlignCenterStyle = workbook.createCellStyle();
    Font blobFont = workbook.createFont();
    blobFont.setBold(true);
    // blobFont.set
    blobAlignCenterStyle.setAlignment(HorizontalAlignment.CENTER);
    blobAlignCenterStyle.setFont(blobFont);

    CellStyle blobCenterBgYellowCenterStyle = workbook.createCellStyle();
    blobCenterBgYellowCenterStyle.setAlignment(HorizontalAlignment.CENTER);
    blobCenterBgYellowCenterStyle.setFont(blobFont);
    blobCenterBgYellowCenterStyle.setWrapText(true);
    blobCenterBgYellowCenterStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    blobCenterBgYellowCenterStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    CellStyle leftBgYellowCenterStyle = workbook.createCellStyle();
    leftBgYellowCenterStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    leftBgYellowCenterStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    leftBgYellowCenterStyle.setWrapText(true);
    // leftBgYellowCenterStyle.setBOrder

    CellStyle blobCenterBgBlue60CenterStyle = workbook.createCellStyle();
    blobCenterBgBlue60CenterStyle.setFont(blobFont);
    blobCenterBgBlue60CenterStyle.setAlignment(HorizontalAlignment.CENTER);
    blobCenterBgBlue60CenterStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
    blobCenterBgBlue60CenterStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    CellStyle blobCenterBgGreenCenterStyle = workbook.createCellStyle();
    blobCenterBgGreenCenterStyle.setFont(blobFont);
    blobCenterBgGreenCenterStyle.setAlignment(HorizontalAlignment.CENTER);
    blobCenterBgGreenCenterStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
    blobCenterBgGreenCenterStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    Cell cell = topRow.createCell(0);
    cell.setCellValue(title);
    cell.setCellStyle(blobAlignCenterStyle);
    sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));


    Row tipRow = sheet1.createRow(1);
    Cell leftTips = tipRow.createCell(0);
    Cell rightTips = tipRow.createCell(1);
    leftTips.setCellValue(Constants.IDEA_EXCEL_LEFT_TIPS);
    rightTips.setCellValue(Constants.IDEA_EXCEL_RIGHT_TIPS);
    sheet1.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));
    leftTips.setCellStyle(blobCenterBgYellowCenterStyle);
    rightTips.setCellStyle(leftBgYellowCenterStyle);

    Row titleRow = sheet1.createRow(2);
    Cell moduleCell = titleRow.createCell(0);
    Cell elementCell = titleRow.createCell(1);
    Cell descCell = titleRow.createCell(2);
    Cell contentCell = titleRow.createCell(3);
    moduleCell.setCellValue(Constants.IDEA_EXCEL_TITLE_MODULE);
    moduleCell.setCellStyle(blobCenterBgBlue60CenterStyle);
    elementCell.setCellValue(Constants.IDEA_EXCEL_TITLE_ELEMENT);
    elementCell.setCellStyle(blobCenterBgBlue60CenterStyle);
    descCell.setCellValue(Constants.IDEA_EXCEL_TITLE_DESC);
    descCell.setCellStyle(blobCenterBgGreenCenterStyle);
    contentCell.setCellValue(Constants.IDEA_EXCEL_TITLE_CONTENT);
    contentCell.setCellStyle(blobCenterBgGreenCenterStyle);
    FileOutputStream outputStream;
    try {
      outputStream = new FileOutputStream(new File("//Users/liam/test/rich.xlsx"));
      workbook.write(outputStream);

      System.out.println(workbook.getNumCellStyles());

      CellStyle cellStyleAt0 = workbook.getCellStyleAt(0);
      System.out.println(cellStyleAt0.getFontIndex());
      System.out.println(cellStyleAt0.getAlignmentEnum());
      CellStyle cellStyleAt1 = workbook.getCellStyleAt(1);
      System.out.println(cellStyleAt1.getFillBackgroundColor());
      System.out.println(cellStyleAt1.getAlignmentEnum());
      System.out.println(cellStyleAt1.getFontIndex());
      CellStyle cellStyleAt2 = workbook.getCellStyleAt(2);
      System.out.println(cellStyleAt2.getFillBackgroundColor());
      System.out.println(cellStyleAt2.getAlignmentEnum());
      System.out.println(cellStyleAt2.getFontIndex());
      CellStyle cellStyleAt3 = workbook.getCellStyleAt(3);
      System.out.println(cellStyleAt3.getFillBackgroundColor());
      System.out.println(cellStyleAt3.getAlignmentEnum());
      CellStyle cellStyleAt4 = workbook.getCellStyleAt(4);
      System.out.println(cellStyleAt4.getFillBackgroundColor());
      System.out.println(cellStyleAt4.getFontIndex());
      System.out.println(cellStyleAt4.getAlignmentEnum());
      CellStyle cellStyleAt5 = workbook.getCellStyleAt(5);
      System.out.println(cellStyleAt5);



    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(workbook);
    }
  }
}
