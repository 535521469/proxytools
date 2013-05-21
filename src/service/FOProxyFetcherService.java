package service;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FOProxyFetcherService {

	private BlockingQueue<String> proxyUrls = new ArrayBlockingQueue<String>(5);
	private int periodDelay = 1800;
	private int poolSize = 1;

	public int getPoolSize() {
		return poolSize;
	}

	public int getPeriodDelay() {
		return periodDelay;
	}

	public FOProxyFetcherService() {
	}

	private void setPeriodDelay(int periodDelay) {
		this.periodDelay = periodDelay;
	}

	private void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	protected final Logger getLogger() {
		return LoggerFactory.getLogger(this.getClass());
	}

	public FOProxyFetcherService(Properties prop) {
		int poolsize = this.getPoolSize();
		int perioddelay = this.getPeriodDelay();
		String periodDelay = prop.getProperty("source.51.periodDelay",
				String.valueOf(this.periodDelay));

		try {
			perioddelay = Integer.valueOf(periodDelay);
		} catch (NumberFormatException e) {
			this.getLogger().warn(
					"convert source.51.periodDelay \"" + periodDelay
							+ "\" to int error , use default value "
							+ this.periodDelay);
		}

		String poolSize = prop.getProperty("source.51.poolSize",
				String.valueOf(this.poolSize));
		try {
			poolsize = Integer.valueOf(poolSize).intValue();
		} catch (NumberFormatException e) {
			this.getLogger().warn(
					"convert source.51.poolSize \"" + poolSize
							+ "\" to int error , use default value "
							+ this.poolSize);
		}

		this.setPeriodDelay(perioddelay);
		this.setPoolSize(poolsize);

		this.getLogger().info(
				"source.51 use " + this.getPoolSize() + " Threads,fetch every "
						+ this.getPeriodDelay() + " seconds ");

	}

	public FOProxyFetcherService(int periodDelay, int poolSize) {
		this.periodDelay = periodDelay;
		this.poolSize = poolSize;
	}

	public void fillProxyUrls() {
		this.proxyUrls.add("http://51dai.li/http_fast.html");
		this.proxyUrls.add("http://51dai.li/http_anonymous.html");
		this.proxyUrls.add("http://51dai.li/http_non_anonymous.html");
	}

	public void kickOff() {
		this.fillProxyUrls();

		ScheduledExecutorService es = (ScheduledExecutorService) Executors
				.newScheduledThreadPool(this.getPoolSize());
		try {
			while (!this.proxyUrls.isEmpty()) {
				es.scheduleAtFixedRate(
						new FOProxyFetcher(this.proxyUrls.take()), 0,
						this.periodDelay, TimeUnit.SECONDS);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
