package io.github.sidaoswat.config;

import io.github.sidaoswat.service.impl.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UsuarioServiceImp usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//gera um hash sempre diferente mesmo para senha iguais
    }
        //Essa parte serve para caso eu queria implementar minha propria forma de criptografia
        /*PasswordEncoder passwordEncoder = new PasswordEncoder() {

            @Override
            public String encode(CharSequence charSequence) {
                return null;
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return false;
            }
        }*/
    //}

    /*metodo para configuração de autenticação*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());

        /*deixando usuário em memória*/
        /*auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("sidao")
                .password(passwordEncoder().encode("123"))
                .roles("USER", "ADMIN");*/
    }

    /*metodo para configração de acesso as rotas do usuário autenticado*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //o csrf serve para que haja um segurança em backend e frontend
                .authorizeRequests()
                .antMatchers("/api/clientes/**")//antMatchers possibilita vários tipos de acesso
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/pedidos/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/produtos/**")
                    .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/usuarios/**")
                    .permitAll()
                .anyRequest().authenticated()
                .and()
                    //.formLogin();//utiliza já a tela criada, ou então pode-se criar já um formulário de login como parametro "/login.html"
                    .httpBasic(); //passa as informaçãoes por requisições headers

    }
}
