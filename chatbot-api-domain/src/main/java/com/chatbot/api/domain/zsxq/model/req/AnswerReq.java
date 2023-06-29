package com.chatbot.api.domain.zsxq.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:请求问答接口信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerReq {
    private ReqData req_data;
}
