package com.icybiscuit.idol.entity.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RankVO implements Serializable {
    private String name;
    private int counts;
}
