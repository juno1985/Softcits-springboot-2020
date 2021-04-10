package org.softcits.cn.model;

/**
 * 
 * @author thinkpad
 *CREATE TABLE forecast(
id INT AUTO_INCREMENT,
`date` VARCHAR(20),
high VARCHAR(20),
fengli VARCHAR(20),
low VARCHAR(20),
fengxiang VARCHAR(20),
`type` VARCHAR(20),
cid INT NOT NULL,
PRIMARY KEY (id),
CONSTRAINT city_constraint FOREIGN KEY(cid) REFERENCES city (id)
)
 */
public class Forecast {
	
	private Integer id;
	private String date;
	private String high;
	private String fengli;
	private String low;
	private String fengxiang;
	private String type;
	private Integer cid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getFengli() {
		return fengli;
	}
	public void setFengli(String fengli) {
		this.fengli = fengli;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getFengxiang() {
		return fengxiang;
	}
	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	

}
