package kaz.hillgiants.obstacles;

import kaz.hillgiants.task.Task;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

public class Enterdungen extends Task<ClientContext> {

	public Enterdungen(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	int[] stairsid = { 12389 };

	@Override
	public boolean activate() {
		GameObject stair = ctx.objects.select().id(stairsid).nearest().poll();

		return ctx.movement.findPath(stair.tile().derive(1, 1)).valid();

	}

	@Override
	public void execute() {
		GameObject stair = ctx.objects.select().id(stairsid).nearest().poll();
		stair.interact("Climb-Down");
		System.out.print("Entering Dungeon");

	}

}
