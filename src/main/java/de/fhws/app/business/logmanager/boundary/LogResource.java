/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.logmanager.boundary;

import de.fhws.app.business.logmanager.entity.LogInfo;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Matthias Reining
 */
@Path("logs")
@Stateless
public class LogResource {

    @Inject
    LogService logService;

    @GET
    public List<LogInfo> allLogs(@QueryParam("limit") @DefaultValue("100") int limit) {
        System.out.println("limit:" + limit);
        List<LogInfo> logInfoList = logService.findAll();
        if (limit < logInfoList.size())
            return logInfoList.subList(0, limit);
        return logInfoList;
    }

    @GET
    @Path("{id}")
    public LogInfo getLogInfo(@PathParam("id") long id) {
        return logService.getLogInfoById(id);
    }

    @POST
    public Response createLogInfo(LogInfo logInfo) {

        logInfo = logService.createOrUpdate(logInfo);
        
        return Response
                .status(Response.Status.CREATED)
                .entity(logInfo)
                .build();
    }

}
