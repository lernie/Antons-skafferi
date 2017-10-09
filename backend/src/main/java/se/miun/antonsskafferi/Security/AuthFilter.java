package se.miun.antonsskafferi.Security;


import io.jsonwebtoken.Claims;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;


public class AuthFilter implements ContainerRequestFilter {

    public void filter(ContainerRequestContext requestContext) {
        try {
            String authHeaderVal = requestContext.getHeaderString("Authorization");

            //consume JWT i.e. execute signature validation
            if (authHeaderVal != null && authHeaderVal.startsWith("Bearer")) {
                //authorisation
                Claims userObject = createJWT.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTA3MjM1MTAwLCJzdWIiOiJIQUhBIiwiaXNzIjoiQW50b25za2FmZmVyaSIsImV4cCI6MTUwNzIzNTIwMH0.MVA39fXyX9RYJd4lz5wokZSMhAdY_T9G4iAuEwrUQPA");
                requestContext.abortWith(Response.accepted().build());

            } else {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (NullPointerException npe) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
