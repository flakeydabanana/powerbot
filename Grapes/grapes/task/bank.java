package task;

public class bank extends task {

	@Override
	public boolean activate() {
		return ctx.bank.inViewport()
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
