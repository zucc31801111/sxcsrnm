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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Coupon;
import model.DeliveryAddressList;
import starter.SXCSUtil;
import util.BaseException;

public class FrmCoupon extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_CouponManager=new JMenu("优惠券管理");
    private JMenuItem  menuItem_Flash=new JMenuItem("刷新");
    private JMenuItem  menuItem_CouponAdd=new JMenuItem("增添优惠券");
    private JMenuItem  menuItem_CouponDelete=new JMenuItem("删除优惠券");
    
    private Object tblCouponTitle[]=Coupon.tblCouponTitle;
	private Object tblCouponData[][];
	DefaultTableModel tabCouponModel=new DefaultTableModel();
	private JTable dataTableCoupon=new JTable(tabCouponModel);
	
	private Coupon curCoupon=null;
	List<Coupon> allCoupon=null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCoupon frame = new FrmCoupon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
private void reloadCouponTable(){
		try {
			allCoupon=SXCSUtil.adminManager.loadCoupon();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCouponData =  new Object[allCoupon.size()][Coupon.tblCouponTitle.length];
		for(int i=0;i<allCoupon.size();i++){
			for(int j=0;j<Coupon.tblCouponTitle.length;j++)
				tblCouponData[i][j]=allCoupon.get(i).getCell(j);
		}
		tabCouponModel.setDataVector(tblCouponData,tblCouponTitle);
		this.dataTableCoupon.validate();
		this.dataTableCoupon.repaint();
	}
	/**
	 * Create the frame.
	 */
	public FrmCoupon() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("优惠券系统");
			menu_CouponManager.setFont(new Font("新宋体", Font.PLAIN, 15));
			menuItem_Flash.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_CouponManager.add(menuItem_Flash);
		    menuItem_Flash.addActionListener(this);
			menuItem_CouponAdd.setFont(new Font("宋体", Font.PLAIN, 15));
			menu_CouponManager.add(menuItem_CouponAdd);
			menuItem_CouponAdd.addActionListener(this);		    
			menuItem_CouponDelete.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_CouponManager.add(menuItem_CouponDelete);
		    menuItem_CouponDelete.addActionListener(this);
		    menubar.add(menu_CouponManager);
		    this.setJMenuBar(menubar);
		    
		    FrmCoupon.this.reloadCouponTable();
		    this.getContentPane().add(new JScrollPane(this.dataTableCoupon), BorderLayout.CENTER);
		    this.dataTableCoupon.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmCoupon.this.dataTableCoupon.getSelectedRow();
					if(i<0) {
						return;
					}		
				}
		    	
		    });  
		    this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_Flash){
			this.reloadCouponTable();

		}
		else if(e.getSource()==this.menuItem_CouponAdd){
			FrmCouponAdd dlg=new FrmCouponAdd();
			dlg.setVisible(true);

		}
		else if(e.getSource()==this.menuItem_CouponDelete) {
			int i=FrmCoupon.this.dataTableCoupon.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择优惠券", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				SXCSUtil.adminManager.deleteCoupon(this.allCoupon.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
	}

}
