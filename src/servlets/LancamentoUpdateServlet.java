package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Lancamento;
import domain.TipoLancamento;
import services.LancamentosService;
import utils.FileToString;
import utils.RelativePath;

public class LancamentoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendUpdateForm(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			updateRecord(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		LancamentosService lancamentoService = new LancamentosService();
		Lancamento lancamento = lancamentoService.findBy(Integer.parseInt(request.getParameter("id")));
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<String> fields = new ArrayList<String>();

		fields.add(request.getRequestURI() + "?id=" + lancamento.getIdLancamento());
		fields.add(format.format(lancamento.getData()));
		fields.add(lancamento.getValor().toString());
		fields.add(lancamento.getTipoLancamento().getCod().toString());
		fields.add(lancamento.getTipoLancamento().toString());
		fields.add(lancamento.getTipoLancamento().inverse().getCod().toString());
		fields.add(lancamento.getTipoLancamento().inverse().toString());

		String HTML = FileToString.convert(RelativePath.fileName(this, "lancamentoForm.html"), fields);

		out.println(HTML);
	}

	void updateRecord(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

		LancamentosService lancamentosService = new LancamentosService();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Date date = format.parse(request.getParameter("data"));

		TipoLancamento tp = TipoLancamento.toEnum(Integer.parseInt(request.getParameter("tipo")));

		Lancamento lancamento = new Lancamento(Integer.parseInt(request.getParameter("id")), date,
				Float.parseFloat(request.getParameter("valor")), tp);

		lancamentosService.upadte(lancamento);
		response.sendRedirect("lancamentoSearch?id=" + request.getSession().getAttribute("id_cliente"));
	}
}
