package kr.co.polycube.backendtest.lotto.dto;

import kr.co.polycube.backendtest.lotto.db.Lotto;
import lombok.Getter;

@Getter
public class CreateLottoResDTO {
    private int[] numbers;

    public CreateLottoResDTO(Lotto lotto) {
        numbers = new int[]{lotto.getNumber1(),
                lotto.getNumber2(),
                lotto.getNumber3(),
                lotto.getNumber4(),
                lotto.getNumber5(),
                lotto.getNumber6()};
    }
}
