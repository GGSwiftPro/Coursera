package org.example.Model;

import java.time.LocalDate;

public class Courses {
    int id;
    String name;
    int instructor_id;
    Byte total_time;
    Byte credit;
    LocalDate time_created;



    public Courses(int id, String name, int instructor_id, Byte total_time, Byte credit, LocalDate time_created) {
        this.id = id;
        this.name = name;
        this.instructor_id = instructor_id;
        this.total_time = total_time;
        this.credit = credit;
        this.time_created=time_created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public Byte getTotal_time() {
        return total_time;
    }

    public void setTotal_time(Byte total_time) {
        this.total_time = total_time;
    }

    public Byte getCredit() {
        return credit;
    }

    public void setCredit(Byte credit) {
        this.credit = credit;
    }

    public LocalDate getTime_created() {
        return time_created;
    }

    public void setTime_created(LocalDate time_created) {
        this.time_created = time_created;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructor_id=" + instructor_id +
                ", total_time=" + total_time +
                ", credit=" + credit +
                ", time_created=" + time_created +
                '}';
    }
}
