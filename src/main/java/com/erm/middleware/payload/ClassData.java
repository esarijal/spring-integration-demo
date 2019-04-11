package com.erm.middleware.payload;

public class ClassData {
    private String classRoom;
    private int passed;
    private int failed;

    public ClassData() {
    }

    public ClassData(String classRoom, int passed, int failed) {
        this.classRoom = classRoom;
        this.passed = passed;
        this.failed = failed;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    @Override
    public String toString() {
        return "ClassData{" +
                "classRoom='" + classRoom + '\'' +
                ", passed=" + passed +
                ", failed=" + failed +
                '}';
    }
}
