package com.icybiscuit.idol.entity.DO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MsgDaysCounts implements Serializable {
    private String team;
    private String date;
    private Long counts;
}
