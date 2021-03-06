package service88;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Callback implements Callable<Map<String, Collection<?>>> {
	private ResponseWrapper responseWrapper;

	protected final Logger getLogger() {
		return LoggerFactory.getLogger(this.getClass());
	}
	
	public ResponseWrapper getResponseWrapper() {
		return responseWrapper;
	}

	public void setResponseWrapper(ResponseWrapper responseWrapper) {
		this.responseWrapper = responseWrapper;
	}
}