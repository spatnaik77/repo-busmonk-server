package com.busmonk.security;

import com.busmonk.service.bo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sr250345 on 10/31/16.
 */
public class JWTUtil {

    public static String SIGNING_KEY = "abcde0123456789";

    private static String issuer = "www.busmonk.com";
    private static long ttlMillis = -1; //token is valid for ever

    private static String EMAIL  = "EMAIL";
    private static String MOBILE = "MOBILE";
    private static String ID     = "ID";

    public static String createJWT(User u)
    {
        String id = System.currentTimeMillis() + "";
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Key signingKey = getSigningKey();

        JwtBuilder builder = Jwts.builder();
        builder.setId(id);
        builder.setIssuedAt(now);
        builder.setIssuer(issuer);
        builder.signWith(SignatureAlgorithm.HS256, signingKey);
        //if it has been specified, let's add the expiration
        /*if (ttlMillis >= 0)
        {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }*/

        builder.setSubject(u.getId());
        builder.claim(MOBILE, u.getMobile());
        builder.claim(EMAIL, u.getEmail());

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

     public static User parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SIGNING_KEY))
                .parseClaimsJws(jwt).getBody();

        System.out.println("ID: " + claims.getId());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());

        System.out.println("Subject: " + claims.getSubject());
        System.out.println("MOB: " + claims.get("MOB"));

         User u = new User();
         u.setId(claims.getSubject());
         Object oMobile = claims.get(MOBILE);
         u.setMobile(Long.parseLong(oMobile.toString()));
         u.setEmail(claims.get(EMAIL).toString());


        return u;
    }
    private static Key getSigningKey()
    {
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SIGNING_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
        return signingKey;

    }

    public static void main(String[] args)
    {
        long ttl = 2592000000l;
        User u = new User();
        u.setId("12345");
        u.setMobile(9902354300l);
        u.setEmail("spatnaik77@gmail.com");
        String token = createJWT(u);
        token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDgwMzUwMzg3NzMxIiwiaWF0IjoxNDgwMzUwMzg3LCJpc3MiOiJ3d3cuYnVzbW9uay5jb20iLCJzdWIiOiIxNDgwMzQ3OTgwMzIxIiwiTU9CSUxFIjoxMTExMTExMTExLCJFTUFJTCI6ImpkamRra3NscyJ9.HrcOkEyQOShlYpcXvZ7pqlYM5kUcfOjQ0HrPg_IyH6g";
        User u1 = parseJWT(token);
        System.out.print(u1);
    	
    	
    	//String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDgwMzQ5NzcyNTI4IiwiaWF0IjoxNDgwMzQ5NzcyLCJpc3MiOiJ3d3cuYnVzbW9uay5jb20iLCJzdWIiOiIxNDgwMzQ4NjE5ODg5IiwiTU9CSUxFIjoyMjIyMjIyMjIyLCJFTUFJTCI6ImZmZ2gifQ";
    	//parseJWT(token);
    }
}
