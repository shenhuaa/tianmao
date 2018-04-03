package tianmao.mapper;

import org.apache.ibatis.annotations.Param;
import tianmao.model.sencode.VerificationCode;
import tianmao.mybatis.BaseMapper;

/**
 * 验证码服务类
 *
 * @author roach
 * @date 2017/11/27
 */
public interface VerificationCodeMapper extends BaseMapper<VerificationCode> {

    boolean create(VerificationCode verificationCode);

    VerificationCode getByUsernameAndCode(@Param("username") String username, @Param("code") int code);

    VerificationCode getByUsername(@Param("username") String username);

    boolean destroy(@Param("username") String username, @Param("code") int code);

}