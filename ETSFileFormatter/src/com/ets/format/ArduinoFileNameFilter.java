package com.ets.format;

import java.io.File;
import java.io.FilenameFilter;

public class ArduinoFileNameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		/*System.out.println("dir::"+dir);
		System.out.println("name::"+name);*/
		if(name.contains("arduino")){
			return true;
		}else{
			return false;
		}
	}
		

}
