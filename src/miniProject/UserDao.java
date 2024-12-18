package miniProject;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

/**
 * 파일에서 List<User> 정보를 저장하거나 읽어오는 기능
 */
public class UserDao {
    private final String filename;

    public UserDao(String filename) {
        this.filename = filename;
    }

    public void saveUser(List<User> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        List<User> list = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            list = (List<User>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }
}
