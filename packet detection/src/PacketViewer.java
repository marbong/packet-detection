import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.PacketReceiver;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;

import org.jfree.ui.RefineryUtilities;

class PacketViewer extends Thread implements PacketReceiver {

	int count = 0;
	int out;
	int graph;
	int size = 0;
	Demo grap = new Demo(); // 전체그래프
	Demo1 grap1 = new Demo1(); // 실시간 그래프

	public void receivePacket(Packet packet) {

		count++; // 패킷이 한개 들어올 때 마다 1씩 증가

		Packetout pk = new Packetout(packet); // 패킷을 Packetout클래스에 넘겨줌

		size += packet.len;

		if (out == 1) // 1초가 되면 카운트 초기화
		{
			graph++;
	
				grap.DualAxisDemoSet(count, size, graph);
				//RefineryUtilities.centerFrameOnScreen(grap);
				//RefineryUtilities.positionFrameOnScreen(grap, 1, 1);  //시작 위치
				grap.pack();
				grap.setVisible(true);
			
				grap1.ShowData(count);
				grap1.pack();
				grap1.setVisible(true);
		
			count = 0;
			out = 0;
			size = 0;
		}

	}

	public PacketViewer() {

		start();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				out = 1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public PacketViewer(NetworkInterface device) throws Exception {

		JpcapCaptor captor = JpcapCaptor.openDevice(device, 20000, true, 20);
		// captor.setFilter("udp", true);
		captor.loopPacket(-1, new PacketViewer()); // 패킷이 하나 들어올때마다 새로운
													// PacketVier가 생긴다.

	}
}
