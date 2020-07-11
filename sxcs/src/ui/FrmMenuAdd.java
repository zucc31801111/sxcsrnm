package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


public class FrmMenuAdd extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton = new JButton("确认");
	private JButton button = new JButton("取消");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMenuAdd frame = new FrmMenuAdd();
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
	public FrmMenuAdd() {
		setTitle("添加菜谱");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("菜谱名");
		label.setBounds(33, 32, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("菜谱食材");
		label_1.setBounds(33, 85, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("菜谱步骤");
		label_2.setBounds(33, 136, 72, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("菜谱图片");
		label_3.setBounds(33, 182, 72, 18);
		contentPane.add(label_3);
		
		textField = new JTextField();
		textField.setBounds(119, 29, 107, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(119, 82, 107, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(119, 133, 107, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(119, 179, 107, 24);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		
		btnNewButton.setBounds(14, 235, 113, 27);
		contentPane.add(btnNewButton);
		
		
		button.setBounds(171, 235, 113, 27);
		contentPane.add(button);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		button.addActionListener(this);
		btnNewButton.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnNewButton){;
			String menu_name=this.textField.getText();
			String menu_materials=this.textField_1.getText();
			String menu_step=this.textField_2.getText();
			String menu_picture=this.textField_3.getText();
			try {
				SXCSUtil.commodityManager.createMenu(menu_name, menu_materials, menu_step, menu_picture);
				this.setVisible(false);
		} catch (BaseException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		}
		else if(e.getSource()==this.button){
			this.setVisible(false);
		}
	}
}
