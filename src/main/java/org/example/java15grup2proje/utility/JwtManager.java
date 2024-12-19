package org.example.java15grup2proje.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {
	@Value("${java15grup2proje.jwt.secret-key}")
	private String secretKey;
	@Value("${java15grup2proje.jwt.issuer}")
	private String issuer;
	private final Long exDate=1000L*60;
	
	public String createToken(String authId, String userType){
		Date createdDate=new Date(System.currentTimeMillis());
		Date expirationDate=new Date(System.currentTimeMillis()+exDate);
		Algorithm algorithm=Algorithm.HMAC512(secretKey);
		String token = JWT.create()
		                  .withAudience()
		                  .withIssuer(issuer)
		                  .withIssuedAt(createdDate)
		                  .withExpiresAt(expirationDate)
		                  .withClaim("authId", authId)
		                  .withClaim("key","JX_15_TJJJ")
						  .withClaim("role",userType)
		                  .sign(algorithm);
		return token;
	}
	
	public Optional<Long> validateToken(String token){
		try{
			Algorithm algorithm=Algorithm.HMAC512(secretKey);
			JWTVerifier verifier=JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(token);
			if (Objects.isNull(decodedJWT))
				return Optional.empty();
			Long authId = decodedJWT.getClaim("authId").asLong();
			return Optional.of(authId);
		}
		catch (Exception exception) {
			return Optional.empty();
		}

	}
}