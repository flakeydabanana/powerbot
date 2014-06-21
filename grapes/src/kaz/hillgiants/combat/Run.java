package kaz.hillgiants.combat;

import kaz.hillgiants.task.Task;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

public class Run extends Task<ClientContext> {

	public Run(ClientContext ctx) {
		super(ctx);
	}

	int[] ladder = { 29355 };
	int[] doorid = { 1804 };

	@Override
	public boolean activate() {
		return ctx.players.local().healthPercent() <= 30
				|| ctx.backpack.select().count() == 28;

	}

	@Override
	public void execute() {
		GameObject escape = ctx.objects.select().id(ladder).poll();
		if (escape.inViewport()) {
			escape.interact("Climb-up");
			System.out.print("climbing ladder");
		} else {
			ctx.movement.step(escape);
			escape.interact("Climb-up");
			System.out.print("climbing ladder");
		}
		GameObject door = ctx.objects.select().id(doorid).nearest().poll();
		door.interact("Open");

	}

}
