package org.flightythought.smile.admin.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_sys_parameter", schema = "smile", catalog = "")
public class SysParameterEntity extends BaseEntity{
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "parameter_key")
    private String parameterKey;

    @Basic
    @Column(name = "parameter_value")
    private String parameterValue;

    @Basic
    @Column(name = "parameter_desc")
    private String parameterDesc;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getParameterKey() {
        return parameterKey;
    }

    public void setParameterKey(String parameterKey) {
        this.parameterKey = parameterKey;
    }


    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterDesc() {
        return parameterDesc;
    }

    public void setParameterDesc(String parameterDesc) {
        this.parameterDesc = parameterDesc;
    }

}
