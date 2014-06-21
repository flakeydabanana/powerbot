package kaz.hillgiants.walking;

import java.util.concurrent.Callable;

import kaz.hillgiants.task.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.TilePath;

public class Tobank extends Task<ClientContext> {

	public Tobank(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.players.local().healthPercent() <= 30
				|| ctx.backpack.select().count() == 28;
	}

	@Override
	public void execute() {

		final Tile[] togiants = { new Tile(3182, 3444, 0),
				new Tile(3186, 3446, 0), new Tile(3178, 3447, 0),
				new Tile(3172, 3450, 0), new Tile(3165, 3450, 0),
				new Tile(3157, 3450, 0), new Tile(3155, 3448, 0),
				new Tile(3149, 3443, 0), new Tile(3143, 3440, 0),
				new Tile(3132, 3439, 0), new Tile(3120, 3443, 0),
				new Tile(3115, 3448, 0) };
		final TilePath Path = ctx.movement.newTilePath(togiants);

		if (Path.valid()) {
			Path.reverse().traverse();
			System.out.print("Walking to giants...");
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return ctx.movement.distance(ctx.players.local().tile(),
							Path.reverse().end()) <= 20;

				}
			});

		}

	}
}
