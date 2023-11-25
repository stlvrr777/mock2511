package com.example.newMock.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class RequestDTO {
    private String rqUID,clientId, account, openDate, closeDate;

}
