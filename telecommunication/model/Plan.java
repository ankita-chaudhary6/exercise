package telecommunication.model;

public class Plan {
	private int id;
	private int baseFare;
	private int talktime;
	private int extraCharge;

	public int getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(int baseFare) {
		this.baseFare = baseFare;
	}

	public int getTalktime() {
		return talktime;
	}

	public void setTalktime(int talktime) {
		this.talktime = talktime;
	}

	public int getExtraCharge() {
		return extraCharge;
	}

	public void setExtraCharge(int extraCharge) {
		this.extraCharge = extraCharge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Plan(int id, int baseFare, int talktime, int extraCharge) {
		super();
		this.id = id;
		this.baseFare = baseFare;
		this.talktime = talktime;
		this.extraCharge = extraCharge;
	}
	
}