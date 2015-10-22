package com.stefanini.bob.management.web;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.stefanini.bob.management.domain.TimeSheet;


public class TimesheetDailyExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HSSFSheet excelSheet = workbook.createSheet("TIMESHEET");
		setExcelHeader(excelSheet);
		
		List<TimeSheet> timesheetList = (List<TimeSheet>) model.get("timesheetList");
		setExcelRows(excelSheet,timesheetList);		
	}
	
	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("ID");
		excelHeader.createCell(1).setCellValue("Data");
		excelHeader.createCell(2).setCellValue("Projeto");
		excelHeader.createCell(3).setCellValue("Categoria");
		excelHeader.createCell(4).setCellValue("Atividade");
		excelHeader.createCell(5).setCellValue("Retrabalho");
		excelHeader.createCell(6).setCellValue("Frente de Trabalho");
		excelHeader.createCell(7).setCellValue("Pessoa");
		excelHeader.createCell(8).setCellValue("Quantidade de Horas");
		excelHeader.createCell(9).setCellValue("Hora Extra");
		excelHeader.createCell(10).setCellValue("Anotação");
	}
	
	public void setExcelRows(HSSFSheet excelSheet, List<TimeSheet> timesheetList){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		int record = 1;
		for (TimeSheet timesheet : timesheetList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(timesheet.getId());
			excelRow.createCell(1).setCellValue(dateFormat.format(timesheet.getOccurrenceDate()));
			excelRow.createCell(2).setCellValue(timesheet.getProject().getName());
			excelRow.createCell(3).setCellValue(timesheet.getCategory().getDescription());
			excelRow.createCell(4).setCellValue(timesheet.getTask().getDescription());
			excelRow.createCell(5).setCellValue(timesheet.getTask().getRework()?"S":"N");
			excelRow.createCell(6).setCellValue(timesheet.getWorkGroup()==null?"":timesheet.getWorkGroup().getName());
			excelRow.createCell(7).setCellValue(timesheet.getPerson().getName());
			excelRow.createCell(8).setCellValue(timesheet.getWorkHours().doubleValue());
			excelRow.createCell(9).setCellValue(timesheet.getOvertime()?"S":"N");
			excelRow.createCell(10).setCellValue(timesheet.getNote());
		}
	}
	
	public void putHeaderStyle(HSSFWorkbook workbook, HSSFSheet excelSheet){
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.DARK_BLUE.index);
		headerCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		excelSheet.getRow(0).setRowStyle(headerCellStyle);
	}

}
