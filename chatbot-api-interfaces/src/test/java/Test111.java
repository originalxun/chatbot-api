import com.api.chatbot.ApiApplicationInterfaces;
import com.chatbot.api.domain.zsxq.IZsxqApi;
import com.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:
 */
@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest(classes = ApiApplicationInterfaces.class)
public class Test111 {

    private Logger logger = LoggerFactory.getLogger(Test111.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;


    @Test
    public void zsxqApiTest() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsAggregates(groupId, cookie);
        System.out.println(unAnsweredQuestionsAggregates);
        logger.info("测试结果：{}",unAnsweredQuestionsAggregates);
//        System.out.println("1");
    }

    @Test
    public void soutTest(){
        System.out.println(1111);
    }
}
