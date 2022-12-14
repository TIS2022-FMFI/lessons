package main.entities;

public class Key_word {
    private Integer key_word_id;
    private String title;

    public boolean isPrime() {
        return prime;
    }

    public void setPrime(boolean prime) {
        this.prime = prime;
    }

    private boolean prime;

    public void setKey_word_id(Integer key_word_id) {
        this.key_word_id = key_word_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getKey_word_id() {
        return key_word_id;
    }

    public String getTitle() {
        return title;
    }
}
