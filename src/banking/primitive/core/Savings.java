package banking.primitive.core;

public class Savings extends Account {

	/**
	 * Single argument constructor
	 * @param name
	 */
	public Savings(String name) {
		super(name);
	}

	/**
	 * Constructor with more arguments
	 * @param name
	 * @param balance
	 * @throws IllegalArgumentException
	 */
	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	/**
	 * A deposit comes with a fee of 50 cents per deposit
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f && (amount == ((float)((int)(amount*100))/100))) {
			balance = balance + amount - 0.50F;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	 * A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f && (amount == ((float)((int)(amount*100))/100))) {
			balance = balance - amount;
			numWithdraws++;
			if (numWithdraws > 3){
				balance = balance - 1.0f;
			}
			// KG BVA: should be < 0
			if (balance <= 0.0f) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}
	
	public String getType() { return "Checking"; }

	/**
	 * toString() method for easy tracing
	 */
	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}
	
	private static final long serialVersionUID = 111L;
	private int numWithdraws = 0;
}
