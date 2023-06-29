package com.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:
 */
@Data
public class Question {
    private Owner owner;

    private Questionee questionee;

    private String text;

    private boolean expired;

    private boolean anonymous;

    private OwnerDetail owner_detail;

    private String owner_location;
}
