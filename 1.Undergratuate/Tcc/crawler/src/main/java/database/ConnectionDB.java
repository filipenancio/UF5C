// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:52
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionDB.java

package database;

import java.io.PrintStream;
import java.sql.*;

public class ConnectionDB
{

    public ConnectionDB()
    {
    }

    public static Connection getConnection()
    {
        if(staticConn == null)
            try
            {
                Class.forName("org.postgresql.Driver");
                staticConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/swl", "postgres", "swl");
            }
            catch(ClassNotFoundException e)
            {
                System.out.println("Classe n\343o encontrada");
            }
            catch(SQLException e)
            {
                System.out.println((new StringBuilder("Problemas com o BD")).append(e).toString());
            }
        return staticConn;
    }

    public static void close(Statement stmt)
    {
        try
        {
            if(stmt != null)
                stmt.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs)
    {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection1)
    {
    }

    private static Connection staticConn;
}