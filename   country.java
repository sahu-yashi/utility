import java.io.*;
import java.util.*;
class country
{
    public static void sorting_func(String arr[][],int size)
    {
        for(int i = 0; i<size-1; i++) {
            for (int j = i+1; j<size; j++) {
               if(arr[i][0].compareTo(arr[j][0])>0) {
                  String temp = arr[i][0];
                  arr[i][0] = arr[j][0];
                  arr[j][0] = temp;

                  String temp2 = arr[i][1];
                  arr[i][1] = arr[j][1];
                  arr[j][1] = temp2;
               }
            }
         }
    }
    public static void print_func(String arr[][])
    {
        for(int i=0;i<arr.length;i++)
        System.out.println(arr[i][0]+" "+arr[i][1]);
    }
    public static void csv_write(String arr[][],int size,String out_path)throws IOException
    {
        FileWriter writer = null;
        try
        {
            writer = new FileWriter(out_path);
            for(int i=0;i<size;i++)
            {
                writer.append(arr[i][0]);
                writer.append(',');
                writer.append(arr[i][1]);
                writer.append('\n');
            }
            
            
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            writer.flush();
            writer.close();
        }
    }
    public static void convert_func(String arr[][],int size)
    {
        for(int i=0;i<size;i++)
        {
            String temp=arr[i][1];
            float temp_c=Float.parseFloat(temp);
            float temp_f=temp_c*((float)9/5)+32;
            
            temp=Float.toString(temp_f);
            arr[i][1]=temp;

        }
    }
    public static void main(String[] args) throws IOException
    {   
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter input file path");
        String path=br.readLine().trim();
        //  String path="/Users/cb-it-01-1954/Desktop/temp.csv";
        System.out.println("Enter output file path");
        String out_path=br.readLine();
        // String out_path="temp.csv";
        String inp_file1=path.substring(0,path.lastIndexOf("/"));
        String inp_file2=path.substring(path.lastIndexOf("/")+1);
        


        BufferedReader br2=null;
        BufferedReader br3=null;
        
        String line = "";
        int num_country=0;
        FileWriter writer1 =null;
        FileWriter writer2 =null;
        FileWriter writer3=null;
        try
        {
         br2=new BufferedReader(new FileReader(path));
         line=br2.readLine();
         while(line!=null)
         {
            num_country++;
             line=br2.readLine();
         }
         num_country--;
         String input_file[][]=new String[num_country][2];
         int index=0;
         br3=new BufferedReader(new FileReader(path));
         line=br3.readLine();
          line=br3.readLine();
         while(line!=null)
         {
             String row[]=new String[2];
             row=line.split(",");
             input_file[index][0]=row[0];
             input_file[index++][1]=row[1];
            line=br3.readLine();
         }
         System.out.println("Enter 1 for sorting");
         System.out.println("Enter 2 for temperature convertion");
         System.out.println("Enter 3 for add");
         System.out.println("Enter 4 for deletion");
         System.out.println("Enter 5 to edit");
         int option=Integer.parseInt(br.readLine());
         if(option==1)
         {
            sorting_func(input_file,num_country);
            csv_write(input_file,num_country,out_path);
             print_func(input_file);
        }
        if(option==2)
        {
            convert_func(input_file,num_country);
            csv_write(input_file,num_country,out_path);
            print_func(input_file);
        }
        if(option==3)
        {
            System.out.println("Enter new country");
            String country=br.readLine();
            System.out.println("Enter temperature");
            String temperature=br.readLine();
            br3=new BufferedReader(new FileReader(path)); 
            writer1 = new FileWriter(path,true);
            line=br3.readLine();
            boolean present=false;
            while(line!=null)
            {   
                String row[]=new String[2];
                row=line.split(",");
                if(row[0].equalsIgnoreCase(country)==true)
                {    
                    present=true;
                }

                line=br3.readLine();
            }
            if(present==false)
           { writer1.append('\n');
            writer1.append(country);
            writer1.append(',');
            writer1.append(temperature);
            writer1.append('\n');}
            else
            System.out.println(country+" already present");
            writer1.flush();
            writer1.close();
            br3=new BufferedReader(new FileReader(path));
            line=br3.readLine();
            
             while(line!=null)
         {
             String r[]=new String[2];
             r=line.split(",");
             System.out.println(r[0]+" "+r[1]);
            
            line=br3.readLine();
         }

        }
        if(option==4)
        {   System.out.println("Enter the country to delete");
            String del=br.readLine().trim();
            br3=new BufferedReader(new FileReader(path)); 
            writer2 = new FileWriter(inp_file1+"/temporary.csv");
            line=br3.readLine();
            while(line!=null)
            {   
                String row[]=new String[2];
                row=line.split(",");
                if(row[0].equalsIgnoreCase(del)==false)
                {    writer2.append(line);
                    writer2.append('\n');}

                line=br3.readLine();
            }
             File old_file = new File(path);
             File new_file=new File(inp_file1+"/temporary.csv");
             old_file.delete();
             new_file.renameTo(old_file);
            
             
             
             writer2.flush();
            writer2.close();
            br3=new BufferedReader(new FileReader(path));
            line=br3.readLine();
            
             while(line!=null)
         {
             String r[]=new String[2];
             r=line.split(",");
             System.out.println(r[0]+" "+r[1]);
            
            line=br3.readLine();
         }
        }
        if(option==5)
        {
            System.out.println("Enter the country to edit");
            String ed_country=br.readLine();
            System.out.println("Enter new temperature");
            String ed_temp=br.readLine();
            br3=new BufferedReader(new FileReader(path));
            writer3 = new FileWriter(inp_file1+"/temporary.csv"); 
            line=br3.readLine();
            while(line!=null)
            {   
                String row[]=new String[2];
                row=line.split(",");
                if(row[0].equalsIgnoreCase(ed_country)==false)
                {    writer3.append(line);
                    writer3.append('\n');}
                else{
                    writer3.append(row[0]);
                    writer3.append(',');
                    writer3.append(ed_temp);
                    writer3.append('\n');
                }

                line=br3.readLine();
            }
            

              File old_file = new File(path);
             File new_file=new File(inp_file1+"/temporary.csv");
             old_file.delete();
             new_file.renameTo(old_file);
             writer3.flush();
            writer3.close();

            br3=new BufferedReader(new FileReader(path));
            line=br3.readLine();
            
             while(line!=null)
         {
             String r[]=new String[2];
             r=line.split(",");
             System.out.println(r[0]+" "+r[1]);
            
            line=br3.readLine();
         }

        }
          
           br2.close();
           br3.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally{
            br.close();
        }
        
     }
}