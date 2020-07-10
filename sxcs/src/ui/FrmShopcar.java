package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.UserShopcar;
import starter.SXCSUtil;
import util.BaseException;

public class FrmShopcar extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private Object tblShopcarTitle[]=UserShopcar.tblShopcarTitle;
	private Object tblShopcarData[][];
	DefaultTableModel tabShopcarModel=new DefaultTableModel();
	private JTable dataTableShopcar=new JTable(tabShopcarModel);
	
	private UserShopcar curShopcar=null;
	List<UserShopcar> allShopcar=null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmShopcar frame = new FrmShopcar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
private void reloadShopcarTable(){
		
		try {
			allShopcar=SXCSUtil.userManager.loadShopcar();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopcarData =  new Object[allShopcar.size()][UserShopcar.tblShopcarTitle.length];
		for(int i=0;i<allShopcar.size();i++){
			for(int j=0;j<UserShopcar.tblShopcarTitle.length;j++)
				tblShopcarData[i][j]=allShopcar.get(i).getCell(j);
		}
		tabShopcarModel.setDataVector(tblShopcarData,tblShopcarTitle);
		this.dataTableShopcar.validate();
		this.dataTableShopcar.repaint();
	}
	
	/**
	 * Create the frame.
	 */
	public FrmShopcar() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("购物车");
		
		
		   FrmShopcar.this.reloadShopcarTable();
		    this.getContentPane().add(new JScrollPane(this.dataTableShopcar), BorderLayout.CENTER);
		    this.dataTableShopcar.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmShopcar.this.dataTableShopcar.getSelectedRow();
					if(i<0) {
						return;
					}		
				}
		    	
		    });
		    
		    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
