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
	Demo grap = new Demo(); // ��ü�׷���
	Demo1 grap1 = new Demo1(); // �ǽð� �׷���

	public void receivePacket(Packet packet) {

		count++; // ��Ŷ�� �Ѱ� ���� �� ���� 1�� ����

		Packetout pk = new Packetout(packet); // ��Ŷ�� PacketoutŬ������ �Ѱ���

		size += packet.len;

		if (out == 1) // 1�ʰ� �Ǹ� ī��Ʈ �ʱ�ȭ
		{
			graph++;
	
				grap.DualAxisDemoSet(count, size, graph);
				//RefineryUtilities.centerFrameOnScreen(grap);
				//RefineryUtilities.positionFrameOnScreen(grap, 1, 1);  //���� ��ġ
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
		captor.loopPacket(-1, new PacketViewer()); // ��Ŷ�� �ϳ� ���ö����� ���ο�
													// PacketVier�� �����.

	}
}
