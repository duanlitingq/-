import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by 付鹏 on 2017/10/10.
 */

@ContextConfiguration(locations={"classpath:spring-*.xml"})
@Controller
public class test {

    @Autowired
    private RedisTemplate template ;

    @Test
    public void testRedis(){

//        stringRedisTemplate.opsForValue().set("1","fuengp");
//        stringRedisTemplate.opsForValue().set("2","hello world!");
//        System.out.println(stringRedisTemplate.opsForValue().get("1"));
//        System.out.println(stringRedisTemplate.opsForValue().get("2"));
//        System.out.println("---------------------------");
        System.out.println("------------template---------------" + template);

    }

}
