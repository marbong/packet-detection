import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.FileWriter;
import java.io.IOException;


public class OutputData {
	CharArrayReader bais = null;
	BufferedWriter bw = null;
	
	
	public OutputData(String message)
	{
		try {
			print(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void print(String message) throws IOException {

		try {

			char[] buffer = new char[512];
			char[] fileArray = message.toCharArray();

			int readcount = 0;
			bais = new CharArrayReader(fileArray);

			bw = new BufferedWriter(new FileWriter("패킷정보.txt", true));

			while ((readcount = bais.read(buffer)) != -1) {
				bw.write(buffer, 0, readcount);
				bw.flush();
			}

		} catch (IOException e) {
			System.err.println(e);
		} finally {
			bw.close();
		}

	}

}
