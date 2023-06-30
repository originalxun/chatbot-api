package com.chatbot.api;

import com.chatbot.api.domain.zsxq.IZsxqApi;
import com.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.chatbot.api.domain.zsxq.model.res.RespData;
import com.chatbot.api.domain.zsxq.model.vo.Question;
import com.chatbot.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerTest {

    private Logger logger = LoggerFactory.getLogger(AnswerTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Autowired
    private IZsxqApi zsxqApi;


    @Test
    public void zsxqApiTest() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsAggregates(groupId, cookie);
        logger.info("测试结果：{}",unAnsweredQuestionsAggregates);

        RespData resp_data = unAnsweredQuestionsAggregates.getResp_data();
        List<Topics> topics = resp_data.getTopics();

        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String questionText = topic.getQuestion().getText();
//            String questionText = question.getText();

            logger.info("tipicId：{}，text:{}",topicId,questionText);

            // 回答问题
            zsxqApi.answer(groupId,cookie,topicId,questionText,false);
        }
    }

    @Test
    public void soutTest(){
        System.out.println(1111);
    }
}
