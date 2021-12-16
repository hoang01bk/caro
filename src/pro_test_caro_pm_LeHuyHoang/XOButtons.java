/**
 * Copyright Luvina Company 2021 lhhoang
 * XOButtons lhoang
 */
package pro_test_caro_pm_LeHuyHoang;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * lớp XOButtons khỏi tạo các nút XO
 * @author Admin
 *
 */
public class XOButtons extends JButton {
	//khai báo thuộc tính kiểu  ImageIcon quân cò X
	private ImageIcon X;
	//lhai báo thuộc tính kiểu ImageIcon cho O
	private ImageIcon O;
	//tạo thuộc tính kiểu point cho nút
	public Point point;
	//biến kiểm tra lượt đánh
	public static boolean isXMove = true;
	// giá trị kiểu String cho nút
	public String value = "";
/**
 * contructor khỏi tạo nút
 * @param x tọa độ x
 * @param y tọa độ y
 */
	public XOButtons(int x, int y) {
		//gán ảnh cho X
		X = new ImageIcon(this.getClass().getResource("X.png"));
		//gán ảnh cho O
		O = new ImageIcon(this.getClass().getResource("O.png"));
		//căn chỉnh theo chieu ngang cho ảnh  
		setHorizontalAlignment(SwingConstants.CENTER);
		//căn chỉnh theo chiều dọc cho ảnh
		setVerticalAlignment(SwingConstants.CENTER);
		this.point = new Point(x, y);
	}
/**
 * cài đặt giá trị cho nút với mỗi lượt đánh
 * @param isXMove lượt đánh
 */
	public void setState(Boolean isXMove) {
		//nếu X (người) đánh
		if (isXMove) {
			//gán ảnh X cho nút đó
			this.setIcon(X);
			//gán giá trị cho nút đó
			this.value = "X";
			//chuyển lượt đánh sang O(máy)
			XOButtons.isXMove = false;
			//nếu O(máy) đánh
		} else {
			//gán ảnh O cho nút đó
			this.setIcon(O);
			//gán giá trị cho nút đó
			this.value = "O";
			//chuyển lượt đánh sang X(người)
			XOButtons.isXMove = true;
		}
	}

}
