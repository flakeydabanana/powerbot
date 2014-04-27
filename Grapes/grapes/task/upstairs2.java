package task;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.GameObject;

public class upstairs2 extends task {

	@Override
	public boolean activate() {
		return (ctx.players.local().tile().floor() == 1)
				&& ctx.backpack.count() == 0;
	}

	@Override
	public void execute() {
		GameObject stair2 = ctx.objects.select().id(constants.doorstair.stair2Ids).nearest().poll();
		if (stair2.inViewport()) {
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

