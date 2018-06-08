package com.tianmao.service.type;
import java.io.Serializable;

/**
 * 枚举基类
 *
 * @author roach
 * @date 2017/12/7
 */
public interface BaseEnum extends Serializable {

    int getIndex();

    void setIndex(int index);

    String getRemark();

    void setRemark(String remark);

}