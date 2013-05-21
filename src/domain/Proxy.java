package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 */
public class Proxy implements Serializable {

	private static final long serialVersionUID = -8937105843400850425L;
	private String seqid;
	private String procotol;
	private String ip;
	private int port;
	private Date validdatetime;
	private String validflag;
	private Date fetchdate;

	public enum ValidFlagEnum {
		UNVALID, INVALID, VALID;

		private Map<ValidFlagEnum, String> enumCodeMapping;
		private Map<String, ValidFlagEnum> codeEnumMapping;

		private Map<ValidFlagEnum, String> getEnumCodeMapping() {
			if (this.enumCodeMapping == null) {
				this.enumCodeMapping = new HashMap<ValidFlagEnum, String>();
				this.enumCodeMapping.put(ValidFlagEnum.UNVALID, "2");
				this.enumCodeMapping.put(ValidFlagEnum.VALID, "1");
				this.enumCodeMapping.put(ValidFlagEnum.INVALID, "0");
			}
			return this.enumCodeMapping;
		}

		private Map<String, ValidFlagEnum> getCodeEnumMapping() {
			if (this.codeEnumMapping == null) {
				this.codeEnumMapping = new HashMap<String, ValidFlagEnum>();
				this.codeEnumMapping.put("2", ValidFlagEnum.UNVALID);
				this.codeEnumMapping.put("1", ValidFlagEnum.VALID);
				this.codeEnumMapping.put("0", ValidFlagEnum.INVALID);
			}
			return this.codeEnumMapping;
		}

		public ValidFlagEnum getEnumByCode(String code) {
			return getCodeEnumMapping().get(code);
		}

		public String getCodeByEnum() {
			return getEnumCodeMapping().get(this);
		}
	}

	public enum ProtocolEnum {
		HTTP;

		private Map<ProtocolEnum, String> enumCodeMapping;
		private Map<String, ProtocolEnum> codeEnumMapping;

		private Map<ProtocolEnum, String> getEnumCodeMapping() {
			if (this.enumCodeMapping == null) {
				this.enumCodeMapping = new HashMap<ProtocolEnum, String>();
				this.enumCodeMapping.put(ProtocolEnum.HTTP, "HTTP");
			}
			return this.enumCodeMapping;
		}

		private Map<String, ProtocolEnum> getCodeEnumMapping() {
			if (this.codeEnumMapping == null) {
				this.codeEnumMapping = new HashMap<String, ProtocolEnum>();
				this.codeEnumMapping.put("HTTP", ProtocolEnum.HTTP);
			}
			return this.codeEnumMapping;
		}

		public ProtocolEnum getEnumByCode(String code) {
			return getCodeEnumMapping().get(code);
		}

		public String getCodeByEnum() {
			return getEnumCodeMapping().get(this);
		}
	}

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

	@Override
	public String toString() {

		StringBuilder repr = new StringBuilder();
		// repr.append("seqid:" + this.seqid);
		// repr.append("procotol:" + this.procotol);
		// repr.append("ip:" + String.valueOf(this.ip));
		// repr.append("validdatetime:" + this.validdatetime);
		// repr.append("validflag:" + this.validflag);
		// repr.append("fetchdate:" + this.fetchdate);

		repr.append(this.procotol);
		repr.append("://");
		repr.append(this.ip);
		repr.append(":");
		repr.append(this.port);

		return repr.toString();
	}

}
