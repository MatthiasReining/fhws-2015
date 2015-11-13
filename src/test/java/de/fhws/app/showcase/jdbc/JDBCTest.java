package de.fhws.app.showcase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static junit.framework.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Intialier SQL Befehl:
 * <pre>
 * DROP TABLE IF EXISTS TEST;
 * CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));
 * INSERT INTO TEST VALUES(1, 'Hello');
 * INSERT INTO TEST VALUES(2, 'World');
 * SELECT * FROM TEST ORDER BY ID;
 * UPDATE TEST SET NAME='Hi' WHERE ID=1;
 * DELETE FROM TEST WHERE ID=2;
 * </pre>
 *
 * @author Matthias Reining
 */
@Ignore
public class JDBCTest {

    private static final String dbUrl = "jdbc:h2:tcp://localhost/D:/fhws/2015/srv/h2/databases/fhws-db";

    private Connection connection;

    @Before
    public void init() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(dbUrl, "sa", "sa");
        connection.setAutoCommit(false);
    }

    @After
    public void closeConnection() throws SQLException {
        if (!connection.isClosed())
            connection.close();
    }

    @Test
    public void shouldConnect() throws ClassNotFoundException, SQLException {
        assertTrue(connection.isValid(3));
    }

    @Test
    public void shouldSelectTestTable() throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST order by id");
        rs.next();

        assertEquals(1l, rs.getLong(1));
        assertEquals("Hi", rs.getString(2));

    }

    @Test
    public void shouldSelectTestTableWithWhereClause() throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST where id = 2");
        rs.next();

        assertEquals(2l, rs.getLong(1));
        assertEquals("FHWS", rs.getString(2));
    }

    @Test
    public void shouldSelectTestTableWithParameter() throws SQLException {

        String name = "FHWS";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST where name = '" + name + "'");
        rs.next();

        assertEquals(2l, rs.getLong(1));
        assertEquals("FHWS", rs.getString(2));
    }

    //@Test <-- do not execute
    public void doSQLInjection() throws SQLException {

        String name = "FHWS'; DELETE FROM TEST where name != 'xxx";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST where name = '" + name + "'");
        rs.next();

        assertEquals(2l, rs.getLong(1));
        assertEquals("FHWS", rs.getString(2));
    }

    @Test
    public void selectTableTestWithPreparedStatement() throws SQLException {

        String name = "FHWS";

        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM TEST where name = ?");
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        assertEquals(2l, rs.getLong(1));
        assertEquals("FHWS", rs.getString(2));
    }

    @Test
    public void shouldInsertData() throws SQLException {

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TEST(id, name) VALUES(?,?)");
        pstmt.setLong(1, 3);
        pstmt.setString(2, "Hello World!");
        pstmt.execute();

        connection.commit();

    }

    @Test
    public void shouldNotInsertData() throws SQLException {

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TEST(id, name) VALUES(?,?)");
        pstmt.setLong(1, 4);
        pstmt.setString(2, "Hallo Welt!");
        pstmt.execute();

        System.out.println("hat geklappt :-)" + 5 / 0);

        connection.commit();

    }

    @Test
    public void shouldDoRollback() throws SQLException {

        int count = 0;

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TEST(id, name) VALUES(?,?)");
        pstmt.setLong(1, 4);
        pstmt.setString(2, "Hallo Welt!");
        pstmt.execute();
        count++;

        pstmt.setLong(1, 5);
        pstmt.setString(2, "Hallo Du!");
        pstmt.execute();
        count++;

        if (count < 10)
            connection.rollback();

        connection.commit();

    }

    @Test
    public void shouldDoInOneTransaction() throws SQLException {

        int count = 0;

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TEST(id, name) VALUES(?,?)");
        pstmt.setLong(1, 4);
        pstmt.setString(2, "Hallo Welt!");
        pstmt.execute();
        count++;

        //set breakpoint
        
        pstmt.setLong(1, 5);
        pstmt.setString(2, "Hallo Du!");
        pstmt.execute();
        count++;

        connection.commit();

    }
}
