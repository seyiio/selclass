package com.fkczxt.selclassserver.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private static  final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("{jwt.expiration}")
    private String expiration;

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    public String getUsernameFromToken(String token){
        String username="";
        try{
            Claims claims =getClaimsFormToken(token);
            username=claims.getSubject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return username;
    }
    public  boolean validateToken(String token,UserDetails userDetails){
        String username= getUsernameFromToken(token);
        return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        Date expireDate=getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }
    private Date getExpiredDateFromToken(String token){

            Claims claims =getClaimsFormToken(token);

        return claims.getExpiration();

    }
    public  Claims getClaimsFormToken(String token){
        Claims claims=null;
        try{
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  claims;
    }
    public  boolean canRefresh(String token){
        return !isTokenExpired(token);
    }
    public String refreshToken(String token){
        Claims claims =getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    private  String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+604800*1000);
    }
}
