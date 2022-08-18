public class Condition{
    public static void main(String[] args){
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("점수를 입력해주세요");
        int score = sc.nextInt();
        
        if(score >= 60){
            System.out.println("합격");
            if(score >= 90){
                System.out.println("1등급");
            } else if(score >= 70){
                System.out.println("2등급");
            } else{
                System.out.println("3등급");
            }
        } else {
            System.out.println("불합격");    
        }
    }
}