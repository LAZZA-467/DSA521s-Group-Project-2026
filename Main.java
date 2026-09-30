class Student {
    String studentNo;
    String name;
    String serviceType;
    int estimatedServiceTime;

    public Student(String studentNo, String name, String serviceType, int estimatedServiceTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
    }

    @Override
    public String toString() {
        return "[" + studentNo + " | " + name + " | " + serviceType + " | " + estimatedServiceTime + " mins]";
    }
}

class Node {
    Student data;
    Node next;

    public Node(Student data) {
        this.data = data;
        this.next = null;
    }
}

class ServiceQueue {
    private Node front;
    private Node rear;
    private int size;

    public ServiceQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Student student) {
        Node newNode = new Node(student);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Enqueued: " + student.name);
    }

    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }
        Student servedStudent = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        System.out.println("Served (Dequeued): " + servedStudent.name);
        return servedStudent;
    }

    public Student peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }
        return front.data;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("The waiting queue is currently empty.");
            return;
        }
        System.out.println("\n--- Current Waiting Line Queue ---");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ServiceQueue queue = new ServiceQueue();
        System.out.println(" 6 Student Arrivals (Enqueue)");
        queue.enqueue(new Student("221045678", "Maria", "Registration", 12));
        queue.enqueue(new Student("222034512", "Tomas", "Student Card", 5));
        queue.enqueue(new Student("223041876", "Ndapewa", "Fees", 8));
        queue.enqueue(new Student("221067341", "Simon", "Documents", 4));
        queue.enqueue(new Student("224098123", "Anna", "Registration", 10));
        queue.enqueue(new Student("225012345", "John", "Inquiries", 6));
        queue.displayQueue();
        System.out.println("3 Students Being Served (Dequeue) ");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.displayQueue();
    }
}
