public class User {
    private int userRank;
    private int progress;

    // Constructor
    public User() {
        userRank = -8;
        progress = 0;
    }

    // Getter for rank
    public int getRank() {
        return userRank;
    }

    // Getter for progress
    public int getProgress() {
        return progress;
    }

    // Method to increase progress based on activity rank
    public void incProgress(int activityRank) {
        if (activityRank < -8 || activityRank > 8 || activityRank == 0) {
            throw new IllegalArgumentException("The rank of an activity cannot be less than 8, 0, or greater than 8!");
        }
        int difference =  calculateRank(activityRank) - calculateRank(userRank);
        int pointsEarned = 0;

        if (difference == 0) {
            pointsEarned = 3;
        } else if (difference == -1) {
            pointsEarned = 1;
        } else if (difference > 0) {
            pointsEarned = 10 * difference * difference;
        }

        if (pointsEarned > 0) {
            updateProgress(pointsEarned);
        }
    }

    // Adjusts the ranking system so that rank -1 is directly followed by rank 1
    private int calculateRank(int rankValue) {
        if (rankValue < 0) {
            return rankValue;
        } else {
            return rankValue - 1; // Shifts positive ranks down by one to account for the missing zero
        }
    }

    // Update progress and rank accordingly
    private void updateProgress(int points) {
        progress = progress + points;

        while (progress >= 100 && userRank < 8) {
            progress = progress - 100;

            if (userRank == -1) {
                userRank = 1;
            } else {
                userRank++;
            }
        }

        if (userRank == 8) {
            progress = 0; // Stop further progress at rank 8
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "rank=" + userRank +
                ", progress=" + progress +
                '}';
    }
}