package GraphicDictionary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;


public class AddForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel Jnhap, Jnghia;
	private JButton btnOk;
	private JButton btnCancel;
	private JRadioButton rAnhViet;
	private JRadioButton rVietAnh;
	private ButtonGroup group;
	private JTextArea textArea;
	private static JScrollPane scr;
	
	Dictionary dict = Dictionary.getInstance();
	private JTextArea textArea_1;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AddForm(String title) {
		setTitle(title);
		setSize(360, 417);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Jnhap = new JLabel("Nhập từ: ");
		Jnhap.setBounds(10, 11, 54, 14);
		contentPane.add(Jnhap);

		textField = new JTextField();
		textField.setBounds(74, 8, 195, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		Jnghia = new JLabel("Nghĩa: ");
		Jnghia.setBounds(10, 42, 46, 14);
		contentPane.add(Jnghia);

		scr = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr.setBounds(10, 67, 299, 196);
		scr.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.add(scr);
		textArea = new JTextArea();
		scr.setViewportView(textArea);
		textArea.setBounds(10, 122, 283, 245);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(74, 318, 89, 23);
		contentPane.add(btnOk);

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (textField.getText().equals("") || textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng hoàn thành !!!");
				} else {
					String wordAnh = "", wordViet = "";
					if (rAnhViet.isSelected()) {
						wordAnh = textField.getText();
						wordViet = textArea.getText();
						if(dict.check(wordAnh, wordViet, dict.listEN)){
							String str = wordAnh + "\t" + wordViet;
							dict.addWordEN(wordAnh, wordViet);
							FileData.writeFile(str, MainForm.FILE_NAME_EN);
							JOptionPane.showMessageDialog(null, "Thêm từ thành công !!!");
						}
						else{
							JOptionPane.showMessageDialog(null, "Từ đã có !!!");
						}
						
					} 
					else if (rVietAnh.isSelected()) {
						wordViet = textField.getText();
						wordAnh = textArea.getText();
						
						if(dict.check(wordAnh, wordViet, dict.listVI)){
							dict.addWordVI(wordViet, wordAnh);
							String str = wordViet + "\t" + wordAnh;					
							FileData.writeFile(str, MainForm.FILE_NAME_VI);
							JOptionPane.showMessageDialog(null, "Thêm từ thành công !!!");
						}
						else{
							JOptionPane.showMessageDialog(null, "Từ đã có !!!");
						}
					}
				}
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(184, 318, 89, 23);
		contentPane.add(btnCancel);

		rAnhViet = new JRadioButton("Anh-Việt", true);
		rAnhViet.setBounds(74, 276, 89, 23);
		contentPane.add(rAnhViet);

		rVietAnh = new JRadioButton("Việt-Anh", false);
		rVietAnh.setBounds(184, 276, 109, 23);
		contentPane.add(rVietAnh);

		group = new ButtonGroup();
		group.add(rAnhViet);
		group.add(rVietAnh);
		
		

	}
}
