package ro.mta.facc.selab.mihaiapp.helpers;

import java.io.*;
import java.util.ArrayList;
/**
 * author: Stoica Mihai
 * class responsible for working with Files
 * name of input file is hardcoded
 */
public class FileWorker {
    private String name = "src/main/resources/index.txt";
    private ArrayList<FileLine> fileLines;
    public FileWorker()
    {
        fileLines = new ArrayList<FileLine>();
    }

    /**
     * read lines from input file and store them in a list
     * @throws IOException
     */
    public void read() throws IOException {
        if(fileLines.size() == 0)
        {
            File file = new File(this.name);
            BufferedReader bufferedReaderr = new BufferedReader(new FileReader(file));
            String line;
            while((line = bufferedReaderr.readLine())!= null)
            {
                String tokens[] = line.split(" ");
                FileLine newFileLine = new FileLine(tokens[1],Float.parseFloat(tokens[2]),Float.parseFloat(tokens[3]),tokens[4],Integer.parseInt(tokens[0]));
                fileLines.add(newFileLine);
            }
        }
    }

    /**
     *
     * @return a list of Fileline created from input file
     */
    public ArrayList<FileLine> getArguments() {
        return fileLines;
    }
}
