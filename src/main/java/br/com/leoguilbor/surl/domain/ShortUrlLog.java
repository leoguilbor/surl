package br.com.leoguilbor.surl.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

@Repository
@Entity
@Table(name="short_url_log")
public class ShortUrlLog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
	private ShortUrl shortUrl; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private LogType type;
	private Boolean exist;
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private User user;
	
	public ShortUrlLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ShortUrlLog(Long id, ShortUrl shortUrl, Date date, Boolean exist, User user) {
		super();
		this.id = id;
		this.shortUrl = shortUrl;
		this.date = date;
		this.exist = exist;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ShortUrl getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(ShortUrl shortUrl) {
		this.shortUrl = shortUrl;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public LogType getType() {
		return type;
	}

	public void setType(LogType type) {
		this.type = type;
	}

	public Boolean getExist() {
		return exist;
	}
	public void setExist(Boolean exist) {
		this.exist = exist;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
