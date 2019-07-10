package Facade;

class Courses {
    private String[] courses = new String[]{
            "Java",
            "Spring",
            "DMBS",
            "JQuery",
            "React",
    };

    String[] getCourses() {
        return courses;
    }
}
