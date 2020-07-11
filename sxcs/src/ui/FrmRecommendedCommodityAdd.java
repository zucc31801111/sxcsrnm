package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Menu;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FrmRecommendedCommodityAdd extends JFrame implements ActionListener{
	public Menu curMenu=null;
	private JPanel contentPane;
	private JTextField textField;
	private JButton button_1 = new JButton("取消");
	private JButton button = new JButton("确认");
	private JLabel label_1;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRecommendedCommodityAdd frame = new FrmRecommendedCommodityAdd();
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
	public FrmRecommendedCommodityAdd() {
		setTitle("添加推荐商品");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("商品名");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(74, 13, 71, 37);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(158, 17, 94, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		button.setBounds(48, 114, 113, 27);
		contentPane.add(button);
		
		
		button_1.setBounds(215, 114, 113, 27);
		contentPane.add(button_1);
		
		label_1 = new JLabel("描述");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(74, 69, 72, 32);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(158, 69, 94, 32);
		contentPane.add(textField_1);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		button.addActionListener(this);
		button_1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == this.button) {
			String commodity_name=this.textField.getText();
			String describe=this.textField_1.getText();
			try {
			SXCSUtil.commodityManager.addRecommendedCommodity(commodity_name, describe, curMenu);
			this.setVisible(false);
	} catch (BaseException e1) {
		JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
		return;
	}
		}
		
		else if(e.getSource() == this.button_1) {
			this.setVisible(false);
		}
	}
}
