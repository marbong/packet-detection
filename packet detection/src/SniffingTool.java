import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;

public class SniffingTool extends Frame implements ItemListener,
		ActionListener, Runnable { // itemlistener을//
	// implements
	// //
	// 시켜준다
	private List list; // 리스트
	private TextField tf; // 텍스트 필드
	private TextArea ta;//
	private Button butt; // 버튼
	private Button butt1; // 버튼
	private Button butt2; // 버튼
	private Button butt3; // 버튼
	private int buton;
	public static int graphtype0 = 0,graphtype1 = 0;
	Thread th0 ,th1  ;

	public SniffingTool() throws Exception {
		super("Packet"); // 부모클래스호출

		this.init();
		this.exit();
		this.setLocation(200, 100); // 윈도우 창의 처음 위치
		super.setVisible(true); // 상위클래스호출
		super.setSize(900, 350); // 윈도우 창의 크기
		super.setResizable(false); // 윈도우 창의 사이즈

	}

	public void init() throws Exception {
		Panel panel1 = new Panel(); // 왼쪽
		Panel panel2 = new Panel(); // 오른쪽
		BorderLayout b1 = new BorderLayout(); // 왼쪽패널 설정
		// FlowLayout b2 = new FlowLayout(); //오른쪽패널 설정

		tf = new TextField(10); // 텍스트 필드를 생성한다
		ta = new TextArea(3, 30); //
		list = new List(20); // List 객체 생성및 Item 등록
		butt = new Button("패킷 캡처 시작");
		butt1 = new Button("패킷 캡처 중지");
		butt2 = new Button("캡처중지");
		butt3 = new Button("다시시작");
		butt.addActionListener(this);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);

		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		for (int i = 0; i < devices.length; i++) {
			// 네트워크 장비 만큼 루프
			list.add("[Index : " + i + "] 장비명 :" + devices[i].name
					+ "  , 설명 : " + devices[i].description + "");
			// 장비 이름과 설명

		}

		panel1.setLayout(b1);
		panel1.add("Center", list);
		panel1.add("South", ta);

		panel2.setLayout(null);
		butt.setLocation(15, 0);
		butt1.setLocation(15, 65);
		//butt2.setLocation(15, 130);
		//butt3.setLocation(15, 195);

		butt.setSize(160, 60);
		butt1.setSize(160, 60);
		//butt2.setSize(160, 60);
		//butt3.setSize(160, 60);

		panel2.add(butt);
		panel2.add(butt1);
		//panel2.add(butt2);
		//panel2.add(butt3);

		add("West", panel1);
		add("Center", panel2);

		list.addItemListener(this); // List 객체에 ItemListener를 등록 시킴

	}

	public void exit() {
		this.addWindowListener(new WindowAdapter() { // x를 누르면 종료
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public void run() {

		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
	
				if (buton == 0) {
					try {
						new PacketViewer(devices[0]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (buton == 1) {
					try {
						new PacketViewer(devices[1]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (buton == 2) {
					try {
						new PacketViewer(devices[2]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (buton == 3) {
					try {
						new PacketViewer(devices[3]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (buton == 4) {
					try {
						new PacketViewer(devices[4]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (buton == 5) {
					try {
						new PacketViewer(devices[5]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (buton == 6) {
					try {
						new PacketViewer(devices[6]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (buton == 7) {
					try {
						new PacketViewer(devices[7]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
		
		

	}

	public void actionPerformed(ActionEvent e) {
		String item = Integer.toString(list.getSelectedIndex());
		Object obj = e.getSource(); // 이벤트를 발생 시킨 객체의 주소를 받아낸다.
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();

		if ((Button) obj == butt) { // 캡처 시작
	
			th0 = new Thread(this);
			th0.start();
			
		}

		if ((Button) obj == butt1) //캡처 중지
		{
		}


	}

	public void itemStateChanged(ItemEvent ie) {// Item의 상태가 변화 했을 때
		String ip = "";
		String datalink = "";

		NetworkInterface[] devices = JpcapCaptor.getDeviceList();

		if (list.getSelectedIndex() == 0) {
			NetworkInterfaceAddress add[] = devices[0].addresses;
			// 해당 장비의 주소에서 네트워크 정보를 가져온다.
			datalink = (" datalink: " + devices[0].datalink_name + "("
					+ devices[0].datalink_description + ")" + "\n");
			// 데이터링크를 가져온다.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP주소, subnet mask , broadcast 주소를 가져온다.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 0;
		} else if (list.getSelectedIndex() == 1) {
			NetworkInterfaceAddress add[] = devices[1].addresses;
			// 해당 장비의 주소에서 네트워크 정보를 가져온다.
			datalink = (" datalink: " + devices[1].datalink_name + "("
					+ devices[1].datalink_description + ")" + "\n");
			// 데이터링크를 가져온다.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP주소, subnet mask , broadcast 주소를 가져온다.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 1;
		} else if (list.getSelectedIndex() == 2) {
			NetworkInterfaceAddress add[] = devices[2].addresses;
			// 해당 장비의 주소에서 네트워크 정보를 가져온다.
			datalink = (" datalink: " + devices[2].datalink_name + "("
					+ devices[2].datalink_description + ")" + "\n");
			// 데이터링크를 가져온다.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP주소, subnet mask , broadcast 주소를 가져온다.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 2;
		} else if (list.getSelectedIndex() == 3) {
			NetworkInterfaceAddress add[] = devices[3].addresses;
			// 해당 장비의 주소에서 네트워크 정보를 가져온다.
			datalink = (" datalink: " + devices[3].datalink_name + "("
					+ devices[3].datalink_description + ")" + "\n");
			// 데이터링크를 가져온다.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP주소, subnet mask , broadcast 주소를 가져온다.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 3;
		} else if (list.getSelectedIndex() == 4) {
			NetworkInterfaceAddress add[] = devices[4].addresses;
			// 해당 장비의 주소에서 네트워크 정보를 가져온다.
			datalink = (" datalink: " + devices[4].datalink_name + "("
					+ devices[4].datalink_description + ")" + "\n");
			// 데이터링크를 가져온다.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP주소, subnet mask , broadcast 주소를 가져온다.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 4;
		} else if (list.getSelectedIndex() == 5) {
			NetworkInterfaceAddress add[] = devices[5].addresses;
			// 해당 장비의 주소에서 네트워크 정보를 가져온다.
			datalink = (" datalink: " + devices[5].datalink_name + "("
					+ devices[5].datalink_description + ")" + "\n");
			// 데이터링크를 가져온다.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP주소, subnet mask , broadcast 주소를 가져온다.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 5;
		} else if (list.getSelectedIndex() == 6) {
			NetworkInterfaceAddress add[] = devices[6].addresses;
			// 해당 장비의 주소에서 네트워크 정보를 가져온다.
			datalink = (" datalink: " + devices[6].datalink_name + "("
					+ devices[6].datalink_description + ")" + "\n");
			// 데이터링크를 가져온다.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP주소, subnet mask , broadcast 주소를 가져온다.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 6;
		} else if (list.getSelectedIndex() == 7) {
			NetworkInterfaceAddress add[] = devices[7].addresses;
			// 해당 장비의 주소에서 네트워크 정보를 가져온다.
			datalink = (" datalink: " + devices[7].datalink_name + "("
					+ devices[7].datalink_description + ")" + "\n");
			// 데이터링크를 가져온다.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP주소, subnet mask , broadcast 주소를 가져온다.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 7;
		}

	}

	public static void main(String[] args) throws Exception {
		SniffingTool le = new SniffingTool();
	}
}