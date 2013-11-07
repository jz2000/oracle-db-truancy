package org.jz.truancy.oradb;

public class DbConnection {

	private Integer id;
	private String alias;
	private String url;
	private String description;
	
	public DbConnection(){
	}
	
	public DbConnection(Integer id, String alias, String url, String description,  String preview, Integer price){
		this.id = id;
		this.alias = alias;
		this.url = url;
		this.description = description;
	}
        
	public Integer getId() {
		return id;
	}
        
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

        public void setDescription(String description) {
		this.description = description;
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
