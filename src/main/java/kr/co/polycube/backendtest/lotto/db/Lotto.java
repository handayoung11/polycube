package kr.co.polycube.backendtest.lotto.db;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
