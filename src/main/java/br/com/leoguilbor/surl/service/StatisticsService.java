package br.com.leoguilbor.surl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leoguilbor.surl.domain.ShortUrl;
import br.com.leoguilbor.surl.domain.User;
import br.com.leoguilbor.surl.dto.DomainStatsDTO;
import br.com.leoguilbor.surl.dto.StatsDTO;
import br.com.leoguilbor.surl.exception.ObjectNotFoundException;
import br.com.leoguilbor.surl.repository.ShortUrlLogRepository;
import br.com.leoguilbor.surl.repository.ShortUrlRepository;

@Service
public class StatisticsService {

	@Autowired
	private ShortUrlLogRepository repLog;
	@Autowired
	private ShortUrlRepository repShortUrl;

	public StatsDTO countFullStats() {
		StatsDTO statsDTO = new StatsDTO(repLog.countShorted(),repLog.countClicked());
		return statsDTO;
	}
	
	public StatsDTO countFullStatsToday() {
		StatsDTO statsDTO = new StatsDTO(repLog.countShortedToday(),repLog.countClickedToday());
		return statsDTO;
	}
	
	public StatsDTO countStatsByUser(Long id) {
		StatsDTO statsDTO = new StatsDTO(repLog.countShortedByUser(id),repLog.countClickedByUser(id));
		return statsDTO;
	}

	public StatsDTO countStatsByUser(String uid) {
		ShortUrl sUrl = repShortUrl.findByUid(uid).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! UID:" + uid + " Tipo:" + User.class.getName()));
		StatsDTO statsDTO = new StatsDTO(repLog.countShortedByLink(sUrl.getId()),repLog.countClickedByLink(sUrl.getId()));
		return statsDTO;
	}
	
	public List<DomainStatsDTO> countStatsByDomain() {


		return repLog.getDomains().stream()
				.map(value -> new DomainStatsDTO( value,repLog.countShortedByDomain("%"+value+"%"), repLog.countClickedByDomain("%"+value+"%")))
				.collect(Collectors.toList());
	}
	
	public StatsDTO countStatsByDomain(String domain) {
		StatsDTO statsDTO = new StatsDTO(repLog.countShortedByDomain(domain),repLog.countClickedByDomain(domain));
		return statsDTO;
	}

}
