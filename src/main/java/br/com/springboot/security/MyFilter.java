package br.com.springboot.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.springboot.modules.user.dto.ErrorDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // acesso o cabeçalho
    if (request.getHeader("Authorization") != null) {
      // o cabeçalho é recuperado
      Authentication auth = TokenUtil.decodeToken(request);

      // se a validação ocorre tudo certo ele adiciona as informações retornadas no
      // contexto da requisição
      // permitindo assim que o security spring boot consiga validar as autorizações
      // do usuário caso possua ou não
      // e permite ou não o acesso ao recurso acessado
      if (auth != null) {
        SecurityContextHolder.getContext().setAuthentication(auth);
      } else {

        // caso o contrário ele retorna erro de permissão de acesso, token inválido
        ErrorDTO erro = new ErrorDTO(401, "Usuario nao autorizado!");
        response.setStatus(erro.getStatus());
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().print(mapper.writeValueAsString(erro));
        response.getWriter().flush();
        return;
      }
    }

    // passa a requisição pra frente
    filterChain.doFilter(request, response);
  }

}
