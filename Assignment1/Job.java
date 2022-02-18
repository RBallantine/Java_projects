package L00162110.assign1;

/**
 * The Job class encapsulates information about a job.
 * includes job description, rate and time
 *
 * Author Ronan Ballantine L00162110
 */
public class Job {

    private String jobDescription;
    private double rate;
    private double time;

    /**
     * Job constructor initialises jobDescription, rate and time
     * @param jobDescription Sting job that was completed
     * @param rate double hourly rate
     * @param time double time spent on the job
     */
    public Job(String jobDescription, double rate, double time) {
        this.jobDescription = jobDescription;
        this.rate = rate;
        this.time = time;
    }

    /**
     * jobDescription gets the job type
     * @return String jobDescription
     */
    public String getJobDescription() {
        return this.jobDescription;
    }

    /**
     * Calculates price of the job
     * @return double job price
     */
    public double getPrice() {
        return (this.time * this.rate);
    }

    /**
     * gets the rate of the job
     * @return double job rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * gets the time spent on the job
     * @return double time spent
     */
    public double getTime() {
        return time;
    }

    /**
     * overrides the toString to print the job
     * type and price
     * @return String description and price
     */
    @Override
    public String toString() {
        return "Job = " + getJobDescription()
                + ", price = " + getPrice();
    }
}
