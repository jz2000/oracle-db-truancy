package org.jz.truancy.oradb;

import java.util.List;

/**
 *
 * @author sergey.zheznyakovskiy - 2013-11-07
 */
public interface DbSession
{

    public void validate() throws Exception;

    public List<DbObject> getObjectsForType(String typeName) throws Exception;
    
}
