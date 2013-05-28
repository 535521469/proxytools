package service88.proxy.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import service88.Callback;
import service88.RequestWrapper;
import service88.ResponseWrapper;
import service88.proxy.FOProxyResource;

public class ProxyService {

	private ArrayList<String> proxyUrls = new ArrayList<String>();

	public ArrayList<String> getProxyUrls() {
		return proxyUrls;
	}

	public void setProxyUrls(ArrayList<String> proxyUrls) {
		this.proxyUrls = proxyUrls;
	}

	public void fillProxyUrls() {
		this.proxyUrls.add("http://51dai.li/http_fast.html");
		this.proxyUrls.add("http://51dai.li/http_anonymous.html");
		this.proxyUrls.add("http://51dai.li/http_non_anonymous.html");
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		ProxyService ps = new ProxyService();
		ps.fetch();

		ThreadPoolExecutor ex = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(2);

		while (true) {
			Callback cb = FOProxyResource.pollExtractMap(ProxyCallback.class
					.getName());

			if (cb == null) {
				Thread.sleep(1000);
			} else {
				Future<Map<String, Collection<?>>> x = ex.submit(cb);
				x.get();
			}

		}

	}

	public void fetch() throws InterruptedException, ExecutionException {

		ScheduledThreadPoolExecutor pe = (ScheduledThreadPoolExecutor) Executors
				.newScheduledThreadPool(2);

		this.fillProxyUrls();
		for (String url : this.getProxyUrls()) {
			pe.scheduleAtFixedRate(new InitRunnable(url), 0, 8,
					TimeUnit.SECONDS);
		}

	}

	class InitRunnable implements Runnable {

		private String url;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public InitRunnable(String url) {
			this.setUrl(url);
		}

		@Override
		public void run() {
			ProxyFetcher pf = new ProxyFetcher();
			RequestWrapper requestWrapper = new RequestWrapper();
			Callback callback = new ProxyCallback();
			requestWrapper.setCallback(callback);
			requestWrapper.setUrl(url);
			pf.setRequestWrapper(requestWrapper);
			try {
				ResponseWrapper rw = pf.call();
				Callback cb = pf.getRequestWrapper().getCallback();
				cb.setResponseWrapper(rw);

				FOProxyResource.offerExtractMap(cb.getClass().getName(), cb, 0,
						TimeUnit.SECONDS);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
