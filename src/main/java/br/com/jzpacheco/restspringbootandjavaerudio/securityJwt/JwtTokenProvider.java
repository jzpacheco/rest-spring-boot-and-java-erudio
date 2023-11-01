package br.com.jzpacheco.restspringbootandjavaerudio.securityJwt;

import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.security.TokenVO;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    @Value("${security.jwt.token.expire-lenght:3600000}")
    private long validityInMillisecods = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createAcessToken(String username, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMillisecods);
        var acessToken = getAcessToken( username, roles, now, validity);
        var refreshToken = getAcessToken( username, roles, now);


        return new TokenVO(username, true, now, validity, acessToken, refreshToken);
    }

    private String getAcessToken(String username, List<String> roles, Date now) {
    }

    private String getAcessToken(String username, List<String> roles, Date now, Date validity) {

    }
}
