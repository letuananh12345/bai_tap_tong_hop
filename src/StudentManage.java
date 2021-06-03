import java.io.IOException;
import java.util.*;

public class StudentManage {
    private List<Student> studentList;
    Scanner scanner = new Scanner(System.in);
    Validation validation =new Validation();
    List<Student> searchList = null;

    public StudentManage() {
        studentList = new ArrayList<>();
    }

    public void StudentManagement() {
        studentList = new ArrayList<>();
    }

    public List<Student> getList() {
        return studentList;
    }

    public void setList(List<Student> list) {
        this.studentList = list;
    }

    public boolean checkID(String id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public Student createStudent() {
        System.out.print("Enter id: ");
        String id;
        boolean check = false;
        do {
            id = validateData(validation.ID_REGEX);
            check = checkID(id);
            if (!check) {
                System.out.println("Dupicated ID, re input:");
            }
        } while (!check);
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter birthday: ");
        String birthDay = validateData(validation.DATE_OF_BIRTH_REGEX);
        System.out.print("Enter gender (1.Nam/2.Nu): ");
        int gender = Integer.parseInt(validateData(validation.GENDER_REGEX));
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = validateData(validation.EMAIL_REGEX);
        System.out.print("Enter score: ");
        double score = -1.0;
        while (score == -1) {
            try {
                score = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Wrong type, re input:");
            } finally {
                scanner.nextLine();
            }
        }
        return new Student(id, fullName, birthDay, gender, address, email, score);
    }

    public void addStudent() {
        Student student = createStudent();
        studentList.add(student);
    }

    public void display() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public void displaySearchList() {
        if (searchList == null) {
            System.out.println("No student was found!");
        } else {
            for (Student student : searchList) {
                System.out.println(student);
            }
        }
    }

    public List<Student> searchStudentById() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        searchList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                searchList.add(studentList.get(i));
                return searchList;
            }
        }
        return searchList;
    }

    public List<Student> searchStudentByName() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        searchList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getName().contains(name)) {
                searchList.add(studentList.get(i));
            }
        }
        return searchList;
    }

    public List<Student> searchStudentByAgeRange() {
        System.out.print("Enter lower age: ");
        int value = -1;
        int lowerAge = checkInputType(value);
        System.out.print("Enter higher age: ");
        int higherAge = checkInputType(value);
        searchList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            String year = studentList.get(i).getAge().substring(6);
            int age = 2021 - Integer.parseInt(year);
            if (age >= lowerAge && age <= higherAge) {
                searchList.add(studentList.get(i));
            }
        }
        return searchList;
    }

    public List<Student> searchStudentByScoreRange() {
        System.out.print("Enter lower score: ");
        double lowerScore = -1.0;
        while (lowerScore == -1) {
            try {
                lowerScore = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Wrong type, re input:");
            } finally {
                scanner.nextLine();
            }
        }
        System.out.print("Enter higher score: ");
        double higherScore = -1.0;
        while (higherScore == -1) {
            try {
                higherScore = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Wrong type, re input:");
            } finally {
                scanner.nextLine();
            }
        }
        searchList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            double score = studentList.get(i).getGpa();
            if ((score >= lowerScore) && (score <= higherScore)) {
                searchList.add(studentList.get(i));
            }
        }
        return searchList;
    }

    public void confirmDeleteStudent() {
        System.out.print("Confirm? (Y/N): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            studentList.removeAll(searchList);
            System.out.println("Deleting successful!");
        } else {
            System.out.println("Deleting failed!");
        }
    }

    public void deleteStudentById() {
        searchList = searchStudentById();
        if (searchList.size() == 0) {
            System.out.println("No student was found!");
        } else {
            System.out.println("Do you want to delete this student?");
            confirmDeleteStudent();
        }
    }

    public int checkInputType(int choice) {
        while (choice == -1) {
            try {
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Wrong type, re enter: ");
            } finally {
                scanner.nextLine();
            }
        }
        return choice;
    }

    public void sort() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getGpa() > o2.getGpa()) {
                    return 1;
                } else if (o1.getGpa() < o2.getGpa()) {
                    return -1;
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        });
    }

    public String validateData(String regex) {
        String string;
        boolean check = false;
        do {
            string = scanner.nextLine();
            check = validation.validate(regex, string);
            if (!check) {
                System.out.println("Wrong input, re input: ");
            }
        } while (!check);
        return string;
    }

    public void editName(Student student) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        if (!name.equals("")) {
            student.setName(name);
            System.out.println("Update successful!");
        } else {
            System.out.println("Update failed!");
        }
    }

    public void editBirthDay(Student student) {
        System.out.print("Enter birthday: ");
        String birthDay = validateData(validation.DATE_OF_BIRTH_REGEX);
        if (!birthDay.equals("")) {
            student.setAge(birthDay);
            System.out.println("Update successful!");
        } else {
            System.out.println("Update failed!");
        }
    }

    public void editGender(Student student) {
        System.out.print("Enter gender (1.Male/2.Female): ");
        int gender = Integer.parseInt(validateData(validation.GENDER_REGEX));
        student.setGender(gender);
        System.out.println("Update successful!");
    }

    public void editAddress(Student student) {
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        if (!address.equals("")) {
            student.setAddress(address);
        }
    }

    public void editEmail(Student student) {
        System.out.print("Enter email: ");
        String email = validateData(validation.EMAIL_REGEX);
        if (!email.equals("")) {
            student.setEmail(email);
            System.out.println("Update successful!");
        } else {
            System.out.println("Update failed!");
        }
    }

    public void editScore(Student student) {
        System.out.print("Enter gpa: ");
        Double gpa = -1.0;
        while (gpa == -1) {
            try {
                gpa = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Wrong type, re input:");
            } finally {
                scanner.nextLine();
            }
        }
        student.setGpa(gpa);
    }

    public void editInformation(Student student) {
        editName(student);
        editBirthDay(student);
        editGender(student);
        editAddress(student);
        editEmail(student);
        editScore(student);
    }

    public void writeToFile() throws IOException {
        WriteAndRead.writeFile("new.csv",studentList);
    }

    public List<Student> readFromFile() throws IOException {
        studentList = WriteAndRead.readFile("student.csv");
        return studentList;
    }
}
