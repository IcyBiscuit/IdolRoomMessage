package com.icybiscuit.idol.entity.DO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * `member_name`, `member_team`, `support_color`, `support_number`, `room_id`, `birthday`
 */

@Getter
@Setter
@ToString
@JsonIgnoreProperties({"id","roomId"})
public class IdolInfoDO implements Serializable {

//    @JsonIgnore
    private Long id;
    private String memberName;
    private String memberTeam;
    private String supportColor;
    private String supportNumber;

//    @JsonIgnore
    private String roomId;
    private String birthday;
//    private String backgroundColor;
//    private String borderColor;
}
