package org.jz.truancy.oradb;

public class Connection {

	private Integer id;
	private String alias;
	private String url;
	private String preview;
	private String description;
	private Integer price;
	
	public Connection(){
	}
	
	public Connection(Integer id, String alias, String url, String description,  String preview, Integer price){
		this.id = id;
		this.alias = alias;
		this.url = url;
		this.preview = preview;
		this.description = description;
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
