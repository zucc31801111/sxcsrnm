package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class FrmCouponAdd extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton button = new JButton("确认");
	private JButton button_1 = new JButton("取消");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCouponAdd frame = new FrmCouponAdd();
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
	public FrmCouponAdd() {
		setTitle("添加优惠券");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("达到优惠金额");
		label.setBounds(40, 47, 117, 34);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("优惠金额");
		label_1.setBounds(40, 94, 93, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("优惠内容");
		label_2.setBounds(40, 16, 72, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("开始时间");
		label_3.setBounds(40, 137, 72, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("结束时间");
		label_4.setBounds(40, 181, 72, 18);
		contentPane.add(label_4);
		
		
		button.setBounds(46, 226, 113, 27);
		contentPane.add(button);
		
		
		button_1.setBounds(210, 226, 113, 27);
		contentPane.add(button_1);
		
		textField = new JTextField();
		textField.setBounds(156, 10, 117, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(156, 52, 117, 24);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(156, 91, 117, 24);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(156, 134, 117, 24);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(156, 178, 117, 24);
		contentPane.add(textField_4);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		
		button.addActionListener(this);
		button_1.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.button){
			String coupon_end_time=null;
			String coupon_start_time=null;
			 String coupon_content=null;
		double coupon_pricedel=0;
		double coupon_price=0;
		try {
			     coupon_content=this.textField.getText();
				coupon_price= Double.parseDouble(this.textField_1.getText());
		        coupon_pricedel= Double.parseDouble(this.textField_2.getText());
		        coupon_start_time=this.textField_3.getText();
		         coupon_end_time=this.textField_4.getText();
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "价格不能为空", "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			SXCSUtil.adminManager.addCoupon(coupon_content,coupon_price,  coupon_pricedel, coupon_start_time, coupon_end_time);;
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
