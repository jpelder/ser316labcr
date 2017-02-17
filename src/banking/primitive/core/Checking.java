package banking.primitive.core;

public class Checking extends Account {

	/**
	 * Constructor to invoke superclass constructor
	 * @param name
	 */
	private Checking(String name) {
		super(name);
	}

	/**
	 * Create a checking account
	 * @param name
	 * @return
	 */
    public static Checking createChecking(String name) {
        return new Checking(name);
    }

    /**
     * Alternate constructor with different signature
     * @param name
     * @param balance
     */
	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	 * A deposit may be made unless the Checking account is closed
	 * @param float is the deposit amount
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f && (amount == ((float)((int)(amount*100))/100))) {
			balance = balance + amount;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	 * Withdrawal. After 10 withdrawals a fee of $2 is charged per transaction You may 
	 * continue to withdraw an overdrawn account until the balance is below -$100
	 */
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {		
			// KG: incorrect, last balance check should be >=
			if ((getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > -100.0f)) && (amount == ((float)((int)(amount*100))/100))) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > 10){ 
					balance = balance - 2.0f;
				}
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}

	public String getType() { return "Checking"; }
	
	/**
	 * toString() method for easy tracing
	 */
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
	
	private static final long serialVersionUID = 11L;
	private int numWithdraws = 0;
}
