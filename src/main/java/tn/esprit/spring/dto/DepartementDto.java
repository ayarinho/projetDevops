package tn.esprit.spring.dto;

public class DepartementDto {
	
    private int id;
	
	private String nameDep;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getNameDep() {
		return nameDep;
	}

	public void setNameDep(String nameDep) {
		this.nameDep = nameDep;
	}

	public DepartementDto(int id, String nameDep) {
		super();
		this.id = id;
		this.nameDep = nameDep;
	}

	public DepartementDto() {
		super();
	}
	
	

}
