package L00162110.assign1;

import java.util.ArrayList;

/**
 * Student Class extends Person and maintains
 * a list of jobs they have done as well as any
 * money made from doing jobs that they can then
 * use to buy beer.
 *
 * Author Ronan Ballantine L00162110
 */
public class Student extends Person implements PartTimeAble {

    private double beerMoney;
    private String course;
    private ArrayList<Job> jobs = new ArrayList<Job>();


    /**
     * Student Constructor initialises student name,
     * age and course they are doing
     * @param name String student's name
     * @param age int student's age
     * @param course String student's course
     */
    public Student(String name, int age, String course) {
        super(name, age);
        this.course = course;
    }

    /**
     * Student Constructor initialises student name,
     * age, course they are doing and print output format
     * @param name String student's name
     * @param age int student's age
     * @param course String student's course
     * @param printer ConsolePrint output format
     */
    public Student(String name, int age, String course, ConsolePrint printer) {
        super(name, age, printer);
        this.course = course;
    }

    /**
     * Method retrieves beer money earned
     * @return double beerMoney
     */
    public double getBeerMoney() {
        return this.beerMoney;
    }

    /**
     * Method retrieves student's course of study
     * @return String course
     */
    public String getCourse() {
        return this.course;
    }

    /**
     * gets a list of jobs completed by the student
     * @return ArrayList<Job> jobs
     */
    public ArrayList<Job> getJobs() {
        return jobs;
    }

    /**
     * Overrides doJob method to add job price
     * to earned beer money
     * @param j job completed
     */
    @Override
    public void doJob(Job j) {
        this.beerMoney += j.getPrice();
        jobs.add((j));
    }

    /**
     * method generates a description of the student
     * including name, age, course, beer money earned
     * and a list of jobs completed
     * @return String getDescription
     */
    @Override
    public String getDescription() {
        return "Student Name: " + super.getName()
                + ", Student Age: " + super.getAge()
                + ", course: " + this.course
                + ", Beer Money: " + this.beerMoney
                + ", Job: " + getJobs();
    }

}
