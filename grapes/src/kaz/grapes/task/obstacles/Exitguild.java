package kaz.grapes.task.obstacles;

import kaz.grapes.constants.*;

import java.util.concurrent.Callable;
import kaz.grapes.task.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

public class Exitguild extends Task<ClientContext> {
	public Exitguild(ClientContext ctx) {
		super(ctx);
	}
	public GameObject GetDoor() {
		return ctx.objects.select().id(Doorstair.DOORIDS).nearest().poll();
	}

	public GameObject GetStair() {
		return ctx.objects.select().id(Doorstair.STAIR3IDS).nearest().poll();
	}

	public Tile GetDoorTile() {

		return GetDoor().tile().derive(1, 1);
	}

	public Tile GetStairTile() {

		return GetStair().tile().derive(1, 1);

	}

	@Override
	public boolean activate() {

		return ctx.backpack.count() == 28
				&& ctx.players.local().tile().floor() == 0
				&& ctx.movement.findPath(GetDoorTile()).valid()
				&& ctx.movement.findPath(GetStair()).valid();

	}

	@Override
	public void execute() {

		if (ctx.movement.distance(GetDoorTile().tile(), ctx.players.local()
				.tile()) < 2) {

			if ((GetDoor().inViewport()) && GetDoor().valid()) {
				GetDoor().interact("open");

				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return !ctx.movement.findPath(GetStairTile()).valid();
					}
				});
				System.out.print("Leaving Guild");

			} else {
				ctx.movement.step(GetDoor());
			}

		}
	}
}
