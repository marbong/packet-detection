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
			srcport = (  "[����� ��Ʈ]:" +((TCPPacket)packet).src_port+"\t");
			desport = (  "[������ ��Ʈ]:" +((TCPPacket)packet).dst_port+"\t");
		}
		else if(((IPPacket)packet).protocol == 17)
		{
			protocol = ("[" +"UDP"+"]  ");
			srcport = (  "[����� ��Ʈ]:" +((UDPPacket)packet).src_port+"\t");
			desport = (  "[������ ��Ʈ]:" +((UDPPacket)packet).dst_port+"\t");
		}
		else 
			protocol = ("[" +((IPPacket)packet).protocol+"]\t");
		
		
		
		srcip =(  "[����� �ּ�]:" +((IPPacket)packet).src_ip +"\t");
		desip = (  "[������ �ּ�]:" +((IPPacket)packet).dst_ip+"\t");
		size = packet.len;
		
		
		
		message += protocol+ srcip + desip + srcport + desport+ "\t[��Ŷ������]:  "+size+"\r\n";
		
		new OutputData(message);
	
}
}
