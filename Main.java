import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {




    public static void main(String[] args) throws Exception {

        //instantiate CreateSheet object
        CreateSheet dataSheet = new CreateSheet();

        //instantiate DataGenerator class object
        DataGenerator dataGenerator = new DataGenerator();

        //stores the newly created sheet id in variable named 'id'.
        String id = dataSheet.createSpreadsheet("Sheet");

        //writing newly created sheet's id to a file to keep track of the sheets.
        try
        {
            FileWriter fileWriter = new FileWriter("/Users/uneebsiddiqui/Desktop/OAS/Java/src/main/resources/log.txt");
            fileWriter.write("Sheet ID " + id);
            fileWriter.close();
        }

        catch (IOException e)
        {
            System.out.println("Can not write to log file.");
        }

        //loading data to List of list
        List<List<Object>> listOfData = dataGenerator.addDataListToList();

        //uploading data to google sheet
        dataGenerator.updateValues(id,"Sheet1!A1:Z","USER_ENTERED",listOfData);

        CreateSheet.shareFile(id,"uu1997@gmail.com", "@gmail.com");

        List<List<Object>>  clientsData = DataExtractor.getData(id,"Sheet1", "A2:Z");

        DataTransformer.dataParser(clientsData);

        List<ClientInfo> clientInfoList = new ArrayList<>();
        clientInfoList = DataTransformer.convertedJsonValidation(clientsData);


        Connection connection = DataLoader.dataConnection();
        DataLoader.createTable(connection);
        DataLoader.dataLoading(clientInfoList, connection);
    }
}

