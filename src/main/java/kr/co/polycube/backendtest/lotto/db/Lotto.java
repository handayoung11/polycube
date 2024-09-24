package kr.co.polycube.backendtest.lotto.db;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_1", nullable = false)
    private int number1;
    @Column(name = "number_2", nullable = false)
    private int number2;
    @Column(name = "number_3", nullable = false)
    private int number3;
    @Column(name = "number_4", nullable = false)
    private int number4;
    @Column(name = "number_5", nullable = false)
    private int number5;
    @Column(name = "number_6", nullable = false)
    private int number6;

    public static Lotto createLotto() {
        Random random = new Random();
        Lotto lotto = new Lotto();
        lotto.number1 = random.nextInt(100);
        lotto.number2 = random.nextInt(100);
        lotto.number3 = random.nextInt(100);
        lotto.number4 = random.nextInt(100);
        lotto.number5 = random.nextInt(100);
        lotto.number6 = random.nextInt(100);
        return lotto;
    }
}
