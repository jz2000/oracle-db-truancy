package org.jz.truancy.oradb;

/**
 *
 * @author sergey.zheznyakovskiy - 2013-11-07
 */
public class DbObject
{
    public String type;
    public String name;
    public String comment;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
