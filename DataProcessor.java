import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/* Class to demonstrate the use of Spreadsheet Write and Read API */
public class DataProcessor {



    public static UpdateValuesResponse updateValues(String spreadsheetId,
                                                    String range,
                                                    String valueInputOption,
                                                    List<List<Object>> values)

        throws IOException {
        /* Load pre-authorized user credentials from the environment.
           TODO(developer) - See https://developers.google.com/identity for
            guides on implementing OAuth2 for your application. */
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                credentials);

        // Create the sheets API client
        Sheets service = new Sheets.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Sheets samples")
                .build();

        UpdateValuesResponse result =null;
        try {
            // Updates the values in the specified range.
            ValueRange body = new ValueRange()
                    .setValues(values);
            result = service.spreadsheets().values().update(spreadsheetId, range, body)
                    .setValueInputOption(valueInputOption)
                    .execute();
            System.out.printf("%d cells updated.", result.getUpdatedCells());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 404) {
                System.out.printf("Spreadsheet not found with id '%s'.\n",spreadsheetId);
            } else {
                throw e;
            }
        }
        return result;
    }

    public static  String randomNumericStrings(int length)
    {
        String numbers = "0123456789";

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i=0; i<length; i++)
        {
            int index = random.nextInt(numbers.length());
            char randomNum = numbers.charAt(index);
            stringBuilder.append(randomNum);

        }
        return stringBuilder.toString();
    }

    public static String randomStates()
    {
        String[] countries = {"New York","Texas",
                               "Boston","California", "Arizona",
                                 "Arkansas", "Florida", "Virginia", "Alaska", "Washington"};
        Random random = new Random();
        int randomCountry = random.nextInt(countries.length);
        return countries[randomCountry];
    }

    public static String randomStrings()
    {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 11;
        boolean first = true;
        for (int i=0; i<length; i++)
        {
            int index = random.nextInt(letters.length());
            char randomChar;
            if(first) {
                randomChar = letters.charAt(index);
                first = false;
            }
            else
            {
                randomChar = lowerCaseLetters.charAt(index);
            }

            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static List<Object> dataGeneration()
    {
        List<Object> list = new ArrayList<Object>();


        String firstName = randomStrings();
        String lastName = randomStrings();
        String phone = randomNumericStrings(10);
        String state = randomStates();

        String zip = randomNumericStrings(5);
            list.add(firstName);
            list.add(lastName);
            list.add(phone);
            list.add(state);
            list.add(zip);

        return list;
    }

    public static List<List<Object>> addDataToList()
    {
        List<List<Object>>  theList = new ArrayList<>();
        for(int i=0; i<10; i++)
        {
            List<Object> list = new ArrayList<Object>();
            list = dataGeneration();
            theList.add(list);
        }

        return theList;
    }


}



/*


            import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;



import java.io.IOException;
import java.util.*;
*/

/* Class to demonstrate the use of Spreadsheet Create API */
/*
public class HelloWorld {
    /**
     * Create a new spreadsheet.
     *
     * @param title - the name of the sheet to be created.
     * @return newly created spreadsheet id
     * @throws IOException - if credentials file not found.
     */


/*
    public static String createSpreadsheet(String title) throws IOException {
        /* Load pre-authorized user credentials from the environment.
           TODO(developer) - See https://developers.google.com/identity for
            guides on implementing OAuth2 for your application. */

            /*
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                credentials);

        // Create the sheets API client
        Sheets service = new Sheets.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Sheets samples")
                .build();

        // Create new spreadsheet with a title
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle(title));
        spreadsheet = service.spreadsheets().create(spreadsheet)
                .setFields("spreadsheetId")
                .execute();

        // Prints the new spreadsheet id
        System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
        return spreadsheet.getSpreadsheetId();
    }

    public static UpdateValuesResponse updateValues(String spreadsheetId,
                                                    String range,
                                                    String valueInputOption,
                                                    List<List<Object>> values)

            throws IOException {
        /* Load pre-authorized user credentials from the environment.
           TODO(developer) - See https://developers.google.com/identity for
            guides on implementing OAuth2 for your application. */

    /*
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                credentials);

        // Create the sheets API client
        Sheets service = new Sheets.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Sheets samples")
                .build();

        UpdateValuesResponse result =null;
        try {
            // Updates the values in the specified range.
            ValueRange body = new ValueRange()
                    .setValues(values);
            result = service.spreadsheets().values().update(spreadsheetId, range, body)
                    .setValueInputOption(valueInputOption)
                    .execute();
            System.out.printf("%d cells updated.", result.getUpdatedCells());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 404) {
                System.out.printf("Spreadsheet not found with id '%s'.\n",spreadsheetId);
            } else {
                throw e;
            }
        }
        return result;
    }


    public static List<Object> dataGeneration(String firstName,String lastName, String email,String state, String city, String zip)
    {
        List<Object> list = new ArrayList<Object>();
        list.add(firstName);
        list.add(lastName);
        list.add(email);
        list.add(state);
        list.add(city);
        list.add(zip);

        return list;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("hello");
        String id = "13obkoMGF_gwWf3VNMg06f4c4-zXhRmLq0E7p3YLCBQI";
        List<List<Object>> aList = new LinkedList<List<Object>>();
        List<Object> another = dataGeneration("Uneeb", "Siddiqui", "uu1997@gmail.com", "NY", "Queens","11432");
        aList.add(another);

        updateValues(id,"Sheet1!A2:F","RAW",aList);



    }
}




*/