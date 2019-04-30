package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import utils.FileToString;
import utils.RelativePath;

public class ClienteInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendInsertForm(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		insertRecord(req, resp);
	}

	private void sendInsertForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		List<String> fields = new ArrayList<String>();

		fields.add(request.getRequestURI());
		fields.add("");
		fields.add("");
		fields.add("");

		String HTML = FileToString.convert(RelativePath.fileName(this, "clienteForm.html"), fields);

		out.println(HTML);
	}

	void insertRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			Cliente cliente = new Cliente(request.getParameter("nome"), Long.parseLong(request.getParameter("cpf")),
					request.getParameter("email"));
			request.getSession().setAttribute("cliente", cliente);
			response.sendRedirect("lancamentoInsert");
		} catch (Exception e) {
			response.sendRedirect("Mensagem?acao=insert&domain=Cliente&classe=danger");
		}
	}
}
