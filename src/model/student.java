package model;

public class student {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String course;

    public student(int id, String name, int age, String gender, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.course = course;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getCourse() { return course; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setCourse(String course) { this.course = course; }
}