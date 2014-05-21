package kaz.grapes.task.walking;

import java.util.concurrent.Callable;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.TilePath;
import kaz.grapes.constants.*;
import kaz.grapes.task.Task;

public class Tobank extends Task<ClientContext> {
	public Tobank(ClientContext ctx) {
		super(ctx);
	}
	@Override
	public boolean activate() {
		GameObject stair = ctx.objects.select().id(Doorstair.STAIR3IDS)
				.nearest().poll();

		Tile stairtile = stair.tile().derive(1, 1);
		return ctx.backpack.count() == 28
				&& ctx.players.local().tile().floor() == 0
				&& !ctx.movement.findPath(stairtile).valid();

	}

	@Override
	public void execute() {

		final Tile[] Path = { new Tile(3156, 3449, 0), new Tile(3163, 3450, 0),
				new Tile(3176, 3450, 0), new Tile(3183, 3450, 0),
				new Tile(3181, 3444, 0) };

		final TilePath bankpath = ctx.movement.newTilePath(Path);
		System.out.print("Walking to Bank...");
		if (bankpath.valid()) {
			bankpath.traverse();

			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return ctx.movement.distance(ctx.players.local().tile(),
							bankpath.end()) <= 20;

				}
			});

		}
	}

}
