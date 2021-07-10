package _02_Chat_Application;

import java.awt.Dimension;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import _00_Click_Chat.gui.ButtonClicker;
import _02_Chat_Application.Client;
import _02_Chat_Application.Server;

public class ChatApp extends JFrame {
	
	JButton button = new JButton("Send");
	JTextField mesBox = new JTextField();
	Dimension mesDem = new Dimension(400, 50);
	
	Server server;
	Client client;
	
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp(){
		mesBox.setSize(mesDem);
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e)->{
				server.sendMes(mesBox.getText());
				System.out.println(mesBox.getText());
				mesBox.setText("");
			});
			add(mesBox);
			add(button);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			button.addActionListener((e)->{
				client.sendMes(mesBox.getText());
				System.out.println("Message:");
				System.out.println(mesBox.getText());
				mesBox.setText("");
			});
			add(mesBox);
			add(button);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
	
}
