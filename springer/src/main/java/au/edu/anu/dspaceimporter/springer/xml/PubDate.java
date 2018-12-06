package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlElement;

public class PubDate {
	private Integer day;
	private Integer month;
	private Integer year;
	
	@XmlElement(name="day")
	public Integer getDay() {
		return day;
	}
	
	public void setDay(Integer day) {
		this.day = day;
	}
	
	@XmlElement(name="month")
	public Integer getMonth() {
		return month;
	}
	
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	@XmlElement(name="year")
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
}
