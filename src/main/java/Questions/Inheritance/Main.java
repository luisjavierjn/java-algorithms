package Questions.Inheritance;

// What is printed when a subclass is instantiated, and which parent methods can it use?
public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.setA(1);
        System.out.println(parent.getA());

        Child child = new Child();
        child.setA(2);
        child.setB(3);
        System.out.println(child.getA());
        System.out.println(child.getB());
    }
}

class Parent {
    private int a;

    public Parent() {
        System.out.println("A");
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}

class Child extends Parent {
    private int b;

    public Child() {
        System.out.println("B");
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}

/*
 * Expected output: A, 1, A, B, 2, 3 (one value per line).
 * Creating Child invokes Parent's constructor first through the implicit super() call.
 * Child inherits Parent's public getters and setters, which access Parent's private field a.
 */
