package com.codrpwh.rabbitmq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FundTypeDto {

    private Integer type;
    private String desc;

}
