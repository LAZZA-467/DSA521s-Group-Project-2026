class Student {
    String studentNo;
    String name;
    String serviceType;
    int estimatedServiceTime;
    StudentNode next;

    public void StudentNode(String studentNo, String name, String serviceType, int estimatedServiceTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
        this.next = null;
    }

    @Override
    public String toString() {
        return "[" + studentNo + " | " + name + " | " + serviceType + " | " + estimatedServiceTime + " mins]";
    }
}
class StudentLinkedList {
    private StudentNode head;

    public StudentLinkedList() {
        this.head = null;
    }

    public void insertAtBeginning(String studentNo, String name, String serviceType, int time) {
        StudentNode newNode = new StudentNode(studentNo, name, serviceType, time);
        newNode.next = head;
        head = newNode;
        System.out.println("Inserted at beginning: " + name);
    }

    public void insertAtEnd(String studentNo, String name, String serviceType, int time) {
        StudentNode newNode = new StudentNode(studentNo, name, serviceType, time);
        if (head == null) {
            head = newNode;
            System.out.println("Inserted at end: " + name);
            return;
        }
        StudentNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        System.out.println("Inserted at end: " + name);
    }

    public void insertAtPosition(int position, String studentNo, String name, String serviceType, int time) {
        if (position < 1) {
            System.out.println("Invalid position!");
            return;
        }
        if (position == 1) {
            insertAtBeginning(studentNo, name, serviceType, time);
            return;
        }

        StudentNode newNode = new StudentNode(studentNo, name, serviceType, time);
        StudentNode current = head;
        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
    }
        if (current == null) {
            System.out.println("Position invalid. Inserting at the end instead.");
            insertAtEnd(studentNo, name, serviceType, time);
        } else {
            newNode.next = current.next;
            current.next = newNode;
            System.out.println("Inserted " + name + " at position " + position);
        }
    }

    public void deleteStudent(String studentNo) {
        if (head == null) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }
        if (head.studentNo.equals(studentNo)) {
            System.out.println("Deleted record: " + head.name);
            head = head.next;
            return;
        }

        StudentNode current = head;
        while (current.next != null && !current.next.studentNo.equals(studentNo)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Student with number " + studentNo + " not found.");
        } else {
            System.out.println("Deleted record: " + current.next.name);
            current.next = current.next.next;
        }
    }
    public void searchStudent(String studentNo) {
        StudentNode current = head;
        int position = 1;
        while (current != null) {
            if (current.studentNo.equals(studentNo)) {
                System.out.println("Found! Student found at position " + position + ": " + current);
                return;
            }
            current = current.next;
            position++;
        }
        System.out.println("Student with number " + studentNo + " not found.");
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("The student service records list is empty.");
            return;
        }
        System.out.println("\n- Current Student Service Records -");
        StudentNode current = head;
        int i= 1;
        while (current != null) {
            System.out.println(i + ". " + current);
            current = current.next;
            i++;
        }
    }
}
    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();

        list.insertAtEnd("222034512", "Tomas", "Student Card", 5);
        list.insertAtEnd("223041876", "Ndapewa", "Fees", 8);
        list.insertAtBeginning("221045678", "Maria", "Registration", 12);
        list.insertAtPosition(3, "221067341", "Simon", "Documents", 4);
        list.displayStudents();
        list.searchStudent("223041876");
        list.deleteStudent("222034512");
        list.displayStudents();
    }
