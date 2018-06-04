package com.tianmao.web.app.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.OutputStream;
import java.util.List;

/**
 * 
 * @ClassName: ExportUtil
 * @Description: EXCEL导出yUTIL
 * @author CM
 * @date 2015年10月6日 下午6:10:31
 * @since version 1.0
 */
public class ExportUtil {

	public static void createExcelFile(OutputStream out, String title,
                                       List<String[]> contenList) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFRow row = null;
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont f = wb.createFont();
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		f.setColor(HSSFFont.COLOR_RED);// 红色
		style.setFont(f);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		if (null != contenList && contenList.size() > 0) {
			final int excelMaxRow = 65535;// 一个excel表格sheet最多只能存放65535条记录
			int index = 0;// 记录数是否达到excelMaxRow
			int contentSize = contenList.size();
			int sheetNum = (contentSize / excelMaxRow)
					+ ((contentSize % excelMaxRow) > 0 ? 1 : 0);
			int len = 0;
			for (int i = 0; i < sheetNum; i++) {
				HSSFSheet sheet = wb.createSheet();
				wb.setSheetName(i, "sheet" + (i + 1));
				while (!contenList.isEmpty()) {
					row = sheet.createRow(index);
					String[] content = contenList.remove(0);
					len = content.length;
					for (int k = 0; k < len; k++) {
						HSSFCell cell = row.createCell(k);
						cell.setCellValue(content[k]);
//						if (k == 10) {
//							cell.setCellStyle(style);
//						}
						// else if (index == 0
						// && (k == 0 || k == 1 || k == 5 || k == 10)) {
						// cell.setCellStyle(style);
						// }
					}
					index++;
					if (index % excelMaxRow == 0 || index >= contentSize) {
						index = 0;
						break;
					}
				}
				for (int j = 0; j < len; j++) {
					sheet.autoSizeColumn(j);
					System.out.println((sheet.getColumnWidth(j) * 2));
					if(sheet.getColumnWidth(j) * 2 < 255*256){
						sheet.setColumnWidth(j, (sheet.getColumnWidth(j) * 2));
					}
				}
			}

			wb.write(out);
			out.flush();
			out.close();
		}

	}
	
	public static void createExcelFile4Report(OutputStream out, String title,
                                              List<String[]> contenList, Integer status) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFRow row = null;
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont f = wb.createFont();
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		f.setColor(HSSFFont.COLOR_RED);// 红色
		style.setFont(f);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		if (null != contenList && contenList.size() > 0) {
			final int excelMaxRow = 65535;// 一个excel表格sheet最多只能存放65535条记录
			int index = 0;// 记录数是否达到excelMaxRow
			int contentSize = contenList.size();
			int sheetNum = (contentSize / excelMaxRow)
					+ ((contentSize % excelMaxRow) > 0 ? 1 : 0);
			int len = 0;
			for (int i = 0; i < sheetNum; i++) {
				HSSFSheet sheet = wb.createSheet();
				wb.setSheetName(i, "sheet" + (i + 1));
				while (!contenList.isEmpty()) {
					row = sheet.createRow(index);
					String[] content = contenList.remove(0);
					len = content.length;
					for (int k = 0; k < len; k++) {
						HSSFCell cell = row.createCell(k);
						cell.setCellValue(content[k]);
						if (status != null && index!=0 && k == 8 && status==2 && !content[k].equals(content[5])) {
							cell.setCellStyle(style);
						}
						if (status != null && index!=0 && k == 9 && status==3 && !content[k].equals(content[5])) {
							cell.setCellStyle(style);
						}
					}
					index++;
					if (index % excelMaxRow == 0 || index >= contentSize) {
						index = 0;
						break;
					}
				}
				for (int j = 0; j < len; j++) {
					sheet.autoSizeColumn(j);
					sheet.setColumnWidth(j, (sheet.getColumnWidth(j) * 2));
				}
			}

			wb.write(out);
			out.flush();
			out.close();
		}

	}
	public static void createExcelFile2(OutputStream out, String title, List<String[]> contenList,String headline,String firstLine) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFRow row = null;
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont f = wb.createFont();
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		f.setColor(HSSFFont.COLOR_RED);// 红色
		style.setFont(f);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

//第一行样式
		// 设置字体
		HSSFFont headfont = wb.createFont();
		headfont.setFontName("黑体");
		headfont.setFontHeightInPoints((short) 15);// 字体大小
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		HSSFCellStyle headstyle = wb.createCellStyle();
		headstyle.setFont(headfont);
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
// 第二行样式
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 10);
		// 另一个样式
		HSSFCellStyle centerstyle = wb.createCellStyle();
		centerstyle.setFont(font);
		centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		centerstyle.setWrapText(true);
		centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
		centerstyle.setBorderLeft((short) 1);
		centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
		centerstyle.setBorderRight((short) 1);
		centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．


		contenList.add(new String[contenList.get(0).length]);
		contenList.add(new String[contenList.get(0).length]);
		if (null != contenList && contenList.size() > 0) {
			final int excelMaxRow = 65535;// 一个excel表格sheet最多只能存放65535条记录
			int index = 0;// 记录数是否达到excelMaxRow
			int contentSize = contenList.size();
			int sheetNum = (contentSize / excelMaxRow) + ((contentSize % excelMaxRow) > 0 ? 1 : 0);
			int len = 0;
			for (int i = 0; i < sheetNum; i++) {
				HSSFSheet sheet = wb.createSheet();
				wb.setSheetName(i, "sheet" + (i + 1));
				while (!contenList.isEmpty()) {
					CellRangeAddress range = new CellRangeAddress(0, 0, 0, contenList.get(0).length);
					if(index == 0 || index == 1){
						if(index == 0){
							row = sheet.createRow(index);
							row.setHeightInPoints(30);
							range = new CellRangeAddress(0, 0, 0, contenList.get(0).length);
							sheet.addMergedRegion(range);
							HSSFCell cell1 = row.createCell(0);
							cell1.setCellValue(new HSSFRichTextString(headline));
							cell1.setCellStyle(headstyle);
						}
						if(index == 1){
							row = sheet.createRow(index);
							row.setHeightInPoints(30);
							range = new CellRangeAddress(1, 1, 0, contenList.get(0).length);
							sheet.addMergedRegion(range);
							HSSFCell cell1 = row.createCell(0);
							cell1.setCellValue(new HSSFRichTextString(firstLine));
							cell1.setCellStyle(centerstyle);
						}
					} else {
						row = sheet.createRow(index);
						String[] content = contenList.remove(0);
						len = content.length;
						for (int k = 0; k < len; k++) {
							HSSFCell cell = row.createCell(k);
							cell.setCellValue(content[k]);
						}
					}
					index++;
					if (index % excelMaxRow == 0 || index >= contentSize) {
						index = 0;
						break;
					}
				}
				for (int j = 0; j < len; j++) {
					sheet.autoSizeColumn(j);
					System.out.println((sheet.getColumnWidth(j) * 2));
					if (sheet.getColumnWidth(j) * 2 < 255 * 256) {
						sheet.setColumnWidth(j, (sheet.getColumnWidth(j) * 2));
					}
				}
			}

			wb.write(out);
			out.flush();
			out.close();
		}

	}
}
