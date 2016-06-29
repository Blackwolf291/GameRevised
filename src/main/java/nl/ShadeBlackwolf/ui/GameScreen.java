package nl.ShadeBlackwolf.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.UI;

@Component
public class GameScreen extends JFrame implements UI{
	
	@Autowired
	private InputParser inputParser;
	
	private static final long serialVersionUID = 1L;
	private final int red = 200;
	private final int blue = 200;
	private final int green = 200;
	private final Color color = new Color(red, green, blue);
	private JPanel contentPane;
	private JTextField inputField;
	private JTextArea outputField;
	private JTextArea statsScreen;
	public void run(){
		inputField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "readInput");
		inputField.getActionMap().put("readInput", new OnEnter());
	}
	
	public GameScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Alpha Adventure");
		setBackground(color);
		
		inputField = new JTextField();
		inputField.setBounds(207, 530, 564, 20);
		contentPane.add(inputField);
		inputField.setColumns(10);
		inputField.setBackground(color);
		inputField.setText("");
		
		outputField = new JTextArea();
		outputField.setBounds(207, 5, 564, 519);
		contentPane.add(outputField);
		outputField.setEnabled(false);
		outputField.setBackground(color);
		outputField.setText("");
		
		statsScreen = new JTextArea();
		statsScreen.setBounds(10, 5, 192, 546);
		contentPane.add(statsScreen);
		statsScreen.setEnabled(false);
		statsScreen.setBackground(color);
		setVisible(true);

	}
	
	public void println(String text){
			outputField.append(text + "\n");
	}
	
	public void clearText(){
		outputField.setText("");
	}
	
	String getText(){
		return outputField.getText();
	}
	public void setInput(String text){
		inputField.setText(text);
	}
	class OnEnter extends AbstractAction{
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (! inputField.getText().equals("")){
				println("> " + inputField.getText());
				inputParser.setInput(inputField.getText());
				synchronized (inputParser){
					inputParser.notify();
				}
				inputField.setText("");
			}
		}
	}
}
