package LOGIN;
import java.awt.*;
import javax.swing.*;
import java.io.*;


import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;

import LOGIN.PeopleA;



public class Board extends JFrame implements ActionListener {
	

	JLabel label_in, label_out, in_m, out_m, d_label ; //가계부 라벨 변수
	JTextField field_in, in_memo, field_out, out_memo, total; // 가계부 텍스트필드 변수
	JButton insert, insert2, view_in, view_out ; //가계부 버튼 변수
	JFrame J1; //프레임(가계부에 이용)
	JTextArea t; //메모창 띄움
	
	private JButton PeopleAdd; //주소록 추가 버튼
	private JTextArea People; //주소록 출력 버튼
	
	JButton[]lbl=new JButton[32];
	JPanel J=new JPanel();
	JPanel topPane = new JPanel();
	JButton prevBtn = new JButton("◀");
	JButton nextBtn = new JButton("▶");
	JLabel yearLbl = new JLabel("년");
	JLabel monthLbl = new JLabel("월");
	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
	JComboBox<Integer> monthCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();

	JPanel centerPane = new JPanel(new BorderLayout());//센터에는 NORTH SOUTH EAST WEST CENTER 배치관리자 적용
	JPanel titlePane = new JPanel(new GridLayout(1, 7));//7개열을 가지는 배치관리 작용
	String titleStr[] = {"일", "월", "화", "수", "목", "금", "토"};
	JPanel datePane = new JPanel(new GridLayout(0, 7)); //1개의 행과 7개의 열을 가지는 배치
	
	
	Calendar now;
	int year, month, date;


	public Board() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);	//자원 해제 후 종료
		now = Calendar.getInstance();	//현재 날짜
		year = now.get(Calendar.YEAR);
		month = now.get(Calendar.MONTH)+1;
		date = now.get(Calendar.DATE);
		topPane.add(prevBtn);

		for(int i=year-100; i<=year+50; i++){ //현재년도에서 100년전 50년후 년도모델을 yearModel에 할당
			yearModel.addElement(i);
		}
		yearCombo.setModel(yearModel); //콤보에 yearModel 적용
		yearCombo.setSelectedItem(year);	//현재 년도 선택
		topPane.add(yearCombo); //상단에 콤보 적용
		topPane.add(yearLbl);//상단에 "년" 적용

		for(int i=1; i<=12; i++){ //1월~12월 달모델에 적용
			monthModel.addElement(i);
		}
		monthCombo.setModel(monthModel); // 달모델을 month 콤보에 할당
		monthCombo.setSelectedItem(month);	//현재 월 선택
		topPane.add(monthCombo); //상단에 콤보 적용
		topPane.add(monthLbl); //상단에 "달" 적용
		topPane.add(nextBtn); //상단에 다음으로 넘기는 버튼 적용
		topPane.setBackground(new Color(100, 200, 200)); //상단에 배경색 적용
		topPane.setBounds(20,20,350,40);
		J.add(topPane); //최종적으로 상단바 적용


