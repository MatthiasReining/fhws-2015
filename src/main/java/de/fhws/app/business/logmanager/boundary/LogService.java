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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Matthias Reining
 */
public class LogService {

    DataSource ds;

    public LogService(DataSource ds) {
        this.ds = ds;
    }

    public void log(String message) {
        LogInfo li = new LogInfo();

        li.setMessage(message);
        li.setTs(new Date());

        //save(li);
    }

    @Deprecated
    void save(Object data) {

            //save
        //insert into loginfo ( id , message , ts ) values (1, 'blub', now());
        String sql = "INSERT INTO " + data.getClass().getName() + "VALUES( ";
        String sqlValues = "";
        for (Method m : LogInfo.class.getMethods()) {
            System.out.println(m.getName());
            if (m.getName().startsWith("get")) {

                String attr = m.getName().substring(3);

                try {
                    Object obj = m.invoke(data);
                    //FIXME fertig stellen
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }


        /*Connection connection = ds.getConnection();

         PreparedStatement ps = connection.prepareStatement("insert into loginfo ( id , message , ts ) values ( jdbc_seq.nextval, ?, ?)");

         ps.setString(1, data.getMessage());
         ps.setTimestamp(2, new java.sql.Timestamp(data.getTs().getTime()));

         ps.execute();
         */
            //ds.getConnection().commit();
    }

    public List<LogInfo> findAll() {
        List<LogInfo> liList = new ArrayList<>();

        try {
            PreparedStatement ps = ds.getConnection().prepareCall("SELECT * FROM LOGINFO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LogInfo li = new LogInfo();

                for (Method m : LogInfo.class.getMethods()) {
                    System.out.println(m.getName());
                    if (m.getName().startsWith("set")) {

                        String attr = m.getName().substring(3);
                        Object value = rs.getObject(attr);
                        try {
                            m.invoke(li, value);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                liList.add(li);
            }

            return liList;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

}
