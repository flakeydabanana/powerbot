package kaz.grapes.task.obstacles;

import java.util.concurrent.Callable;
import kaz.grapes.constants.tiles;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.GameObject;
import kaz.grapes.constants.*;
import kaz.grapes.task.Task;

public class Upstairs extends Task {

	@Override
	public boolean activate() {
		return ctx.backpack.count() == 0
				&& tiles.GUILD_AREA.contains(ctx.players.local());
	}

	@Override
	public void execute() {
		GameObject stair = ctx.objects.select()
				.id(Doorstair.STAIR3IDS).nearest().poll();
		;
		if (stair.inViewport() && stair.valid()
				&& ctx.players.local().tile().floor() == 0) {
			stair.interact("Climb-up");
			System.out.print("Going up stairs");
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (ctx.players.local().tile().floor() == 1);
				}

			});

		} else {
			ctx.movement.step(stair);
		}
	}
}
