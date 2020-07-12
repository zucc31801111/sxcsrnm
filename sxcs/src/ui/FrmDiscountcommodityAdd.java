package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DiscountInformation;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FrmDiscountcommodityAdd extends JFrame implements ActionListener{
	public DiscountInformation discountInformation =null;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btconf = new JButton("确实");
	private JButton btesc = new JButton("取消");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDiscountcommodityAdd frame = new FrmDiscountcommodityAdd();
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
	public FrmDiscountcommodityAdd() {
		setTitle("添加满折商品");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("商品编号");
		label.setBounds(57, 55, 86, 21);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(137, 53, 118, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		btconf.setBounds(30, 113, 113, 27);
		contentPane.add(btconf);
		
		btesc.setBounds(175, 113, 113, 27);
		contentPane.add(btesc);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		btconf.addActionListener(this);
		btesc.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btconf){
			int commodityId=0;
			try {
				commodityId=Integer.parseInt(this.textField.getText());
			
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "商品编号不能为空", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					SXCSUtil.commodityManager.addDiscountCommodity(commodityId, discountInformation);
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
