package assignment.application.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import assignment.application.model.Company;
import assignment.application.model.Project;
import assignment.application.model.ProjectOwner;
import assignment.application.model.Student;

public class FileReadWrite
{
    // private variables
    private List<String[]> readLine;
    private String[][] content = null;
    private String[] temp = null;
    int rowSize, columnSize;

    public FileReadWrite()
    {
        readLine = new ArrayList<String[]>();
        rowSize = 0;
        columnSize = 0;
    }

    // function to read a text file and returns it's content in 2D array
    public String[][] readFile(String fileName)
    {
        rowSize = 0;
        columnSize = 0;
        String[][] content = null;
        readLine.clear();

        // try-catch block to handle any exception while reading a file
        try
        {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine())
            {
                // reading the first line of file, split the content by space and store in an
                // array
                temp = myReader.nextLine().trim().split(";");

                // setting the column size based on the length of temp array
                if (columnSize == 0)
                {
                    columnSize = temp.length;
                }

                // adding the temp array to array-list
                readLine.add(temp);
            }

            // setting the row size based on the length of temp array
            rowSize = readLine.size();

            // moving the values in readLine array-list to a 2D array
            if (rowSize > 0 && columnSize > 0)
            {
                content = new String[rowSize][columnSize];

                for (int i = 0; i < readLine.size(); i++)
                {
                    System.arraycopy(readLine.get(i), 0, content[i], 0, columnSize);
                }
            }

            myReader.close();
        }
        catch (FileNotFoundException exp)
        {
            System.out.println("An error occurred while opening file. \nExiting the program");
            System.exit(0);
        }
        return content;
    }

    // Function to generate course_report.txt
    // add append functionality
    public void writeCompany(Map<String, Company> company)
    {
        try
        {
            FileWriter myWriter = new FileWriter("companies.txt");

            for (Map.Entry<String, Company> entry : company.entrySet())
            {
                myWriter.write(entry.getValue().toString().trim() + '\n');
            }
            myWriter.close();

            System.out.println("companies.txt. generated!");
        }
        catch (IOException exp)
        {
            System.out.println("An error occurred while writing to file");
        }
    }

    // Function to generate owners.txt
    public void writeOwners(Map<String, ProjectOwner> Owner)
    {
        try
        {
            FileWriter myWriter = new FileWriter("owners.txt", true);
            for (Map.Entry<String, ProjectOwner> entry : Owner.entrySet())
            {
                myWriter.write(entry.getValue().toString().trim() + '\n');
            }
            myWriter.close();
            System.out.println("owners.txt generated!");
        }
        catch (IOException exp)
        {
            System.out.println("An error occurred while writing to file");
        }
    }

    // Function to generate projects.txt
    public void writeProject(Map<String, Project> project)
    {
        try
        {
            FileWriter myWriter = new FileWriter("projects.txt");
            for (Map.Entry<String, Project> entry : project.entrySet())
            {
                myWriter.write(entry.getValue().toString().trim() + '\n');
            }
            myWriter.close();
            System.out.println("projects.txt generated!");
        }
        catch (IOException exp)
        {
            System.out.println("An error occurred while writing to file");
        }
    }

    public void writeStudent(Map<String, Student> student, String file)
    {
        try
        {
            FileWriter myWriter = new FileWriter(file);

            for (Map.Entry<String, Student> entry : student.entrySet())
            {
                if (file.equals("studentinfo.txt"))
                {
                    myWriter.write(entry.getValue().toString() + '\n');
                }
                else if (file.equals("preferences.txt"))
                {
                    String pref = "";
                    Map<String, Integer> temp = new HashMap<String, Integer>(entry.getValue().getPreference());

                    for (Map.Entry<String, Integer> entry2 : temp.entrySet())
                    {
                        pref += entry2.getKey() + " " + entry2.getValue() + "   ";
                    }
                    myWriter.write(entry.getValue().getStudentID() + "; " + pref + ";\n");
                }
            }
            myWriter.close();
            System.out.println(file + " generated!");
        }
        catch (IOException exp)
        {
            System.out.println("An error occurred while writing to file");
        }
    }

}
