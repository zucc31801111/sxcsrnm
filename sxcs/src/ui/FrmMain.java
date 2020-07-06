package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.FrmLogin;

public class FrmMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */private FrmLogin dlgLogin=null;
		private JPanel statusBar = new JPanel();
	public FrmMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
	this.setTitle("个人计划管理系统");
	dlgLogin=new FrmLogin();
	dlgLogin.setVisible(true);
	}
	

	/**
	 * Create the frame.
	 */
	

}
