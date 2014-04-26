package task;

import constants.Tiles;
import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.GameObject;

public class ExitGuild extends Task {

	@Override
	public boolean activate() {

		return ctx.backpack.count() == 28
				&& ctx.players.local().tile().floor() == 0
				&& Tiles.GUILD_AREA.contains(ctx.players.local());

	}

	@Override
	public void execute() {
		GameObject door = ctx.objects.select().id(constants.DoorStair.doorIds)
				.nearest().poll();
		if (ctx.movement.distance(door.tile(), ctx.players.local().tile()) < 2) {

			if ((door.inViewport())) {
				door.interact("open");

				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return !Tiles.GUILD_AREA.contains(ctx.players.local());
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

