package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.CommodityInformation;
import model.Recommended;
import starter.SXCSUtil;
import util.BaseException;

public class FrmShopCarAddtwo extends JFrame implements ActionListener {

	public Recommended recommended=null;
	private JPanel contentPane;
	private JTextField textField;
	private JButton button = new JButton("确认");
	private JButton button_1 = new JButton("取消");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmShopCarAddtwo frame = new FrmShopCarAddtwo();
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
	public FrmShopCarAddtwo() {
		setTitle("添加至购物车");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("加入购物车数量");
		label.setBounds(40, 56, 160, 54);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(177, 65, 124, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		button.setBounds(40, 137, 113, 27);
		contentPane.add(button);
		
		
		button_1.setBounds(219, 137, 113, 27);
		contentPane.add(button_1);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		button.addActionListener(this);
		button_1.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.button) {
			int sum=0;
			try {
				sum =Integer.parseInt(this.textField.getText());
				
			}
			catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "数量不能为空", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				
				SXCSUtil.userManager.addUserShopcartwo(sum, recommended);
						this.setVisible(false);
				} catch (BaseException  e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		}
		else if(e.getSource() == this.button_1) {
			this.setVisible(false);
		}
	}
	
}
