package banking.primitive.core;


public class AccountServerFactory {

	protected static AccountServerFactory singleton = null;

	/**
	 * Constructor - default (explicit)
	 */
	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	/**
	 * Check the instance of the AccountServer
	 * @return
	 */
	public AccountServer lookup() {
		return new ServerSolution();
	}
}
