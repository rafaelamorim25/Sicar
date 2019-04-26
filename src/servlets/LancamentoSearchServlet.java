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
import domain.Lancamento;
import services.ClienteService;
import utils.FileToString;
import utils.RelativePath;

public class LancamentoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendSearchResult(request, response);
	}

	void sendSearchResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ClienteService clienteService = new ClienteService();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Cliente cliente = clienteService.findBy(Integer.parseInt(request.getParameter("id")));

			request.getSession().setAttribute("id_cliente", Integer.parseInt(request.getParameter("id")));
			List<String> fields = new ArrayList<String>();
			fields.add(cliente.getNome());
			fields.add(cliente.getCpf().toString());
			fields.add(cliente.getEmail());

			StringBuilder str = new StringBuilder();

			for (Lancamento lancamento : cliente.getLancamentos()) {
				str.append("<TR>\n");
				str.append("<TD>" + lancamento.getData() + "</TD>\n");
				str.append("<TD>" + lancamento.getValor() + "</TD>\n");
				str.append("<TD>" + lancamento.getTipoLancamento() + "</TD>\n");
				str.append("<TD><A HREF=lancamentoUpdate?id=" + lancamento.getIdLancamento() + "><i class=\"fas fa-edit\"></i></A></TD>\n");
				str.append("<TD><A class=\"text-danger\" HREF=lancamentoDelete?id=" + lancamento.getIdLancamento() + "><i class=\"fas fa-trash-alt\"></i></A></TD>\n");
				str.append("</TR>");
			}

			str.append("<TR><TD>Saldo:</TD><TD>" + cliente.saldo() + "</TD><TD></TD><TD></TD><TD></TD></TR>");

			fields.add(str.toString());
			String HTML = FileToString.convert(RelativePath.fileName(this, "lancamentos.html"), fields);
			out.println(HTML);

	}
}
