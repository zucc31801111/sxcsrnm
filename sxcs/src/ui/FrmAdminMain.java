package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.CommodityInformation;
import model.FreshCategory;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmAdminMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_Manager=new JMenu("个人信息管理");
   // private JMenu menu_UserManager=new JMenu("用户管理");
    private JMenu menu_CommodityCategory=new JMenu("商品分类管理");
    private JMenu menu_Commodity=new JMenu("商品管理");
    
    private JMenuItem  menuItem_ChangePwd=new JMenuItem("修改密码");
    
   // private JMenuItem  menuItem_UserSerach =new JMenuItem("用户查询");
   // private JMenuItem  menuItem_UserDelete =new JMenuItem("用户删除");
    
    private JMenuItem  menuItem_CategoryAdd =new JMenuItem("增加分类");
    private JMenuItem  menuItem_CategoryDelete =new JMenuItem("删除分类");
  
    private JMenuItem  menuItem_MenuManager=new JMenuItem("菜谱管理");
    private JMenuItem  menuItem_CommodityAdd=new JMenuItem("添加商品");
    private JMenuItem  menuItem_CommodityDelete=new JMenuItem("删除商品");
    private JMenuItem  menuItem_CommodityPurchase=new JMenuItem("采购商品");
    private JMenuItem  menuItem_CommodityPurchaseList=new JMenuItem("商品采购表");
    private JMenuItem  menuItem_CommodityDiscount=new JMenuItem("满折管理");
    private JMenuItem  menuItem_Promotion=new JMenuItem("限时促销管理");
    private JMenuItem  menuItem_Coupon=new JMenuItem("优惠券管理");
    
    
    
	//private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	
	private Object tblCategoryTitle[]=FreshCategory.tblCategoryTitle;
	private Object tblCategoryData[][];
	DefaultTableModel tabCategoryModel=new DefaultTableModel();
	private JTable dataTableCategory=new JTable(tabCategoryModel);
	
	
	private Object tblCommodityTitle[]=CommodityInformation.tblCommodityTitle;
	private Object tblCommodityData[][];
	DefaultTableModel tabCommodityModel=new DefaultTableModel();
	private JTable dataTableCommodity=new JTable(tabCommodityModel);
	
	private FreshCategory curCategory=null;
	List<FreshCategory> allCategory=null;
	List<CommodityInformation> categoryCommodity=null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAdminMain frame = new FrmAdminMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	private void reloadFreshCategoryTable(){
		try {
			allCategory=SXCSUtil.commodityManager.loadFreshCategory();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCategoryData =  new Object[allCategory.size()][FreshCategory.tblCategoryTitle.length];
		for(int i=0;i<allCategory.size();i++){
			for(int j=0;j<FreshCategory.tblCategoryTitle.length;j++)
				tblCategoryData[i][j]=allCategory.get(i).getCell(j);
		}
		tabCategoryModel.setDataVector(tblCategoryData,tblCategoryTitle);
		this.dataTableCategory.validate();
		this.dataTableCategory.repaint();
	}
	private void reloadCommodityInformationTabel(int planIdx){
		if(planIdx<0) return;
		curCategory=allCategory.get(planIdx);
		try {
			categoryCommodity=SXCSUtil.commodityManager.loadCommodity(curCategory);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCommodityData =new Object[categoryCommodity.size()][CommodityInformation.tblCommodityTitle.length];
		for(int i=0;i<categoryCommodity.size();i++){
			for(int j=0;j<CommodityInformation.tblCommodityTitle.length;j++)
				tblCommodityData[i][j]=categoryCommodity.get(i).getCell(j);
		}
		
		tabCommodityModel.setDataVector(tblCommodityData,tblCommodityTitle);
		this.dataTableCommodity.validate();
		this.dataTableCommodity.repaint();
	}
	
	
	public FrmAdminMain() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("管理员系统");
		    menu_Manager.setFont(new Font("新宋体", Font.PLAIN, 15));
		    menuItem_ChangePwd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_ChangePwd);
		    menuItem_ChangePwd.addActionListener(this);
		    menubar.add(menu_Manager);
		    
		 /*   menu_UserManager.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		    menuItem_UserSerach.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_UserManager.add(this.menuItem_UserSerach);
		    menuItem_UserSerach.addActionListener(this);
		    menuItem_UserDelete.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_UserManager.add(this.menuItem_UserDelete);
		    menuItem_UserDelete.addActionListener(this);
		    menubar.add(menu_UserManager);*/
		    
		    
		    
		    menu_CommodityCategory.setFont(new Font("宋体", Font.PLAIN, 15));
		    menuItem_CategoryAdd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_CommodityCategory.add(this.menuItem_CategoryAdd);
		    menuItem_CategoryAdd.addActionListener(this);
		    menuItem_CategoryDelete.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_CommodityCategory.add(this.menuItem_CategoryDelete);
		    menuItem_CategoryDelete.addActionListener(this);
		    menubar.add(menu_CommodityCategory);
		    
		    
		    menu_Commodity.setFont(new Font("宋体", Font.PLAIN, 15));  
		    menuItem_MenuManager.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Commodity.add(this.menuItem_MenuManager);
		    menuItem_MenuManager.addActionListener(this);
		    menuItem_CommodityAdd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Commodity.add(this.menuItem_CommodityAdd);
		    menuItem_CommodityAdd.addActionListener(this);
		    menuItem_CommodityDelete.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Commodity.add(this.menuItem_CommodityDelete);
		    menuItem_CommodityDelete.addActionListener(this);
		    menuItem_CommodityPurchase.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Commodity.add(this.menuItem_CommodityPurchase);
		    menuItem_CommodityPurchase.addActionListener(this);
		    menuItem_CommodityPurchaseList.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Commodity.add(this.menuItem_CommodityPurchaseList);
		    menuItem_CommodityPurchaseList.addActionListener(this);
		    menuItem_CommodityDiscount.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Commodity.add(this.menuItem_CommodityDiscount);
		    menuItem_CommodityDiscount.addActionListener(this);
		    menuItem_Promotion.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Commodity.add(this.menuItem_Promotion);
		    menuItem_Promotion.addActionListener(this);
		    menuItem_Coupon.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Commodity.add(this.menuItem_Coupon);
		    menuItem_Coupon.addActionListener(this);
		    menubar.add(menu_Commodity);
		    this.setJMenuBar(menubar);
		    
		    this.getContentPane().add(new JScrollPane(this.dataTableCategory), BorderLayout.WEST);
		    this.dataTableCategory.addMouseListener(new MouseAdapter (){

		    	
		    	
				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmAdminMain.this.dataTableCategory.getSelectedRow();
					if(i<0) {
						return;
					}
					FrmAdminMain.this.reloadCommodityInformationTabel(i);
				}
		    	
		    });
		    this.getContentPane().add(new JScrollPane(this.dataTableCommodity), BorderLayout.CENTER);
		    
		    this.reloadFreshCategoryTable();
		    
		    
		    //状态栏
		    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		    JLabel label=new JLabel("您好!");//修改成   您好！+登陆用户名
		    statusBar.add(label);
		    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		    this.addWindowListener(new WindowAdapter(){   
		    	public void windowClosing(WindowEvent e){ 
		    		System.exit(0);
	             }
	        });
		    this.setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this.menuItem_ChangePwd){
				FrmAdminChangePwd dlg=new FrmAdminChangePwd();
				dlg.setVisible(true);
	
			}

			else if(e.getSource()==this.menuItem_CommodityAdd){
				
					if(this.curCategory==null) {
						JOptionPane.showMessageDialog(null, "请选择分类", "错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
						FrmCommodityAdd dlg=new FrmCommodityAdd();
						dlg.category=curCategory;
						dlg.setVisible(true);
				
			}
			else if(e.getSource()==this.menuItem_CommodityDelete){
				int i=FrmAdminMain.this.dataTableCommodity.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					SXCSUtil.commodityManager.deleteCommodity(this.categoryCommodity.get(i));
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else if(e.getSource()==this.menuItem_CategoryDelete) {
				if(this.curCategory==null) {
					JOptionPane.showMessageDialog(null, "请选择分类", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					SXCSUtil.commodityManager.deleteCategory(this.curCategory);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
			else if(e.getSource()==this.menuItem_CategoryAdd) {
				FrmCategoryAdd dlg=new FrmCategoryAdd();
				dlg.setVisible(true);
			}
			
			else if(e.getSource()==this.menuItem_CommodityPurchase){
				int i=FrmAdminMain.this.dataTableCommodity.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			
					FrmPurchase dlg=new FrmPurchase();
					dlg.commodity=this.categoryCommodity.get(i);
					dlg.setVisible(true);
				
			}
		
			else if(e.getSource()==this.menuItem_CommodityPurchaseList){
				FrmPurchaseList dlg =new FrmPurchaseList();
				dlg.setVisible(true);
				 
			}
			else if(e.getSource()==this.menuItem_MenuManager) {
				FrmMenu dlg =new FrmMenu();
				dlg.setVisible(true);
			}
			else if(e.getSource()==this.menuItem_CommodityDiscount){
				//FrmBookLendSearch dlg=new FrmBookLendSearch(this,"图书借阅情况查询",true);
				//dlg.setVisible(true);
			}
			else if(e.getSource()==this.menuItem_Promotion){
				FrmPromotion dlg=new FrmPromotion();
				dlg.setVisible(true);
			}
			else if(e.getSource()==this.menuItem_Coupon){
				//FrmBookLendStatic dlg=new FrmBookLendStatic(this,"图书借阅统计",true);
				//dlg.setVisible(true);
			}
			
		}
		
		
	}

