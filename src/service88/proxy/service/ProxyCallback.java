package service88.proxy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import domain.Proxy;

import service88.Callback;
import service88.ResponseWrapper;
import service88.proxy.FOFetcherConstant;
import service88.proxy.FOProxyResource;

public class ProxyCallback extends Callback {

//	static {
//		System.out.println(ProxyCallback.class.getName());
//		BlockingQueue<Callback> x = new LinkedBlockingQueue<Callback>();
//		System.out.println(x);
//		FOProxyResource.getExtractMap().put(ProxyCallback.class.getName(), x);
//	}

	// static {
	//
	// Document doc = null;
	// try {
	// doc = Jsoup.connect(getUrl()).get();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// Collection<Proxy> proxies = new ArrayList<Proxy>();
	//
	// Element div_tag = doc.select("#tb").first();
	// for (Element tr_tag : div_tag.select("tr")) {
	// Elements td_tags = tr_tag.select("td");
	// if (0 == td_tags.size()) {
	// continue;
	// }
	// Proxy p = new Proxy();
	// p.setIp(td_tags.get(1).text().trim());
	// p.setPort(Integer.valueOf(td_tags.get(2).text().trim()));
	// p.setProcotol(Proxy.ProtocolEnum.HTTP.getCodeByEnum());
	// proxies.add(p);
	// }
	// getLogger()
	// .info(String.format("total get %d proxy,%s", proxies.size(),
	// getUrl()));
	// return proxies;
	// }

	@Override
	public Map<String, Collection<?>> call() throws Exception {

		Map<String, Collection<?>> m = new HashMap<String, Collection<?>>();
		Collection<Proxy> proxies = new ArrayList<Proxy>();
		Document doc = this.getResponseWrapper().getDoc();

		Element div_tag = doc.select("#tb").first();
		for (Element tr_tag : div_tag.select("tr")) {
			Elements td_tags = tr_tag.select("td");
			if (0 == td_tags.size()) {
				continue;
			}
			Proxy p = new Proxy();
			p.setIp(td_tags.get(1).text().trim());
			p.setPort(Integer.valueOf(td_tags.get(2).text().trim()));
			p.setProcotol(Proxy.ProtocolEnum.HTTP.getCodeByEnum());
			proxies.add(p);
		}

		getLogger().info("fetch " + proxies.size() + "proxies");

		m.put(FOFetcherConstant.ITEMS, proxies);

		return m;
	}
}
