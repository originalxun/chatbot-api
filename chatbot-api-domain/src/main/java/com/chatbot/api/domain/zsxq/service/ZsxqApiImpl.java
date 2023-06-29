package com.chatbot.api.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chatbot.api.domain.zsxq.IZsxqApi;
import com.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.chatbot.api.domain.zsxq.model.req.AnswerReq;
import com.chatbot.api.domain.zsxq.model.req.ReqData;
import com.chatbot.api.domain.zsxq.model.res.AnswerRes;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:
 */
public class ZsxqApiImpl implements IZsxqApi {


    private Logger logger = LoggerFactory.getLogger(ZsxqApiImpl.class);

    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsAggregates(String groupId, String cookie) throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = new UnAnsweredQuestionsAggregates();

        String uri = "https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=all&count=20";

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(uri);

        httpGet.addHeader("cookie", cookie);
        httpGet.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            unAnsweredQuestionsAggregates = JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
            logger.info("拉取提问数据。groupId：{} jsonStr：{}", groupId, jsonStr);
        } else {
            logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
        }
        return unAnsweredQuestionsAggregates;
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        AnswerRes answerRes = new AnswerRes();

        String uri = "https://api.zsxq.com/v2/topics/" + topicId + "/answer";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(uri);

        httpPost.addHeader("cookie", cookie);
        httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");

        /**
         silenced：true  是否回答给提问者，true：是，false：否
         */
//        String paramJson = "{\n" +
//                "    \"req_data\": {\n" +
//                "        \"text\": \"" + text + "\n\",\n" +
//                "        \"image_ids\": [],\n" +
//                "        \"silenced\": " + silenced + "\n" +
//                "    }\n" +
//                "}";

        ReqData reqData = new ReqData(text, silenced);
        AnswerReq answerReq = new AnswerReq(reqData);

        String paramJson = JSONObject.toJSONString(answerReq);

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            logger.info("回答问题结果。groupId：{} topicId：{} jsonStr：{}", groupId, topicId, jsonStr);
        } else {
            logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
        }
        return answerRes.isSucceeded();
    }
}
