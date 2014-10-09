package Gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;


public class JavaCalcGui {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
			
				try {
				
					JavaCalcGui window = new JavaCalcGui();
					window.frame.setVisible(true);
			
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			
			}
		
		});
	
	}

	/**
	 * Create the application.
	 */
	public JavaCalcGui() {
		
		this.initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter);
		panelCenter.setLayout(new GridLayout(1, 2, 0, 0));
		
		
		JPanel panelNumber = new JPanel();
		panelCenter.add(panelNumber);
		panelNumber.setLayout(new GridLayout(5, 3, 0, 0));
		
		JButton buttonPoint = new JButton(".");
		panelNumber.add(buttonPoint);
		
		JButton buttonLP = new JButton("(");
		panelNumber.add(buttonLP);
		
		JButton buttonRP = new JButton(")");
		panelNumber.add(buttonRP);
		
		JButton button9 = new JButton("9");
		panelNumber.add(button9);
		
		JButton button8 = new JButton("8");
		panelNumber.add(button8);
		
		JButton button7 = new JButton("7");
		panelNumber.add(button7);
		
		JButton button6 = new JButton("6");
		panelNumber.add(button6);
		
		JButton button5 = new JButton("5");
		panelNumber.add(button5);
		
		JButton button4 = new JButton("4");
		panelNumber.add(button4);
		
		JButton button3 = new JButton("3");
		panelNumber.add(button3);
		
		JButton button2 = new JButton("2");
		panelNumber.add(button2);
		
		JButton button1 = new JButton("1");
		panelNumber.add(button1);
		
		JButton button0 = new JButton("0");
		panelNumber.add(button0);
		
		JButton buttonPI = new JButton("PI");
		panelNumber.add(buttonPI);
		
		JButton buttonE = new JButton("e");
		panelNumber.add(buttonE);
		
		
		
		
		JPanel panelOperator = new JPanel();
		panelCenter.add(panelOperator);
		panelOperator.setLayout(new GridLayout(5, 1, 0, 0));
		
		JButton buttonPlus = new JButton("+");
		panelOperator.add(buttonPlus);
		
		JButton buttonBinMinus = new JButton("-");
		panelOperator.add(buttonBinMinus);
		
		JButton buttonMult = new JButton("*");
		panelOperator.add(buttonMult);
		
		JButton buttonDiv = new JButton("/");
		panelOperator.add(buttonDiv);
		
		JButton buttonUnMinus = new JButton("(-)");
		panelOperator.add(buttonUnMinus);
		
		
		
		
		
	}
	
}
