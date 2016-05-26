package GraphicDictionary;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class EditForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lbNhap;
	private JButton btnOk;
	private JLabel lblNewLabel_1;
	private JRadioButton rbtnAnhviet, rbtnVietanh;
	private ButtonGroup group;
	Dictionary dict = Dictionary.getInstance();
	private JTextField textField_2;
	private boolean checkEN=true, checkVI=false;
	
	/**
	 * Create the frame.
	 */
	public EditForm(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(445, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbNhap = new JLabel("Sửa từ:");
		lbNhap.setBounds(10, 137, 106, 14);
		contentPane.add(lbNhap);
		
		textField = new JTextField();
		textField.setBounds(138, 134, 201, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(175, 207, 89, 23);
		contentPane.add(btnOk);
		
		lblNewLabel_1 = new JLabel("Sửa nghĩa:");
		lblNewLabel_1.setBounds(10, 163, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(138, 160, 201, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		rbtnAnhviet = new JRadioButton("Anh-Việt", checkEN);
		rbtnAnhviet.setBounds(104, 45, 89, 23);
		contentPane.add(rbtnAnhviet);
		
		rbtnVietanh = new JRadioButton("Việt-Anh", checkVI);
		rbtnVietanh.setBounds(247, 45, 109, 23);
		contentPane.add(rbtnVietanh);
		
		group = new ButtonGroup();
		group.add(rbtnAnhviet);
		group.add(rbtnVietanh);
		
		JLabel lblTmTCn = new JLabel("Tìm từ cần sửa");
		lblTmTCn.setBounds(10, 11, 106, 14);
		contentPane.add(lblTmTCn);
		
		textField_2 = new JTextField();
		textField_2.setBounds(138, 8, 201, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnTim = new JButton("Tìm từ");
		btnTim.setBounds(175, 86, 89, 23);
		contentPane.add(btnTim);
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(textField_2.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập từ cần tìm !!!");
				}
				else{
					if(rbtnAnhviet.isSelected()){
						if(dict.check(textField_2.getText(), "", dict.listEN)){
							JOptionPane.showMessageDialog(null, "Không có từ cần tìm !!!");
						}
						else{
							JOptionPane.showMessageDialog(null, "Bạn có thể sửa từ !!!");
						}
					}
					else {
						if(dict.check("", textField_2.getText(), dict.listVI)){
							JOptionPane.showMessageDialog(null, "Không có từ cần tìm !!!");
						}else{
							JOptionPane.showMessageDialog(null, "Bạn có thể sửa từ !!!");
						}
						
					}
				}
			}
		});
		
		
		btnOk.addActionListener(new ActionListener() {
			String string="";
			int count;
			String keyWord="";
			FileData file= new FileData();
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(textField.getText().equals("")||textField_1.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng hoàn thành !!!");
				}
				else{
					String wordAnh="", wordViet="";
					if(rbtnAnhviet.isSelected()){
							wordAnh=textField.getText();
							wordViet=textField_1.getText();
							dict.addWordEN(wordAnh, wordViet);
							string=wordAnh+"\t"+wordViet;
							keyWord=textField_2.getText();
							count=dict.searchAnh(keyWord);
							try {
								file.editFile(MainForm.FILE_NAME_EN, string, count);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Sửa từ thành công !!!");
					}else {
							wordViet=textField.getText();
							wordAnh=textField_1.getText();
							dict.addWordVI(wordViet, wordAnh);
							string=wordViet+"\t"+wordAnh;
							keyWord=textField_2.getText();
							count=dict.searchViet(keyWord);
							try {
								file.editFile(MainForm.FILE_NAME_VI, string, count);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Sửa từ thành công !!!");
					}
				}
			}
		});
	}
}
