package kaz.grapes.task.obstacles;

import java.util.concurrent.Callable;
import kaz.grapes.constants.*;
import kaz.grapes.task.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

public class Upstairs2 extends Task<ClientContext> {
	public Upstairs2(ClientContext ctx) {
		super(ctx);
	}
	@Override
	public boolean activate() {
		return (ctx.players.local().tile().floor() == 1)
				&& ctx.backpack.select().count() == 0;
	}

	@Override
	public void execute() {
		GameObject stair2 = ctx.objects.select().id(Doorstair.STAIR2IDS).nearest().poll();
		if (stair2.inViewport() && stair2.valid()) {
			stair2.interact("Climb-up");
			System.out.print("Going up other stairs");
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (ctx.players.local().tile().floor() == 2);
				}

			});
		}
		
	}

}

