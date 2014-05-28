package kaz.grapes.task.obstacles;

import java.util.concurrent.Callable;

import kaz.grapes.constants.Doorstair;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import kaz.grapes.task.Task;

public class Upstairs extends Task<ClientContext> {
	public Upstairs(ClientContext ctx) {
		super(ctx);
	}
	@Override
	public boolean activate() {
		GameObject stair = ctx.objects.select().id(Doorstair.STAIR3IDS)
				.nearest().poll();
		final Tile stairtile = stair.tile().derive(1, 1);
		return ctx.backpack.select().count() == 0
				&& ctx.movement.findPath(stairtile).valid();

	}

	@Override
	public void execute() {
		GameObject stair = ctx.objects.select().id(Doorstair.STAIR3IDS)
				.nearest().poll();
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
