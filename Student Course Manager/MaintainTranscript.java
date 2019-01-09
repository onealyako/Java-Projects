import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MaintainTranscript extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MaintainTranscript() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListOfCourses = new JLabel("List of Courses Taken");
		lblListOfCourses.setBounds(21, 79, 143, 16);
		contentPane.add(lblListOfCourses);

		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.setBounds(19, 156, 117, 29);
		contentPane.add(btnAddCourse);

		JButton btnRemoveCourse = new JButton("Remove Course");
		btnRemoveCourse.setBounds(301, 156, 143, 29);
		contentPane.add(btnRemoveCourse);

		JButton btnChangeGrade = new JButton("Change Grade");
		btnChangeGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangeGrade.setBounds(161, 156, 117, 29);
		contentPane.add(btnChangeGrade);

		JLabel lblCourseMaintainingIn = new JLabel("Course Maintaining in Transcript");
		lblCourseMaintainingIn.setBounds(106, 15, 236, 16);
		contentPane.add(lblCourseMaintainingIn);
	}

}
