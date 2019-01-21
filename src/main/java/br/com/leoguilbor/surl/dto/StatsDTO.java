package br.com.leoguilbor.surl.dto;

public class StatsDTO {


	private Long shorted;
	private Long clicked;

	public StatsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatsDTO(Long shorted,Long clicked) {
		super();
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
}