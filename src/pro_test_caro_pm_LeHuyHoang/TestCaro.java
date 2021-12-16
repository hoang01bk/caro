/**
 * Copyright Luvina Company 2021 lhhoang
 * TestCaro 
 */
package pro_test_caro_pm_LeHuyHoang;

import java.awt.EventQueue;



/**
 * lớp Testcaro là lớp chứa hàm main
 * @author lhhoang
 *
 */
public class TestCaro {

	/**
	 * hàm main gọi ra giao dien
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAndAction window = new GuiAndAction();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
