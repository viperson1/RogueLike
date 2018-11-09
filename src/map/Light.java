package map;

public class Light {
	private boolean isSource;
	private int lightLevel;
	private int lightColorR;
	private int lightColorG;
	private int lightColorB;
	
	Light() {
		this.isSource = false;
		this.lightLevel = 0;
		this.lightColorR = 0;
		this.lightColorG = 0;
		this.lightColorB = 0;
	}
	
	public void setColor(int r, int g, int b) {
		this.lightColorR = r;
		this.lightColorG = g;
		this.lightColorB = b;
	}
	
	public String getColorHex() {
		return String.format("#%02x%02x%02x", this.lightColorR, this.lightColorG, this.lightColorB);
	}
	
	public int getLightLevel() {
		return this.lightLevel;
	}
	public void setLightLevel(int lightLevel) {
		this.lightLevel = lightLevel;
	}
	
	public boolean isSource() {
		return this.isSource;
	}
	
	public void setSource(boolean isSource) {
		this.isSource = isSource;
	}
}
