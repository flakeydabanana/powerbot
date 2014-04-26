package task;

public class Bank extends Task {

	@Override
	public boolean activate() {
		return ctx.bank.inViewport();
		     
	}

	@Override
	public void execute() {
		if (ctx.backpack.select().count() == 28) {
			ctx.bank.open();
		}
		if (ctx.bank.opened()) {
			ctx.bank.depositInventory();
		}
		if (ctx.backpack.isEmpty()) {
			ctx.bank.close();
		}
	}
}
