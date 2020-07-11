package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.UserShopcar;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmShopcarChange extends JFrame implements ActionListener{
	public UserShopcar shopcar=null;
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
					FrmShopcarChange frame = new FrmShopcarChange();
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
	public FrmShopcarChange() {
		setTitle("商品数量修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("数量");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(55, 51, 50, 23);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(127, 52, 93, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		button.setBounds(33, 108, 93, 23);
		contentPane.add(button);
		
		
		button_1.setBounds(148, 108, 93, 23);
		contentPane.add(button_1);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		button.addActionListener(this);
		button_1.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == this.button) {
			int sum=0;
			try {
				sum =Integer.parseInt(this.textField.getText());
				
			}
			catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "数量不能为空", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
   try {
				
				SXCSUtil.userManager.changeShopcar(shopcar, sum);
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
