package org.jz.truancy.oradb;

import java.util.List;
import org.zkoss.zk.ui.Component;
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
public class WelcomeController extends SelectorComposer<Component> {
     
    private static final long serialVersionUID = 1L;
 
	@Wire
	private Textbox searchTextBox;
	@Wire
	private Listbox connectionListBox;
	@Wire
	private Label modelLabel;
	@Wire
	private Label makeLabel;
	@Wire
	private Label priceLabel;
	@Wire
	private Label descriptionLabel;
	@Wire
	private Image previewImage;

	private ConnectionService carService = new ConnectionServiceImpl();

    public WelcomeController()
    {
		List<Connection> result = carService.findAll();
		//connectionListBox.setModel(new ListModelList<>(result));
    }
        
        
        
	@Listen("onClick = #searchButton")
	public void search(){
		String keyword = searchTextBox.getValue();
		List<Connection> result = carService.search(keyword);
		connectionListBox.setModel(new ListModelList<>(result));
	}
	
	@Listen("onSelect = #connectionListBox")
	public void showDetail(){
		Connection selected = connectionListBox.getSelectedItem().getValue();
		//previewImage.setSrc(selected.getPreview());
		modelLabel.setValue(selected.getAlias());
		makeLabel.setValue(selected.getUrl());
		//priceLabel.setValue(selected.getPrice().toString());
		//descriptionLabel.setValue(selected.getDescription());
	}
        
    @Listen("onClick = a, button")
    public void cannedResponse(MouseEvent event){
        showNotify("Clicked "+((LabelElement)event.getTarget()).getLabel());
    }
     
    private void showNotify(String msg){
        Clients.showNotification(msg,"info",null,null,1000);
    }
}
