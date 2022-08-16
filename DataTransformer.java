import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
 latest-master
import java.util.ArrayList;

 master
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 latest-master
public class DataTransformer

{
    public static void dataParser(List<List<Object>> dataList)


public class DataTransformer {


    public static void dataParser(List<List<Object>> dataList)
 master
    {
        for(int i=0; i<dataList.size(); i++)
        {
            parseData(dataList.get(i));
        }

    }

 latest-master
    private static void parseData(List<Object> list)
    {
        long value;


    private static void parseData(List<Object> list) {
        long value;

 master
        // to check if there is any occurrences of at least one digit.
        Pattern pattern = Pattern.compile("[0-9]");
        //to look for any occurrence of any character or characters in a string
        Pattern alphaPattern = Pattern.compile("[abcdefghijklmnopqrstuvwxyz]", Pattern.CASE_INSENSITIVE);

        if(list.size()<5)
        {
            list.add("");
        }

 latest-master
        for (int i = 0; i < list.size(); i++)
        {

        for (int i = 0; i < list.size(); i++) {
 master
            //if we have a match in a given string store it in a Matcher Object.
            Matcher matcher = pattern.matcher(list.get(i).toString());
            Matcher charMatcher = alphaPattern.matcher(list.get(i).toString());

 latest-master
            try
            {
                //column 3(phone number) or column 5(zipcode)
                if (i == 2 || i == 4)
                {
                    //if column 3 or 5 has no value or if they are not numeric
                    if (list.get(i).toString().isEmpty() || charMatcher.find())
                    {


            try {
                //column 3(phone number) or column 5(zipcode)
                if (i == 2 || i == 4) {
                    //if column 3 or 5 has no value or if they are not numeric
                    if (list.get(i).toString().isEmpty() || charMatcher.find()) {
 master
                        //set value to zero
                        list.set(i, 0);
                    }

 latest-master
                    else
                    {

                    else {

 master
                        value = Long.parseLong(list.get(i).toString());
                        list.set(i, value);
                    }

latest-master
                }

                else if (i == 0 || i == 1 || i == 3)
                {
                    //if column 1,2,or 4 contains an empty string or numbers
                    if (list.get(i).toString().isEmpty() || matcher.find())
                    {

                } else if (i == 0 || i == 1 || i == 3) {

                    //if column 1,2,or 4 contains an empty string or numbers
                    if (list.get(i).toString().isEmpty() || matcher.find()) {
 master
                        list.set(i, "null");
                    }

                }

 latest-master
            }

            catch (NumberFormatException e)

            } catch (NumberFormatException e)
 master
            {
                list.set(i,0);
            }

 latest-master
        }

    }

    public static ClientInfo getClientObject(String jsonString)
    {
        Gson gson = new Gson();
        ClientInfo clientInfo = gson.fromJson(jsonString,ClientInfo.class);
        return clientInfo;

    }

    public static List<ClientInfo> convertedJsonValidation(List<List<Object>> listOfDataList) throws IOException
    {
        String jsonString;
        List<ClientInfo> clientData = new ArrayList<>();

        for(int i=0; i<listOfDataList.size(); i++)
        {
           jsonString = convertToJson(listOfDataList.get(i));
           validateSchema(jsonString);
           clientData.add(getClientObject(jsonString));
        }

        return clientData;
    }

    public static String convertToJson(List<Object> data) throws IOException
    {


        }


    }

    public static String convertToJson(List<Object> data) throws IOException {

 master
        Map<String,Object> map = new HashMap<>();
        map.put("firstName", data.get(0));
        map.put("lastName", data.get(1));
        map.put("phoneNumber", data.get(2));
        map.put("state", data.get(3));
        map.put("zipcode", data.get(4));

 latest-master
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(map);

        return json;
    }

    public static void jsonToObject() throws IOException
    {
        FileReader fileReader = new FileReader("/Users/uneebsiddiqui/Desktop/OAS/Java/src/main/resources/file1.json");
           fileReader.read();
    }

    public static void validateSchema(String jsonString) throws FileNotFoundException
    {



        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(map);


        return json;
    }


    public static void validateSchema(String jsonString) throws FileNotFoundException {

 master
        File schemaFile = new File("/Users/uneebsiddiqui/Desktop/OAS/Java/src/main/resources/schemaFile.json");
        JSONTokener schemaData = new JSONTokener(new FileInputStream(schemaFile));
        JSONObject jsonSchema = new JSONObject(schemaData);

 latest-master
        try
        {
            FileWriter fileWriter = new FileWriter("/Users/uneebsiddiqui/Desktop/OAS/Java/src/main/resources/file1.json");
            fileWriter.write(jsonString);
            fileWriter.close();
        }

        catch (IOException e)
        {

        try {

            FileWriter fileWriter = new FileWriter("/Users/uneebsiddiqui/Desktop/OAS/Java/src/main/resources/file1.json");
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
 master
            System.out.println("file not found");
        }

        File jsonData = new File("/Users/uneebsiddiqui/Desktop/OAS/Java/src/main/resources/file1.json");
        JSONTokener jsonDataFile = new JSONTokener(new FileInputStream(jsonData));
        JSONObject jsonObject = new JSONObject(jsonDataFile);
//
        Schema schemaValid = SchemaLoader.load(jsonSchema);
        schemaValid.validate(jsonObject);


    }

}
