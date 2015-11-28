/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.logmanager.boundary;

import de.fhws.app.business.logmanager.entity.LogInfo;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Matthias Reining
 */
@Stateless
public class LogService {

    @PersistenceContext
    EntityManager em;

    @Inject
    LogService ls;

    public void manageLogEventsAtDatabase(@Observes @LogEvent String message) {
        ls.log(message);

    }

    public LogInfo getLogInfoById(long id) {
        return em.find(LogInfo.class, id);
    }

    public LogInfo createOrUpdate(LogInfo logInfo) {
        logInfo.setTs(new Date());
        return em.merge(logInfo);
    }

    @Asynchronous
    public void log(String message) {

        //simulate heavy work
        System.out.println("im log service");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
        }

        LogInfo li = new LogInfo();

        li.setMessage(message);
        createOrUpdate(li);

        System.out.println("ende log service");
    }

    @Asynchronous
    public Future<List<LogInfo>> findAllAsync() {

        List<LogInfo> list = em.createNamedQuery(LogInfo.findAll, LogInfo.class).getResultList();

        System.out.println(list.size());
        //simulate heavy work
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new AsyncResult(list);
    }

    public List<LogInfo> findAll() {
        return em.createNamedQuery(LogInfo.findAll, LogInfo.class).getResultList();
    }
}
