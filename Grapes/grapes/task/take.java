package task;

import java.util.concurrent.Callable;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.GroundItem;

import constants.prices;

public class take extends task {
	int lootCount1 = ctx.backpack.id(prices.Grape).count();

	@Override
	public boolean activate() {
		return (ctx.backpack.select().count() < 28 && ctx.players.local()
				.tile().floor() == 2);

	}

	@Override
	public void execute() {
		final GroundItem Grapes = ctx.groundItems.id(prices.Grape).poll();
		if (ctx.movement.distance(Grapes.tile(), ctx.players.local().tile()) < 2) {

			if (Grapes.valid()) {
				Grapes.interact("Take");
				ctx.camera.turnTo(Grapes);
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return (!Grapes.valid());

					}
				});

				int lootCount2 = ctx.backpack.id(prices.Grape).count();
				if (lootCount2 > lootCount1) {
					System.out.print("Taking Grapes");
					prices.Grapes++;
				}
			}
		} else {
			ctx.movement.step(Grapes);
			System.out.print("moving to Grapes");
		}
	}
}

