package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Long recourdCount;
private int totalPages;
private List<SearchItem> itemList;
public Long getRecourdCount() {
	return recourdCount;
}
public void setRecourdCount(Long recourdCount) {
	this.recourdCount = recourdCount;
}
public int getTotalPages() {
	return totalPages;
}
public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
}
public List<SearchItem> getItemList() {
	return itemList;
}
public void setItemList(List<SearchItem> itemList) {
	this.itemList = itemList;
}


}
