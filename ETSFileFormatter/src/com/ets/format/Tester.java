package com.ets.format;

import java.io.File;
import java.io.FilenameFilter;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final File[] lofRoots = File.listRoots();
		//System.out.println("name::"+lofRoots);
		for (int i = 0; i < lofRoots.length; i++) {
			
			FilenameFilter filter = new ArduinoFileNameFilter();
			File[] f = lofRoots[i].listFiles(filter);
			
			if(f!=null && f.length>0){				
				System.out.println(f.length);
				for (int j = 0; j < f.length; j++) {
					System.out.println(f[j].getAbsolutePath());		
					
				}
				
			}
		}
		
		/*FilenameFilter filter = new ArduinoFileNameFilter();
		File[] f = lofRoots[1].listFiles(filter);
		System.out.println("found "+f.length + f[0].getAbsolutePath());*/

	}

}
