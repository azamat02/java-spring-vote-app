package main.spring.models;

public class Stat {
    public String question_text;
    public String option_a;
    public String option_b;
    public String option_c;

    public int option_a_count;
    public int option_b_count;
    public int option_c_count;
    public int total;

    public Stat(String question_text, String option_a, String option_b, String option_c, int option_a_count, int option_b_count, int option_c_count) {
        this.question_text = question_text;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_a_count = option_a_count;
        this.option_b_count = option_b_count;
        this.option_c_count = option_c_count;
        this.total = option_a_count + option_b_count + option_c_count;
    }

    public float getPercentagesOf(String option_to_calculate){
        float percentage = 0;

        if (option_to_calculate.equalsIgnoreCase("A")){
            percentage = (this.option_a_count * 100)/total;
        }
        if (option_to_calculate.equalsIgnoreCase("B")){
            percentage = (this.option_b_count * 100)/total;
        }
        if (option_to_calculate.equalsIgnoreCase("C")){
            percentage = (this.option_c_count * 100)/total;
        }
        return percentage;
    }

    @Override
    public String toString(){
        return "Answered "+total+" users\nA. "+option_a+" is "+getPercentagesOf("a")+"%\n" +
                "B. "+option_b+" is "+getPercentagesOf("b")+"%\n" +
                "C. "+option_c+" is "+getPercentagesOf("c")+"%\n";
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public int getOption_a_count() {
        return option_a_count;
    }

    public void setOption_a_count(int option_a_count) {
        this.option_a_count = option_a_count;
    }

    public int getOption_b_count() {
        return option_b_count;
    }

    public void setOption_b_count(int option_b_count) {
        this.option_b_count = option_b_count;
    }

    public int getOption_c_count() {
        return option_c_count;
    }

    public void setOption_c_count(int option_c_count) {
        this.option_c_count = option_c_count;
    }
}
