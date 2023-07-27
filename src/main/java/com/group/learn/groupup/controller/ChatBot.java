package com.group.learn.groupup.controller;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wangyuancun
 * @description: java类作用描述
 * @date: 2023/7/27
 */
@RestController
public class ChatBot {
    @Autowired
    private OpenAiClient openAiClient;
    @PostMapping(value = "/query/v2")
    public List<Message> questionV2(@RequestBody String request) {
        Message message = Message.builder()
                .role(Message.Role.USER)  //
                .content(request)  // 问题内容
                .build();
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .model("gpt-3.5-turbo")  // 模型选择（chatGPT 默认为这个）
                .messages(Arrays.asList(message))
                .stream(false)   // 是否是流式问答，我选择的不是，需要等gpt回答完才能拿到完整数据
                .build();
        ChatCompletionResponse chatCompletionResponse = null;
        chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
        List<Message> list = new ArrayList<>();
        chatCompletionResponse.getChoices().forEach(e -> {
            list.add(e.getMessage());
        });
        return list;
    }

}
