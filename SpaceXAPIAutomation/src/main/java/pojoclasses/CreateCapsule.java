package pojoclasses;

import java.util.List;

public class CreateCapsule {

	private int reuse_count;
	private int water_landings;
	private int land_landings;
	private String last_update;
	private String type;
	private String status;
	private String serial;
	private List<String> launches;

	public int getReuse_count() {
		return reuse_count;
	}

	public void setReuse_count(int reuse_count) {
		this.reuse_count = reuse_count;
	}

	public int getWater_landings() {
		return water_landings;
	}

	public void setWater_landings(int water_landings) {
		this.water_landings = water_landings;
	}

	public int getLand_landings() {
		return land_landings;
	}

	public void setLand_landings(int land_landings) {
		this.land_landings = land_landings;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public List<String> getLaunches() {
		return launches;
	}

	public void setLaunches(List<String> launches) {
		this.launches = launches;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

}
