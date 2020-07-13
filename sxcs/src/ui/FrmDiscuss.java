package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.OrderContent;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FrmDiscuss extends JFrame implements ActionListener{
	public OrderContent OrderContent=null;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton button = new JButton("确认");
	private JButton button_1 = new JButton("取消");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDiscuss frame = new FrmDiscuss();
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
	public FrmDiscuss() {
		setTitle("评价");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("评分");
		label.setBounds(53, 30, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("描述");
		label_1.setBounds(53, 86, 72, 18);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(110, 27, 86, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 86, 172, 87);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		button.setBounds(35, 194, 113, 27);
		contentPane.add(button);
		
		
		button_1.setBounds(215, 194, 113, 27);
		contentPane.add(button_1);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		
		button.addActionListener(this);
		button_1.addActionListener(this);
			
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.button){
			String content=null;
		double eviltion=0;
		try {
			eviltion= Double.parseDouble(this.textField.getText());
			content=this.textField_1.getText();
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "价格不能为空", "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			SXCSUtil.userManager.addCommodityEvaluation(OrderContent, content, eviltion);
			this.setVisible(false);
	} catch (BaseException e1) {
		JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
		return;
	}
		
	}
	else if(e.getSource()==this.button_1){
		this.setVisible(false);
	}
	}
}
