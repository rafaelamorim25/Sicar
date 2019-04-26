package utils;

import javax.servlet.http.HttpServlet;

public class RelativePath {
	
	public static String fileName (HttpServlet servlet, String name) {
		
		String fileSeparator = System.getProperty("file.separator");
		
		return servlet.getServletContext().getRealPath(fileSeparator) + fileSeparator + name;
	}

}
