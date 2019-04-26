package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import services.ClienteService;
import utils.FileToString;
import utils.RelativePath;

public class ClienteSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendSearchResult(response);
	}

	void sendSearchResult(HttpServletResponse response) throws IOException {
		ClienteService clienteService = new ClienteService();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		List<Cliente> clientes = clienteService.findAll();
		StringBuilder str = new StringBuilder();

		for (Cliente cliente : clientes) {

			str.append("<TR>\n");
			str.append("<TD>" + cliente.getNome() + "</TD>\n");
			str.append("<TD>" + cliente.getCpf() + "</TD>\n");
			str.append("<TD>" + cliente.getEmail() + "</TD>\n");
			str.append("<TD>" + cliente.saldo() + "</TD>\n");
			str.append("<TD class=\"text-center\"><a class=\"text-secondary text-center\" HREF=lancamentoSearch?id="
					+ cliente.getId() + "><i class=\"fas fa-list-ul\"></i></a></TD>\n");
			str.append("<TD class=\"text-center\"><a text-center HREF=clienteUpdate?id=" + cliente.getId()
					+ "><i class=\"fas fa-edit\"></i></a></TD>\n");
			str.append("<TD class=\"text-center\"><a class=\"text-danger\" HREF=deleteCliente?id=" + cliente.getId()
					+ "><i class=\"fas fa-trash-alt\"></i></a></TD>\n");
			str.append("</TR>\n");
		}

		String HTML = FileToString.convert(RelativePath.fileName(this, "clientes.html"), str.toString());

		out.println(HTML);
	}
}
