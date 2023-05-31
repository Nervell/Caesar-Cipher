public class Unit {
    private boolean status;
    private String word;
    //constructor
    public Unit(boolean status, String word) {
        this.status = status;
        this.word = word;
    }
    //getter
    public boolean isStatus() {
        return status;
    }
    //getter
    public String getWord() {
        return word;
    }
    //setter
    public void setStatus(boolean status) {
        this.status = status;
    }
    //setter
    public void setWord(String word) {
        this.word = word;
    }

}
