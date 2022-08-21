public class Subject {
    private String title;
    private int mark;

    public Subject() {
        title = "subject";
        mark = 0;
    }

    public String getTitle(){return title;}

    public int getMark(){return mark;}

    public void setTitle(String newTitle){
        title = newTitle;
    }

    public void setMark(int newMark){
        mark = newMark;
    }
}
