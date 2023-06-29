package com.chatbot.api.domain.zsxq.model.aggregates;

import com.chatbot.api.domain.zsxq.model.res.RespData;
import lombok.Data;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:未回答问题的聚合信息
 */
@Data
public class UnAnsweredQuestionsAggregates {
    private boolean succeeded;
    private RespData resp_data;

}
