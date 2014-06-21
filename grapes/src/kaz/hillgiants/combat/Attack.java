package kaz.hillgiants.combat;

import kaz.hillgiants.task.Task;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

public class Attack extends Task<ClientContext> {

	public Attack(ClientContext ctx) {
		super(ctx);
	}

	int[] giantid = { 117, 4691 };

	@Override
	public boolean activate() {
		return (ctx.players.local().healthPercent() > 30)
				&& (ctx.players.local().idle());
	}

	@Override
	public void execute() {
		Npc hillgiant = ctx.npcs.select().id(giantid).poll();
		if (!hillgiant.inCombat() && hillgiant.inViewport()) {
			hillgiant.interact("Attack");
		} else {
			if (!hillgiant.inCombat()) {
				ctx.movement.step(hillgiant);
			}

		}
	}

}
