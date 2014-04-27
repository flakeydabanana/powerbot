
package kaz.grapes.task.banking;
import kaz.grapes.task.*;

public class Bank extends Task {

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
