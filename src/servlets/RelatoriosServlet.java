package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
		fields.add(request.getRequestURI());
		fields.add(format.format(b));
		fields.add(format.format(a));
		fields.add(relatoriosService.amountToReceiveBetween(b, a).toString());
		fields.add(relatoriosService.amountReceivedBetween(b, a).toString());
		fields.add(relatoriosService.amountPendency(b, a).toString());
		fields.add(relatoriosService.amountToReceive().toString());
		fields.add(relatoriosService.amountReceived().toString());
		fields.add(relatoriosService.amountPendency().toString());

		String HTML = FileToString.convert(RelativePath.fileName(this, "relatorios.html"), fields);

		out.println(HTML);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RelatoriosService relatoriosService = new RelatoriosService();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		LocalDate dataAtual = LocalDate.now();
		
		Date a = Date.from(dataAtual.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date b = Date.from(dataAtual.plusMonths(-1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		try {
			a = format.parse(request.getParameter("dataFinal"));
			b = format.parse(request.getParameter("dataInicial"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<String> fields = new ArrayList<String>();
		fields.add(request.getRequestURI());
		fields.add(format.format(b));
		fields.add(format.format(a));
		fields.add(relatoriosService.amountToReceiveBetween(b, a).toString());
		fields.add(relatoriosService.amountReceivedBetween(b, a).toString());
		fields.add(relatoriosService.amountPendency(b, a).toString());
		fields.add(relatoriosService.amountToReceive().toString());
		fields.add(relatoriosService.amountReceived().toString());
		fields.add(relatoriosService.amountPendency().toString());

		String HTML = FileToString.convert(RelativePath.fileName(this, "relatorios.html"), fields);

		out.println(HTML);
	}

}
