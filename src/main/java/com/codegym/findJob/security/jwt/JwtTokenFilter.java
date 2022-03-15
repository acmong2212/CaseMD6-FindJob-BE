package com.codegym.findJob.security.jwt;

import com.codegym.findJob.repository.IUserRepo;
import com.codegym.findJob.security.userprinciple.InfoPriciple;
import com.codegym.findJob.service.ICompanyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    private IUserRepo userRepo;

    private JwtProvider jwtProvider;

    private ICompanyService companyService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        InfoPriciple principle = new InfoPriciple();
        try {
            String token = getJwt(request);
            if (token != null && jwtProvider.validateToken(token)) {
                List<GrantedAuthority> authorities = new ArrayList<>();

                Map<String, Object> claims  = jwtProvider.getClaims(token);
                if((boolean)claims.get("isCompany")){
                    authorities.add(new SimpleGrantedAuthority("ROLE_COMPANY"));
                    principle.setCompanyId(Long.parseLong(claims.get("COMPANY_ID").toString()));
                    principle.setCompany(companyService.findById(principle.getCompanyId()).get());
                    //@TOdo setname, email
                }else {
                    //@Todo set userId, name, email
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    principle.setUserId(Long.parseLong(claims.get("USER_ID").toString()));
                    principle.setUser(userRepo.findById(principle.getUserId()).get());
                }
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        principle, null, authorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authenticationToken);
            }
        }catch (Exception e) {
                logger.error("Can't set user authentication");
        }
        filterChain.doFilter(request,response);
    }

    private String getJwt (HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
