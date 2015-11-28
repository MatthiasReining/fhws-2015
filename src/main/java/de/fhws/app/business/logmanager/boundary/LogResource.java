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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Matthias Reining
 */
@Path("logs")
@Stateless
public class LogResource {

    @Inject
    LogService logService;

    @PersistenceContext
    EntityManager em;

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

        System.out.println("-> id: " + id);
        LogInfo logInfo = em.find(LogInfo.class, id);
        return logInfo;
    }

}
