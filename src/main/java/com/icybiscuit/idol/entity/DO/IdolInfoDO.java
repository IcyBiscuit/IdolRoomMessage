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
@JsonIgnoreProperties({"id"})
public class IdolInfoDO implements Serializable {

    //    @JsonIgnore
    private Long id;
    private String memberName;
    private String memberTeam;
    private String supportColor;
    private String supportNumber;

    private String roomId;
    private String birthday;
//    private String backgroundColor;
//    private String borderColor;

    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberTeam() {
        return memberTeam;
    }

    public String getSupportColor() {
        return supportColor;
    }

    public String getSupportNumber() {
        return supportNumber;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getBirthday() {
        return birthday;
    }
}
