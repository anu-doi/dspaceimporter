package au.edu.anu.dspaceimporter.springer.xml;

public class Result {
	private Long total;
	private Long start;
	private Long pageLength;
	private Long recordsDisplayed;
	
	public Long getPageLength() {
		return pageLength;
	}
	
	public void setPageLength(Long pageLength) {
		this.pageLength = pageLength;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getRecordsDisplayed() {
		return recordsDisplayed;
	}

	public void setRecordsDisplayed(Long recordsDisplayed) {
		this.recordsDisplayed = recordsDisplayed;
	}
	
}
