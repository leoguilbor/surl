package br.com.leoguilbor.surl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leoguilbor.surl.domain.ShortUrl;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long>{
	Optional<ShortUrl> findByUid(String uid);
	Optional<ShortUrl> findByUrl(String url);
}
