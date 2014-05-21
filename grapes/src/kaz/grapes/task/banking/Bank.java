
package kaz.grapes.task.banking;
import org.powerbot.script.rt6.ClientContext;
import kaz.grapes.task.*;

public class Bank extends Task<ClientContext> {
	public Bank(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.bank.present()
				&& ctx.backpack.count() == 28;

	}

	@Override
	public void execute() {
		if (!ctx.bank.opened()) {
			ctx.bank.open();
		} else {
			if (ctx.bank.opened()) {
				ctx.bank.depositInventory();
			}
		}
		if (ctx.backpack.isEmpty()) {
			ctx.bank.close();
		}
	}
}
