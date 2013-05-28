package service88.proxy.service;

import java.util.concurrent.LinkedBlockingQueue;

import service88.Fetcher;
import service88.proxy.FOProxyResource;

public class ProxyFetcher extends Fetcher {

	static {
		FOProxyResource.getFetcherMap().put(ProxyFetcher.class.getName(),
				new LinkedBlockingQueue<Fetcher>());
	}
}
