package cn.com.leadfar.cms.backend.vo;

import java.util.List;

/**
 * VO - Value Object
 * @author Administrator
 *
 */
public class PagerVO {
	private int total;
	private List datas;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
}
