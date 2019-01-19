package br.com.leoguilbor.surl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfigParameter {

	@Value("${surl.uid_length}")
	private int UID_LENGTH;

	@Value("${surl.uid_chars}")
	private String UID_CHARS;

	@Value("${surl.domain}")
	private String DOMAIN;

	@Value("${surl.secure}")
	private Boolean SECURE;

	public AppConfigParameter() {
		// TODO Auto-generated construc
	}

	public int getUID_LENGTH() {
		return UID_LENGTH;
	}

	public String getUID_CHARS() {
		return UID_CHARS;
	}

	public String getDOMAIN() {
		return DOMAIN;
	}

	public Boolean getSECURE() {
		return SECURE;
	}
	
	public String getPrefix() {
		// TODO Auto-generated method stub
		if (this.getSECURE()) {
			return "https://"+this.getDOMAIN();	
		}
		return "http://"+this.getDOMAIN();
	}

}
