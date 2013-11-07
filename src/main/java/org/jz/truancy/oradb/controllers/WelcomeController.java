package org.jz.truancy.oradb.controllers;

import java.util.List;
import org.jz.truancy.oradb.ConnectionService;
import org.jz.truancy.oradb.ConnectionServiceImpl;
import org.jz.truancy.oradb.DbConnection;
import org.jz.truancy.oradb.DbSession;
import org.jz.truancy.oradb.DbSessionFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.LabelElement;

/**
 *
 * @author sergey.zheznyakovskiy - 2013-10-31
 */
public class WelcomeController extends SelectorComposer<Component> 
{
     
    private static final long serialVersionUID = 1L;
 
    @Wire
    private Textbox searchTextBox;
    @Wire
    private Listbox connectionListBox;
    @Wire
    private Label aliasLabel;
    @Wire
    private Label urlLabel;
    @Wire
    private Textbox userNameTextBox;
    @Wire
    private Textbox passwordTextBox;

    private ConnectionService connectionService = new ConnectionServiceImpl();
    private DbConnection selectedDbConnection;

    public WelcomeController()
    {
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception
    {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        List<DbConnection> result = connectionService.findAll();
        connectionListBox.setModel(new ListModelList<>(result));
    }
    
    

    @Listen("onClick = #searchButton")
    public void search()
    {
            String keyword = searchTextBox.getValue();
            List<DbConnection> result = connectionService.search(keyword);
            connectionListBox.setModel(new ListModelList<>(result));
    }

    @Listen("onSelect = #connectionListBox")
    public void showDetail()
    {
            selectedDbConnection = connectionListBox.getSelectedItem().getValue();
            //previewImage.setSrc(selected.getPreview());
            aliasLabel.setValue(selectedDbConnection.getAlias());
            urlLabel.setValue(selectedDbConnection.getUrl());
            
            //priceLabel.setValue(selected.getPrice().toString());
            //descriptionLabel.setValue(selected.getDescription());
    }

    @Listen("onClick = #loginButton")
    public void login()
    {
        Clients.showBusy("Connection...");
        String userName = userNameTextBox.getValue();
        String password = passwordTextBox.getValue();
        showNotify(userName + " / " + password);
        DbSession dbSession = DbSessionFactory.produceDbSession(selectedDbConnection, userName, password);
        try 
        {
            dbSession.validate();
            Sessions.getCurrent(true).setAttribute("dbsession", dbSession);
            Executions.sendRedirect("database.zul");
        }
        catch (Exception ex)
        {
            showNotify(ex.getMessage());
        }
        Clients.clearBusy();
    }
        
    //@Listen("onClick = a, button")
    //public void cannedResponse(MouseEvent event){
    //    showNotify("Clicked "+((LabelElement)event.getTarget()).getLabel());
   // }
     
    private void showNotify(String msg)
    {
        Clients.showNotification(msg,"info",null,null,1000);
    }
}
