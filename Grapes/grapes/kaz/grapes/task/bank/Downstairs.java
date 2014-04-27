package kaz.grapes.task.bank;
import kaz.grapes.constants.*;
import kaz.grapes.task.Task;

import java.util.concurrent.Callable;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.GameObject;

public class Downstairs extends Task {

	@Override
	public boolean activate() {
		return ctx.backpack.count() == 28;

	}

	@Override
	public void execute() {
		GameObject stair = ctx.objects.select()
				.id(Doorstair.STAIRIDS).nearest().poll();
		if (stair.inViewport() && stair.valid() && ctx.players.local().tile().floor() == 2) {
			stair.interact("Climb-down");
			System.out.print("Going down stairs");
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (ctx.players.local().tile().floor() == 1);
				}

			});
		}
		GameObject stair2 = ctx.objects.select()
				.id(Doorstair.STAIR2IDS).nearest().poll();
		if (stair2.inViewport() && stair2.valid()) {
			stair2.interact("Climb-down");
			System.out.print("Going down Other stairs");
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (ctx.players.local().tile().floor() == 0);
				}

			});

		}
	}
}
