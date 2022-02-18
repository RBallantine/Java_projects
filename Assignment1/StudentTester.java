package L00162110.assign1;

import java.util.ArrayList;

public class StudentTester {

    public static void main(String[] args){
        //Create a student
        Student s1 = new Student("Black, Jack", 19, "Electronics");

        System.out.println(s1.getDescription());

        s1.doJob(new Job("clean car", 7.00, 2.0));
        s1.doJob(new Job("paint house", 8.00, 10.00));

        System.out.println(s1.getDescription());

        ArrayList<Job> jacksJobs = s1.getJobs();

        for(Job currJob: jacksJobs)
        {
            System.out.println(currJob.toString());
        }

    }


}
