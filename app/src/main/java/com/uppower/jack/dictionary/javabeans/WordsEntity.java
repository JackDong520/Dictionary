package com.uppower.jack.dictionary.javabeans;

import java.io.Serializable;

/**
 * Created by 72408 on 2017/5/16.
 */
public class WordsEntity implements Serializable {
    @Override
    public String toString() {
        return "WordsEntity{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", exchange='" + exchange + '\'' +
                ", voice='" + voice + '\'' +
                ", times=" + times +
                '}';
    }

    private int id;
    private String word;
    private String exchange;
    private String voice;
    private Integer times;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordsEntity that = (WordsEntity) o;

        if (id != that.id) return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;
        if (exchange != null ? !exchange.equals(that.exchange) : that.exchange != null) return false;
        if (voice != null ? !voice.equals(that.voice) : that.voice != null) return false;
        if (times != null ? !times.equals(that.times) : that.times != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + (exchange != null ? exchange.hashCode() : 0);
        result = 31 * result + (voice != null ? voice.hashCode() : 0);
        result = 31 * result + (times != null ? times.hashCode() : 0);
        return result;
    }
}
