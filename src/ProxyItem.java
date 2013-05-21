import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import domain.Proxy;

public class ProxyItem implements Delayed {

	private Proxy proxy;

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	@Override
	public int compareTo(Delayed o) {
		return this.proxy.getValiddatetime().compareTo(
				((ProxyItem) o).proxy.getValiddatetime());
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(new Date().getTime()
				- this.proxy.getValiddatetime().getTime(),
				TimeUnit.MILLISECONDS);
	}
	
	
}
