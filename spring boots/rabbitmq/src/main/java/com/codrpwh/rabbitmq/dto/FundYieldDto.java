package com.codrpwh.rabbitmq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FundYieldDto {

    private String yield;
    private String desc;

}
