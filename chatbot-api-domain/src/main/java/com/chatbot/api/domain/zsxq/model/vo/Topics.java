package com.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:
 */
@Data
public class Topics {
    private String topic_id;

    private Group group;

    private String type;

    private Question question;

    private boolean answered;

    private int likes_count;

    private int rewards_count;

    private int comments_count;

    private int reading_count;

    private int readers_count;

    private boolean digested;

    private boolean sticky;

    private String create_time;

    private UserSpecific user_specific;
}
