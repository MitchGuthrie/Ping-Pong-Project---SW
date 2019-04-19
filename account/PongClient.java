package account;

import ocsf.client.AbstractClient;

public class PongClient extends AbstractClient {
	LoginControl loginControl;
	CreateAccountControl accountControl;

	public PongClient() {
		// setting default port and address
		super("localhost", 8301);
	}

	public void handleMessageFromServer(Object arg0) {
		System.out.println("Server Message sent to Client " + (String) arg0);
		if (arg0.toString().contains("Login Unsuccessful - Please Try Again")) {
			System.out.println(arg0);
			loginControl.displayError("Username and password are incorrect");
		} else if (arg0.toString().contains("Login Success")) {
			System.out.println(arg0);
			String[] temp = arg0.toString().split(",");
			loginControl.loginSuccess(temp[1], temp[2]);
		} else if (arg0.toString().contains("Username Used")) {
			System.out.println(arg0);
			accountControl.displayError("Username has already been selected");
		} else if (arg0.toString().contains("Account Created")) {
			System.out.println(arg0);
			accountControl.CreateAccountSuccessful();
		}
	}

	public void connectionEstablished() {
		System.out.println("Successfully connected to server");
	}

	public void setLoginControl(LoginControl loginControl) {
		this.loginControl = loginControl;
	}

	public void setCreateAccountControl(CreateAccountControl accountControl) {
		this.accountControl = accountControl;
	}

	public void connectionException(Throwable exception) {
		System.out.println("Connection Exception Occurred");
		System.out.println(exception.getMessage());
		exception.printStackTrace();
	}

}
