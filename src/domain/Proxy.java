package domain;

import java.util.Date;

public class Proxy {

	private String seqid;
	private String procotol;
	private String ip;
	private int port;
	private Date validdatetime;
	private String validflag;
	private Date fetchdate;

	public String getSeqid() {
		return seqid;
	}

	public void setSeqid(String seqid) {
		this.seqid = seqid;
	}

	public String getProcotol() {
		return procotol;
	}

	public void setProcotol(String procotol) {
		this.procotol = procotol;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Date getValiddatetime() {
		return validdatetime;
	}

	public void setValiddatetime(Date validdatetime) {
		this.validdatetime = validdatetime;
	}

	public String getValidflag() {
		return validflag;
	}

	public void setValidflag(String validflag) {
		this.validflag = validflag;
	}

	public Date getFetchdate() {
		return fetchdate;
	}

	public void setFetchdate(Date fetchdate) {
		this.fetchdate = fetchdate;
	}

}
