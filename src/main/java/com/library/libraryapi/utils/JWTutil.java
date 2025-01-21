package com.library.libraryapi.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class JWTutil {
	
	@Value("${jwt.secret}")
	protected String SIGNER_KEY;
	
	public String generateToken(String username) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
		JWTClaimsSet claim = new JWTClaimsSet.Builder()
				.subject(username)
				.issuer("com.library")
				.issueTime(new Date())
				.expirationTime(new Date(
						Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()
						))
				.build();
		Payload payload = new Payload(claim.toJSONObject());
		JWSObject jwsObj = new JWSObject(header, payload);
		try {
			jwsObj.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObj.serialize();
		} catch (JOSEException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public boolean verifyJwt(String token) throws Exception {
        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY);
        return signedJWT.verify(verifier);
    }

    public String getSubject(String token) throws Exception {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }
    
    public boolean isTokenExpired(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            Date expirationTime = claims.getExpirationTime();

            return expirationTime.before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
