package com.codrpwh.rabbitmq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FundRankDto {

    private Integer rank;
    private String desc;

}
