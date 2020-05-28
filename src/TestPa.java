import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class TestPa {
	public static void main(String[] args)
	{
		ByteSource credentialsSalt = ByteSource.Util.bytes("221701237");
	    String password1 = new SimpleHash("MD5","123456789",
	                credentialsSalt,1024).toBase64();
	    System.out.println(password1);
	}
}
