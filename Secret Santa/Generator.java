import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Generator extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Generator frame = new Generator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Generator() {
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSecretSantaGenerator = new JLabel("Secret Santa Generator");
		lblSecretSantaGenerator.setFont(new Font("Copperplate", Font.PLAIN, 26));
		lblSecretSantaGenerator.setForeground(Color.RED);
		lblSecretSantaGenerator.setBounds(71, 6, 350, 16);
		contentPane.add(lblSecretSantaGenerator);
		
		JLabel lblEnterYourName = new JLabel("Enter your name: ");
		lblEnterYourName.setForeground(Color.GREEN);
		lblEnterYourName.setBounds(6, 39, 126, 16);
		contentPane.add(lblEnterYourName);
		
		textField = new JTextField();
		textField.setBounds(123, 34, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBounds(144, 56, 82, 29);
		contentPane.add(btnSubmit);
		
		JLabel lblAddSecretSanta = new JLabel("Add Secret Santa Participant:");
		lblAddSecretSanta.setForeground(Color.GREEN);
		lblAddSecretSanta.setBounds(6, 123, 191, 16);
		contentPane.add(lblAddSecretSanta);
		
		textField_1 = new JTextField();
		textField_1.setBounds(197, 118, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("SUBMIT");
		button.setBounds(219, 141, 82, 29);
		contentPane.add(button);
		
		JLabel lblHowManyParticpants = new JLabel("How many particpants have I added?");
		lblHowManyParticpants.setForeground(Color.GREEN);
		lblHowManyParticpants.setBounds(6, 198, 247, 16);
		contentPane.add(lblHowManyParticpants);
		
		textField_2 = new JTextField();
		textField_2.setBounds(262, 220, 65, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnShow = new JButton("SHOW ");
		btnShow.setBounds(245, 193, 95, 29);
		contentPane.add(btnShow);
		
		
	}

}
