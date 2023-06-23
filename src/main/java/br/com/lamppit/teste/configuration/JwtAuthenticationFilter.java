package br.com.lamppit.teste.configuration;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter /*extends OncePerRequestFilter*/ {

//    @Autowired
//    private JwtUtilities jwtUtilities;
//
////    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws IOException, ServletException {
//
//        String token = jwtUtilities.getToken(request);
//
//        if (token != null && jwtUtilities.validateToken(token)) {
//            UserLoginDto user = null;
//
//            try {
//                user = jwtUtilities.extractUserLoginDto(token);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            if (user != null) {
//
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        user.getUser().getEmail(),
//                        token);
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }

}
