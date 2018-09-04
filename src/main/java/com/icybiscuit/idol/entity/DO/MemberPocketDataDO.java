package com.icybiscuit.idol.entity.DO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class MemberPocketDataDO implements Serializable {

    private String type;
    private Integer counts;
}
