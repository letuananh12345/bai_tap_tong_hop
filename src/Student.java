public class Student {
    private String id;
    private String name;
    private String age;
    private int gender;
    private  String address;
    private String email;
    private double gpa;


    public Student(String id, String name, String age, int gender,String address,String email, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender=gender;
        this.address = address;
        this.email=email;
        this.gpa=gpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        String year = getAge().substring(6);
        int age = 2021 - Integer.parseInt(year);
        String sex = "";
        if (getGender()==1) {
            sex = "Nam";
        } else if (getGender() == 2) {
            sex = "Nu";
        }
        return "Student{" +
                "id: '" + id + '\'' +
                ", fullName: '" + name + '\'' +
                ", age: " + age +
                ", gender: " + sex + '\'' +
                ", address: '" + address + '\'' +
                ", email: '" + email + '\'' +
                ", score: " + gpa +
                '}';
    }
}
