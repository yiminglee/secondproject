package resource;

import java.sql.Date;

public class Lcsvbean {
	

	private int cmdid;
	private String cmdname;
	private String indcat;
	private String addr;
	private String chairname;
	private long income;
	private Date createdate;

	
	@Override
	public String toString() {
		return "Lcsvbean [cmdid=" + cmdid + ", cmdname=" + cmdname + ", indcat=" + indcat + ", addr=" + addr
				+ ", chairname=" + chairname + ", income=" + income + ", createdate=" + createdate + "]";
	}
	
	public int getCmdid() {
		return cmdid;
	}

	public void setCmdid(int cmdid) {
		this.cmdid = cmdid;
	}

	public String getCmdname() {
		return cmdname;
	}

	public void setCmdname(String cmdname) {
		this.cmdname = cmdname;
	}

	public String getIndcat() {
		return indcat;
	}

	public void setIndcat(String indcat) {
		this.indcat = indcat;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getChairname() {
		return chairname;
	}

	public void setChairname(String chairname) {
		this.chairname = chairname;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}
