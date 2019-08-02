package dev.filters;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import dev.domains.User;

@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse resp = (HttpServletResponse) servletResponse;

		Cookie[] cookies = req.getCookies();

		User connectedUser = null;
		for (Cookie cookie : cookies) {

			if (cookie.getName().equals("AUTH_TOKEN")) {

				String value = cookie.getValue();

				String tab[] = value.split("\\.");

				String signature = "";
				if (tab.length > 1) {
					signature = tab[1];
				}
				String data2 = tab[0];

				if (signature.contentEquals(new HmacUtils(HmacAlgorithms.HMAC_SHA_512, "SECRET").hmacHex(data2))) {

					System.out.println(value);
					String data = new String(Base64.getUrlDecoder().decode(data2));

					String[] split = data.split(";");
					connectedUser = new User();
					connectedUser.setFirstname(split[0]);
					connectedUser.setFirstname(split[1]);

					req.setAttribute("connectedUser", connectedUser);

				}

			}
		}

		// User connectedUser = (User) req.getSession().getAttribute("connectedUser");

		if (connectedUser != null || req.getRequestURI().contains("/login")) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	public void destroy() {

	}
}
