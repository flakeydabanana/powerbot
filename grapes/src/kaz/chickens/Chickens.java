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
	int[] chickenIds = { 41, 1017 }; // correct id
	int[] featherIds = { 314 }; // incorrect id

	public Npc GetChicken() {
		return ctx.npcs.select().id(chickenIds).first().poll();
	}

	public GroundItem GetFeather() {
		return ctx.groundItems.select().id(featherIds).first().poll();
	}

	public void poll() {
		System.out.print("Starting loop");

		{

			if (!ctx.players.local().inCombat()) {
				if (GetChicken().inViewport()) {
					GetChicken().interact("attack");
					System.out.print("Attacking");
					Condition.wait(new Callable<Boolean>() {
						public Boolean call() throws Exception {
							System.out.print("Waiting for combat to be finished");
							return !ctx.players.local().inCombat();
						}
					});
				} else {
					if (!GetFeather().valid() && !GetChicken().inViewport())
						ctx.movement.step(GetChicken());
					System.out.print("Moving to Chicken");
					Condition.wait(new Callable<Boolean>() {
						public Boolean call() throws Exception {
							return (ctx.movement.distance(ctx.players.local()
									.tile(), ctx.movement.destination().tile())) < 10;
						}
					});
				}

			}
		}
		{
			if (GetFeather().valid() && !ctx.players.local().inCombat()) {
				GetFeather().interact("take", "feather");
				System.out.print("Taking feather");
				Condition.wait(new Callable<Boolean>() {
					public Boolean call() throws Exception {
						System.out.print("Waiting until feather is taken");
						return !ctx.groundItems.id(featherIds).first().poll()
								.valid();
					}
				});
			}

		}

	}
}