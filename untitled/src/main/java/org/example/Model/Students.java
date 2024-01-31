package org.example.Model;
import java.time.LocalDate;

public class Students {
    String pin;
    String first_name;
    String last_name;
    LocalDate time_created;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
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

    public Students(String pin, String first_name, String last_name, LocalDate time_created) {
        this.pin = pin;
        this.first_name = first_name;
        this.last_name = last_name;
        this.time_created = time_created;
    }

    @Override
    public String toString() {
        return "Students{" +
                "pin='" + pin + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", time_created=" + time_created +
                '}';
    }
}
