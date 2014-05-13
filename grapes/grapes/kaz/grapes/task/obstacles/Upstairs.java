package kaz.grapes.task.obstacles;

import java.util.concurrent.Callable;

import kaz.grapes.constants.Doorstair;
import kaz.grapes.constants.tiles;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.GameObject;
import kaz.grapes.constants.*;
import kaz.grapes.task.Task;

public class Upstairs extends Task {
	GameObject stair = ctx.objects.select().id(Doorstair.STAIR3IDS).nearest()
			.poll();
	final Tile stairtile = stair.tile().derive(1, 1);

	@Override
	public boolean activate() {
		return ctx.backpack.count() == 0
				&& ctx.movement.findPath(stairtile).valid();

	}

	@Override
	public void execute() {
		if (stair.valid() && ctx.players.local().tile().floor() == 0) {
			ctx.camera.turnTo(stair);
			stair.interact("Climb-up");
			System.out.print("Going up stairs");
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (ctx.players.local().tile().floor() == 1);
				}

			});

		}
	}
}
