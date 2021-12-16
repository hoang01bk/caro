/**
 * Copyright Luvina Company 2021 lhhoang
 * Point lhhoang
 */
package pro_test_caro_pm_LeHuyHoang;

/**
 * tạo các điểm với tọa độ x,y
 * @author lhhoang
 *
 */
public class Point {
	//khai báo thuộc tính là các tọa độ x và y
	public int x, y;
	/**
	 * contructor khởi tạo đối tượng
	 * @param x hoành độ
	 * @param y tung độ
	 */
	public Point(int x, int y) {
		//hoành độ
		this.x = x;
		//tung độ
		this.y = y;
	}
/**
 * 
 * @return
 */
//	public Point findStartXPoint() {
//		int startX = x - 5 < 0 ? 0 : x - 5;
//		return new Point(startX, y);
//	}
//	public Point findEndXPoint() {
//		int endX = x + 5 > 19 ? 19 : x + 5;
//		return new Point(endX, y);
//	}
//	public Point findStartYPoint() {
//		int startY = y - 5 < 0 ? 0 : y - 5;
//		return new Point(x, startY);
//	}
//	public Point findEndYPoint() {
//		int endY = y + 5 > 19 ? 19 : y + 5;
//		return new Point(x, endY);
//	}
//	public Point findLeftTopPoint() {
//		int startX = x - 5 < 0 ? 0 : x - 5;
//		int startY = y - 5 < 0 ? 0 : y - 5;
//		return new Point(startX, startY);
//	}
//	public Point findRightTopPoint() {
//		int endX = x + 5 > 19 ? 19 : x + 5;
//		int startY = y - 5 < 0 ? 0 : y - 5;
//		return new Point(endX, startY);
//	}
//	public Point findLeftBottomPoint() {
//		int startX = x - 5 < 0 ? 0 : x - 5;
//		int endY = y + 5 > 19 ? 19 : y + 5;
//		return new Point(startX, endY);
//	}
//	public Point findRightBottomPoint() {
//		int endX = x + 5 > 19 ? 19 : x + 5;
//		int endY = y + 5 > 19 ? 19 : y + 5;
//		return new Point(endX, endY);
//	}

}
