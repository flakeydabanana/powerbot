package kaz.hillgiants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import kaz.hillgiants.combat.*;
import kaz.hillgiants.obstacles.*;
import kaz.hillgiants.task.*;
import kaz.hillgiants.walking.*;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt6.ClientContext;

@Manifest(description = "Kills hill giants in the Edgevill dungeon", name = "Giant Killer")
public class Hillgiants extends PollingScript<ClientContext> implements
		PaintListener {

	private List<Task> taskList = new ArrayList<Task>();

	private BufferedImage paintImage = null;

	@Override
	public void start() {
		taskList.add(new Togiants(ctx)); // testing needed
		taskList.add(new Attack(ctx)); // testing needed
		taskList.add(new Run(ctx)); // testing needed
		taskList.add(new Enterdungen(ctx)); // testing needed
		taskList.add(new Tobank(ctx)); // testing needed
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

	@Override
	public void repaint(Graphics arg0) {
		// to be done later

	}
}
