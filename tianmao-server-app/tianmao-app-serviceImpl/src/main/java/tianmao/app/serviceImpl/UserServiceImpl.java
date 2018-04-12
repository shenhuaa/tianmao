package tianmao.app.serviceImpl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tianmao.common.HttpCode;
import tianmao.app.mapper.UserMapper;
import tianmao.model.user.User;
import tianmao.service.app.UserService;
import tianmao.service.exception.BaseServiceException;

import java.util.List;

@RestController
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> selectList() {
        List<User> usersList = userMapper.selectAll();
        if(1 == 1) {
            throw new BaseServiceException(HttpCode.ILLEGAL_REQUEST,"测试抛service异常");
        }
        return new PageInfo<>(usersList);

    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        user.setMobile(username);
        return userMapper.selectOne(user);
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User registerAccountBind(@RequestBody User user) {
        return null;
    }
}
