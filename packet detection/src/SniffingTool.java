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
		ActionListener, Runnable { // itemlistener��//
	// implements
	// //
	// �����ش�
	private List list; // ����Ʈ
	private TextField tf; // �ؽ�Ʈ �ʵ�
	private TextArea ta;//
	private Button butt; // ��ư
	private Button butt1; // ��ư
	private Button butt2; // ��ư
	private Button butt3; // ��ư
	private int buton;
	public static int graphtype0 = 0,graphtype1 = 0;
	Thread th0 ,th1  ;

	public SniffingTool() throws Exception {
		super("Packet"); // �θ�Ŭ����ȣ��

		this.init();
		this.exit();
		this.setLocation(200, 100); // ������ â�� ó�� ��ġ
		super.setVisible(true); // ����Ŭ����ȣ��
		super.setSize(900, 350); // ������ â�� ũ��
		super.setResizable(false); // ������ â�� ������

	}

	public void init() throws Exception {
		Panel panel1 = new Panel(); // ����
		Panel panel2 = new Panel(); // ������
		BorderLayout b1 = new BorderLayout(); // �����г� ����
		// FlowLayout b2 = new FlowLayout(); //�������г� ����

		tf = new TextField(10); // �ؽ�Ʈ �ʵ带 �����Ѵ�
		ta = new TextArea(3, 30); //
		list = new List(20); // List ��ü ������ Item ���
		butt = new Button("��Ŷ ĸó ����");
		butt1 = new Button("��Ŷ ĸó ����");
		butt2 = new Button("ĸó����");
		butt3 = new Button("�ٽý���");
		butt.addActionListener(this);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);

		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		for (int i = 0; i < devices.length; i++) {
			// ��Ʈ��ũ ��� ��ŭ ����
			list.add("[Index : " + i + "] ���� :" + devices[i].name
					+ "  , ���� : " + devices[i].description + "");
			// ��� �̸��� ����

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

		list.addItemListener(this); // List ��ü�� ItemListener�� ��� ��Ŵ

	}

	public void exit() {
		this.addWindowListener(new WindowAdapter() { // x�� ������ ����
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
		Object obj = e.getSource(); // �̺�Ʈ�� �߻� ��Ų ��ü�� �ּҸ� �޾Ƴ���.
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();

		if ((Button) obj == butt) { // ĸó ����
	
			th0 = new Thread(this);
			th0.start();
			
		}

		if ((Button) obj == butt1) //ĸó ����
		{
		}


	}

	public void itemStateChanged(ItemEvent ie) {// Item�� ���°� ��ȭ ���� ��
		String ip = "";
		String datalink = "";

		NetworkInterface[] devices = JpcapCaptor.getDeviceList();

		if (list.getSelectedIndex() == 0) {
			NetworkInterfaceAddress add[] = devices[0].addresses;
			// �ش� ����� �ּҿ��� ��Ʈ��ũ ������ �����´�.
			datalink = (" datalink: " + devices[0].datalink_name + "("
					+ devices[0].datalink_description + ")" + "\n");
			// �����͸�ũ�� �����´�.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP�ּ�, subnet mask , broadcast �ּҸ� �����´�.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 0;
		} else if (list.getSelectedIndex() == 1) {
			NetworkInterfaceAddress add[] = devices[1].addresses;
			// �ش� ����� �ּҿ��� ��Ʈ��ũ ������ �����´�.
			datalink = (" datalink: " + devices[1].datalink_name + "("
					+ devices[1].datalink_description + ")" + "\n");
			// �����͸�ũ�� �����´�.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP�ּ�, subnet mask , broadcast �ּҸ� �����´�.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 1;
		} else if (list.getSelectedIndex() == 2) {
			NetworkInterfaceAddress add[] = devices[2].addresses;
			// �ش� ����� �ּҿ��� ��Ʈ��ũ ������ �����´�.
			datalink = (" datalink: " + devices[2].datalink_name + "("
					+ devices[2].datalink_description + ")" + "\n");
			// �����͸�ũ�� �����´�.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP�ּ�, subnet mask , broadcast �ּҸ� �����´�.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 2;
		} else if (list.getSelectedIndex() == 3) {
			NetworkInterfaceAddress add[] = devices[3].addresses;
			// �ش� ����� �ּҿ��� ��Ʈ��ũ ������ �����´�.
			datalink = (" datalink: " + devices[3].datalink_name + "("
					+ devices[3].datalink_description + ")" + "\n");
			// �����͸�ũ�� �����´�.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP�ּ�, subnet mask , broadcast �ּҸ� �����´�.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 3;
		} else if (list.getSelectedIndex() == 4) {
			NetworkInterfaceAddress add[] = devices[4].addresses;
			// �ش� ����� �ּҿ��� ��Ʈ��ũ ������ �����´�.
			datalink = (" datalink: " + devices[4].datalink_name + "("
					+ devices[4].datalink_description + ")" + "\n");
			// �����͸�ũ�� �����´�.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP�ּ�, subnet mask , broadcast �ּҸ� �����´�.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 4;
		} else if (list.getSelectedIndex() == 5) {
			NetworkInterfaceAddress add[] = devices[5].addresses;
			// �ش� ����� �ּҿ��� ��Ʈ��ũ ������ �����´�.
			datalink = (" datalink: " + devices[5].datalink_name + "("
					+ devices[5].datalink_description + ")" + "\n");
			// �����͸�ũ�� �����´�.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP�ּ�, subnet mask , broadcast �ּҸ� �����´�.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 5;
		} else if (list.getSelectedIndex() == 6) {
			NetworkInterfaceAddress add[] = devices[6].addresses;
			// �ش� ����� �ּҿ��� ��Ʈ��ũ ������ �����´�.
			datalink = (" datalink: " + devices[6].datalink_name + "("
					+ devices[6].datalink_description + ")" + "\n");
			// �����͸�ũ�� �����´�.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP�ּ�, subnet mask , broadcast �ּҸ� �����´�.
			}
			ta.setText(datalink + ip);
			ip = "";
			datalink = "";
			buton = 6;
		} else if (list.getSelectedIndex() == 7) {
			NetworkInterfaceAddress add[] = devices[7].addresses;
			// �ش� ����� �ּҿ��� ��Ʈ��ũ ������ �����´�.
			datalink = (" datalink: " + devices[7].datalink_name + "("
					+ devices[7].datalink_description + ")" + "\n");
			// �����͸�ũ�� �����´�.

			for (int a = 0; a < add.length; a++) {
				ip += (" IP address: (" + add[a].address + ") " + add[a].subnet
						+ " " + add[a].broadcast + "\n");
				// IP�ּ�, subnet mask , broadcast �ּҸ� �����´�.
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