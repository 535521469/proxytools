package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProxyFetcherService {

	public static void main(String[] args) {
		new ProxyFetcherService().fetch();
	}

	public void fetch() {
		InputStream is = ProxyFetcherService.class.getClassLoader()
				.getResourceAsStream("source.property");
		Properties prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String source_51 = prop.getProperty("source.51").intern();

		if ("1".equals(source_51)) {
			FOProxyFetcherService fo = new FOProxyFetcherService(prop);
			fo.kickOff();
		}
	}

}
