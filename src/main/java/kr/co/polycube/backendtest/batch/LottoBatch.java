package kr.co.polycube.backendtest.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LottoBatch {

    @Scheduled(cron = "0 0 0 * * SUN")
    public void checkLottoWinner() {
        // TODO 로또 번호 당첨자 검수
    }
}
