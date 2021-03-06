package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Lancamento;
import services.LancamentosService;
import utils.FileToString;
import utils.RelativePath;

public class LancamentoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LancamentosService lancamentosService = new LancamentosService();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Lancamento lancamento = lancamentosService.findBy(Integer.parseInt(request.getParameter("id")));
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		if(lancamento != null) {
			
			List<String> fields = new ArrayList<String>();
			
			fields.add(format.format(lancamento.getData()));
			fields.add(lancamento.getTipoLancamento().toString());
			fields.add(lancamento.getValor().toString());
			fields.add(request.getRequestURI());
			fields.add(request.getParameter("id"));
			
			String HTML = FileToString.convert(RelativePath.fileName(this, "lancamentoDelete.html"), fields);

			out.println(HTML);
			
		}else {
			response.sendRedirect("clienteSearch");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			LancamentosService lancamentosService = new LancamentosService();
			
			lancamentosService.delete(Integer.parseInt(req.getParameter("id")));
			
			resp.sendRedirect("Mensagem?acao=delete&domain=Lancamento&classe=success");
		}catch (Exception e) {
			resp.sendRedirect("Mensagem?acao=delete&domain=Lancamento&classe=danger");
		}
		
	}
}
