package kaz.grapes.task.obstacles;

import kaz.grapes.constants.*;

import java.util.concurrent.Callable;

import kaz.grapes.constants.tiles;
import kaz.grapes.task.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.GameObject;

public class Exitguild extends Task {
	GameObject door = ctx.objects.select().id(Doorstair.DOORIDS).nearest()
			.poll();
	GameObject stair = ctx.objects.select().id(Doorstair.STAIR3IDS).nearest()
			.poll();
	final Tile doortile = door.tile().derive(1, 1);
	final Tile stairtile = stair.tile().derive(1, 1);

	@Override
	public boolean activate() {

		return ctx.backpack.count() == 28
				&& ctx.players.local().tile().floor() == 0
				&& ctx.movement.findPath(doortile).valid()
				&& ctx.movement.findPath(stairtile).valid();

	}

	@Override
	public void execute() {

		if (ctx.movement.distance(door.tile(), ctx.players.local().tile()) < 2) {

			if ((door.inViewport()) && door.valid()) {
				door.interact("open");

				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return !ctx.movement.findPath(stairtile).valid();
					}
				});
				System.out.print("Leaving Guild");

			} else {
				ctx.movement.step(door);
			}

		}
	}
}
