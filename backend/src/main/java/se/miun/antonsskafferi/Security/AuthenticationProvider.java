package se.miun.antonsskafferi.Security;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

public class AuthenticationProvider {

    //Sample method to construct a JWT
    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("qwertyuiopasdfghjklzxcvbnm123456");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer).signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    //Sample method to validate and read the JWT
    public static Claims parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Claims signature = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary("qwertyuiopasdfghjklzxcvbnm123456"))
                    .parseClaimsJws(jwt).getBody();

            return signature;
        }catch (JwtException e){
            throw e;
        }

    }
}


