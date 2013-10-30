package tutorial;

import org.jz.truancy.oradb.ConnectionServiceImpl;
import org.jz.truancy.oradb.Connection;
import org.jz.truancy.oradb.ConnectionService;
import java.util.List;
import org.zkoss.bind.annotation.*;

public class SearchViewModel {
	
	private String keyword;
	private List<Connection> carList;
	private Connection selectedCar;
	
	private ConnectionService carService = new ConnectionServiceImpl();
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}

	public List<Connection> getCarList(){
		return carList;
	}
	
		
	public void setSelectedCar(Connection selectedCar) {
		this.selectedCar = selectedCar;
	}
	public Connection getSelectedCar() {
		return selectedCar;
	}

	
	@Command
	@NotifyChange("carList")
	public void search(){
		carList = carService.search(keyword);
	}
}
