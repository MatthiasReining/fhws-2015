/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.usermanagement.boundary;

import de.fhws.app.business.usermanagement.entity.AppUser;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserManagementService ums;

    @GET
    @Path("{id}")
    public AppUser findById(@PathParam("id") long id) {
        AppUser user = ums.findUserById(id);
        return user;
    }

    @GET
    @Path("dummy")
    public Response produceDummyJson() {

        JsonObject result = Json.createObjectBuilder()
                .add("firstname", "Mickey")
                .add("lastname", "Mouse")
                .add("list", Json.createArrayBuilder()
                        .add(5)
                        .add(3)
                        .add(6))
                .build();

        return Response.ok(result).build();
    }
}
