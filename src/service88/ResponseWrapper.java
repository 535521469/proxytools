package service88;

import org.jsoup.nodes.Document;

public class ResponseWrapper {

	private RequestWrapper requestWrapper;

	private Document doc;

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public RequestWrapper getRequestWrapper() {
		return requestWrapper;
	}

	public void setRequestWrapper(RequestWrapper requestWrapper) {
		this.requestWrapper = requestWrapper;
	}

}
