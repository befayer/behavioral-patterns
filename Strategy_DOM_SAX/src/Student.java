public class Student {
    private String lastname;
    private Subject[] subjects;
    private double averageOfMarks;

    public Student(){
        lastname = "default student";
        subjects = new Subject[0];
        averageOfMarks = 0;
    }

    public String getLastname(){return lastname;}

    public void setLastname(String newLastname){
        lastname = newLastname;
    }

    public Subject[] getSubjects(){return subjects;}

    public void setSubjects(Subject[] newSubjects) {
        subjects = newSubjects;
    }

    public double getAverageOfMarks(){
        return averageOfMarks;
    }

    public void setAverageOfMarks(double newAverageOfMarks){
        averageOfMarks = newAverageOfMarks;
    }

    public void setNewValueForAverageOfMarks(){
        double correctAverage = 0;
        for (Subject subject : subjects) {
            correctAverage += subject.getMark();
        }
        correctAverage = correctAverage / subjects.length;
        averageOfMarks = correctAverage;
    }
}
