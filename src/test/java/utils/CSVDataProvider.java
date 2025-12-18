package utils;

import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataProvider {
    /*
This class was not written  by me.
I generated it with ChatGPT, but I didn’t just copy and paste it —
I rewrote it line by line to understand how it works.
*/

    @DataProvider(name = "loginNegativeData")
    public static Object[][] getLoginNegativeData() throws Exception {

        InputStream inputStream =
                CSVDataProvider.class
                        .getClassLoader()
                        .getResourceAsStream("loginNegativeData.csv.csv");

        if (inputStream == null) {
            throw new RuntimeException(
                    "CSV file 'loginNegativeData.csv' not found in src/test/resources"
            );
        }

        CSVReader reader = new CSVReader(
                new InputStreamReader(inputStream)
        );

        List<Object[]> data = new ArrayList<>();
        String[] line;

        // Skip CSV header
        reader.readNext();

        while ((line = reader.readNext()) != null) {
            data.add(new Object[]{
                    line[0],
                    line[1],
                    line[2]
            });
        }

        reader.close();

        return data.toArray(new Object[0][0]);
    }
}
