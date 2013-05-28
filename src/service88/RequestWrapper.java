package service88;

import java.util.Collection;

public class RequestWrapper {

	private String url;
	private Collection<RequestWrapper> referRequestWrappers;
	private Callback callback;

	public Callback getCallback() {
		return callback;
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Collection<RequestWrapper> getReferRequestWrappers() {
		return referRequestWrappers;
	}

	public void setReferRequestWrappers(
			Collection<RequestWrapper> referRequestWrappers) {
		this.referRequestWrappers = referRequestWrappers;
	}

}
