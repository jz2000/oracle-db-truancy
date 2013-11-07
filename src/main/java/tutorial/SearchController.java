package tutorial;


import org.jz.truancy.oradb.ConnectionServiceImpl;
import org.jz.truancy.oradb.DbConnection;
import org.jz.truancy.oradb.ConnectionService;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.*;

public class SearchController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox keywordBox;
	@Wire
	private Listbox carListbox;
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
	
	@Listen("onClick = #searchButton")
	public void search(){
		String keyword = keywordBox.getValue();
		List<DbConnection> result = carService.search(keyword);
		carListbox.setModel(new ListModelList<DbConnection>(result));
	}
	
	@Listen("onSelect = #carListbox")
	public void showDetail(){
		DbConnection selected = carListbox.getSelectedItem().getValue();
		modelLabel.setValue(selected.getAlias());
		makeLabel.setValue(selected.getUrl());
		descriptionLabel.setValue(selected.getDescription());
	}
}
