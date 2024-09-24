package kr.co.polycube.backendtest.lotto;

import kr.co.polycube.backendtest.lotto.db.Lotto;
import kr.co.polycube.backendtest.lotto.db.LottoRepo;
import kr.co.polycube.backendtest.lotto.dto.CreateLottoResDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("lottos")
public class LottoController {

    private LottoRepo lottoRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateLottoResDTO createLotto() {
        Lotto lotto = Lotto.createLotto();
        lottoRepo.save(lotto);
        return new CreateLottoResDTO(lotto);
    }
}
