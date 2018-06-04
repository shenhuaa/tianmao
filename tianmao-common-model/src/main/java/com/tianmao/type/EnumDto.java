package com.tianmao.type;

import lombok.Data;

import java.io.Serializable;

/**
 * @author roach
 * @date 2017/11/23
 */
@Data
public class EnumDto implements Serializable {

    private int index;

    private String name;

    private EnumDto() {

    }

    public EnumDto(int index, String name) {
        this.index = index;
        this.name = name;
    }

}
