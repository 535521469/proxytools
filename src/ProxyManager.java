import service.ProxyFetcherService;

public class ProxyManager {

	public static void main(String[] args) {
		ProxyFetcherService pfs = new ProxyFetcherService();
		pfs.fetch();
	}
}
