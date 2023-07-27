package com.group.learn.groupup.config;

import com.unfbx.chatgpt.OpenAiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wangyuancun
 * @description: java类作用描述
 * @date: 2023/7/27
 */
@Configuration
public class OpenAiConfig {
    private final static String token = "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";

    @Bean
    public OpenAiClient createClient() {
        return OpenAiClient.builder().apiKey(token)
                .connectTimeout(1000*60*3)    //3分钟还没连接上就失败
                .readTimeout(1000*60*3)
                .writeTimeout(1000*60*3)
                .build();
    }
}


