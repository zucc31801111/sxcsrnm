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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.DeliveryAddressList;
import model.Promotion;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JTable;

public class FrmAddress extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_Manager=new JMenu("地址管理");
    
    private JMenuItem  menuItem_AddressAdd=new JMenuItem("增添地址");
    private JMenuItem  menuItem_AddressChange=new JMenuItem("修改地址");
    private JMenuItem  menuItem_AddressDelete=new JMenuItem("删除地址");
    
    private Object tblAddressTitle[]=DeliveryAddressList.tblAddressTitle;
	private Object tblAddressData[][];
	DefaultTableModel tabAddressModel=new DefaultTableModel();
	private JTable dataTableAddress=new JTable(tabAddressModel);
	
	private DeliveryAddressList curAddress=null;
	List<DeliveryAddressList> allAddress=null;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAddress frame = new FrmAddress();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    private void reloadAddressTable(){
		
		try {
			allAddress=SXCSUtil.userManager.loadAddress();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblAddressData =  new Object[allAddress.size()][DeliveryAddressList.tblAddressTitle.length];
		for(int i=0;i<allAddress.size();i++){
			for(int j=0;j<DeliveryAddressList.tblAddressTitle.length;j++)
				tblAddressData[i][j]=allAddress.get(i).getCell(j);
		}
		tabAddressModel.setDataVector(tblAddressData,tblAddressTitle);
		this.dataTableAddress.validate();
		this.dataTableAddress.repaint();
	}
	/**
	 * Create the frame.
	 */
	public FrmAddress() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("地址管理系统");
		
			menu_Manager.setFont(new Font("新宋体", Font.PLAIN, 15));
			menuItem_AddressAdd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_AddressAdd);
		    menuItem_AddressAdd.addActionListener(this);		    
		    menuItem_AddressChange.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_AddressChange);
		    menuItem_AddressChange.addActionListener(this);
		    menuItem_AddressDelete.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_AddressDelete);
		    menuItem_AddressDelete.addActionListener(this);
		    menubar.add(menu_Manager);
		    this.setJMenuBar(menubar);
		    
		    FrmAddress.this.reloadAddressTable();
		    this.getContentPane().add(new JScrollPane(this.dataTableAddress), BorderLayout.CENTER);
		    this.dataTableAddress.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmAddress.this.dataTableAddress.getSelectedRow();
					if(i<0) {
						return;
					}		
				}
		    	
		    });
		    
		    
		    this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_AddressAdd){
			FrmAddressAdd dlg=new FrmAddressAdd();
			dlg.setVisible(true);

		}
		else if(e.getSource()==this.menuItem_AddressDelete) {
			int i=FrmAddress.this.dataTableAddress.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择地址", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				SXCSUtil.userManager.deleteAddress(this.allAddress.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
     else if(e.getSource()==this.menuItem_AddressChange) {
    	 int i=FrmAddress.this.dataTableAddress.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择地址", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
				FrmAddressChange dlg=new FrmAddressChange();
				dlg.address=this.allAddress.get(i);
				dlg.setVisible(true);
				
				
			
		}
	}
}
