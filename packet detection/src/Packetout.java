import jpcap.PacketReceiver;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;


public class Packetout implements PacketReceiver {

	
	public Packetout(Packet packet) {
		receivePacket(packet);
		
	}



	public void receivePacket(Packet packet) {
		
		String srcip;
		String desip;
		String srcport = "";
		String desport= "";
		String protocol;
		String message ="";
		int size;
		
		
		if(((IPPacket)packet).protocol == 6)
		{
			protocol = ("[" +"TCP"+"]  ");
			srcport = (  "[출발지 포트]:" +((TCPPacket)packet).src_port+"\t");
			desport = (  "[도착지 포트]:" +((TCPPacket)packet).dst_port+"\t");
		}
		else if(((IPPacket)packet).protocol == 17)
		{
			protocol = ("[" +"UDP"+"]  ");
			srcport = (  "[출발지 포트]:" +((UDPPacket)packet).src_port+"\t");
			desport = (  "[도착지 포트]:" +((UDPPacket)packet).dst_port+"\t");
		}
		else 
			protocol = ("[" +((IPPacket)packet).protocol+"]\t");
		
		
		
		srcip =(  "[출발지 주소]:" +((IPPacket)packet).src_ip +"\t");
		desip = (  "[도착지 주소]:" +((IPPacket)packet).dst_ip+"\t");
		size = packet.len;
		
		
		
		message += protocol+ srcip + desip + srcport + desport+ "\t[패킷사이즈]:  "+size+"\r\n";
		
		new OutputData(message);
	
}
}
