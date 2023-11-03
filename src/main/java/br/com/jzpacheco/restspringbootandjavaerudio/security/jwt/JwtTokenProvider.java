package br.com.jzpacheco.restspringbootandjavaerudio.security.jwt;

import br.com.jzpacheco.restspringbootandjavaerudio.exceptions.InvalidJwtAuthenticationException;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.security.TokenVO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMillisecods = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init(){

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        System.out.println("secretKey after: "+secretKey);
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createAcessToken(String username, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMillisecods);
        var acessToken = getAcessToken( username, roles, now, validity);
        var refreshToken = getRefreshToken( username, roles, now);

        return new TokenVO(username, true, now, validity, acessToken, refreshToken);
    }

    private String getAcessToken(String username, List<String> roles, Date now, Date validity) {
        String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        System.out.println("secretKey before: "+secretKey);
        System.out.println("algorithm: "+algorithm);
        return JWT.create()
                .withClaim("roles",roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issueUrl)
                .sign(algorithm)
                .strip();
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken =  new Date( now.getTime() + (validityInMillisecods * 3));
        return JWT.create()
                .withClaim("roles",roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .strip();
    }

    public Authentication getAuthentication (String token){
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();

        return verifier.verify(token);
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        try{
            if (decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
