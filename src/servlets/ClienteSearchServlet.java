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
import utils.Cpf;
import utils.FileToString;
import utils.RelativePath;

public class ClienteSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendSearchResult(request, response, "");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("palavraChave");
		sendSearchResult(request, response, keyword);
	}

	void sendSearchResult(HttpServletRequest request, HttpServletResponse response, String keyword) throws IOException {
		ClienteService clienteService = new ClienteService();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<String> fields = new ArrayList<String>();
		fields.add(request.getRequestURI());

		List<Cliente> clientes = clienteService.customSearch(keyword);
		StringBuilder str = new StringBuilder();

		for (Cliente cliente : clientes) {

			str.append("<TR>\n");
			str.append("<TD>" + cliente.getNome() + "</TD>\n");
			str.append("<TD>" + Cpf.format(cliente.getCpf()) + "</TD>\n");
			str.append("<TD>" + cliente.getEmail() + "</TD>\n");
			str.append("<TD>R$" + cliente.saldo() + "</TD>\n");
			str.append("<TD class=\"text-center\"><a class=\"text-secondary text-center\" HREF=lancamentoSearch?id="
					+ cliente.getId() + "><i class=\"fas fa-list-ul\"></i></a></TD>\n");
			str.append("<TD class=\"text-center\"><a text-center HREF=clienteUpdate?id=" + cliente.getId()
					+ "><i class=\"fas fa-edit\"></i></a></TD>\n");
			str.append("<TD class=\"text-center\"><a class=\"text-danger\" HREF=deleteCliente?id=" + cliente.getId()
					+ "><i class=\"fas fa-trash-alt\"></i></a></TD>\n");
			str.append("</TR>\n");
		}
		
		fields.add(str.toString());

		String HTML = FileToString.convert(RelativePath.fileName(this, "clientes.html"), fields);

		out.println(HTML);
	}
}
