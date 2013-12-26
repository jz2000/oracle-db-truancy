package org.jz.orahle;



import java.util.List;

/**
 *
 * @author sergey.zheznyakovskiy - 2013-11-07
 */
public interface DbSession
{

    public void validate() throws Exception;

    public List<DbObject> getObjectsForType(String typeName) throws Exception;

    public List<DbObject> searchObjects(String typeName, String keyword) throws Exception;

    public List<DbObjectType> getObjectTypes();
}
