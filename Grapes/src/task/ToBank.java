package task;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.TilePath;

import constants.Tiles;

public class ToBank extends Task {

	

	@Override
	public boolean activate() {
		return ctx.backpack.count() == 28
				&& !Tiles.GUILD_AREA.contains(ctx.players.local())
				&& ctx.players.local().tile().floor() == 0;
				
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

