package br.com.leoguilbor.surl.dto;

public class DomainStatsDTO {

	private String domain;
	private Long shorted;
	private Long clicked;

	public DomainStatsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DomainStatsDTO(String domain,Long shorted,Long clicked) {
		super();
		this.domain = domain;
		this.shorted = shorted;
		this.clicked = clicked;
	}

	public Long getShorted() {
		return shorted;
	}

	public void setShorted(Long shorted) {
		this.shorted = shorted;
	}

	public Long getClicked() {
		return clicked;
	}

	public void setClicked(Long clicked) {
		this.clicked = clicked;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
