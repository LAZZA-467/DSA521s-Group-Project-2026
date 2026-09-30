public class DailyStatistics {
    public static void main(String[] args) {
        int[] serviceTimes = {12, 5, 8, 4, 15, 9};
        int totalStudents = serviceTimes.length;
        int totalServiceTime = 0;

        int highestServiceTime = serviceTimes[0];
        int lowestServiceTime = serviceTimes[0];
        int servicesLongerThanTen = 0;

        for (int i = 0; i < serviceTimes.length; i++) {
            int time = serviceTimes[i];
            totalServiceTime = totalServiceTime + time;

            if (time > highestServiceTime) {
                highestServiceTime = time;
            }

            if (time < lowestServiceTime) {
                lowestServiceTime = time;
            }

            if (time > 10) {
                servicesLongerThanTen++;
            }
        }
        double averageServiceTime;

        if (totalStudents > 0) {
            averageServiceTime = (double) totalServiceTime / totalStudents;
        } else {
            averageServiceTime = 0.0;
        }

        System.out.println("===================================");
        System.out.println("     DAILY SERVICE CENTRE STATS     ");
        System.out.println("===================================");
        System.out.println("Total students served: " + totalStudents);
        System.out.println("Total service time: " + totalServiceTime + " mins");
        System.out.println("Average service time: " + String.format("%.2f", averageServiceTime) + " mins");
        System.out.println("Highest service time: " + highestServiceTime + " mins");
        System.out.println("Lowest service time: " + lowestServiceTime + " mins");
        System.out.println("Services longer than 10 mins: " + servicesLongerThanTen);
        System.out.println("=================================");
    }
}
