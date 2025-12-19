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

        public static Object[][] readCsv(String filename, int columnsCount) throws Exception {

            InputStream inputStream =
                    CSVDataProvider.class
                            .getClassLoader()
                            .getResourceAsStream(filename);


            if (inputStream == null) {
                throw new RuntimeException(
                        "Csv file '" + filename + "' not found in src/test/resources "
                );
            }

            CSVReader reader = new CSVReader(
                    new InputStreamReader(inputStream)
            );

            List<Object[]> data = new ArrayList<>();
            String[] line;

            while((line = reader.readNext()) != null){

                Object[] row = new Object[columnsCount];

                for(int i = 0; i < columnsCount; i++) {
                    row[i]=line[i];
                }
                data.add(row);
            }
            reader.close();

            return data.toArray(new Object[0][0]);
        }
    }