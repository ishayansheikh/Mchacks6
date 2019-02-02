
public class Person {
	
	private int xCord;
	private int yCord;
	private static int maxX = 800;
	private static int maxY = 600;
	private static int minX = 1;
	private static int minY = 1;
	
	public Person() {
		this.xCord = minX + (int) (Math.random()*(maxX-minX));
		this.yCord = minY + (int) (Math.random()*(maxY-minY));
	}
	
	public int getX() {
		return this.xCord;
	}
	
	public int getY() {
		return this.yCord;
	}
	
	public void setX (int x) {
		this.xCord = x;
	}
	
	public void setY(int y) {
		this.yCord = y;
	}

	
}
