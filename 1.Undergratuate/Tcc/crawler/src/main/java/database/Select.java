// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:53
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Select.java

package database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

// Referenced classes of package database:
//            ConnectionDB

public class Select
{

    public Select()
    {
    }

    protected static List selectListOf(String sql)
    {
        Connection connection;
        LinkedList lista;
        PreparedStatement stmt;
        ResultSet rs;
        if(sql.endsWith(";"))
            sql = sql.substring(0, sql.length() - 1);
        connection = null;
        lista = new LinkedList();
        stmt = null;
        rs = null;
        try
        {
            connection = ConnectionDB.getConnection();
            stmt = connection.prepareStatement(sql);
            for(rs = stmt.executeQuery(); rs.next(); lista.addLast(rs.getString(1)));
            break MISSING_BLOCK_LABEL_125;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConnectionDB.close(rs);
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        break MISSING_BLOCK_LABEL_138;
        Exception exception;
        exception;
        ConnectionDB.close(rs);
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        throw exception;
        ConnectionDB.close(rs);
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        return lista;
    }

    protected static String[][] getMatrizOf(String sql)
    {
        Connection connection;
        LinkedList lista;
        PreparedStatement stmt;
        ResultSet rs;
        if(sql.endsWith(";"))
            sql = sql.substring(0, sql.length() - 1);
        connection = null;
        lista = new LinkedList();
        stmt = null;
        rs = null;
        try
        {
            connection = ConnectionDB.getConnection();
            stmt = connection.prepareStatement(sql);
            String registro[];
            for(rs = stmt.executeQuery(); rs.next(); lista.addLast(registro))
            {
                ResultSetMetaData rsmd = rs.getMetaData();
                int numeroColunas = rsmd.getColumnCount();
                registro = new String[numeroColunas];
                for(int i = 0; i < registro.length; i++)
                    registro[i] = rs.getString(i + 1);

            }

            break MISSING_BLOCK_LABEL_177;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConnectionDB.close(rs);
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        break MISSING_BLOCK_LABEL_190;
        Exception exception;
        exception;
        ConnectionDB.close(rs);
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        throw exception;
        ConnectionDB.close(rs);
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        return (String[][])lista.toArray(new String[lista.size()][]);
    }

    protected static String getValor(String sql)
    {
        Connection connection;
        String sRet;
        PreparedStatement stmt;
        ResultSet rs;
        connection = null;
        sRet = null;
        stmt = null;
        rs = null;
        try
        {
            connection = ConnectionDB.getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.next();
            sRet = rs.getString(1);
            break MISSING_BLOCK_LABEL_90;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConnectionDB.close(rs);
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        break MISSING_BLOCK_LABEL_103;
        Exception exception;
        exception;
        ConnectionDB.close(rs);
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        throw exception;
        ConnectionDB.close(rs);
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        return sRet;
    }
}