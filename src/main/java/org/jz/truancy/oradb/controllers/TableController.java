package org.jz.truancy.oradb.controllers;

import java.util.List;
import org.jz.truancy.oradb.DatabaseService;
import org.jz.truancy.oradb.DatabaseServiceDebugImpl;
import org.jz.truancy.oradb.DbObject;
import org.jz.truancy.oradb.DbSession;
import org.jz.truancy.oradb.ObjectType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author sergey.zheznyakovskiy - 2013-11-12
 */
public class TableController extends SelectorComposer<Component> 
{
    private static final long serialVersionUID = 1L;

    @Wire
    private Listbox tabsListBox;
    @Wire
    private Listbox columnsListBox;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception
    {
        super.doAfterCompose(comp);
    }
    
    @Listen("onSelect = #tabsListBox")
    public void showDetail()
    {
        //selectedObjectType = objectTypeListBox.getSelectedItem().getValue();
    }
    
    @Listen("onSelect = #columnsListBox")
    public void selectObject()
    {
        //selectedObject = selectedObjectTypeItemsListBox.getSelectedItem().getValue();
    }
    
    private void showNotify(String msg)
    {
        Clients.showNotification(msg,"info",null,null,1000);
    }
}
