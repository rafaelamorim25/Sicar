package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import domain.Lancamento;
import domain.TipoLancamento;
import services.ClienteService;
import services.LancamentosService;
import utils.FileToString;
import utils.RelativePath;

public class LancamentoInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendInsertForm(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute("cliente") != null) {
			insertRecordFirst(req, resp);
		} else {
			insertRecord(req, resp);
		}
	}

	private void sendInsertForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<String> fields = new ArrayList<String>();

		fields.add(request.getRequestURI());
		fields.add("");
		fields.add("");
		fields.add(TipoLancamento.COMPRA.getCod().toString());
		fields.add(TipoLancamento.COMPRA.toString());
		fields.add(TipoLancamento.PAGAMENTO.getCod().toString());
		fields.add(TipoLancamento.PAGAMENTO.toString());

		String HTML = FileToString.convert(RelativePath.fileName(this, "lancamentoForm.html"), fields);

		out.println(HTML);
	}

	void insertRecordFirst(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("To no first");

		ClienteService clienteService = new ClienteService();
		LancamentosService lancamentosService = new LancamentosService();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Cliente cliente = null;
		Date date = null;

		try {

			cliente = (Cliente) request.getSession().getAttribute("cliente");
			clienteService.insert(cliente);
			cliente = clienteService.findBy(cliente.getCpf());
			request.getSession().setAttribute("id_cliente", cliente.getId());
			request.getSession().setAttribute("cliente", null);

			date = format.parse(request.getParameter("data"));
			Lancamento lancamento = new Lancamento(date, Float.parseFloat(request.getParameter("valor")),
					TipoLancamento.toEnum(Integer.parseInt(request.getParameter("tipo"))), cliente);
			lancamentosService.insert(lancamento);
			response.sendRedirect("lancamentoSearch?id=" + request.getSession().getAttribute("id_cliente"));
		} catch (Exception e) {
			request.getSession().setAttribute("cliente", null);
			response.sendRedirect("insertCliente");
		}
	}

	void insertRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("To no normal");

		ClienteService clienteService = new ClienteService();
		LancamentosService lancamentosService = new LancamentosService();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Cliente cliente = null;
		Date date = null;

		try {

			cliente = clienteService.findBy((int) request.getSession().getAttribute("id_cliente"));

			date = format.parse(request.getParameter("data"));
			Lancamento lancamento = new Lancamento(date, Float.parseFloat(request.getParameter("valor")),
					TipoLancamento.toEnum(Integer.parseInt(request.getParameter("tipo"))), cliente);
			lancamentosService.insert(lancamento);
			response.sendRedirect("lancamentoSearch?id=" + request.getSession().getAttribute("id_cliente"));
		} catch (Exception e) {
			response.sendRedirect("lancamentoSearch?id=" + request.getSession().getAttribute("id_cliente"));
		}
	}
}
