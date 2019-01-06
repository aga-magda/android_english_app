package com.magda.aga.english_app_3;

public class EnglishWord {

    private long id;
    private String en_word;
    private int bad_answer;
    private int known_word;
    private int uncertained_word;
    private int unknown_word;


    // constructors
    public EnglishWord(){
    }

    public EnglishWord(String en_word, int bad_answer, int known_word, int uncertained_word, int unknown_word) {
        this.en_word = en_word;
        this.bad_answer = bad_answer;
        this.known_word = known_word;
        this.uncertained_word = uncertained_word;
        this.unknown_word = unknown_word;
    }

    public EnglishWord(int id, String en_word, int bad_answer, int known_word, int uncertained_word, int unknown_word) {
        this.id = id;
        this.en_word = en_word;
        this.bad_answer = bad_answer;
        this.known_word = known_word;
        this.uncertained_word = uncertained_word;
        this.unknown_word = unknown_word;
    }


    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEn_word() {
        return en_word;
    }

    public void setEn_word(String en_word) {
        this.en_word = en_word;
    }

    public int getBad_answer() {
        return bad_answer;
    }

    public void setBad_answer(int bad_answer) {
        this.bad_answer = bad_answer;
    }

    public int getKnown_word() {
        return known_word;
    }

    public void setKnown_word(int known_word) {
        this.known_word = known_word;
    }

    public int getUncertained_word() {
        return uncertained_word;
    }

    public void setUncertained_word(int uncertained_word) {
        this.uncertained_word = uncertained_word;
    }

    public int getUnknown_word() {
        return unknown_word;
    }

    public void setUnknown_word(int unknown_word) {
        this.unknown_word = unknown_word;
    }

}
