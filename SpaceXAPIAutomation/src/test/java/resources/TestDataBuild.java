package resources;

import java.util.ArrayList;
import java.util.List;

import pojoclasses.CreateCapsule;

public class TestDataBuild {

	public CreateCapsule createCapsulePayLoad() {
		CreateCapsule cap = new CreateCapsule();
		cap.setReuse_count(1);
		cap.setWater_landings(1);
		cap.setLand_landings(0);
		cap.setLast_update("Reentered after three weeks in orbit");
		cap.setSerial("C101");
		cap.setStatus("retired");
		cap.setStatus("Dragon 1.0");
		List<String> launches =new ArrayList<String>();
		launches.add("5eb87cdeffd86e000604b330");
		cap.setLaunches(launches);
		return cap;
	}


}
