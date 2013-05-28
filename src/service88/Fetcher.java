package service88;

import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Fetcher implements Callable<ResponseWrapper> {
	private RequestWrapper requestWrapper;

	public Fetcher() {
	}

	public Fetcher(RequestWrapper requestWrapper) {
		this.setRequestWrapper(requestWrapper);
	}

	protected final Logger getLogger() {
		return LoggerFactory.getLogger(this.getClass());
	}

	public RequestWrapper getRequestWrapper() {
		return requestWrapper;
	}

	public void setRequestWrapper(RequestWrapper requestWrapper) {
		this.requestWrapper = requestWrapper;
	}

	@Override
	public ResponseWrapper call() throws Exception {

		Document doc = Jsoup.connect(this.getRequestWrapper().getUrl()).get();
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setRequestWrapper(this.getRequestWrapper());
		responseWrapper.setDoc(doc);

		return responseWrapper;

	}

}
