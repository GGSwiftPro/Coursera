package org.example.Model;
import java.time.LocalDate;

public class Instructors {
    int id;
    String first_name;
    String last_name;
    LocalDate time_created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getTime_created() {
        return time_created;
    }

    public void setTime_created(LocalDate time_created) {
        this.time_created = time_created;
    }

    public Instructors(int id, String first_name, String last_name, LocalDate time_created) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.time_created = time_created;
    }

    @Override
    public String toString() {
        return "Instructors{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", time_created=" + time_created +
                '}';
    }
}
