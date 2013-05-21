package service;

import org.jsoup.nodes.Document;

public abstract class IProxyFetcher implements Runnable {

	private IFetchable fetchable;
	
	public IFetchable getFetchable() {
		return fetchable;
	}

	public void setFetchable(IFetchable fetchable) {
		this.fetchable = fetchable;
	}
	abstract boolean validSource(Document doc);
}