/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.logmanager.boundary;

import de.fhws.app.business.logmanager.entity.LogInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author Matthias Reining
 */
@Stateless
public class LogService {

    @PersistenceContext
    EntityManager em;

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
        li.setTs(new Date());

        em.persist(li);

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
