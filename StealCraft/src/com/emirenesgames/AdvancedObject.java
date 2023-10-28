package com.emirenesgames;

public class AdvancedObject implements java.io.Serializable {
	
	public java.util.List toList(Object[] list) {
		if(list == null) {
			throw new java.lang.NullPointerException("list = null!");
		}
		@SuppressWarnings("rawtypes")
		java.util.List list1 = new java.util.ArrayList();
		for (int i = 0; i < list.length; i++) {
			list1.add(list[i]);
		}
		return list1;
	}

}
