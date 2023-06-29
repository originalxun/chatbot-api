package com.chatbot.api.domain.zsxq;

import com.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:知识星球 API 接口
 */
public interface IZsxqApi {

    // 未回答列表
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsAggregates(String groupId, String cookie) throws IOException;

    boolean answer(String groupId,String cookie,String topicId,String text,boolean silenced) throws  IOException;


}
