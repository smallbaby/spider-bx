package com.jd.wife;
/**
测试

*/
public class OrdArray {
	private long[] a;
	private int nElems;
	
	public OrdArray(int max) {
		a = new long[max];
		nElems = 0;
	}
	public int size() {
		return nElems;
	}
	
	/**
	 * 二分查找
	 * @param searchKey
	 * @return
	 */
	public int find(long searchKey) {
		int left = 0;
		int right = nElems - 1;
		int curIn;
		while(true) {
			curIn = (left+right)/2;
			if(searchKey == a[curIn]) {
				return curIn;
			}else if(left > right) {
				return nElems;
			}else {
				if(a[curIn] < searchKey) {
					left = curIn + 1;
				} else {
					right = curIn - 1;
				}
			}
		}
	}
	
	/**
	 * 插入元素
	 * @param value
	 */
	public void insert(long value) {
		int j;
		for(j=0;j<nElems;j++) {//找到要插入元素的坐标位置
			if(a[j] > value) {
				break;
			}
		}
		for(int k=nElems;k>j;k--) {//从插入的位置后移
			a[k] = a[k-1];
		}
		a[j] = value;
		nElems ++;
	}
	
	public boolean delete(long value) {
		int j = find(value);
		if(j == nElems) return false;
		else {
			for(int k=j;k<nElems;k++) {
				a[k]=a[k+1];
			}
			nElems --;
			return true;
		}
	}
	
	public static void main(String[] args) {
		int maxSize = 100;
		OrdArray arr;
		arr = new OrdArray(maxSize);
		arr.insert(77);
		arr.insert(11);
		arr.insert(99);
		int pos = arr.find(1111);
		System.out.println(pos);
	}
	
	
	
	
	
	
	
	
	
	
	
}
