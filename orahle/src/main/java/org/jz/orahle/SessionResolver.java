package org.jz.orahle;

import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jz
 */
public class SessionResolver
{
    private String dbAlias;
    private Map<String, DbSession> sessions;

    public String getDbAlias()
    {
        return dbAlias;
    }

    public void setDbAlias(String dbAlias)
    {
        this.dbAlias = dbAlias;
    }

    public Map<String, DbSession> getSessions()
    {
        return sessions;
    }

    public void setSessions(Map<String, DbSession> sessions)
    {
        this.sessions = sessions;
    }
    
    public DbSession getDbSession() 
    {
        DbSession dbSession = sessions.get(dbAlias);
        return dbSession;
    }
}
