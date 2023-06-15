package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.var;
import med.voll.api.domain.usuario.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UsuarioRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String tokenJWT = recuperarToken(request);
		System.out.println(tokenJWT);
		if (tokenJWT != null) {
			String subject = tokenService.getSubject(tokenJWT);
			UserDetails usuario = repository.findByLogin(subject);
			Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		// manda para proxima requisição
		filterChain.doFilter(request, response);

	}

	private String recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", ""); // Adicionar um espaço em branco no final da palavra
																// Bearer
		}
		return null;
	}

}
