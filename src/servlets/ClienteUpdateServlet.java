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
import services.ClienteService;
import utils.FileToString;

public class ClienteUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendUpdateForm(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updateRecord(request, response);
	}

	private void sendUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ClienteService clienteService = new ClienteService();
		Cliente cliente = clienteService.findBy(Integer.parseInt(request.getParameter("id")));

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		List<String> fields = new ArrayList<String>();

		fields.add(request.getRequestURI() + "?id=" + cliente.getId());
		fields.add(cliente.getNome());
		fields.add(cliente.getCpf().toString());
		fields.add(cliente.getEmail());

		String fileSeparator = System.getProperty("file.separator");

		String HTML = FileToString.convert(
				this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "clienteForm.html", fields);

		out.println(HTML);
	}

	void updateRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ClienteService clienteService = new ClienteService();
		Cliente cliente = new Cliente(Integer.parseInt(request.getParameter("id")), request.getParameter("nome"),
				Long.parseLong(request.getParameter("cpf")), request.getParameter("email"));
		clienteService.update(cliente);
		response.sendRedirect("clienteSearch");

	}
}
