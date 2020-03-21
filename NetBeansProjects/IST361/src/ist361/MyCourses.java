package ist361;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Julia
 */
public class MyCourses {

    private static ObservableList<String> courses = FXCollections.observableArrayList();
    private String course;

    public MyCourses() {
        this.courses = courses;
        this.course = course;
    }
    
    public static void main(String[] args) {
      MyCourses obj = new MyCourses();
   }

    public static ObservableList<String> getCourses() {
        return courses;
    }

    public static void addCourse(String e) {
        courses.add(e);
    }
    
    public static void printCourses(){
        for (int i = 0; i < courses.size(); i++){
            System.out.println(courses.get(i));
        }
    }

}
