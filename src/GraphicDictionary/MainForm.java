package GraphicDictionary;

import java.awt.EventQueue;
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
import javax.swing.SwingConstants;



public class MainForm extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblInPut, lblResult;
	private JTextField textField;
	private JButton btnSearch, btnEdit, btnAdd;
	private JRadioButton rAnhViet, rVietAnh;
	private ButtonGroup group;
	private static JScrollPane scr;
	private JLabel lblLabel;
	public static final String FILE_NAME_EN = "anhViet.txt";
	public static final String FILE_NAME_VI = "vietAnh.txt";
	
	Dictionary dict = Dictionary.getInstance();
	

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
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
	public MainForm() {
		setTitle("Dictionary");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 450);
		setLocationRelativeTo(null);
		setResizable(true);
		creatJFrame();
		FileData.readFile(FILE_NAME_EN);
		FileData.readFile(FILE_NAME_VI);
	}

	public void creatJFrame() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblInPut = new JLabel("Nhập Từ:");
		lblInPut.setBounds(10, 11, 64, 14);
		contentPane.add(lblInPut);

		textField = new JTextField();
		textField.setBounds(84, 8, 247, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		btnSearch = new JButton("Tìm Kiếm");
		btnSearch.setBounds(10, 65, 89, 23);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(this);

		btnAdd = new JButton("Thêm ");
		btnAdd.setBounds(139, 65, 89, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);

		btnEdit = new JButton("Sửa");
		btnEdit.setBounds(268, 65, 89, 23);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(this);

		lblResult = new JLabel("Kết quả:");
		lblResult.setBounds(10, 97, 46, 14);
		contentPane.add(lblResult);
		
	
		
		rAnhViet = new JRadioButton("Anh-Việt", true);
		rAnhViet.setBounds(84, 35, 81, 23);
		contentPane.add(rAnhViet);

		rVietAnh = new JRadioButton("Việt-Anh", false);
		rVietAnh.setBounds(206, 35, 109, 23);
		contentPane.add(rVietAnh);

		group = new ButtonGroup();
		group.add(rVietAnh);
		group.add(rAnhViet);
		
		
		
		lblLabel = new JLabel("");
		lblLabel.setVerticalAlignment(SwingConstants.TOP);
		lblLabel.setBounds(10, 122, 401, 261);
		scr = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr.setBounds(10, 122, 401, 261);
		scr.setViewportView(lblLabel);
		scr.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.add(scr);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int count;
		// TODO Auto-generated method stub
		if (e.getSource() == btnAdd) {
			AddForm gc = new AddForm("Add Word");
		}
		if (e.getSource() == btnEdit) {
			EditForm edit = new EditForm("Edit Word");
		}
		if (e.getSource() == btnSearch) {
			String keyword = textField.getText();
			if (rAnhViet.isSelected()) {
				
				count=dict.searchAnh(keyword);
				if(count!=-1){
					
					lblLabel.setText(dict.listEN.get(count).getWordViet());
				}else{
					lblLabel.setText("");
					JOptionPane.showMessageDialog(null, "Không có từ cần tìm !!!");
					
				}
				
			}
			if (rVietAnh.isSelected()) {
				
				count=dict.searchViet(keyword);
				if(count!=-1){
					lblLabel.setText(dict.listVI.get(count).getWordAnh());
				}else{
					lblLabel.setText("");
					JOptionPane.showMessageDialog(null, "Không có từ cần tìm !!!");
					
				}
			}

		}
	}
}
