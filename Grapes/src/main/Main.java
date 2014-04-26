package main;

import task.*;
import constants.Prices;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt6.ClientContext;

@Manifest(description = "Takes grapes at cooking guild", name = "Grape Taker")
public class Main extends PollingScript<ClientContext> implements PaintListener {

private List<Task> taskList = new ArrayList<Task>();

@Override
public void start() {
taskList.add(new Take()); // Fixes
taskList.add(new DownStairs()); // Works...
taskList.add(new ExitGuild()); // Works...
taskList.add(new ToBank()); // Works...
taskList.add(new Bank()); // Works...
taskList.add(new ToGuild()); // Works
      taskList.add(new UpStairs()); // Works...
        taskList.add(new UpStairs2()); // Works...
        taskList.add(new Antiban()); // Antiban needed...
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
public void repaint(Graphics g) {
g.setColor(Color.WHITE);
g.setFont(new Font("Tahoma", Font.BOLD, 16));
g.drawString("Time Running:" + calc.Time.gettime(getRuntime()), 50, 65);
g.drawString("Grapes: " + Prices.Grapes, 50, 80);
g.drawString(
"Grapes per hr: "
+ calc.Time.perHour(Prices.Grapes, getRuntime()), 50,
100);
g.drawString("Profit: " + (Prices.Grapes * Prices.GrapesPrice), 50, 120);
g.drawString(
"Profit per hr: "
+ ((calc.Time.perHour(Prices.Grapes, getRuntime())) * Prices.GrapesPrice),
50, 140);

}

}
