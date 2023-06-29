package com.chatbot.api.domain.zsxq.model.req;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public ReqData() {
    }

    public ReqData(String text, boolean silenced) {
        this.text = text;
        this.silenced = silenced;
    }

}
