
public class Entry {
	
	private String name;
	private int number;
	private boolean even;
	private boolean odd;
	private double betAmmount;
	private Outcome outcome;
	private double winAmmount;
	private String betValue;

	public Entry() {
		
	}
	
	public Entry(String name, int number, double betAmmount) {
		super();
		this.name = name;
		this.number = number;
		this.betAmmount = betAmmount;
		this.betValue = Integer.toString(number);
	}
	
	public Entry(String name, boolean even, double betAmmount) {
		super();
		this.name = name;
		this.even = even;
		this.betAmmount = betAmmount;
		this.betValue = "EVEN";
	}
	
	public Entry(String name, boolean odd, double betAmmount, String bet) {
		super();
		this.name = name;
		this.odd = odd;
		this.betAmmount = betAmmount;
		this.betValue = "ODD";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public double getBetAmmount() {
		return betAmmount;
	}
	public void setBetAmmount(double betAmmount) {
		this.betAmmount = betAmmount;
	}
	
	public double getWinAmmount() {
		return winAmmount;
	}

	public void setWinAmmount(double winAmmount) {
		this.winAmmount = winAmmount;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	public boolean isEven() {
		return even;
	}

	public void setEven(boolean even) {
		this.even = even;
	}

	public boolean isOdd() {
		return odd;
	}

	public void setOdd(boolean odd) {
		this.odd = odd;
	}

	public String getBetValue() {
		return betValue;
	}

	public void setBetValue(String betValue) {
		this.betValue = betValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Entry [name=");
		builder.append(name);
		builder.append(", number=");
		builder.append(number);
		builder.append(", even=");
		builder.append(even);
		builder.append(", odd=");
		builder.append(odd);
		builder.append(", betAmmount=");
		builder.append(betAmmount);
		builder.append(", outcome=");
		builder.append(outcome);
		builder.append(", winAmmount=");
		builder.append(winAmmount);
		builder.append(", betValue=");
		builder.append(betValue);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
