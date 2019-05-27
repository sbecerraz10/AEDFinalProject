package model;

public class TrainStation {
	
	private String cityname;
	
	private int index;

	public TrainStation(String cityname, int index) {
		super();
		this.cityname = cityname;
		this.index = index;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
	

}
