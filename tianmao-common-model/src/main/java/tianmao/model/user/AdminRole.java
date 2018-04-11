package tianmao.model.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 管理员角色实体类
 *
 * @author 陈明
 * @date 2017年09月01日
 */
@Data
@Entity
@Table(name = "system_admin_role")
public class AdminRole implements Serializable {
    private static final long serialVersionUID = 2934913079427118929L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private Long adminId;

    private Long roleId;

}