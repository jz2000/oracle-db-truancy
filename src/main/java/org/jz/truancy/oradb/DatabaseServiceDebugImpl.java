package org.jz.truancy.oradb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sergey.zheznyakovskiy - 2013-11-05
 */
public class DatabaseServiceDebugImpl implements DatabaseService
{
    
    private List<ObjectType> knownObjectTypes = Arrays.asList(
            new ObjectType("Table", "TABLE"),
            new ObjectType("View", "VIEW"),
            new ObjectType("Sequence", "SEQUENCE"),
            new ObjectType("Procedure", "PROCEDURE"),
            new ObjectType("Function", "FUNCTION"),
            new ObjectType("Package", "PACKAGE"),
            new ObjectType("Package Body", "PACKAGE_BODY"),
            new ObjectType("Index", "INDEX"),
            new ObjectType("Constraint", "CONSTRAINT"),
            new ObjectType("Any", "%"));

    public DatabaseServiceDebugImpl()
    {
    }

    @Override
    public List<ObjectType> searchObjectTypes(String keyword)
    {
        if ((keyword == null) || keyword.trim().isEmpty())
        {
            return new ArrayList<>(knownObjectTypes);
        } 
        else
        {
            List<ObjectType> result = new ArrayList<>();
            for (ObjectType objectType : knownObjectTypes) 
            {
                if (objectType.getTypeLabel().toLowerCase().contains(keyword.toLowerCase()))
                {
                    result.add(objectType);
                }
            }
            return result;
        }
    }
    
}
