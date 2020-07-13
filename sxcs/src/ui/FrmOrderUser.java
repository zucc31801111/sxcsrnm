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

import model.CommodityOrder;
import model.DiscountCommodity;
import model.DiscountInformation;
import model.OrderContent;
import starter.SXCSUtil;
import util.BaseException;


public class FrmOrderUser extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_Manager=new JMenu("订单管理");
    private JMenuItem  menuItem_Disscus =new JMenuItem("评价");
    
    
    private Object tblCommodityOrderTitle[]=CommodityOrder.tblCommodityOrderTitle;
	private Object tblCommodityOrderData[][];
	DefaultTableModel tabCommodityOrderModel=new DefaultTableModel();
	private JTable dataTableCommodityOrder=new JTable(tabCommodityOrderModel);
	
	
	private Object tblOrderContentTitle[]=OrderContent.tblOrderContentTitle;
	private Object tblOrderContentData[][];
	DefaultTableModel tabOrderContentModel=new DefaultTableModel();
	private JTable dataTableOrderContent=new JTable(tabOrderContentModel);
    
	
	private CommodityOrder curCommodityOrder=null;
	List<OrderContent> allOrderContent=null;
	List<CommodityOrder> allCommodityOrder=null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmOrderUser frame = new FrmOrderUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void reloadDiscountInformationTable(){
		try {
			allCommodityOrder=SXCSUtil.userManager.loadCommodityOrderUser();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCommodityOrderData =  new Object[allCommodityOrder.size()][CommodityOrder.tblCommodityOrderTitle.length];
		for(int i=0;i<allCommodityOrder.size();i++){
			for(int j=0;j<CommodityOrder.tblCommodityOrderTitle.length;j++)
				tblCommodityOrderData[i][j]=allCommodityOrder.get(i).getCell(j);
		}
		tabCommodityOrderModel.setDataVector(tblCommodityOrderData,tblCommodityOrderTitle);
		this.dataTableCommodityOrder.validate();
		this.dataTableCommodityOrder.repaint();
	}
	private void reloadloadDiscountCommodityTabel(int planIdx){
		if(planIdx<0) return;
		curCommodityOrder=allCommodityOrder.get(planIdx);
		try {
			allOrderContent=SXCSUtil.userManager.loadCommodityOrderContent(curCommodityOrder);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblOrderContentData =new Object[allOrderContent.size()][OrderContent.tblOrderContentTitle.length];
		for(int i=0;i<allOrderContent.size();i++){
			for(int j=0;j<OrderContent.tblOrderContentTitle.length;j++)
				tblOrderContentData[i][j]=allOrderContent.get(i).getCell(j);
		}
		
		tabOrderContentModel.setDataVector(tblOrderContentData,tblOrderContentTitle);
		this.dataTableOrderContent.validate();
		this.dataTableOrderContent.repaint();
	}
	
	/**
	 * Create the frame.
	 */
	public FrmOrderUser() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("商品订单列表");
		
			menu_Manager.setFont(new Font("新宋体", Font.PLAIN, 15));
			menuItem_Disscus.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_Disscus);
		    menuItem_Disscus.addActionListener(this);	
		    menubar.add(menu_Manager);
		    this.setJMenuBar(menubar);
		 this.getContentPane().add(new JScrollPane(this.dataTableCommodityOrder), BorderLayout.CENTER);
		    this.dataTableCommodityOrder.addMouseListener(new MouseAdapter (){	
		    	
				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmOrderUser.this.dataTableCommodityOrder.getSelectedRow();
					if(i<0) {
						return;
					}
					FrmOrderUser.this.reloadloadDiscountCommodityTabel(i);
				}
		    	
		    });
		    this.getContentPane().add(new JScrollPane(this.dataTableOrderContent), BorderLayout.EAST);
		    
		    this.reloadDiscountInformationTable();	
		
		    this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_Disscus){
			int i=FrmOrderUser.this.dataTableOrderContent.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择满折商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			FrmDiscuss dlg =new FrmDiscuss();
			dlg.OrderContent=this.allOrderContent.get(i);
			dlg.setVisible(true);
		}
	}

}
