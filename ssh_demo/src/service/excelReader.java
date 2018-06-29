package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dao.fpDao;

public class excelReader {
	public static void main(String[] args){
		String path = "d:/2010.xls";
		try {
			showfpExcel(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "deprecation", "static-access" })
	public static void showfpExcel(String path) throws Exception {
		// �����������ط���
		System.out.println("��ȡ�ļ�");
		int rowIndex = 9;
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(path)));
		HSSFSheet sheet = null;
		sheet = workbook.getSheetAt(0);// ��õ�һ��sheet

		String FLDCBRIEFNAME = "¥��/ƽ��_���";
		String FLDCNAME = "¥��/ƽ��_����";
		String LB1 = "¥��/ƽ��_���";
		HSSFCell ID = sheet.getRow(0).getCell(0);// ��1�е�1��
		
		fpDao fpDao1 = new fpDao();
		// ���£��ǽ���һ����ʱ����fpDao��ʵ��
		fpDao1.addFpHeader(FLDCBRIEFNAME, FLDCNAME, LB1);
		
		if (ID.getCellType() == ID.CELL_TYPE_NUMERIC) {
			int rowNum;
			for (rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
				readfpItemRow(sheet.getRow(rowNum),fpDao1);
			}
		} else {

			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				readfpItemRow(sheet.getRow(rowNum),fpDao1);
			}
		}
	}

	public static void readfpItemRow(HSSFRow row, fpDao fpDao) throws Exception {
		System.out.println("��ȡһ�б������");
		double no = row.getCell(0).getNumericCellValue();
		String name = row.getCell(1).getStringCellValue();
		String lb = row.getCell(2).getStringCellValue();
		
		fpDao.addFpRow(no, name, lb);
		
	}
}
