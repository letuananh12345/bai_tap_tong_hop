import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteAndRead {
    static List<Student> readFile(String path) throws IOException {
        List<Student> listStudent = new ArrayList<>();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] information = line.split(",");
            int value = 0;
            if (information[3].equals("Nam")) {
                value = 1;
            } else if (information[3].equals("Nu")) {
                value = 2;
            }
            listStudent.add(new Student(information[0], information[1], (information[2]), value, information[4], information[5], Integer.parseInt(information[6])));
        }
        br.close();
        fr.close();
        return listStudent;
    }


    static void writeFile(String path, List<Student> listStudent) throws IOException {
        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Student student : listStudent) {
            String gender = "";
            if (student.getGender() == 1) {
                gender = "Nam";
            } else if (student.getGender() == 2) {
                gender = "Nu";
            }
            bw.write(student.getId() + "," + student.getName() + "," + student.getAge() +
                    "," + gender + "," + student.getAddress() + "," + student.getEmail() +
                    "," + student.getGpa() + "\n");
        }
        bw.close();
        fw.close();
    }
}
