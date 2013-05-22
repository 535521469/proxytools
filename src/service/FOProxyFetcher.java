package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dao.DaoUtil;
import domain.Proxy;

public class FOProxyFetcher extends ProxyFetcher {

	private Session getCurrentSession() {
		return DaoUtil.getCurrentSession();
	}

	public FOProxyFetcher(String url) {
		this.setUrl(url);
		this.setFetchable(new FOFetchable());
	}

	@Override
	String handleProxy(Proxy proxy) {

		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		List ps = session.createCriteria(Proxy.class)
				.add(Example.create(proxy)).list();

		Proxy p = null;
		if (ps.size() > 1) {
			p = (Proxy) ps.get(0);
		}

		String conquence = CONSEQUENCE_NEW;
		if (p == null) {
			proxy.setValidflag(Proxy.ValidFlagEnum.UNVALID.getCodeByEnum());
			proxy.setFetchdate(new Date());
			Calendar validdate = Calendar.getInstance();
			validdate.set(1970, 1, 1);
			proxy.setValiddatetime(validdate.getTime());
			session.save(proxy);
			this.getLogger().debug("add " + proxy.toString() + " !");
		} else {
			p.setFetchdate(new Date());
			p.setValidflag(Proxy.ValidFlagEnum.UNVALID.getCodeByEnum());
			this.getLogger().debug("rejuvenate " + proxy.toString() + " !");
			conquence = CONSEQUENCE_ANEW;
		}
		tx.commit();
		return conquence;
	}

	@Override
	boolean validSource(Document doc) {
		return true;
	}

	class FOFetchable extends Fetchable {

		@Override
		public Collection<Proxy> call() {
			Document doc = null;
			try {
				doc = Jsoup.connect(getUrl()).get();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Collection<Proxy> proxies = new ArrayList<Proxy>();

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

			getLogger().info(
					String.format("total get %d proxy,%s", proxies.size(),
							getUrl()));
			return proxies;
		}

	}

}