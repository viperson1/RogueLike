package map.generation;
import java.awt.Point;
import java.util.List;
import java.util.Random;

import com.valkryst.VTerminal.misc.ShapeAlgorithms;

import map.Cell;
import map.Level;
import map.Region;

public class Generator {
	public Generator(Level level, String seed) {
		this.level = level;
		this.pseudoRandom = new Random(seed.hashCode());
		this.tools = new Toolset(level);
	}
	
	private Level level;
	private Toolset tools;
	Random pseudoRandom;
	
	public void cellAuto(int density, int smoothIter) {
		for(int x = 1; x < level.getMapWidth() - 1; x++) {
			for(int y = 1; y < level.getMapHeight() - 1; y++) {
				if(pseudoRandom.nextInt(100) > density) {
					level.getCell(x, y).setOpen(true);
				}
			}
		}
		for(int i = 0; i < smoothIter; i++) {
			tools.smoothMap();
		}
		
		findRegions();
		int regionCount = level.getRegions().size();
		for(int regionA = 1; regionA < regionCount; regionA++) {
			for(int regionB = 1; regionB < regionCount; regionB++) {
				if(regionA != regionB) {
					connectRegions(level.getRegion(regionA), level.getRegion(regionB));
				}
			}
		}
	}
	

	public void findRegions() {
		int region = 1;
		for(int x = 0; x < level.getMapWidth(); x++) {
			for(int y = 0; y < level.getMapHeight(); y++) {
				if(level.getCell(x, y).isOpenSpace() && level.getCell(x, y).getRegion() == 0) {
					Region newRegion = new Region(region);
					level.addRegion(newRegion);
					tools.fill(newRegion, x, y);
					if(newRegion.getRegionSize() < 30) {
						for(Cell cell : newRegion.getCells()) {
							cell.setOpen(false);
							//cell.setRegion(0);
							level.getRegions().remove(newRegion);
						}
					}
					region++;
				}
				else continue;
			}
		}
	}
	
	public void connectRegions(Region regionA, Region regionB) {
		double minDistance = 1000;
		Cell closestCellA = null;
		Cell closestCellB = null;
		for(Cell cellA : regionA.getEdgeCells()) {
			for(Cell cellB : regionB.getEdgeCells()) {
				double dist = Math.hypot(cellA.getPosX() - cellB.getPosX(), cellA.getPosY() - cellB.getPosY());
				if(dist < minDistance) {
					minDistance = dist;
					closestCellA = cellA;
					closestCellB = cellB;
				}
			}
		}
		if(closestCellA != null && closestCellB != null && minDistance < Math.hypot(level.getMapWidth(), level.getMapHeight()) / 4) {
			boolean obstructed = false;
			List<Point> tunnel = ShapeAlgorithms.getLine(closestCellA.getPosX(), closestCellA.getPosY(), closestCellB.getPosX(), closestCellB.getPosY());
			for(Point position : tunnel) {
				if((position != tunnel.get(0) && position != tunnel.get(tunnel.size() - 1) || tunnel.size() < 4)) {
					if(level.getCell(position.x, position.y).isOpenSpace()) {
						obstructed = true;
					}
				}
			}
			if(!obstructed) {
				for(Point position : tunnel) {
					for(int x = position.x - 1; x < position.x + 1; x++) {
						for(int y = position.y - 1; y < position.y + 1; y++) {
							level.getCell(x, y).setOpen(true);
						}
					}
				}
			}
		}
	}
}