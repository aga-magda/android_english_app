package com.magda.aga.english_app_3;

public class PolishWord {

    private long id;
    private String pl_word;



    // constructors
    public PolishWord(){
    }

    public PolishWord(String pl_word){
        this.pl_word = pl_word;
    }

    public PolishWord(int id, String pl_word) {
        this.id = id;
        this.pl_word = pl_word;
    }


    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPl_word() {
        return pl_word;
    }

    public void setPl_word(String pl_word) {
        this.pl_word = pl_word;
    }
}
