package com.ets.format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FormatterMain {

		public static void main(String[] args) throws IOException {
		
		//Scanner scanner = new Scanner(System.in);
		File[] f = null;
		//Reader r;
//		try{
		
			f = createFileList(f);
			
/*	    //  prompt for the user's name
	    System.out.print("Enter Input File location: ");

	    // get their input as a String
	    String inFile = scanner.next();

	    // prompt for their age
	    System.out.print("Enter output File location:  ");

	    // get the age as an int
	    String outFile = scanner.next();
	    
	    scanner.close();
	    
	    System.out.println(inFile);
	    System.out.println(outFile);*/
	    
	    if (f!=null) {
	    	

			for (int i = 0; i < f.length; i++) {
				XSSFWorkbook workbook = new XSSFWorkbook();
				int rowNumS1 = 0;
				int rowNumS2 = 0;
		        XSSFSheet sheetTemp = workbook.createSheet("POT_TEMP_Report");
		        rowNumS1 = createRow(rowNumS1, sheetTemp,"POT","TEMP");
		        XSSFSheet sheetVolt = workbook.createSheet("POT_VOLT_Report");
		        rowNumS2 = createRow(rowNumS2, sheetVolt,"POT","ANODE","VOLT");

		        
				//System.out.println(f[i].getAbsolutePath());
				List<String> lists = FileUtils.readLines(f[i]);
				for (String stringin : lists) {
					if (stringin.contains("POT") && stringin.contains("TEMP")) {
						
						writePotTemp(stringin, sheetTemp, rowNumS1);
						
					} else if (stringin.contains("POT")&& stringin.contains("ANODE")
							&& stringin.contains("VOLT")) {
						
						writePotVoltage(stringin, sheetVolt, rowNumS2);
					}

				}
				
		        try {
		            FileOutputStream outputStream = new FileOutputStream(f[i].getName().substring(0, f[i].getName().length()-4)+"_Report.xlsx");
		            workbook.write(outputStream);
		            workbook.close();
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

			}
		}  
	/*}catch(Exception c){
		c.printStackTrace();
		System.out.println(c);
		
		
	}finally{
		//r.close();
		scanner.close();
	}*/
  }

	/**
	 * @param rowNum
	 * @param sheetTemp
	 * @return
	 */
	private static int createRow(int rowNum, XSSFSheet sheetTemp,String... callVal) {
		Row row =sheetTemp.createRow(rowNum++);
		for (int i = 0; i < callVal.length; i++) {
			Cell c1 = row.createCell(i);			
			c1.setCellValue(callVal[i]);			
		}
		return rowNum;
	}

	/**
	 * @param stringin
	 * @param rowNumS1 
	 * @param sheetTemp 
	 */
	private static void writePotVoltage(String stringin, XSSFSheet sheetVolt, int rowNumS2) {
		StringTokenizer st = new StringTokenizer(stringin, " ");
		String cell1="";
		String cell2="";
		String cell3="";
		System.out.println("Write Pot Voltage:: Line no. "+rowNumS2); 
		while (st.hasMoreTokens()) {
			String crrV = st.nextToken();
			if (crrV.equalsIgnoreCase("POT")) {
				System.out.print("POT::");
				cell1=st.nextToken();
				System.out.print(cell1);
			} else if (crrV.equalsIgnoreCase("ANODE")) {
				System.out.print("	Annode::");
				cell2=st.nextToken();
				System.out.print(cell2);
			} else if (crrV.equalsIgnoreCase("VOLT")) {
				System.out.print("	VOLT::");
				cell3=st.nextToken();
				System.out.println(cell3);
			}
		}
		createRow(rowNumS2,sheetVolt,cell1,cell2,cell3);
	}

	/**
	 * @param stringin
	 * @param sheetTemp 
	 */
	private static void writePotTemp(String stringin, XSSFSheet sheetTemp,int rowNumS1) {
		StringTokenizer st = new StringTokenizer(stringin, " ");
		String cell1="";
		String cell2="";
		System.out.println("Write Pot Temp:: Line no. "+rowNumS1);
		while (st.hasMoreTokens()) {
			//Row r = sheetTemp.createRow(rowNumS1++);
			String crrV = st.nextToken();
			if (crrV.equalsIgnoreCase("POT")) {
				System.out.print("POT::");
				cell1=st.nextToken();
				System.out.print(cell1);
			} else if (crrV.equalsIgnoreCase("TEMP")) {
				System.out.print("	TEMP::");
						cell2=st.nextToken();
						System.out.println(cell2);

			}
		}
		
		createRow(rowNumS1,sheetTemp,cell1,cell2);
		
	}

	private static File[] createFileList(File[] f) {
		final File[] lofRoots = File.listRoots();
		for (int i = 0; i < lofRoots.length; i++) {
			//System.out.println("name::"+lofRoots[i]);
			FilenameFilter filter = new ArduinoFileNameFilter();
			if(lofRoots[i].listFiles(filter).length>0){
				f = lofRoots[i].listFiles(filter);
				break;
			}
			
		}
		return f;
	}
}


