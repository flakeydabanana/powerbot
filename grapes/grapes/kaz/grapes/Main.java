package kaz.grapes;

import kaz.grapes.task.*;
import kaz.grapes.task.banking.*;
import kaz.grapes.task.obstacles.*;
import kaz.grapes.task.walking.*;
import kaz.grapes.constants.*;
import kaz.grapes.task.looting.*;
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
import kaz.grapes.constants.Prices;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt6.ClientContext;

@Manifest(description = "Takes grapes at cooking guild", name = "Grape Taker")
public class Main extends PollingScript<ClientContext> implements PaintListener {

	private List<Task> taskList = new ArrayList<Task>();

	private BufferedImage paintImage = null;

	@Override
	public void start() {
		taskList.add(new Take()); // Fixes
		taskList.add(new Downstairs()); // Works...
		taskList.add(new Exitguild()); // Works...
		taskList.add(new Tobank()); // Works...
		taskList.add(new Bank()); // Works...
		taskList.add(new Toguild()); // Works
		taskList.add(new Upstairs()); // Works...
		taskList.add(new Upstairs2()); // Works...

		paintImage = downloadImage("http://i.imgur.com/lHX0Z47.png");

		return;

	}

	@Override
	public void poll() {
		for (Task task : taskList) {
			if (task.activate()) {
				task.execute();

			}

		}
		return;
	}

	public static class time {

		public static String gettime(final long time) {
			final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
			return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":"
					+ (s < 10 ? "0" + s : s);

		}

		public static int perHour(int in, long time) {
			return (int) ((in) * 3600000D / time);
		}

	}

	@Override
	public void repaint(Graphics g) {
		g.drawImage(paintImage, 0, 450, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.BOLD, 16));
		g.drawString(time.gettime(getRuntime()), 60, 469);
		g.drawString(" " + Prices.grapes, 72, 489);
		g.drawString(" " + time.perHour(Prices.grapes, getRuntime()), 152, 521);
		g.drawString(" " + (Prices.grapes * Prices.grapesprice), 63, 545);
		g.drawString(
				" "
						+ ((time.perHour(Prices.grapes, getRuntime())) * Prices.grapesprice),
				115, 570);

	}

}
