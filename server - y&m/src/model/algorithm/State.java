package model.algorithm;
//An abstract class that creates a situation
//In practice, for each game will implement specific condition for which (he inherited this abstract class)
public abstract class State implements Comparable<State> {
	protected String state;
	protected double price;
	protected State camefrom;
	protected Action cameWithAction;
	
	public State(String state) {//CTOR
		this.state=state;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public State getCamefrom() {
		return camefrom;
	}
	public void setCamefrom(State camefrom) {
		this.camefrom = camefrom;
	}
	public Action getCameWithAction() {
		return cameWithAction;
	}
	public void setCameWithAction(Action cameWithAction) {
		this.cameWithAction = cameWithAction;
	}
	
	@Override
	public String toString(){
		return state;
	}
	
	@Override
	public boolean equals(Object o){
		return state.equals(((State)o).getState());
	}
	
	@Override
	public int compareTo(State state)
	{
		if (this.getPrice() > state.getPrice())
			return 1;
		else if (this.getPrice() < state.getPrice())
			return -1;
		return 0;
	}
	public int[] split ()
	{
		String [] position =  state.split(",");
		int [] nAndm = new int[position.length];
		for (int i = 0; i < nAndm.length; i++)
		{
			nAndm[i] = Integer.valueOf(position[i]);
		}
		return nAndm;
	}
	
	
	
}
