package tianmao.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 当前登录用户
 *
 * @author roach
 * @date 2017/11/15
 */
@Data
@Builder
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = 8448769549180508819L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 登陆用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * app登陆的token
     */
    private String token;

    /**
     * 用户头像
     */
    private String headImgUrl;

    /**
     * 签名
     */
    private String signature;

    /**
     * 门店id
     */
    private Long shopId;

}