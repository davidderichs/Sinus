package UE03A01ThreadingTuermeVonHanoi;

public class HanoiScheibe implements Comparable<HanoiScheibe> {
	
	public int scheibenNr;
	public String stapel;
	public int position;
	public String hexColor;

	public HanoiScheibe(int scheibenNr, String stapel, int position, String hexColor) {
		this.scheibenNr = scheibenNr;
		this.stapel = stapel;
		this.position = position;
		this.hexColor = hexColor;
	}

	@Override
	public int compareTo(HanoiScheibe o) {
		return Integer.compare(this.position, o.position);
	}

}
