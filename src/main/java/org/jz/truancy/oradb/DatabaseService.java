package org.jz.truancy.oradb;

import java.util.List;

/**
 *
 * @author sergey.zheznyakovskiy - 2013-11-05
 */
public interface DatabaseService
{

    public List<ObjectType> searchObjectTypes(String keyword);
    
}
