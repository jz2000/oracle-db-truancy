package tutorial;

import org.jz.truancy.oradb.ConnectionServiceImpl;
import org.jz.truancy.oradb.DbConnection;
import org.jz.truancy.oradb.ConnectionService;
import java.util.List;
import org.zkoss.bind.annotation.*;

public class SearchViewModel {
	
	private String keyword;
	private List<DbConnection> carList;
	private DbConnection selectedCar;
	
	private ConnectionService carService = new ConnectionServiceImpl();
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}

	public List<DbConnection> getCarList(){
		return carList;
	}
	
		
	public void setSelectedCar(DbConnection selectedCar) {
		this.selectedCar = selectedCar;
	}
	public DbConnection getSelectedCar() {
		return selectedCar;
	}

	
	@Command
	@NotifyChange("carList")
	public void search(){
		carList = carService.search(keyword);
	}
}
