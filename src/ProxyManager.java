import java.util.ArrayList;

public class ProxyManager {

	private ArrayList<String> getProxySourceURLs() {
		// TODO Auto-generated method stub
		ArrayList<String> proxySourceURLs = new ArrayList<String>();
		proxySourceURLs.add("http://51dai.li/http_non_anonymous.html");
		proxySourceURLs.add("http://51dai.li/http_anonymous.html");
		proxySourceURLs.add("http://51dai.li/http_non_anonymous.html");

		return proxySourceURLs;
	}

	public static void main(String[] args) {

		ProxyManager pm = new ProxyManager();
		ArrayList<String> proxySourceURLs = pm.getProxySourceURLs();


	}


}
