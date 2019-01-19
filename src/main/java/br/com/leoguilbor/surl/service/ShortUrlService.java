package br.com.leoguilbor.surl.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leoguilbor.surl.config.AppConfigParameter;
import br.com.leoguilbor.surl.domain.ShortUrl;
import br.com.leoguilbor.surl.exception.ObjectNotFoundException;
import br.com.leoguilbor.surl.exception.UrlNotShortedException;
import br.com.leoguilbor.surl.repository.ShortUrlRepository;

@Service
public class ShortUrlService {


	@Autowired
	private AppConfigParameter params;
	
	@Autowired
	private ShortUrlRepository rep;

	public ShortUrl findOne(String uid) {
		Optional<ShortUrl> shortUrl = rep.findByUid(uid);
		return shortUrl.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! UID:" + uid + " Tipo:" + ShortUrl.class.getName()));
	}

	@Transactional
	public ShortUrl insert(ShortUrl surl) {

		surl.setId(null);
		String shortUid;

		do {
			
			shortUid = RandomStringUtils.random(params.getUID_LENGTH(), params.getUID_CHARS());
			
		}while (shortUrlExist(shortUid));

		surl.setUid(shortUid);
		return rep.save(surl);
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

		ShortUrl surl = findOne(uid);
		rep.deleteById(surl.getId());
	}

	public ShortUrl findByUrl(String url) {
		Optional<ShortUrl> oSurl = rep.findByUrl(url);
		return oSurl.orElseThrow(() -> new UrlNotShortedException("URL '" + url + "' wasn't shorted yet!"));

	}

	public String getPrefix() {
		// TODO Auto-generated method stub
		if (params.getSECURE()) {
			return "https://"+this.params.getPREFIX();	
		}
		return "http://"+this.params.getPREFIX();
	}

}
