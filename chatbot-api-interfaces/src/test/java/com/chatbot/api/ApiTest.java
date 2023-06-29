package com.chatbot.api;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:
 */
public class ApiTest {
    @Test
    public void questionsAndAnswers() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

        httpGet.addHeader("cookie","zsxq_access_token=B92ADBDF-AC4B-29B1-65ED-B432310524CC_C5E8759FC83E1FA6; zsxqsessionid=febeac8370cd6383c71578c2a65a9bd4; abtest_env=product");
        httpGet.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(httpGet);


        /**
         评价：https://api.zsxq.com/v2/topics/412228154814218/comments
         {
                "succeeded": true,
                "resp_data": {
                    "comment": {
                        "comment_id": 188458825558582,
                        "create_time": "2023-06-29T16:48:43.566+0800",
                        "owner": {
                            "user_id": 422155121514288,
                            "name": "\u5386\u5386\u4e07\u4e61.",
                            "avatar_url": "https:\/\/images.zsxq.com\/FllICvSFCw2_fIp_URBuQis7DaNH?e=1690819199&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:tLqxkSbodSoVTI1YUSnSl--1zCQ=",
                            "location": "\u6e56\u5357"
                        },
                        "text": "\u6536\u5230\u6536\u5230\uff0cover",
                        "likes_count": 0,
                        "rewards_count": 0,
                        "sticky": false
                    }
                }
            }
        * */

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String s = EntityUtils.toString(response.getEntity());
            System.out.println("=========================获取问题=====================" + s);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }


    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/214448128414151/answer");

        httpPost.addHeader("cookie","zsxq_access_token=B92ADBDF-AC4B-29B1-65ED-B432310524CC_C5E8759FC83E1FA6; zsxqsessionid=febeac8370cd6383c71578c2a65a9bd4; abtest_env=product");
        httpPost.addHeader("Content-Type","application/json; charset=UTF-8");

        /**
         silenced：true  是否回答给提问者，true：是，false：否
         */
        String paramJson = "{\n" +
                "    \"req_data\": {\n" +
                "        \"text\": \"不出门就不会晒黑，建议在家睡觉\\n\",\n" +
                "        \"image_ids\": [],\n" +
                "        \"silenced\": false\n" +
                "    }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String s = EntityUtils.toString(response.getEntity());
            System.out.println(s);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}
