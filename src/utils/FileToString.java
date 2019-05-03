package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;

public class FileToString {
		
	public static String convert(String fileName, List<String> fields) { 
		String html = FileToString.readFile(fileName);
		
		int quantidade = (html.length() - (html.replaceAll("##", "").length()))/2;
		
		for(int i = 0; i < quantidade && i != fields.size(); i++) {
			html = html.replaceFirst("##", Matcher.quoteReplacement(fields.get(i)));
		}
		
		return html;
	}
	
	public static String convert(String fileName, String field) {
		String html = FileToString.readFile(fileName);
		return html.replaceAll("##", field);
	}
	
	public static String convert(String fileName) {
		return FileToString.readFile(fileName);
	}
	
	private static String readFile(String fileName) {
		
		StringBuilder contentBuilder = new StringBuilder();
		
		try {
		    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str + "\n");
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("Deu ruim ao ler arquivo");
		}
		String content = contentBuilder.toString();
		
		return content;
	}
}
