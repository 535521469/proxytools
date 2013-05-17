import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import service.ProxyFetcher;
import domain.Proxy;

public class ProxyManager {

	private BlockingQueue<ProxyItem> proxySourceUrls = new DelayQueue<ProxyItem>();
	private BlockingQueue<String> proxyUrls;

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {

		ScheduledExecutorService es = (ScheduledExecutorService) Executors
				.newScheduledThreadPool(2);

		// es.submit();
	}

	public class FOProxyFetcher extends ProxyFetcher {

		@Override
		public Collection<Proxy> call() throws Exception {
			return null;
		}

		@Override
		public void run() {

		}

	}

}
