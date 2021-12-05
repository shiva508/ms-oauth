package com.pool.restclient;

import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.pool.model.User;
import com.pool.model.VerifyPasswordResponse;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface UserApiClient {

	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	User getUser(@PathParam("username") String username);

	@POST
	@Path("/{username}/verify-password")
	@Produces(MediaType.APPLICATION_JSON)
	public VerifyPasswordResponse verifyUserPassword(@PathParam("username") String username,
			String password);
}
