package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.RelatoriosService;
import utils.FileToString;
import utils.RelativePath;

public class RelatoriosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RelatoriosService relatoriosService = new RelatoriosService();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		LocalDate dataAtual = LocalDate.now();
		
		Date a = Date.from(dataAtual.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date b = Date.from(dataAtual.plusMonths(-1).atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<String> fields = new ArrayList<String>();
		fields.add(relatoriosService.amountReceived().toString());
		fields.add(relatoriosService.amountToReceive().toString());
		fields.add(format.format(a));
		fields.add(format.format(b));
		fields.add(relatoriosService.amountReceivedBetween(a, b).toString());
		fields.add(format.format(a));
		fields.add(format.format(b));
		fields.add(relatoriosService.amountToReceiveBetween(a, b).toString());

		String HTML = FileToString.convert(RelativePath.fileName(this, "relatorios.html"), fields);

		out.println(HTML);
	}

}
