import com.chatbot.api.domain.zsxq.IZsxqApi;
import com.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author: fly
 * @createTime: 2023.06.29
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

//    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Autowired
    private IZsxqApi zsxqApi;


    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsAggregates(groupId, cookie);
        System.out.println(unAnsweredQuestionsAggregates);
//        logger.info("测试结果：{}",unAnsweredQuestionsAggregates);
    }
}
