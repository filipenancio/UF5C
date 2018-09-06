// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:53
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Insert.java

package database;

import java.sql.*;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package database:
//            ConnectionDB

public class Insert
{

    public Insert()
    {
    }

    protected static void doit(List sqls)
    {
        Connection connection;
        Statement stmt;
        connection = null;
        stmt = null;
        try
        {
            connection = ConnectionDB.getConnection();
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            String sql;
            for(Iterator iterator = sqls.iterator(); iterator.hasNext(); stmt.execute(sql))
            {
                sql = (String)iterator.next();
                if(sql.endsWith(";"))
                    sql = sql.substring(0, sql.length() - 1);
            }

            connection.commit();
            break MISSING_BLOCK_LABEL_121;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        break MISSING_BLOCK_LABEL_129;
        Exception exception;
        exception;
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
        throw exception;
        ConnectionDB.close(stmt);
        ConnectionDB.close(connection);
    }
}