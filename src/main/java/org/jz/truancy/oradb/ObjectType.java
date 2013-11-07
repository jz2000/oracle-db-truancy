package org.jz.truancy.oradb;

/**
 *
 * @author sergey.zheznyakovskiy - 2013-11-05
 */
public class ObjectType 
{
    private String typeLabel;
    private String typeName;

    public ObjectType(String typeLabel, String typeName)
    {
        this.typeLabel = typeLabel;
        this.typeName = typeName;
    }
    
    public String getTypeLabel()
    {
        return typeLabel;
    }

    public String getTypeName()
    {
        return typeName;
    }
}
