package org.jz.truancy.oradb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbcp.BasicDataSource;
/**
 *
 * @author sergey.zheznyakovskiy - 2013-11-07
 */
public class OracleDbSession implements DbSession
{
    private static final String VALIDATION_SQL = "select 1 from dual";
    private final BasicDataSource dataSource = new BasicDataSource();
    private final String alias;

    public OracleDbSession(
            String alias, 
            String url, 
            String userName, 
            String password)
    {
        this.alias = alias;
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
    }

    @Override
    public void validate() throws Exception
    {
        try (Connection connection = dataSource.getConnection()) 
        {
            try (CallableStatement stmt = connection.prepareCall(VALIDATION_SQL)) 
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    rs.next();
                }
            }
        }
    }

    @Override
    public List<DbObject> getObjectsForType(String typeName) throws SQLException
    {
        String QUERY = "select * from ALL_TABLES";
        List<DbObject> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) 
        {
            try (CallableStatement stmt = connection.prepareCall(QUERY)) 
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next()) 
                    {
                        DbObject object = new DbObject();
                        object.setType("TABLE");
                        object.setName(rs.getString("owner"));
                        object.setComment("Just read");
                        result.add(object);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<DbObject> searchObjects(String typeName, String keyword) throws Exception
    {
        if (keyword == null || keyword.isEmpty()) 
        {
            return getObjectsForType(typeName);
        }
        else 
        {
            String QUERY = "select * from ALL_TABLES";
            List<DbObject> result = new ArrayList<>();
            try (Connection connection = dataSource.getConnection()) 
            {
                try (CallableStatement stmt = connection.prepareCall(QUERY)) 
                {
                    try (ResultSet rs = stmt.executeQuery())
                    {
                        while (rs.next()) 
                        {
                            DbObject object = new DbObject();
                            object.setType("TABLE");
                            object.setName(rs.getString("owner"));
                            object.setComment("Just read");
                            result.add(object);
                        }
                    }
                }
            }
            return result;
        }
    }
}
