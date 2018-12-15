package map;

import java.util.ArrayList;

import map.generation.Generator;

public class Level {
	public Level(int width, int height, boolean randomSeed, String seed, String genType) {
		this.mapWidth = width;
		this.mapHeight = height;
		this.cells = new Cell[mapWidth][mapHeight];
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				cells[x][y] = new Cell(this, x, y);
			}
		}
		this.regions = new ArrayList<Region>();
		this.lights = new ArrayList<Light>();
		this.gen = new Generator(this, (randomSeed) ? "" + Math.random() : seed);
		switch(genType) {
			case "cellAuto":
				gen.cellAuto(51, 3);
				break;
		}
		
	}
	
	private int mapWidth;
	private int mapHeight;
	private ArrayList<Light> lights;
	private Cell[][] cells;
	private Generator gen;
	private ArrayList<Region> regions;

	public int getMapWidth() {
		return mapWidth;
	}
	
	public int getMapHeight() {
		return mapHeight;
	}
	
	public Cell getCell(int x, int y) {
		return cells[x][y];
	}
	
	public Region getRegion(int index) {
		if(index < regions.size() && index >= 0) {
			return regions.get(index);
		}
		return null;
	}
	
	public ArrayList<Light> getLights() {
		return this.lights;
	}
	
	public ArrayList<Region> getRegions() {
		return regions;
	}
	
	public void addRegion(Region region) {
		regions.add(region);
	}
	
	public int getSurroundingCells(int x, int y) {
		int wallCount = 0;
		for(int xCheck = x - 1; xCheck <= x + 1; xCheck++) {
			for(int yCheck = y - 1; yCheck <= y + 1; yCheck++) {
				if(xCheck >= 0 && xCheck < mapWidth && yCheck >= 0 && yCheck < mapHeight) {
					if(xCheck != x || yCheck != y) {
						if(!cells[xCheck][yCheck].isOpenSpace()) wallCount++;
					}
				}
				else wallCount++;
			}
		}
		return wallCount;
	}
	
	public void configureCell(int x, int y) {
		if(x >= 0 && x < mapWidth && y >= 0 && y < mapHeight) {
			if(cells[x][y].isOpenSpace()) {
				cells[x][y].setConfiguration(1);
				
				boolean topOpen = (y - 1 >= 0) ? cells[x][y - 1].isOpenSpace() : false;
				boolean bottomOpen = (y + 1 < mapHeight) ?cells[x][y + 1].isOpenSpace() : false;
				boolean leftOpen = (x - 1 >= 0) ? cells[x - 1][y].isOpenSpace() : false;
				boolean rightOpen = (x + 1 < mapWidth) ? cells[x + 1][y].isOpenSpace() : false;
				
				if(topOpen) {
					cells[x][y].setConfiguration(cells[x][y].getConfiguration() + 1);
				}
				else if(y != 0){
					cells[x][y - 1].setConfiguration(1);
				}
				if(leftOpen) {
					cells[x][y].setConfiguration(cells[x][y].getConfiguration() + 2);
				}
				if(bottomOpen) {
					cells[x][y].setConfiguration(cells[x][y].getConfiguration() + 4);
				}
				if(rightOpen) { 
					cells[x][y].setConfiguration(cells[x][y].getConfiguration() + 8);
				}
			}
			else if((y + 1 < mapHeight) ? cells[x][y + 1].isOpenSpace() : false);
			else cells[x][y].setConfiguration(0);
		}
	}
}
