import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {



    public static void main(String[] args) throws IOException {

        CreateSheet sheet2 = new CreateSheet();

       sheet2.createSpreadsheet("Sheet2");

        DataProcessor dataProcessor1 = new DataProcessor();
        String id = "13obkoMGF_gwWf3VNMg06f4c4-zXhRmLq0E7p3YLCBQI";
        List<List<Object>> aList = new LinkedList<List<Object>>();
        aList = dataProcessor1.addDataToList();
        // List<Object> another = dataGeneration();
        //aList.add(another);

        dataProcessor1.updateValues(id,"Sheet1!A2:F","RAW",aList);

    }
}
