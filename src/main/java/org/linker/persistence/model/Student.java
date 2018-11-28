package org.linker.persistence.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author RWM
 * @date 2018/11/28
 */
@Table
@Entity(name = "t_galidun_student")
public class Student {

    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String id;

    @Column(name = "name", length = 20)
    public String name;
}
