package map;

import java.util.ArrayList;

public class Region {
	public Region(int regionID) {
		this.regionID = regionID;
		this.regionSize = 0;
		edgeCells = new ArrayList<Cell>();
		region = new ArrayList<Cell>();
	}
	
	private int regionID;
	private int regionSize;
	private ArrayList<Cell> region;
	private ArrayList<Cell> edgeCells;
	
	public void addCell(Cell cell) {
		region.add(cell);
		cell.setRegion(regionID);
		if(cell.getConfiguration() != 16) {
			edgeCells.add(cell);
		}
		regionSize++;
	}
	
	public ArrayList<Cell> getCells() {
		return region;
	}
	
	public int getRegionSize() {
		return regionSize;
	}
	
	public ArrayList<Cell> getEdgeCells() {
		return edgeCells;
	}
}