//센터 부분
		titlePane.setBackground(Color.white);
		for(int i=0; i<titleStr.length; i++){ //월화수목금토일 7개의 배열
			JLabel lbl = new JLabel(titleStr[i], JLabel.CENTER); // 월화수목금토일 라벨을 적용
			if(i == 0){
				lbl.setForeground(Color.red);
			}else if(i == 6){
				lbl.setForeground(Color.blue);
			}
			titlePane.add(lbl); // 일주일 배열 상중단 바에 적용
		}
		centerPane.add(titlePane, "North");//날짜 출력
		dayPrint(year, month);
		centerPane.add(datePane, "Center");//date패널을 센터패널에 추가한다
		centerPane.setBounds(20,60,350,260);
		centerPane.setBackground(Color.white);
		J.add(centerPane);//센터패널을 프레임에 추가한다
		
		datePane.setBackground(Color.WHITE);
	
		//메인
		setSize(700,730);//보드 크기 설정
		J.setLayout(null);
		J.setBackground(new Color(200,191,231));
		JButton back=new JButton("색상 변경");
		back.setBackground(Color.orange);
		back.setBounds(575,625,100,30);
		J.add(back);
		back.addActionListener(e->{
			int R=(int)(Math.random()*256);
			int G=(int)(Math.random()*256);
			int B=(int)(Math.random()*256);
			Color color=new Color(R,G,B);
			J.setBackground(color);//색상 랜덤으로 변경
		
		});
	
		
   	 	t = new JTextArea(); //메모가 나올 JTextArea 생성
   	 	JScrollPane s2=new JScrollPane(t);//스크롤패인 생성
		s2.setBounds(375,40,300,280);
   	 	J.add(s2);
   	 
 
   	 	
   	 	d_label=new JLabel("일");
   	 	d_label.setBounds(400,18,50,20);
   	 	d_label.setFont(new Font("SanSerif",Font.BOLD,15));
   	 	J.add(d_label);
		
		prevBtn.addActionListener(this);// 이벤트 적용
		nextBtn.addActionListener(this);// 이벤트 적용
		yearCombo.addActionListener(this);// 이벤트 적용
		monthCombo.addActionListener(this);// 이벤트 적용
		
   	 	
		//주소록 시작
		PeopleAdd=new JButton("주소록 추가");
		PeopleAdd.setBounds(200,610,150,30);
		PeopleAdd.setFont(new Font("DialogInput",Font.BOLD,16));
		PeopleAdd.setBackground(Color.white);
		J.add(PeopleAdd);
		PeopleAdd.addActionListener(e->{
			PeopleA A=new PeopleA();
		});
		
		People=new JTextArea();
		People.setEditable(false);
		People.setFont(new Font("DialogInput",Font.PLAIN,12));
		JScrollPane s=new JScrollPane(People);
		s.setBounds(20,330,330,270);
		
		try {
			BufferedReader in=new BufferedReader(new FileReader("주소록.txt"));
			String line;
			People.append("\n");
			while((line=in.readLine())!=null) {
				People.append("  "+line+" \r\n");
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		JButton refresh=new JButton("새로고침");//주소록 추가를 한 후 새로고침을 해줘야 입력한 정보가 나옴
		refresh.setBounds(20,610,100,30);
		refresh.setBackground(Color.WHITE);
		J.add(refresh);
		refresh.addActionListener(e->{
			try {
				BufferedReader in=new BufferedReader(new FileReader("주소록.txt"));
				String line;
				People.setText(null);
				People.append("\n");
				while((line=in.readLine())!=null) {
					People.append("  "+line+" \r\n");//JTextArea People에 새로운 내용 다시 씀
				}
			}catch(IOException e1) {
				e1.printStackTrace();
			}	
		});
		People.setCaretPosition(MAXIMIZED_HORIZ);//스크롤 위치 맨 위로
		J.add(s);
		//주소록 끝
		

		
		
		//가계부 시작
	    ButtonListener3 listener3 = new ButtonListener3(); // 버튼 객체 생성
	    ButtonListener4 listener4 = new ButtonListener4();
	    
		in_m=new JLabel("용도"); //수입 용도 라벨 생성
		J.add(in_m);
		in_m.setBounds(400,430,100,20);
		   
		out_m=new JLabel("용도"); //지출 용도 라벨 생성
		J.add(out_m);
		out_m.setBounds(400,490,100,20);	    
		   
		in_memo=new JTextField(15); //수입 용도 입력 필드 생성
		J.add(in_memo);
	    in_memo.setBounds(450,430,100,20);	       
		   
		out_memo=new JTextField(15); //지출 용도 입력 필드 생성
		J.add(out_memo);
	    out_memo.setBounds(450,490,100,20);		    
		    
		label_in = new JLabel("수입"); //수입 라벨 생성  
	    J.add(label_in);
		label_in.setBounds(400,460,100,20);
		
	    label_out = new JLabel("지출"); //지출 라벨 생성
	    J.add(label_out);
		label_out.setBounds(400,520,100,20);
	   
	    field_in = new JTextField(10); //수입 입력 필드 생성
	    J.add(field_in);
	    field_in.setBounds(450,460,100,20);	    
	    
	    field_out = new JTextField(10); //지출 입력 필드 생성
	    J.add(field_out);
	    field_out.setBounds(450,520,100,20);	    
	    
	    insert = new JButton("수입 입력"); //수입 입력 버튼 생성
	    J.add(insert);
	    insert.setBounds(560,440,100,30);
	    insert.setBackground(Color.WHITE); 
	   
	    insert2 =new JButton("지출 입력"); //지출 입력 버튼 생성  
	    J.add(insert2);
	    insert2.setBounds(560,500,100,30);
	    insert2.setBackground(Color.WHITE);
	    
	    view_in=new JButton("수입 내역"); //수입 내역 버튼 생성
	    J.add(view_in);
	    view_in.setBounds(410,570,100,30);
	    view_in.setBackground(Color.WHITE);	    
	    
	    view_out=new JButton("지출 내역"); //지출 내역 버튼 생성
	    J.add(view_out);
	    view_out.setBounds(520,570,100,30);
	    view_out.setBackground(Color.WHITE);	       
	   
	    JLabel Money=new JLabel("<가계부>");//가계부 라벨 생성
	    Money.setBounds(460,330,150,50);
	    Money.setFont(new Font("Monospaced",Font.BOLD, 25));
	    J.add(Money);
	    
	    JLabel Total=new JLabel("현재 잔고");//현재 잔고 라벨 출력
	    Total.setBounds(420,390,100,20);
	    total=new JTextField(10);
	    J.add(Total);
	    J.add(total);
	    total.setBackground(Color.WHITE);
	    total.setBounds(500,390,100,20);
	    total.setEditable(false);
	    try{int sum=0;
		String [] array=null;
		String s1;
		BufferedReader User=new BufferedReader(new FileReader("현재잔고.txt"));
		while((s1=User.readLine())!=null) {
			array=s1.split("/");
		}
		sum=Integer.parseInt(array[0]);
		total.setText(""+sum);
	    }catch(Exception ex){
	    	ex.printStackTrace();
		}
	    
	    total.addActionListener(listener3);
	    out_memo.addActionListener(listener3);
	    in_memo.addActionListener(listener3);
	    insert.addActionListener(listener3);
	    insert2.addActionListener(listener3);
	    field_in.addActionListener(listener3);
	    field_out.addActionListener(listener3); 
	    view_in.addActionListener(listener4);
	    view_out.addActionListener(listener4);//가계부 관련 버튼 및 필드 이벤트 처리
	   //가계부 끝
	    
	    
	    JButton Logout=new JButton("로그아웃");
	    Logout.setBounds(470,625,100,30);
	    Logout.setBackground(Color.orange);
	    J.add(Logout);//로그아웃 버튼 생성
	    Logout.addActionListener(e->{
	    	Diary D1=new Diary();
	    	dispose();
	    });
	    JButton Finish=new JButton("종료");
	    Finish.setBounds(395,625,70,30);
	    Finish.setBackground(Color.orange);
	    J.add(Finish);//종료 버튼 생성
	    Finish.addActionListener(e->{
	    	dispose();
	    });
	    add(J);		
		setVisible(true);

	}
	

//이벤트 처리 메소드!!!
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj instanceof JButton){
			JButton eventBtn = (JButton)obj;
			int yy = (Integer)yearCombo.getSelectedItem();
			int mm = (Integer)monthCombo.getSelectedItem();
			if(eventBtn.equals(prevBtn)){	//전달
				if(mm==1){
					yy--; mm=12;
				}else{
					mm--;
				}				//prev버튼을 누를시에 1월에서 12월로 이동
			}else if(eventBtn.equals(nextBtn)){	//다음달
				if(mm==12){
					yy++; mm=1;
				}else{
					mm++;
				}
			}// next버튼 누를때 12월은 1월로 이동

			yearCombo.setSelectedItem(yy);
			monthCombo.setSelectedItem(mm);
		}else if(obj instanceof JComboBox){	//콤보박스 이벤트 발생시
			createDayStart();
		}
	}

	public void createDayStart(){
		datePane.setVisible(false);	//패널 숨기기 그래야 매달마다 일자가 바뀜
		datePane.removeAll();	//날짜 출력한 라벨 지우기
		dayPrint((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());
		datePane.setVisible(true);	//패널 재출력	
		

	}	

	public void dayPrint(int y, int m){
		ButtonListener listener=new ButtonListener();
		Calendar cal = Calendar.getInstance();
		cal.set(y, m-1, 1);	//출력할 첫날의 객체 만든다.
		int week = cal.get(Calendar.DAY_OF_WEEK);	//1일에 대한 요일	일요일 : 0
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);	//그 달의 마지막 날
		for(int i=1; i<week; i++){	//날짜 출력 전까지의 공백 출력
			datePane.add(new JLabel(" "));
		}
		;
		
		for(int i=1; i<=lastDate; i++){
			lbl[i] = new JButton(String.valueOf(i)); //날짜 선택할 수 있는 버튼 생성
			lbl[i].setBackground(new Color(250,250,100));
			lbl[i].addActionListener(listener);
			if(y==now.get(Calendar.YEAR)&&m==now.get(Calendar.MONTH)+1&&i==now.get(Calendar.DATE)) {//현재 날짜 버튼 색깔 다르게 함
				lbl[i].setForeground(Color.WHITE);
				lbl[i].setBackground(Color.ORANGE);
				}
			cal.set(y, m-1, i);
			int outWeek = cal.get(Calendar.DAY_OF_WEEK);
			if(outWeek==1){
				lbl[i].setForeground(Color.red); //일요일인 날 글자색 설정			
			}else if(outWeek==7){
				lbl[i].setForeground(Color.BLUE); //토요일인 날 글자색 설정
			}
			datePane.add(lbl[i]);
			}
	}	
	private class ButtonListener implements ActionListener{//메모장 이벤트 처리
		 public void actionPerformed(ActionEvent e) {
			 for(int i=0;i<32;i++) {
		           
		         if(e.getSource()==lbl[i]) {
		            int j = i;
		            t.setText("");
		            t.setFont(new Font("Serif",Font.PLAIN,14));
		        	 d_label.setText(i+"일");
		        	 JMenuBar menuBar=new JMenuBar();
		        	 JMenuItem save=new JMenuItem("메모 저장");
		        	 JMenuItem load=new JMenuItem("메모 불러오기");
		        	 save.setBackground(Color.WHITE);
		        	 menuBar.add(save);
		        	 menuBar.add(load);
		        	 setJMenuBar(menuBar);
		        	 try {
		        	 String filename="C:\\Users\\Grace\\eclipse-workspace\\Diary\\DateMemo\\"+j+"date.txt"; //경로수정 필요
	                	FileReader r=new FileReader(filename);
	                	if(r.read()!=0) {
	                		JOptionPane.showMessageDialog(null, "저장된 메모가 있습니다. 확인해 주세요.");
	                	}r.close();
		        	 }catch(Exception E) {JOptionPane.showMessageDialog(null, "저장된 메모가 없습니다.");}//클릭한 날짜 j의 기존 메모 내용 존재여부 확인,창 띄우기
		         save.addActionListener(new ActionListener() {
		             public void actionPerformed(ActionEvent e) {
		                 try {
		                	String filename="C:\\Users\\Grace\\eclipse-workspace\\Diary\\DateMemo\\"+j+"date.txt"; //클릭한 날짜 j의 메모 내용을 저장할 주소, 이름을 날짜와 맞춤
		                	FileWriter w = new FileWriter(filename);
		                    String s =" "+ t.getText();
		                    s = s.replace("\n","\r\n");
		                    w.write(s); //메모 내용을 입력받고 저장함. 로드할 때를 위해 " "로 시작하게 함. 
		                    w.close();
		                    JOptionPane.showMessageDialog(null, "저장되었습니다.");
		                    } catch (Exception ex1) {
		                    	JOptionPane.showMessageDialog(null, "저장에 실패하였습니다.");
		                 }  
		            }    
		     }); 
		        load.addActionListener(new ActionListener() {
		             public void actionPerformed(ActionEvent e) {
		             }            
		         }); 
		         load.addActionListener(new ActionListener() {
		             
		             public void actionPerformed(ActionEvent e) {
		                             
		                String filename2="C:\\Users\\Grace\\eclipse-workspace\\Diary\\DateMemo\\"+j+"date.txt"; //클릭한 날짜 j의 메모내용을 불러올 주소  
		                //테스트를 위한 경로 수정 필요
		                  try {
		                       FileReader r = new FileReader( filename2 ); 
		                       if(r.read()==0) {  
		                          return ;//파일 내용이 존재하지 않을 경우 return
		                       }
		                       int k; 
		                       String s ="";
		                       for( ;  ; ) {
		                           k = r.read();// 메모 내용을 읽은 값을 k에 집어넣음
		                           if( k == -1) break;
		                           s += (char)k; 
		                           t.setText(s); //k값이 -1이 아니라면 문자타입으로 변환해 s에 넣어 준 다음 이를 보여준다.
		                       }           
		                       r.close();
		                       
		                    } catch (Exception e2) {
		                    	JOptionPane.showMessageDialog(null, "새로운 메모를 저장하세요.");
		                    } 
		             	}            
		         	}); 
		         }
			 	}
			 }
		 }
	
	 
	   private class ButtonListener3 implements ActionListener{ // 버튼 이벤트 정의
	       
	       public void actionPerformed(ActionEvent e) {
	          if(e.getSource()==insert) {
					try {
						int sum=0;
						String in_get = field_in.getText();
				        int s_add = Integer.parseInt(in_get);
						String s;
						String [] array=null;
						BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\현재잔고.txt"));//현재 잔액을 영구히 기억하기 위해 txt파일에 따로 저장
						while((s=User.readLine())!=null) {
							array=s.split("/");
						}
						sum=Integer.parseInt(array[0]);
						sum+=s_add;
						total.setText(""+sum);//현재 잔고 출력
								
						BufferedWriter NUser=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\현재잔고.txt",false));//수입 계산 후 잔액 다시 작성
						NUser.write(sum+"/");
						in_memo.requestFocus();
						User.close();
						NUser.close();
						
						String s2;
						String[] array2;
						BufferedWriter NUser2=new BufferedWriter(new FileWriter("C:\\Users\\\\Grace\\eclipse-workspace\\Diary\\수입내역.txt",true));//수입내역 저장
						NUser2.write(in_memo.getText()+"/");
						NUser2.write(field_in.getText()+"\r\n");
						NUser2.close();
					 
					
					}
	          
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
	          if(e.getSource()==insert2) {
					try {
						int sum=0;
						String out_get = field_out.getText();
				        int s_minus = Integer.parseInt(out_get);
						String s;
						String [] array=null;
						BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\현재잔고.txt"));//현재 잔액을 영구히 기억하기 위해 txt파일에 따로 저장
						while((s=User.readLine())!=null) {
							array=s.split("/");
						}
						sum=Integer.parseInt(array[0]);
						sum-=s_minus;
						total.setText(""+sum);//현재 잔고 출력
								
						BufferedWriter NUser=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\현재잔고.txt",false));//지출 계산 후 잔액 다시 작성
						NUser.write(sum+"/");
						out_memo.requestFocus();
						User.close();
						NUser.close();
						
						String s2;
						String[] array2;
						BufferedWriter NUser2=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\지출내역.txt",true));//지출내역 저장
						NUser2.write(out_memo.getText()+"/");
						NUser2.write(field_out.getText()+"\r\n");
						NUser2.close();
						
					 }
	          
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
	       }
	   private class ButtonListener4 implements ActionListener{//회원가입 이벤트처리
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==view_in) {//수입내역 버튼을 눌렀을 때 수입내역 파일을 불러옴
					try {
						J1=new JFrame();
						J1.setBounds(500,500,300,300);
						JTextArea view=new JTextArea();
						view.setEditable(false);
						view.setFont(new Font("DialogInput",Font.PLAIN,15));
						JScrollPane s=new JScrollPane(view, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//스크롤 생성
						s.setSize(300,300);
						J1.add(s);
						BufferedReader in=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\수입내역.txt"));
						String line;
						while((line=in.readLine())!=null) {
							view.append("  "+line+" \r\n");
						}
						in.close();
						J1.setVisible(true);
					}catch(IOException e1) {
						e1.printStackTrace();}
				}
				else if(e.getSource()==view_out) {//지출내역 버튼을 눌렀을 때 수입내역 파일을 불러옴
					try{
						J1=new JFrame();
						J1.setBounds(500,500,300,300);
						JTextArea view=new JTextArea();
						view.setEditable(false);
						view.setFont(new Font("DialogInput",Font.PLAIN,15));
						JScrollPane s=new JScrollPane(view, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//스크롤 생성
						s.setSize(300,300);
						J1.add(s);
						
						BufferedReader in=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\지출내역.txt"));
						String line;
						while((line=in.readLine())!=null) {
							view.append("  "+line+" \r\n");
						}
						J1.setVisible(true);
						in.close();
					}catch(IOException e1) {
						e1.printStackTrace();
					}
				
			}	
		}
  }
	
	   
}
