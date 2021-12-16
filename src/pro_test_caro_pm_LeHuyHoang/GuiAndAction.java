/**
 * Copyright Luvina Company 2021 lhhoang
 * GUI lhhoang
 */
package pro_test_caro_pm_LeHuyHoang;

import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Lớp GUI tạo ra giao dien ban co và chơi cờ
 * @author lhhoang
 *
 */
public class GuiAndAction {
	//tạo thuộc tính kiểu JFrame đẻ tạo ra màn hình
	public JFrame frame;
	//thuộc tính cho số hàng
	private int row = 20;
	//thuộc tính cho số cột
	private int col = 20;
	//mảng 2 chiều để chứa các nút
	public XOButtons[][] Buttons = new XOButtons[row][col];
	//tạo một panel để hiện thi ma trận nút
	JPanel panel = new JPanel();
	/**
	 * contructor khởi tạo bàn cờ
	 */
	public GuiAndAction() {
		//phương thức khởi tạo bàn cờ
		initialize();
	}
	/**
	 * giao dien
	 */
	public void initialize() {
		//tạo frame
		frame = new JFrame();
		//cài đặt kích thước
		frame.setSize(600,600);
		//kích thước cố định
		frame.setResizable(false);
		//đóng frame thì đóng chương trình
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//tạo ma trận nút trong panel
		panel.setLayout(new GridLayout(row,col));
		//với từng phần tử trong mảng
		for(int i = 0; i < Buttons.length; i++) {
			for(int j = 0; j < Buttons.length; j++ ) {
				//gán tọa độ cho nút đó
				Point point = new Point(i, j);
				//tạo nút lại tọa độ trên
				Buttons[i][j] = new XOButtons(i, j);
				//tạo sự kiện cho nút với thao tác click chuột
				MouseListener moseEvent = new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						try {
							//gọi ra đối tượng xử lí của lớp Handle
							//gọi phương thức xử lý sự kiện
							handleClick(point);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
					
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						
					}
				}; 
				//thêm nút vào trong panel
				panel.add(Buttons[i][j]);
				//thêm sự kiện vào nút
				Buttons[i][j].addMouseListener(moseEvent);
				
			}
		}
		//thêm panel vào frame
		frame.add(panel);
		//hiển thị frame
		frame.setVisible(true);
	}
	
	
	
	/**
	 * Xử lý
	 */
	
	
	
	/**
	 * hàm xử lý sự kiện
	 * @param point điểm được chọn
	 * @throws Exception
	 */
	
	public void handleClick(Point point) throws Exception {
		//cài đặt giá trị cho X(nguoi danh)
		Buttons[point.x][point.y].setState(true);
		//kiểm tra người thắng
		if(checkWin(point)) {
			//nếu thắng thì hien thong bao
			System.out.println("nguoi thang");
			JOptionPane.showConfirmDialog(frame,"nguoi thang"); 
			//System.exit(0);
		}
		//lấy ra điểm máy  vứa đánh
		Point check = MayDanh(point);
		//kiểm tra máy thắng
		if(checkWin(check)) {
			//hiển thị thông báo
			System.out.println("may thang");
			JOptionPane.showConfirmDialog(frame,"may thang"); 
			//System.exit(0);
		}
		
	}
	/**
	 * hàm máy đánh
	 * @param point điểm người vứa đánh
	 * @return điểm máy vừa đánh
	 * @throws Exception
	 */
	public Point MayDanh(Point point) throws Exception {
		//khởi tạo bufferedReader để đọc file
		 BufferedReader input = null;
		 //điểm máy sẽ đánh
		 Point truePoint = null;
		 try {
			 //đọc file config.properties để lấy ra path của file thế cờ
			 Properties properties = new Properties();
			 //load file cònig
			 properties.load(new FileInputStream("config.properties"));
			 //láy đường dẫn file thế cờ
			 String urlSource = properties.getProperty("url");
			 //khởi tạo ma trận  5*5
			 String[][] checkMatrix = new String[5][5];
			 //chuẩn bị để đọc file thế cờ
			 FileReader read = new FileReader(urlSource);
			 input = new BufferedReader(read);
			 //khoi tao String để xử lí dữ liệu khi đọc file
			 String s = "";
			 //đếm số thế cờ
			 int step = 0;
			 loop1:
				 //đọc từng thế cờ sau đó kiểm tra
			 while (true) {
				 //số thế cờ tằng lên 1
				 step++;
				 for(int i = 0; i < 5; i++) {
					 //đọc từng dòng trong file thế cờ
					 s = input.readLine();
					 //kiểm tra đọc hết file chua
					 if(s == null) {
						 //neu doc het thi may se danh random 1 nước
						 truePoint = danhRandom(point);
						 System.out.println("danh random");
						 //thoát khỏi vòng lặp loop1, hết lượt máy đánh đến lượt người
						 break loop1;
					 }
					 for(int j = 0; j < 5; j++) {
						 //láy từng kí tự trong mỗi dòng đưa vào ma trận 5*5
						 checkMatrix[i][j] = Character.toString(s.charAt(j));
					 }
				 }
				 //khỏi tạo biến để kiểm tra ma trận thế cờ với từng ma trận con trong ban cờ
			 boolean checked = true;
			 
			loop2:// vòng lặp kiểm tra ma trận thế cờ với từng ma trận con trên ban cờ đẻ tính nước đi
				//kiểm tra theo hang 
				for(int hang = 0; hang < 16; hang++) {
					//kiem tra theo cot
					for(int cot = 0; cot < 16; cot++) {
						//bat dau voi check bang tru
						checked = true;
						loop3://so sánh 2 ma trận với nhau, ma trận bàn cờ và ma trạn thế cờ
							//láy từng phần tử thep hang
							for(int i = 0; i < 5;i++) {
								//theo cột
								for(int j = 0; j < 5; j++) {
									//lây đôi tượng String trong ma trận thế cờ
									String checkingPoint = checkMatrix[i][j];
									//lấy ra điểm trong bàn cờ có vị trí tương xứng với điểm trong thế 
									Point pointBoardCheck = new Point(i+hang,j+cot);
									//láy giá trị của điểm cần kiểm tra trên bàn cờ
									String checkingPointBoard = Buttons[pointBoardCheck.x][pointBoardCheck.y].value;
									//so sánh 2 mận với nhau
									if((checkingPoint.equals("O") && checkingPointBoard == "O")
										|| (checkingPoint.equals("X") && checkingPointBoard == "X")
										|| (checkingPoint.equals("D") && checkingPointBoard == "")
										|| (checkingPoint.equals("G"))) {
										//neu 2 ma trận trung nhau kiểm tra điểm máy cần đánh
									}else if (checkingPoint.equals("T") && checkingPointBoard == "") {
										//láy ra điểm máy cần đánh vào
										truePoint = new Point(i+hang,j+cot);
									}else {
										//2 ma trận khongo trung nhau thì kiêm tra thất bai
										checked = false;
										//thoát khỏi vòng lặp loop3 để kiểm tra với ma trận con khác
										break loop3;
									}
								}
							}
						//nếu kiểm tra đúng
						if(checked) {
							//máy đánh
							Buttons[truePoint.x][truePoint.y].setState(false);
							//hiển thị thông báo
							System.out.println("may da danh");
							System.out.println("ma tran thu:"+step);
							// thoát khỏi vòng lặp loop ngừng so sánh ma trận thế cờ với từng ma trận con
							break loop2;
						}
						
					}
				}
			//nếu kiểm tra đúng
			 if(checked)
			 {//ngừng lấy ra file thế cờ để kiểm tra
				 break loop1;
			 }
			 
			 }
			 }finally {
				 //đóng file
			 if(input != null) {
				 input.close();
			 }
		 }// trả về điểm máy đánh
		 return truePoint;
	 }
	/**
	 * hám để máy đánh vào 1 trong 8 vị trí xung quanh vị trí người vừa đánh
	 * @param point điểm người vừa đánh
	 * @return điểm máy vứa đánh
	 */
	public Point danhRandom(Point point) {
		//tạo ra random 1 số nguyên từ 0 đến 7
		 int random = ThreadLocalRandom.current().nextInt(0,8);
		 //điểm máy sẽ đánh
		 Point truePoint = null;
		 //coi điểm người vừa đánh là điểm chính giuawx trong ma trận 3*3
		 
		 //random = 0 và điểm góc trái trên của ma trận 3*3 tróng thì đánh vào đó
		 if(random == 0 && Buttons[point.x - 1][point.y - 1].value == "") {
			 Buttons[point.x - 1][point.y - 1].setState(false);
			 //láy ra điểm máy vuawf đánh
			 truePoint = Buttons[point.x - 1][point.y - 1].point;
			 
			// tương tự đánh vào các vị trí còn lại trong ma trận 3*3 
			 //với điểm ngưofi đánh ở chính giữa
			 
			 //đánh vào điểm giua của hàng dầu tiên
		 }else if(random == 1 && Buttons[point.x - 1][point.y].value == "") {
			 Buttons[point.x - 1][point.y].setState(false);
			 truePoint = Buttons[point.x - 1][point.y].point;
			 
			 //danh vào góc phải trên
		 }else if(random == 2 && Buttons[point.x - 1][point.y + 1].value == "") {
			 Buttons[point.x - 1][point.y + 1].setState(false);
			 truePoint = Buttons[point.x - 1][point.y + 1].point;
			 
			 //dánh vào dong 2 ,cột1 của ma trận 3*3
		 }else if(random == 3 && Buttons[point.x][point.y - 1].value == "") {
			 Buttons[point.x][point.y - 1].setState(false);
			 truePoint = Buttons[point.x][point.y - 1].point;
			
			 // dánh vào vị trí dòng 2 cột 3
		 }else if(random == 4 && Buttons[point.x][point.y + 1].value == "") {
			 Buttons[point.x][point.y + 1].setState(false);
			 truePoint = Buttons[point.x][point.y + 1].point;
			 
			 //dòng 3 cột 1
		 }else if(random == 5 && Buttons[point.x + 1][point.y - 1].value == "") {
			 Buttons[point.x + 1][point.y - 1].setState(false);
			 truePoint = Buttons[point.x + 1][point.y - 1].point;
			 
			 //dòng 3 cột 2
		 }else if(random == 6 && Buttons[point.x + 1][point.y].value == "") {
			 Buttons[point.x + 1][point.y].setState(false);
			 truePoint = Buttons[point.x + 1][point.y].point;
			 
			 //dòng 3 cột 3. 
		 }else if(random == 7 && Buttons[point.x + 1][point.y + 1].value == "") {
			 Buttons[point.x + 1][point.y + 1].setState(false);
			 truePoint = Buttons[point.x + 1][point.y + 1].point;
		 }
		 //trả về điểm máy vuawf đánh
		 return truePoint;
}
	
	/**
	 * kiểm tra thắng
	 * @param point điểm người vuawf đanh hoặc máy vuawf đánh
	 * @return true nếu 1 bên thắng, false khi chuaw bên nào thắng
	 */
	public boolean checkWin(Point point) {
		//biến kiểm tra số điểm thẳng hàng
		int diem = 0; 
		//vị trí của hàng
		int hang = point.x; 
		//vị trí cột
		int cot = point.y;
		// kiểm tra hàng
		//nếu điêm kề nhau theo hàng bằng nhau
		while (Buttons[hang][cot].value == Buttons[point.x][point.y].value) {
		    //đeẻm thẳng hàng +1
			diem++;
			//kiểm tra điểm bên cạnh với cột lớn hơn
			cot++;
			//thoát nếu cột >19
			if(cot > 19) {
				break;
			}
		}
		//quay lại kiểm tra với điểm cùng hàng với cột nhỏ hơn
		cot = point.y - 1;
		while (Buttons[hang][cot].value == Buttons[point.x][point.y].value) {
			//đierm thẳna hàng tằng lên
			diem++;
			//kiểm tra cột nhỏ hơn
			cot--;
			if(cot < 0) {
				break;
			}
		}
		//nếu số diểm thẳng hàng lớn hơn 4 là win
		if (diem > 4) return true;
		//tương tự
		// kiểm tra theo cột
		diem = 0; cot = point.y ; hang = point.x;
		while(Buttons[hang][cot].value == Buttons[point.x][point.y].value) {
			diem++;
			hang++;
			if(hang > 19) {
				break;
			}
		}
		hang = point.x - 1;
		while(Buttons[hang][cot].value == Buttons[point.x][point.y].value) {
			diem++;
			hang--;
			if(hang < 0) {
				break;
			}
		}
		if (diem > 4) return true;
		// kiểm tra đường chéo 1 (hangf ,cột cùng tăng hoặc, cùng giảm)
		hang = point.x; cot = point.y; diem = 0;
		while (Buttons[hang][cot].value == Buttons[point.x][point.y].value) {
			diem++;
			hang++;
			cot++;
			if(hang > 19 || cot > 19) {
				break;
			}
		}
		hang = point.x - 1; cot = point.y - 1;
		while (Buttons[hang][cot].value == Buttons[point.x][point.y].value) {
			diem++;
			hang--;
			cot--;
			if(hang < 0 || cot < 0) {
				break;
			}
		}
		if (diem > 4) return true;
		// kiểm tra đường chéo 2 (hàng tăng, cột giảm hoặc hàng giảm cột tăng)
		hang = point.x; cot = point.y; diem = 0;
		while (Buttons[hang][cot].value == Buttons[point.x][point.y].value) {
			diem++;
			hang++;
			cot--;
			if(hang > 19 || cot < 0) {
				break;
			}
		}
		hang = point.x - 1; cot = point.y + 1;
		while (Buttons[hang][cot].value == Buttons[point.x][point.y].value) {
			diem++;
			hang--;
			cot++;
			if(hang < 0 || cot > 19) {
				break;
			}
		}
		if (diem > 4) return true;
		// nếu không đương chéo nào thỏa mãn thì trả về false.
		return false ;
	}

	
}
