package com.example.newMock.Model;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDTO {
    private String rqUID, clientId, account, currency;
    private BigDecimal balance, maxLimit;

}
