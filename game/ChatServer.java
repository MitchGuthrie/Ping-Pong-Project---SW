package game;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ChatServer extends AbstractServer {
	private JTextArea log;
	private JLabel status;
	private DatabaseFile dbFile;

	public ChatServer() {
		super(8300);
		dbFile = new DatabaseFile();
	}

	public ChatServer(int port) {
		super(port);
	}

	public void setLog(JTextArea log) {
		this.log = log;
	}

	public JTextArea getLog() {
		return log;
	}

	public void setStatus(JLabel status) {
		this.status = status;
	}

	public JLabel getStatus() {
		return status;
	}

	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		System.out.println("Message from Client" + arg0.toString() + arg1.toString());
		log.append("Message from Client: " + arg0.toString() + arg1.toString() + "\n");
		if (arg0 instanceof LoginData) {
			LoginData loginData = (LoginData) arg0;
			String temp = dbFile.verifyAccount(loginData.getUsername(), loginData.getPassword()) + ","
					+ loginData.getUsername() + "," + loginData.getPassword();
			log.append(temp);
			try {
				arg1.sendToClient(temp);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (arg0 instanceof CreateAccountData) {
			CreateAccountData createAccountData = (CreateAccountData) arg0;
			String temp = dbFile.createAccount(createAccountData.getUsername(), createAccountData.getPassword());
			log.append(temp);
			try {
				arg1.sendToClient(temp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	protected void listeningException(Throwable exception) {
		// Display info about the exception
		System.out.println("Listening Exception:" + exception);
		exception.printStackTrace();
		System.out.println(exception.getMessage());

		if (this.isListening()) {
			log.append("Server not Listening\n");
			status.setText("Not Connected");
			status.setForeground(Color.RED);
		}
	}

	protected void serverStarted() {
		System.out.println("Server Started");
		log.append("Server Started\n");
		status.setText("Listening");
		status.setForeground(Color.GREEN);
	}

	protected void serverStopped() {
		System.out.println("Server Stopped");
		log.append("Server Stopped Accepting New Clients - Press Listen to Start Accepting New Clients\n");
		status.setText("Stopped");
		status.setForeground(Color.RED);
	}

	protected void serverClosed() {
		System.out.println("Server and all current clients are closed - Press Listen to Restart");
		log.append("Server and all current clients are closed - Press Listen to Restart\n");
		status.setText("Close");
		status.setForeground(Color.RED);
	}

	protected void clientConnected(ConnectionToClient client) {
		System.out.println("Client Connected");
		log.append("Client " + client.getId() + ": Connected\n");
	}

}
