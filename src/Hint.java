

/**
 * This is the entity for Hint 
 * @author leo_s
 *
 */
public class Hint {
	
	
	public Hint(int hintId, int xcoordinat, int ycoordinat, String imageUrl) {
		super();
		this.hintId = hintId;
		this.xcoordinat = xcoordinat;
		this.ycoordinat = ycoordinat;
		this.imageUrl = imageUrl;
	}
	
	
	public int getHintId() {
		return hintId;
	}
	public void setHintId(int hintId) {
		this.hintId = hintId;
	}
	public int getXcoordinat() {
		return xcoordinat;
	}
	public void setXcoordinat(int xcoordinat) {
		this.xcoordinat = xcoordinat;
	}
	public int getYcoordinat() {
		return ycoordinat;
	}
	public void setYcoordinat(int ycoordinat) {
		this.ycoordinat = ycoordinat;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	@Override
	public String toString() {
		return "Hint [hintId=" + hintId + ", xcoordinat=" + xcoordinat + ", ycoordinat=" + ycoordinat + ", imageUrl="
				+ imageUrl + "]";
	}


	private int hintId;
	private int xcoordinat;
	private int ycoordinat;
	private String imageUrl;

}
