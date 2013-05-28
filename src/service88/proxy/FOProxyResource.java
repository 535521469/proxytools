package service88.proxy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import service88.Callback;
import service88.Fetcher;
import service88.proxy.service.ProxyCallback;
import service88.proxy.service.ProxyFetcher;

public class FOProxyResource {

	private static ConcurrentHashMap<String, BlockingQueue<Fetcher>> fetcherMap = new ConcurrentHashMap<String, BlockingQueue<Fetcher>>();
	private static ConcurrentHashMap<String, BlockingQueue<Callback>> extractMap = new ConcurrentHashMap<String, BlockingQueue<Callback>>();

	static {

		System.out.println(ProxyCallback.class.getName());
		BlockingQueue<Callback> x = new LinkedBlockingQueue<Callback>();
		System.out.println(x);
		FOProxyResource.getExtractMap().put(ProxyCallback.class.getName(), x);

	}

	public static ConcurrentHashMap<String, BlockingQueue<Fetcher>> getFetcherMap() {
		return fetcherMap;
	}

	public static void setFetcherMap(
			ConcurrentHashMap<String, BlockingQueue<Fetcher>> fetcherMap) {
		FOProxyResource.fetcherMap = fetcherMap;
	}

	public static ConcurrentHashMap<String, BlockingQueue<Callback>> getExtractMap() {
		return extractMap;
	}

	public static void setExtractMap(
			ConcurrentHashMap<String, BlockingQueue<Callback>> extractMap) {
		FOProxyResource.extractMap = extractMap;
	}

	public static Fetcher pollFetcherMap(String key)
			throws InterruptedException {
		// synchronized (fetcherMap.get(key)) {
		return fetcherMap.get(key).poll();
		// }
	}

	public static boolean offerFetcherMap(String key, Fetcher e, long timeout,
			TimeUnit unit) throws InterruptedException {
		// synchronized (fetcherMap.get(key)) {
		return fetcherMap.get(key).offer(e, timeout, unit);
		// }
	}

	public static Callback pollExtractMap(String key)
			throws InterruptedException {

		System.out.println(key);
		synchronized (extractMap.get(key)) {
			return extractMap.get(key).poll();
		}

	}

	public static boolean offerExtractMap(String key, Callback e, long timeout,
			TimeUnit unit) throws InterruptedException {

		// synchronized (extractMap.get(key)) {
		return extractMap.get(key).offer(e, timeout, unit);
		// }

	}

}
