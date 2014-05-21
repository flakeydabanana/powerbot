package kaz.chickens;

import java.util.concurrent.Callable;
import org.powerbot.script.Condition;
import org.powerbot.script.Script;
import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.Npc;

@Script.Manifest(name = "ChickenKiller", description = "Kills Chickens and loots feathers")
public class Chickens extends PollingScript<ClientContext> {
	int[] chickenIds = { 100 };
	int[] featherIds = { 100 };

	public Npc GetChicken() {
		return ctx.npcs.select().id(chickenIds).first().poll();
	}

	public GroundItem GetFeather() {
		return ctx.groundItems.select().id(featherIds).first().poll();
	}

	public void poll() {
		return;
	}

	{

		if (!ctx.players.local().inCombat()) {
			if (GetChicken().inViewport() && !GetFeather().valid()) {
				GetChicken().interact("attack");
			} else {
				if (!GetFeather().valid())
					ctx.movement.step(GetChicken());
				Condition.wait(new Callable<Boolean>() {
					public Boolean call() throws Exception {
						return (ctx.movement.distance(ctx.players.local()
								.tile(), ctx.movement.destination().tile())) < 10;
					}
				});
			}

		} else {
			Condition.wait(new Callable<Boolean>() {
				public Boolean call() throws Exception {
					return !ctx.npcs.id(chickenIds).first().poll().valid();
				}
			});
		}
	}
	{
		if (GetFeather().valid() && !ctx.players.local().inCombat()) {
			GetFeather().interact("take", "feather");
			Condition.wait(new Callable<Boolean>() {
				public Boolean call() throws Exception {
					return !ctx.groundItems.id(featherIds).first().poll()
							.valid();
				}
			});
		}

	}
}