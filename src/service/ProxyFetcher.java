package service;

import java.io.IOException;
import java.util.Collection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Proxy;
import exception.FetchSourceChangeException;

public abstract class ProxyFetcher extends IProxyFetcher {

	protected final static String CONSEQUENCE_NEW = "NEW";
	protected final static String CONSEQUENCE_ANEW = "ANEW";

	protected final Logger getLogger() {
		return LoggerFactory.getLogger(this.getClass());
	}

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	abstract String handleProxy(Proxy proxy);

	@Override
	public void run() {

		int newAmount = 0;
		int anewAmount = 0;

		try {
			Document doc = Jsoup.connect(this.getUrl()).get();
			if (this.validSource(doc)) {
				Collection<Proxy> proxies = this.getFetchable().call();
				for (Proxy proxy : proxies) {
					String consequence = this.handleProxy(proxy);
					if (CONSEQUENCE_ANEW == consequence) {
						anewAmount += 1;
					} else {
						newAmount += 1;
					}
				}

				this.getLogger().info(
						"new:" + newAmount + ", anew:" + anewAmount + ";"
								+ this.getUrl());

			} else {
				this.getLogger().error("Source " + this.getUrl() + " changed!");
				throw new FetchSourceChangeException("Source:" + this.getUrl()
						+ " changed!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}