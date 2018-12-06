package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Article {
	private String articleType;
	private String language;
	private Front front;
	
	@XmlAttribute(name="article-type")
	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	@XmlAttribute(name="lang",namespace="http://www.w3.org/1998/Math/MathML")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@XmlElement(name="front")
	public Front getFront() {
		return front;
	}

	public void setFront(Front front) {
		this.front = front;
	}
}
