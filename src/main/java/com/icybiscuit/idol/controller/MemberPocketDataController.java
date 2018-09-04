package com.icybiscuit.idol.controller;

import com.icybiscuit.idol.service.MemberPocketDataService;
import com.icybiscuit.idol.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ajax/pocketdata")
public class MemberPocketDataController {

    private MemberPocketDataService memberPocketDataService;

    @Autowired
    public void setMemberPocketDataService(MemberPocketDataService memberPocketDataService) {
        this.memberPocketDataService = memberPocketDataService;
    }

    @GetMapping(path = "/{roomId}")
    public MessageUtil<Object> getMemberPocketData(@PathVariable String roomId) {
        Map<String, Object> data = service(roomId);

        return buildMessageUtil(data);
    }


    @PostMapping
    public MessageUtil<Object> postMemberPocketData(@RequestParam(name = "roomId", required = false) String roomId) {

        Map<String, Object> data = service(roomId);

        return buildMessageUtil(data);
    }

    private Map<String, Object> service(String roomId) {
        if (roomId != null && roomId.matches("\\d+")) {
            return memberPocketDataService.getMemberPocketData(roomId);
        }
        return null;
    }

    private MessageUtil<Object> buildMessageUtil(Map<String, Object> data) {
        if (data == null) {
            MessageUtil<Object> messageUtil = new MessageUtil<>();
            messageUtil.setDesc("请正确输入请求roomId");
            messageUtil.setStatus(505);
            return messageUtil;
        } else {
            return MessageUtil.ok(data);
        }
    }
}
