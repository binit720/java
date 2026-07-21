import java.util.Scanner;

public class Student {

    int roll;
    String name;

    Student(int r, String n) {
        roll = r;
        name = n;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Roll: " + roll);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        String n = sc.nextLine();

        System.out.println("Enter your roll:");
        int r = sc.nextInt();

        Student u = new Student(r, n);

        u.display();
    }
}