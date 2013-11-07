package org.jz.truancy.oradb.controllers;

import java.util.List;
import org.jz.truancy.oradb.DatabaseService;
import org.jz.truancy.oradb.DatabaseServiceDebugImpl;
import org.jz.truancy.oradb.DbObject;
import org.jz.truancy.oradb.DbSession;
import org.jz.truancy.oradb.ObjectType;
import org.zkoss.zk.ui.Component;
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
 * @author sergey.zheznyakovskiy - 2013-11-05
 */
public class DatabaseController extends SelectorComposer<Component> 
{
    private static final long serialVersionUID = 1L;

    @Wire
    private Textbox searchTextBox;
    @Wire
    private Listbox objectTypeListBox;
    @Wire
    private Label selectedObjectTypeTitle;
    @Wire
    private Listbox selectedObjectTypeItemsListBox;
    
    private DatabaseService databaseService = new DatabaseServiceDebugImpl();
    
    private ObjectType selectedObjectType;

    @Override
    public void doAfterCompose(Component comp) throws Exception
    {
        super.doAfterCompose(comp);
        List<ObjectType> result = databaseService.searchObjectTypes(null);
        objectTypeListBox.setModel(new ListModelList<>(result));
    }
    
    @Listen("onClick = #searchObjectTypeButton")
    public void search()
    {
        String keyword = searchTextBox.getValue();
        List<ObjectType> result = databaseService.searchObjectTypes(keyword);
        objectTypeListBox.setModel(new ListModelList<>(result));
    }
    
    @Listen("onSelect = #objectTypeListBox")
    public void showDetail()
    {
        selectedObjectType = objectTypeListBox.getSelectedItem().getValue();
        selectedObjectTypeTitle.setValue(selectedObjectType.getTypeLabel());
        DbSession dbSession = (DbSession)Sessions.getCurrent().getAttribute("dbsession");
        if (dbSession == null) 
        {
                showNotify("NO SESSION");
        }
        else
        {
            try 
            {
                List<DbObject> objects = dbSession.getObjectsForType(selectedObjectType.getTypeName());
                selectedObjectTypeItemsListBox.setModel(new ListModelList<>(objects));
            }
            catch(Exception ex) 
            {
                showNotify(ex.getMessage());
            }
        }
    }
    
    private void showNotify(String msg)
    {
        Clients.showNotification(msg,"info",null,null,1000);
    }
}
