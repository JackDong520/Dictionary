package com.uppower.jack.dictionary.javabeans;

/**
 * Created by 72408 on 2017/5/16.
 */
public class PosEntity {
    private int id;
    private String name;
    private String means;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosEntity posEntity = (PosEntity) o;

        if (id != posEntity.id) return false;
        if (name != null ? !name.equals(posEntity.name) : posEntity.name != null) return false;
        if (means != null ? !means.equals(posEntity.means) : posEntity.means != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (means != null ? means.hashCode() : 0);
        return result;
    }
}
