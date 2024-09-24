package kr.co.polycube.backendtest.lotto.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LottoRepo extends JpaRepository<Lotto, Long> {
}