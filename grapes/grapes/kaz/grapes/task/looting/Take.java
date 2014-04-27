package kaz.grapes.task.looting;

import java.util.concurrent.Callable;
import kaz.grapes.constants.Prices;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.GroundItem;
import kaz.grapes.task.Task;


public class Take extends Task {
	int lootCount1 = ctx.backpack.id(Prices.grape).count();

	@Override
	public boolean activate() {
		return (ctx.backpack.select().count() < 28 && ctx.players.local()
				.tile().floor() == 2);

	}

	@Override
	public void execute() {
		final GroundItem grapes = ctx.groundItems.id(Prices.grape).poll();
		if (ctx.movement.distance(grapes.tile(), ctx.players.local().tile()) < 2) {

			if (grapes.valid()) {
				grapes.interact("Take");
				ctx.camera.turnTo(grapes);
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return (!grapes.valid());

					}
				});

				int lootCount2 = ctx.backpack.id(Prices.grape).count();
				if (lootCount2 > lootCount1) {
					System.out.print("Taking grapes");
					Prices.grapes++;
				}
			}
		} else {
			ctx.movement.step(grapes);
			System.out.print("moving to grapes");
		}
	}
}

