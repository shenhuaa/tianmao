package tianmao.model.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "system_user_message")
public class UserMessage implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    /**
     *
     */
    private Long id;
    /**
     *标题
     */
    private String title;
    /**
     *内容
     */
    private String content;
    /**
     *用户id
     */
    private Long userId;
    /**
     *是否已读
     */
    private Boolean  isRead;
    /**
     *时间
     */
    private Date addTime;
}
