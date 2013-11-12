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
 * @author sergey.zheznyakovskiy - 2013-11-05
 */
public class DatabaseController extends SelectorComposer<Component> 
{
    private static final long serialVersionUID = 1L;

    @Wire
    private Textbox searchTextBox;
    @Wire
    private Textbox searchObjectTextBox;
    @Wire
    private Listbox objectTypeListBox;
    @Wire
    private Label selectedObjectTypeTitle;
    @Wire
    private Listbox selectedObjectTypeItemsListBox;
    
    private DatabaseService databaseService = new DatabaseServiceDebugImpl();
    
    private ObjectType selectedObjectType;

    private DbObject selectedObject;

    @Override
    public void doAfterCompose(Component comp) throws Exception
    {
        super.doAfterCompose(comp);
        List<ObjectType> result = databaseService.searchObjectTypes(null);
        objectTypeListBox.setModel(new ListModelList<>(result));
        selectedObjectType = null;
    }
    
    @Listen("onClick = #searchObjectTypeButton")
    public void search()
    {
        String keyword = searchTextBox.getValue();
        List<ObjectType> result = databaseService.searchObjectTypes(keyword);
        objectTypeListBox.setModel(new ListModelList<>(result));
        selectedObjectType = null;
    }
    
    @Listen("onClick = #openObjectTypeButton")
    public void openSelectedObjectTupe()
    {
        if (selectedObjectType == null) 
        {
            showNotify("No object type selected");
        } 
        else
        {
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
                    selectedObject = null;
                }
                catch(Exception ex) 
                {
                    showNotify(ex.getMessage());
                }
            }
        }
    }
    
    @Listen("onClick = #searchObjectButton")
    public void searchObject()
    {
        DbSession dbSession = (DbSession)Sessions.getCurrent().getAttribute("dbsession");
        if (dbSession == null) 
        {
                showNotify("NO SESSION");
        }
        else
        {
            try 
            {
                String keyword = searchObjectTextBox.getValue();
                List<DbObject> result = dbSession.searchObjects(selectedObjectType.getTypeName(), keyword);
                selectedObjectTypeItemsListBox.setModel(new ListModelList<>(result));
                selectedObject = null;
            }
            catch(Exception ex) 
            {
                showNotify(ex.getMessage());
            }
        }
    }
    
    @Listen("onClick = #openObjectButton")
    public void openSelectedObject()
    {
        if (selectedObject == null)
        {
            showNotify("No object selected");
        }
        else
        {
            showNotify("SELECTED OBJECT : " + selectedObject.getName() + " " + selectedObject.getType());
            if (selectedObject.getType().equals("TABLE"))
            {
                Executions.sendRedirect("table.zul" + "?" + "table-name" + "=" + selectedObject.getName());
            }
        }
    }
    
    @Listen("onSelect = #objectTypeListBox")
    public void showDetail()
    {
        selectedObjectType = objectTypeListBox.getSelectedItem().getValue();
    }
    
    @Listen("onSelect = #selectedObjectTypeItemsListBox")
    public void selectObject()
    {
        selectedObject = selectedObjectTypeItemsListBox.getSelectedItem().getValue();
    }
    
    private void showNotify(String msg)
    {
        Clients.showNotification(msg,"info",null,null,1000);
    }
}
