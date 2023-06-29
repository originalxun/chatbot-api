package com.chatbot.api.domain.zsxq.model.res;

import com.chatbot.api.domain.zsxq.model.vo.Topics;
import lombok.Data;

import java.util.List;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:结果数据
 */
@Data
public class RespData {
    private List<Topics> topics;
}
