package map.generation;

import map.Cell;
import map.Level;
import map.Region;

public class Toolset {
	public Toolset(Level level) {
		this.level = level;
	}
	
	private Level level;
	
	public void fillRect(int startX, int startY, int endX, int endY) {
		if(inRangeOfMap(startX, startY, endX, endY)) {
			for(int x = startX; x <= endX; x++) {
				for(int y = startY; y <= endY; y++) {
					level.getCell(x, y).setOpen(true);
				}
			}
		}
	}
	
	public boolean areaEmpty(int direction, int startX, int startY, int width, int height) {
		switch(direction) {
		case 1: //left
			if(inRangeOfMap((startX - width) - 1, (startY - height / 2) - 1, (startX + width) + 1, (startY + height / 2) + 1)) {	
				for(int x = (startX - width) - 1; x <= startX + 1; x++) {
					for(int y = (startY - height / 2) - 1; y <= (startY + height / 2) + 1; y++) {
						if(level.getCell(x, y).isOpenSpace()) {
							return false;
						}
					}
				}
				return true;
			}
			return false;
		case 2: //right
			if(inRangeOfMap(startX, (startY - height / 2) - 1, startX + width + 1, (startY + height / 2) + 1)) {
				for(int x = startX - 1; x <= startX + width + 1; x++) {
					for(int y = (startY - height / 2) - 1; y <= (startY + height / 2) + 1; y++) {
						if(level.getCell(x, y).isOpenSpace()) {
							return false;
						}
					}
				}
				return true;
			}
			return false;
		case 3: //bottom
			if(inRangeOfMap((startX - width / 2) - 1, startY, (startX + width / 2) + 1, startY + height + 1)) {
				for(int x = (startX - width / 2) - 1; x <= (startX + width / 2) + 1; x++) {
					for(int y = startY - 1; y <= startY + height + 1; y++) {
						if(level.getCell(x, y).isOpenSpace()) {
							return false;
						}
					}
				}
				return true;
			}
			return false;
		case 4: //top
			if(inRangeOfMap((startX - width / 2) - 1, (startY - height) - 1, (startX + width / 2) + 1, startY)) {
				for(int x = (startX - width / 2) - 1; x <= (startX + width / 2) + 1; x++) {
					for(int y = (startY - height) - 1; y <= startY + 1; y++) {
						if(level.getCell(x, y).isOpenSpace()) {
							return false;
						}
					}
				}
				
				return true;
			}
			return false;
		}
		return false;
	}
	
	public void fillRectRelative(int direction, int startX, int startY, int width, int height) {
			level.getCell(startX, startY).setOpen(true);
			
			switch(direction) {
				case 1: //left
					fillRect(startX - width, startY - height / 2, startX - 1, startY + height / 2);
					break;
				case 2: //right
					fillRect(startX + 1, startY - height / 2, startX + width, startY + height / 2);
					break;
				case 3: //bottom
					fillRect(startX - width / 2, startY + 1, startX + width / 2, startY + height);
					break;
				case 4: //top
					fillRect(startX - width / 2, startY - height, startX + width / 2, startY - 1);	
					break;
			}
	}
	
	public boolean inRangeOfMap(int startX, int startY, int endX, int endY) {
		if(startX > 0 && startY > 0 && endX < level.getMapWidth() - 1 && endY < level.getMapHeight() - 1) return true;
		return false;
	}

	public void smoothMap() {
		for(int y = 0; y < level.getMapHeight(); y++) {
			for(int x = 0; x < level.getMapWidth(); x++) {
				int wallCount = level.getSurroundingCells(x, y);
				
				if(wallCount < 4) {
					level.getCell(x, y).setOpen(true);
				}
				else if(wallCount > 4) {
					level.getCell(x, y).setOpen(false);
				}
			}
		}
	}

	public void fill(Region fillRegion, int x, int y) {
		Cell cell = level.getCell(x, y);
		if(cell.isOpenSpace() && cell.getRegion() == 0) {
			fillRegion.addCell(cell);
			
			fill(fillRegion, x - 1, y);
			fill(fillRegion, x + 1, y);
			fill(fillRegion, x, y - 1);
			fill(fillRegion, x, y + 1);
		}
	}
}
