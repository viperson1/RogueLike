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
		final Font font = FontLoader.loadFontFromJar("Fonts/DejaVu Sans Mono/12pt/bitmap.png", "Fonts/DejaVu Sans Mono/12pt/data.fnt", 1);
		screen = new Screen(level.getMapWidth() * 3, level.getMapHeight(), font);
		screen.addCanvasToFrame();
	}
	
	public static void main(String[] args) throws IOException {
		Level level = new Level(65, 52, true, "test", "cellAuto");
		Display test = new Display(level);
		test.drawMap(level);
	}
	
	public void drawMap(Level level) {
		for(int x = 0; x < level.getMapWidth(); x++) {
			for(int y = 0; y < level.getMapHeight(); y++) {
				Cell cell = level.getCell(x, y);
				for(int i = 0; i < 3; i++) {
					Tile tile = screen.getTileAt((x * 3) + i, y);
					tile.setCharacter(Tilesets.unicodeTiles[7][cell.getConfiguration()].charAt(i));
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
						}*/
						if(i == 1) {
							tile.setCharacter((char)(cell.getRegion() + 64));
						}
					}
				}
			}
		}
		screen.draw();
	}
}
