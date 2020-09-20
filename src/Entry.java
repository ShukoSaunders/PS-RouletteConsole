
public class Entry {
	
	private String name;
	private int number;
	private boolean even;
	private boolean odd;
	private double betAmmount;
	private boolean win;
	private double winAmmount;

	public Entry() {
		
	}
	
	public Entry(String name, int number,double betAmmount) {
		super();
		this.name = name;
		this.number = number;
		this.betAmmount = betAmmount;
	}
	
	public Entry(String name, boolean even, double betAmmount) {
		super();
		this.name = name;
		this.even = even;
		this.betAmmount = betAmmount;
	}
	
	public Entry(String name, boolean odd, double betAmmount, String bet) {
		super();
		this.name = name;
		this.odd = odd;
		this.betAmmount = betAmmount;
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

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
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
		builder.append(", win=");
		builder.append(win);
		builder.append(", winAmmount=");
		builder.append(winAmmount);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
