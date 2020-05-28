package qj.admin.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
	
	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    public static final Integer JWT_EXPIRE = 1000*60*30;

    public static final String AUTH_HEADER = "Authorization";

    public static final String JWT_ID = "ozg";

    private static final String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmM3wrOX25R1pWBO0QIdWf/HF7+U2re9cNsdKoNR+TNx/zPJgYhKHlTE5ijqRyTtm31lVY9LA2gGIhMGUugwDr4vqcyyWHtBNDPhOr/SsOPDlupVTiz4ju3m6ajZZxxYLzLNfJ3pTsaijdIPW8A0npqkZdyUCHmqMMIsNYY2EesCDWKNxtaGCAKNxVc0lH2IAbtg5Ts54ujliFBv7VbuBtuiKJlOsd66LB4pYRqIo8/qSA8jAKuO6HHDMb5AyVoCRY8oxlnKJzXHr/AeVqdtpwJ8KG57/pVLpN9k+QFZM4MxOTtIhm8ODE26Ifvx+HJF0v4LqY9Jl/zTZGGcEVkgN8wIDAQAB";

    public static String creatJwt(String id, String subject, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("authentication", "ROLE_USER");
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(generalKey(),SignatureAlgorithm.HS256);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            log.info("token过期时间为:{}",exp);
            //设置过期时间
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public static Claims parseJwt(String jwt) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(jwt)
                .getBody();

        return claims;
    }

    public static boolean isTokenExpired(Date expiprationTime) {
        return expiprationTime.before(new Date());
    }

    public static SecretKey generalKey(){
        String stringKey = key;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
        return secretKey;
    }

    public static String getSubject(HttpServletRequest request){
        String header = request.getHeader(JwtUtil.AUTH_HEADER);
        try {
            Claims claims = parseJwt(header);
            return claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
