
public class EntryTotal {
	
	private String name;
	private double totalBet;
	private double totalWin;

	public EntryTotal() {
		
	}
	
	public EntryTotal(String name, double totalWin, double totalBet) {
		super();
		this.name = name;
		this.totalBet = totalBet;
		this.totalWin = totalWin;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getTotalBet() {
		return totalBet;
	}

	public void setTotalBet(double totalBet) {
		this.totalBet = totalBet;
	}

	public double getTotalWin() {
		return totalWin;
	}

	public void setTotalWin(double totalWin) {
		this.totalWin = totalWin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EntryTotal [name=");
		builder.append(name);
		builder.append(", totalBet=");
		builder.append(totalBet);
		builder.append(", totalWin=");
		builder.append(totalWin);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
