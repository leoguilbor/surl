package br.com.leoguilbor.surl.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leoguilbor.surl.config.AppConfigParameter;
import br.com.leoguilbor.surl.domain.LogType;
import br.com.leoguilbor.surl.domain.ShortUrl;
import br.com.leoguilbor.surl.domain.ShortUrlLog;
import br.com.leoguilbor.surl.dto.NewShortUrlDTO;
import br.com.leoguilbor.surl.dto.ShortUrlDTO;
import br.com.leoguilbor.surl.exception.ObjectNotFoundException;
import br.com.leoguilbor.surl.exception.UrlNotShortedException;
import br.com.leoguilbor.surl.repository.ShortUrlLogRepository;
import br.com.leoguilbor.surl.repository.ShortUrlRepository;
import br.com.leoguilbor.surl.repository.UserRepository;
import br.com.leoguilbor.surl.security.JwtUtils;

@Service
public class ShortUrlService {

	@Autowired
	private AppConfigParameter params;

	@Autowired
	private ShortUrlRepository rep;
	@Autowired
	private ShortUrlLogRepository repLog;
	@Autowired
	private UserRepository repUser;
	
	@Autowired
	private JwtUtils jwtUtils;

	public ShortUrl findOne(String uid,String token) {
		Optional<ShortUrl> shortUrl = rep.findByUid(uid);
		ShortUrlLog newSurlLog = new ShortUrlLog();
		
		newSurlLog.setExist(true);
		newSurlLog.setType(LogType.ACCESS);
		newSurlLog.setDate(Calendar.getInstance().getTime());
		newSurlLog.setUser(repUser.findByLogin(jwtUtils.getUsername(token.replace("Bearer ", ""))).orElse(null));
		ShortUrl sUrl = shortUrl.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! UID:" + uid + " Tipo:" + ShortUrl.class.getName()));
		newSurlLog.setShortUrl(sUrl);
		repLog.save(newSurlLog);
		return sUrl;
	}

	@Transactional
	public ShortUrl insert(NewShortUrlDTO newShortUrlDTO,String token) {

		ShortUrl newSurl;
		String shortUid;
		ShortUrlLog newSurlLog = new ShortUrlLog();
		
		try {
			newSurl = this.findByUrl(newShortUrlDTO.getUrl());
			newSurlLog.setExist(true);
		} catch (UrlNotShortedException e) {
			newSurl = this.fromDTO(newShortUrlDTO);
			newSurl.setId(null);
			newSurlLog.setExist(false);
			do {

				shortUid = RandomStringUtils.random(params.getUID_LENGTH(), params.getUID_CHARS());

			} while (shortUrlExist(shortUid));

			newSurl.setCreatedAt(Calendar.getInstance().getTime());
			newSurl.setUid(shortUid);
			newSurl = rep.save(newSurl);
		}
		newSurlLog.setType(LogType.SHORT);
		newSurlLog.setShortUrl(newSurl);
		newSurlLog.setDate(Calendar.getInstance().getTime());
		newSurlLog.setUser(repUser.findByLogin(jwtUtils.getUsername(token.replace("Bearer ", ""))).orElse(null));
		
		repLog.save(newSurlLog);
		return newSurl;
	}

	private Boolean shortUrlExist(String shortUid) {
		// TODO Auto-generated method stub
		if (rep.findByUid(shortUid).isPresent()) {
			return true;
		}
		return false;
	}

	public List<ShortUrl> listAll() {
		return rep.findAll();
	}

	@Transactional
	public ShortUrl update(ShortUrl shortUrl) {

		return rep.save(shortUrl);
	}

	@Transactional
	public void delete(String uid) {

		ShortUrl surl = findOne(uid,"");
		rep.deleteById(surl.getId());
	}

	public ShortUrl findByUrl(String url) {
		Optional<ShortUrl> oSurl = rep.findByUrl(url);
		return oSurl.orElseThrow(() -> new UrlNotShortedException("URL '" + url + "' wasn't shorted yet!"));

	}

	public String getPrefix() {
		return this.params.getPrefix();
	}
	
	public ShortUrl fromDTO(NewShortUrlDTO dto) {
		return new ShortUrl(null, dto.getUrl(), null, null,null);
	}
	
	public ShortUrl fromDTO(ShortUrlDTO dto) {
		return new ShortUrl(null, dto.getUrl(), dto.getUid(),null,null);
	}
}
