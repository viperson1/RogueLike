package map;

public class Cell {
	Cell(Level level, int x, int y) {
		this.level = level;
		this.cellX = x;
		this.cellY = y;
		this.isOpenSpace = false;
		this.configuration = 0;
		this.light = new Light();
		this.objectID = 0;
		this.region = 0;
	}
	
	private int cellX;
	private int cellY;
	private Level level;
	private boolean isOpenSpace;
	private int configuration = 0;
	private Light light = new Light();
	private int objectID = 0;
	private int region;
	
	public boolean isOpenSpace() {
		return isOpenSpace;
	}
	
	public void setOpen(boolean isOpen) {
		this.isOpenSpace = isOpen;
		
		level.configureCell(cellX, cellY);
		level.configureCell(cellX, cellY - 1);
		level.configureCell(cellX, cellY + 1);
		level.configureCell(cellX - 1, cellY);
		level.configureCell(cellX + 1, cellY);
	}
	
	public int getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(int configuration) {
		this.configuration = configuration;
	}
	
	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}

	public Light getLight() {
		return light;
	}
	
	public int getRegion() {
		return region;
	}
	
	public void setRegion(int region) {
		this.region = region;
	}
	
	public int getPosX() {
		return cellX;
	}
	
	public int getPosY() {
		return cellY;
	}
}
