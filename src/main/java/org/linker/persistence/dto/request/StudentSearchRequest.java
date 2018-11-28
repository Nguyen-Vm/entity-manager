package org.linker.persistence.dto.request;

import java.io.Serializable;

/**
 * @author RWM
 * @date 2018/11/28
 */
public class StudentSearchRequest implements Serializable {

    public Integer nowPage;

    public Integer pageSize;

    public String name;
}
