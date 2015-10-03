package mmlib4j.imagej.types;

public enum Channel {
	RED(1), GREEN(2), BLUE(3);
	
	private int value;
	
	private Channel(int value) {
		this.value = value;
	}
	
	public int value(){
		return value;
	}
}
