package com.wf.util.page;

public class PageNavigation {

	/**
	 * nowFirst 첫페이지인지 유무
	 * nowEnd 마지막페이지인지 유무
	 * totalPageCount 전체페이지수
	 * pageNo 현재페이지
	 * navigator 페이징*/
	private boolean nowFirst;
	private boolean nowEnd;
	private int totalPageCount;
	private int pageNo;
	private String navigator;

	public boolean isNowFirst() {
		return nowFirst;
	}

	public void setNowFirst(boolean nowFirst) {
		this.nowFirst = nowFirst;
	}

	public boolean isNowEnd() {
		return nowEnd;
	}

	public void setNowEnd(boolean nowEnd) {
		this.nowEnd = nowEnd;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getNavigator() {
		return navigator;
	}

	public void setNavigator() {
		StringBuffer tmpNavigator = new StringBuffer();

		int prePage = (pageNo - 1) / BoardConstance.PAGE_SIZE * BoardConstance.PAGE_SIZE;

		tmpNavigator.append("<center>\n");
		tmpNavigator.append("<table>\n");
		tmpNavigator.append("<tr>\n");
		if (pageNo == 1) {
			tmpNavigator.append("<td>&lt;&lt;</td>\n");
			tmpNavigator.append("<td>&lt;</td>\n");
		} else if (this.isNowFirst()) {
			tmpNavigator.append("<td>\n<a href='javascript:page(1)'>&lt;&lt;</a>\n");
			tmpNavigator.append("<td>&lt;</td>\n");
		} else {
			tmpNavigator.append("<td>\n<a href='javascript:page(1)'>&lt;&lt;</a>\n");
			tmpNavigator.append("<a href='javascript:page(" + prePage + ")'>&lt;</a></td>\n");
		}

		int startPage = prePage + 1;
		int endPage = startPage + (BoardConstance.PAGE_SIZE - 1);
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		for (int i = startPage; i <= endPage; i++) {
			if (pageNo == i) {
				tmpNavigator.append("<td>" + i + "</td>\n");
			} else {
				tmpNavigator.append("<td><a href='javascript:page(" + i + ")'>" + i + "</td>\n");
			}
		}
		if (pageNo == totalPageCount) {
			tmpNavigator.append("<td>&gt;</td>\n");
			tmpNavigator.append("<td>&gt;&gt;</td>\n");
		} else if (this.isNowEnd()) {
			tmpNavigator.append("<td>&gt;</td>\n");
			tmpNavigator.append("<td><a href='javascript:page(" + totalPageCount + ")'>&gt;&gt;</a></td>\n");
		} else {
			int nextPage = prePage + BoardConstance.PAGE_SIZE + 1;
			tmpNavigator.append("<td><a href='javascript:page(" + nextPage + ")'>&gt;</a></td>\n");
			tmpNavigator.append("<td><a href='javascript:page(" + totalPageCount + ")'>&gt;&gt;</a></td>\n");
		}

		tmpNavigator.append("</tr>\n");
		tmpNavigator.append("</table>\n");
		tmpNavigator.append("</center>\n");

		this.navigator = tmpNavigator.toString();
	}

}