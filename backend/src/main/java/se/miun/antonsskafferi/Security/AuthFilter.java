package se.miun.antonsskafferi.Security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.ws.rs.NameBinding;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        try {
            String authHeaderVal = requestContext.getHeaderString("Authorization");

            //consume JWT i.e. execute signature validation
            if (authHeaderVal != null && authHeaderVal.startsWith("Bearer")) {
                //authorisation
                String[] token_string = authHeaderVal.split(" ");
                String jwt_token = token_string[1];

                try {
                    Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("qwertyuiopasdfghjklzxcvbnm123456")).parseClaimsJws(jwt_token).getSignature();
                } catch (Exception e) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build()); // no signing key on client. We trust that this JWT came from the server and has been verified there
                }
            } else {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (NullPointerException npe) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
