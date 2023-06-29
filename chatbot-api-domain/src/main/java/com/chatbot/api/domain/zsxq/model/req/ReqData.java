package com.chatbot.api.domain.zsxq.model.req;

import lombok.Data;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:请求对象
 */
@Data
public class ReqData {
    private String text;
    private String[] image_ids = new String[]{};
    private boolean silenced;
}
