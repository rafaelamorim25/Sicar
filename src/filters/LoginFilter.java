package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String login = (String) ((HttpServletRequest) request).getSession().getAttribute("loggedIn");

		if (login != null && Boolean.parseBoolean(login)) {
			System.out.println("logado");
			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
				((HttpServletResponse) response).sendRedirect("clienteSearch");
			}
		} else {
			System.out.println("não logado");
			((HttpServletResponse) response).sendRedirect("login");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
	}

}
