package br.com.leoguilbor.surl.repository;


import java.util.List;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.leoguilbor.surl.domain.ShortUrlLog;
import br.com.leoguilbor.surl.dto.DomainStatsDTO;

@Repository
@SqlResultSetMapping(
	    name="shortedByDomainMap",
	    entities = 		@EntityResult(
	    				entityClass = DomainStatsDTO.class, 
	    				fields = {
	    						@FieldResult(name="domain", column="domain"),
	    						@FieldResult(name="shorted",  column="shorted")
	    				})
	    
	)


public interface ShortUrlLogRepository extends JpaRepository<ShortUrlLog, Long>{

	@Query(value = "SELECT count(*) as shorted FROM short_url_log where exist=false AND type = 0 ", nativeQuery = true)
	long countShorted();
	@Query(value = "SELECT count(*) as shorted FROM short_url_log where type = 1", nativeQuery = true)
	long countClicked();
		
	@Query(value = "SELECT count(*) as shorted FROM short_url_log where exist=false AND type = 0 AND date =CURRENT_DATE()", nativeQuery = true)
	long countShortedToday();
	@Query(value = "SELECT count(*) as shorted FROM short_url_log where type = 1 AND date =CURRENT_DATE()", nativeQuery = true)
	long countClickedToday();
	
	@Query(value = "SELECT count(*) as shorted FROM short_url_log where exist=false AND type = 0 and user_id= ?1", nativeQuery = true)
	long countShortedByUser(Long id);
	@Query(value = "SELECT count(*) as shorted FROM short_url_log where type = 1 and user_id= ?1", nativeQuery = true)
	long countClickedByUser(Long id);

	@Query(value = "SELECT count(*) as shorted FROM short_url_log where type = 0 and uid= ?1", nativeQuery = true)
	long countShortedByLink(Long id);
	@Query(value = "SELECT count(*) as shorted FROM short_url_log where type = 1 and uid= ?1", nativeQuery = true)
	long countClickedByLink(Long id);


	@Query(value= "SELECT distinct substring(replace(replace(url,'https://',''),'http://','')"
			+ ",0"
			+ ",LOCATE('/', replace(replace(url,'https://',''),'http://',''))) as domain FROM SHORT_URL"
			, nativeQuery=true)
	List<String> getDomains();
	
	
	//List<DomainStatsDTO> clickedByDomain();
	
	
	@Query(value = "SELECT count(*) as shorted FROM short_url_log sul inner join short_url su on su.id = sul.uid where exist=false AND type = 0 and su.url like ?1", nativeQuery = true)
	long countShortedByDomain(String domain);
	@Query(value = "SELECT count(*) as shorted FROM short_url_log sul inner join short_url su on su.id = sul.uid where type = 1 and su.url like ?1", nativeQuery = true)
	long countClickedByDomain(String domain);

	
	
}
