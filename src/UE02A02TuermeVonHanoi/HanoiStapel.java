package UE02A02TuermeVonHanoi;

import java.util.ArrayList;

public class HanoiStapel {

	public String stapelName;
	public ArrayList<Integer> slotValues;

	public HanoiStapel(String stapelName, int slots) {
		this.stapelName = stapelName;
		this.slotValues = new ArrayList<>(4);
	}

}
