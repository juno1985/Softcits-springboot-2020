package org.softcits.cn.pojo;

public class AffectedNum {

	/**
	 * the num of data affected
	 */
	private Integer num;
	private String desp;
	public AffectedNum(Integer num, String desp) {
		super();
		this.num = num;
		this.desp = desp;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getDesp() {
		return desp;
	}
	public void setDesp(String desp) {
		this.desp = desp;
	}
	
	
}
