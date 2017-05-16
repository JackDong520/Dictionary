package com.uppower.jack.dictionary.javabeans;

/**
 * Created by 72408 on 2017/5/16.
 */
public class MeansEntity {
    private String means;
    private WordsEntity wordsByWordId;
    private PosEntity posByPosId;


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

        MeansEntity that = (MeansEntity) o;

        if (means != null ? !means.equals(that.means) : that.means != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return means != null ? means.hashCode() : 0;
    }

    public WordsEntity getWordsByWordId() {
        return wordsByWordId;
    }

    public void setWordsByWordId(WordsEntity wordsByWordId) {
        this.wordsByWordId = wordsByWordId;
    }

    public PosEntity getPosByPosId() {
        return posByPosId;
    }

    public void setPosByPosId(PosEntity posByPosId) {
        this.posByPosId = posByPosId;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
