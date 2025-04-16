import java.util.;

class Solution {
    static class Applicant {
        String language;
        String job;
        String career;
        String soulFood;
        int score;

        public Applicant(String language, String job, String career, String soulFood, int score) {
            this.language = language;
            this.job = job;
            this.career = career;
            this.soulFood = soulFood;
            this.score = score;
        }
    }


    public static int[] solution(String[] info, String[] query) {
        ArrayListApplicant list = new ArrayList();

        for(String str  info) {
            String[] row = str.split( );

            String language = row[0];
            String job = row[1];
            String career = row[2];
            String food = row[3];
            int score = Integer.parseInt(row[4]);

            Applicant applicant = new Applicant(language, job, career, food, score);
            list.add(applicant);
        }

        int[] answer = new int[query.length];

        for(int i = 0; i  query.length; i++) {
            String[] row = query[i].split( );

            String language = row[0];
            String job = row[2];
            String career = row[4];
            String food = row[6];
            int score = Integer.parseInt(row[7]);

            answer[i] = search(list, language, job, career, food, score);
        }

        return answer;
    }

    public static int search(ArrayListApplicant list, String language, String job, String career, String food, int score) {
        language = language.equals(-)    language;
        job = job.equals(-)    job;
        career = career.equals(-)    career;
        food = food.equals(-)    food;

        int count = 0;
        for(Applicant applicant  list) {
            if(applicant.language.contains(language) && applicant.job.contains(job) && applicant.career.contains(career) && applicant.soulFood.contains(food) && applicant.score = score) {
                count++;
            }
        }

        return count;
    }
}