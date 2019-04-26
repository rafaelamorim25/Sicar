package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FileToString {
	
	public static String convert(String diretorio) {
		
		StringBuilder contentBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(diretorio), "UTF-8"));

		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str + "\n");
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("Deu ruim");
			e.printStackTrace();
		}
		String content = contentBuilder.toString();
		
		return content;
	}
	
	public static String convert(String diretorio, List<String> fields) { 
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(diretorio), "UTF-8"));
		    String str;
		    int i = 0;
		    while ((str = in.readLine()) != null) {
		    	if(str.indexOf("##") >= 0 && i != fields.size()) {
		    		str = str.replace("##", fields.get(i));
		    		i++;
		    	}
		        contentBuilder.append(str + "\n");
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("Deu ruim");
			e.printStackTrace();
		}
		String content = contentBuilder.toString();
		
		return content;
	}
	
	public static String convert(String diretorio, String table) {
		String html = FileToString.convert(diretorio);
		return html.replace("##", table);
	}
}
