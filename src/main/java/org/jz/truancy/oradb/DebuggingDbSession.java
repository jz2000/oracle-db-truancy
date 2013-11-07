package org.jz.truancy.oradb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sergey.zheznyakovskiy - 2013-11-07
 */
public class DebuggingDbSession implements DbSession
{
    private final String alias;
    private final String userName;
    private final String password;
    
    private final Map<String, List<DbObject>> objectsMap = new HashMap<>();
    
    public DebuggingDbSession(
            String alias, 
            String url, 
            String userName, 
            String password)
    {
        this.alias = alias;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void validate() throws Exception
    {
        if (!userName.equals("jzvm")) 
        {
            throw new Exception("Invalid username or password");
        }
        if (password.isEmpty()) 
        {
            throw new Exception("password is empty");
        }
    }

    {
        putDbObject("TABLE", makeDbObject("EMPLOYEE", "Employee"));
        putDbObject("TABLE", makeDbObject("DEPT", "Department"));
        putDbObject("TABLE", makeDbObject("MANAGER", "Manager"));
        putDbObject("TABLE", makeDbObject("SALARY", "Salary"));
        putDbObject("TABLE", makeDbObject("CONTRACT", "Contract"));
        putDbObject("VIEW", makeDbObject("EMPLOYEE_TO_MANAGER_LIST", "Employee to Manager"));
        putDbObject("VIEW", makeDbObject("EMPLOYEE_TO_DEPT_LIST", "Employee to Department"));
        putDbObject("VIEW", makeDbObject("MANAGER_TO_DEPT", "Manager to department"));
        putDbObject("VIEW", makeDbObject("SALARY_TO_DEPT_LIST", "Salary to department"));
        putDbObject("SEQUENCE", makeDbObject("CONTRACT_ID", "Contract id generator"));
        putDbObject("PACKAGE", makeDbObject("EMPLOYEE_OPERATIONS", "Employee operations"));
        putDbObject("PACKAGE", makeDbObject("DEPT_OPERATIONS", "Department operations"));
        putDbObject("PACKAGE_BODY", makeDbObject("EMPLOYEE_OPERATIONS", "Employee operations"));
        putDbObject("PACKAGE_BODY", makeDbObject("DEPT_OPERATIONS", "Department operations"));
        putDbObject("PROCEDURE", makeDbObject("CONTRACT_REVERT", "Contract revertation"));
     }
    
    public void putDbObject(
            String objectType,
            DbObject object)
    {
        List<DbObject> objectList = objectsMap.get(objectType);
        if (objectList == null) 
        {
            objectList = new ArrayList<>();
            objectsMap.put(objectType, objectList);
        }
        objectList.add(object);
    }
    
    public static DbObject makeDbObject(
            String name,
            String comment)
    {
        DbObject result = new DbObject();
        result.setName(name);
        result.setComment(comment);
        return result;
    }
    
    @Override
    public List<DbObject> getObjectsForType(
            String typeName) throws Exception
    {
        List<DbObject> objects = objectsMap.get(typeName);
        if (objects == null) 
        {
            return new ArrayList<>();
        } 
        else 
        {
            return objects;
        }
    }
    
}
