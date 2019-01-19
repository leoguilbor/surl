package br.com.leoguilbor.surl.config;

import org.springframework.beans.factory.annotation.Value;

public class AppConfigParameter {

	@Value("${surl.uid_length}")
	private int UID_LENGTH;

	@Value("${surl.uid_chars}")
	private String UID_CHARS;

	@Value("${surl.prefix}")
	private String PREFIX;

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

	public String getPREFIX() {
		return PREFIX;
	}

	public Boolean getSECURE() {
		return SECURE;
	}

}
