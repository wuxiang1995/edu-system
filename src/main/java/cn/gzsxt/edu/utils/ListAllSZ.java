package cn.gzsxt.edu.utils;

import java.util.List;
import java.util.Map;

public class ListAllSZ {
	
	private List<Map<String, Object>> data;

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	public ListAllSZ(List<Map<String, Object>> data) {
		super();
		this.data = data;
	}

	public ListAllSZ() {
		super();
	}
	
}
