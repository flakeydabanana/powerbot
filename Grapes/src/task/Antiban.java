package task;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.GroundItem;

import constants.Prices;

public class Antiban extends Task {
	GroundItem Grapes = ctx.groundItems.id(Prices.Grape).poll();

	// more Antiban/Idle is needed 
	// what do we do when we play legit?
	// Offscreen
	@Override
	public boolean activate() {

		return 1 < ctx.backpack.id(Prices.Grape).count() << 27
				&& ctx.players.local().tile().floor() == 2 && !Grapes.valid();
	}

	@Override
	public void execute() {

		int random = Random.nextInt(1, 10);
		if (random == 1) {
			final GroundItem Jug = ctx.groundItems.id(Prices.Jug).poll();
			Jug.hover();
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (Grapes.valid());

				}
			});
		}
		if (random == 2) {
			final GroundItem Pot = ctx.groundItems.id(Prices.pot).poll();
			Pot.hover();
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (Grapes.valid());

				}
			});

		}
		if (random == 3) {
			final GroundItem Apple = ctx.groundItems.id(Prices.Apple).poll();
			Apple.hover();
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (Grapes.valid());

				}
			});

		}

	}

}
