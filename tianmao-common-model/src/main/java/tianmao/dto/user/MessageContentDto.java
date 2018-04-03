package tianmao.dto.user;

import lombok.Data;
import tianmao.type.user.MessageTemplateType;

import java.io.Serializable;

/**
 * pc发贴dto
 *
 * @author roach
 * @date 2017/12/19
 */
@Data
public class MessageContentDto implements Serializable {

    private static final long serialVersionUID = -1170104897947744886L;

    private MessageTemplateType messageType;

    private String[] content;

    private Long userId;

    private MessageContentDto() {

    }

    public MessageContentDto(MessageTemplateType messageType, Long userId, String... content) {
        this.messageType = messageType;
        this.content = content;
        this.userId = userId;
    }

}
