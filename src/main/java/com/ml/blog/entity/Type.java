package com.ml.blog.entity;

public class Type {

    private Integer typeId;

    private String typeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Type() {
    }

    public Type(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

}