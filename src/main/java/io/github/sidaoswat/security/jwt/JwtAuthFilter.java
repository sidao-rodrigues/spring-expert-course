package io.github.sidaoswat.security.jwt;

import io.github.sidaoswat.service.impl.UsuarioServiceImp;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    //poderia utililizar anotations também
    private JwtService jwtService;
    private UsuarioServiceImp usuarioServiceImp;

    public JwtAuthFilter(JwtService jwtService, UsuarioServiceImp usuarioServiceImp){
        this.jwtService = jwtService;
        this.usuarioServiceImp = usuarioServiceImp;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");


        if(authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValido(token);
            System.out.println("is valid? "+isValid);
            if(isValid){
                String loginUsuario = jwtService.obterLoginUsuario(token);
                UserDetails usuario = usuarioServiceImp.loadUserByUsername(loginUsuario);
                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
