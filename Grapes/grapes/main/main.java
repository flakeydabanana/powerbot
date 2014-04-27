package main;

import task.*;
import constants.prices;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt6.ClientContext;

@Manifest(description = "Takes grapes at cooking guild", name = "Grape Taker")
public class main extends PollingScript<ClientContext> implements PaintListener {

	private List<task> taskList = new ArrayList<task>();

	private BufferedImage paintImage = null;

	// Method to import
	//private Image getImage(String url) {
	//	try {
	//		return ImageIO.read(new URL(url));
	//	} catch (IOException e) {
	//		return null;
	//	}
	//}

	@Override
	public void start() {
		taskList.add(new take()); // Fixes
		taskList.add(new downstairs()); // Works...
		taskList.add(new exitguild()); // Works...
		taskList.add(new tobank()); // Works...
		taskList.add(new bank()); // Works...
		taskList.add(new toguild()); // Works
		taskList.add(new upstairs()); // Works...
		taskList.add(new upstairs2()); // Works...
		taskList.add(new antiban()); // Antiban needed...
		
		paintImage = downloadImage("http://i.imgur.com/lHX0Z47.png");
		
		return;

	}

	@Override
	public void poll() {
		for (task task : taskList) {
			if (task.activate()) {
				task.execute();

			}

		}
		return;
	}

	@Override
	public void repaint(Graphics g) {
		g.drawImage(paintImage, 0, 450, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.BOLD, 16));
		g.drawString(calc.time.gettime(getRuntime()), 60, 469);
		g.drawString(" " + prices.Grapes, 72, 489);
		g.drawString(
				" "
						+ calc.time.perHour(prices.Grapes, getRuntime()), 152,
				521);
		g.drawString(" " + (prices.Grapes * prices.GrapesPrice), 63, 545);
		g.drawString(
				" "
						+ ((calc.time.perHour(prices.Grapes, getRuntime())) * prices.GrapesPrice),
				115, 570);

	}

}
