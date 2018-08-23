package com.icybiscuit.idol.entity.DO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * `id`, `message_id`, `message_type`, `sender_name`, `sender_level`,
 * `msg_text`, `flip_text`, `flip_username`, `platform`, `room_id`,
 * `source_id`, `version`, `reference_title`, `reference_content`,
 * `phone_name`, `msg_time`, `insert_time`
 */
@Getter
@Setter
@ToString
public class RoomMsgDO implements Serializable {
    private Long id;
    private String senderName;
    private String messageType;
    private String senderLevel;
    private String msgText;
    private String flipText;
    private String flipUsername;
    private Long roomId;
    private Date msgTime;

}
