import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final String fileJson = "new_data.json";

        String json = readString(fileJson);

        List<Employee> list = jsonToList(json);
        if (list.size() > 0) {
            for (Employee e : list) {
                System.out.println(e);
            }
        } else {
            System.out.println("List is empty");
        }
    }

    public static String readString(String fileJson) {
        String json;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileJson))) {
            while ((json = reader.readLine()) != null) {
                sb.append(json);
            }

        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return sb.toString();
    }

    public static List<Employee> jsonToList(String json) {
        JSONParser jsonParser = new JSONParser();
        List<Employee> list = new ArrayList<>();
        try {
            Object obj = jsonParser.parse(json);
            JSONArray array = (JSONArray) obj;

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            for (Object object : array) {
                JSONObject jsonObj = (JSONObject) object;
                Employee employee = gson.fromJson(String.valueOf(jsonObj), Employee.class);
                list.add(employee);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
