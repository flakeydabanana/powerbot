package kaz.grapes.task.obstacles;


import kaz.grapes.constants.*;

import java.util.concurrent.Callable;

import kaz.grapes.constants.tiles;
import kaz.grapes.task.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.GameObject;

public class Exitguild extends Task {

	@Override
	public boolean activate() {

		return ctx.backpack.count() == 28
				&& ctx.players.local().tile().floor() == 0
				&& tiles.GUILD_AREA.contains(ctx.players.local());

	}

	@Override
	public void execute() {
		GameObject door = ctx.objects.select().id(Doorstair.DOORIDS)
				.nearest().poll();
		if (ctx.movement.distance(door.tile(), ctx.players.local().tile()) < 2) {

			if ((door.inViewport()) && door.valid()) {
				door.interact("open");

				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return !tiles.GUILD_AREA.contains(ctx.players.local());
					}
				});
				System.out.print("Leaving Guild");

			} else {
				ctx.camera.turnTo(door);
				ctx.movement.step(door);
			}

		}
	}
}
