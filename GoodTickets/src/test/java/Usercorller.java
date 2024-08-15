import com.yhy.mapper.UserMapper;
import com.yhy.entity.po.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class Usercorller {
    @Test
    public void testq() throws Exception {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user = userMapper.selectbyphone("18735470484");
        System.out.println(user);
    }
}
