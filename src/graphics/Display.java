package graphics;

import java.awt.Color;
import java.io.IOException;

import com.valkryst.VTerminal.Screen;
import com.valkryst.VTerminal.Tile;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.font.FontLoader;

import map.Cell;
import map.Level;

public class Display {
	private Screen screen;
	
	Display(Level level) throws IOException {
		final Font font = FontLoader.loadFontFromJar("fonts/Unifont/unifont_0.png", "fonts/Unifont/unifont.fnt", 1);
		screen = new Screen(level.getMapWidth() * 3, level.getMapHeight(), font);
		screen.addCanvasToFrame();
	}
	
	public static void main(String[] args) throws IOException {
		Level level = new Level(54, 40, true, "test", "cellAuto");
		Display test = new Display(level);
		test.drawMap(level);
	}
	
	public void drawMap(Level level) {
		int variationDensity = 15;
		for(int x = 0; x < level.getMapWidth(); x++) {
			for(int y = 0; y < level.getMapHeight(); y++) {
				Cell cell = level.getCell(x, y);
				for(int i = 0; i < 3; i++) {
					Tile tile = screen.getTileAt((x * 3) + i, y);
					tile.setBackgroundColor(Color.BLACK);
					tile.setCharacter(Math.floor(Math.random() * 99.99) < variationDensity ?
							Tilesets.variationTiles[7][cell.getConfiguration()].charAt(i) :
							Tilesets.unicodeTiles[7][cell.getConfiguration()].charAt(i));
					/*if(Tilesets.unicodeTiles[7][cell.getConfiguration()].charAt(i) == '@') {
						//tile.setForegroundColor(new Color(5, 5, 5));
					}
					
					if(cell.getRegion() != 0) {
						/*switch(cell.getRegion() % 7) {
						case 0:
							tile.setBackgroundColor(Color.MAGENTA);
						case 1:
							tile.setBackgroundColor(Color.GREEN);
							break;
						case 2:
							tile.setBackgroundColor(Color.RED);
							break;
						case 3:
							tile.setBackgroundColor(Color.ORANGE);
							break;
						case 4:
							tile.setBackgroundColor(Color.CYAN);
							break;
						case 5:
							tile.setBackgroundColor(Color.BLUE);
							break;
						case 6:
							tile.setBackgroundColor(Color.PINK);
							break;
						}
						if(i == 1) {
							tile.setCharacter((char)(cell.getRegion() + 64));
						}
					}*/
				}
			}
		}
		screen.draw();
	}
}
