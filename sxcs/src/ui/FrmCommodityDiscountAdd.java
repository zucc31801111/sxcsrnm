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
import javax.swing.JButton;
import javax.swing.JTextField;

public class FrmCommodityDiscountAdd extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btconf = new JButton("确实");
	private JButton btesc = new JButton("取消");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCommodityDiscountAdd frame = new FrmCommodityDiscountAdd();
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
	public FrmCommodityDiscountAdd() {
		setTitle("添加满折");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("满折内容");
		label.setBounds(46, 27, 54, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("适用数量");
		label_1.setBounds(45, 63, 54, 29);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("折扣");
		label_2.setBounds(46, 101, 54, 27);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("开始时间");
		label_3.setBounds(43, 139, 54, 29);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("结束时间");
		label_4.setBounds(46, 185, 54, 15);
		contentPane.add(label_4);
		
	
		btconf.setBounds(46, 238, 93, 23);
		contentPane.add(btconf);
		
		
		btesc.setBounds(174, 238, 93, 23);
		contentPane.add(btesc);
		
		textField = new JTextField();
		textField.setBounds(136, 27, 93, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 66, 93, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(136, 103, 93, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(136, 142, 93, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(136, 182, 93, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		btconf.addActionListener(this);
		btesc.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btconf){
			
			String content=this.textField.getText();
			 int number=0;
			 double sum=0;
			 String starttime=this.textField_3.getText();
			 String  endtime=this.textField_4.getText();
			try {
				number=Integer.parseInt(this.textField_1.getText());
				sum=Double.parseDouble(this.textField_2.getText());
			}
			catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "数字不能为空", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				SXCSUtil.commodityManager.creatDiscountInformation(content, number, sum, starttime, endtime);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	   else if(e.getSource()==this.btesc) { 
		   this.setVisible(false);
		   return;

		}
		
}
	}
