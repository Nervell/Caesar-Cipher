public class Unit {
    private boolean status;
    private String word;

    public Unit(boolean status, String word) {
        this.status = status;
        this.word = word;
    }

    public boolean isStatus() {
        return status;
    }

    public String getWord() {
        return word;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
