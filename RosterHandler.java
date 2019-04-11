/*
Robert Lightfoot
CSCE 111
AttendanceTracker program
4/2/2018
*/

import java.util.*;
import java.text.DateFormat;       // imported to use the date as a string, and then as a file name
import java.text.SimpleDateFormat;
import java.io.*;  //needed to write out the file.

public class RosterHandler{

    public final boolean deBug = false;
    /* This global constant, deBug, is being used to develop the program. When set to getRuntime
     * multiple debugging segments of code will exicute. When set to false, the code will running
     * as designed.
     */

    ArrayList<Roster> masterRoster = new ArrayList<Roster>();
    // this is an array list of roster records. masterRoster contains everyone on the roster.
    ShowPictureID currID = new ShowPictureID();
    

    public  void getSetup(String[] myArgs)throws IOException{
      //String className = "College 101";
      //String schoolName = "Any School, Anywhere.";
      String fileName = "data/Default.txt";

      if(myArgs.length == 0){
        System.out.println("Usage: java AT <optional>filename.txt\n"+
                          "where the file is setup data. (see data/default.txt)");
        System.out.println("Using Default.txt for the setup.");
      }else{
        fileName = myArgs[0];
      }

        try
          {
          BufferedReader in = new BufferedReader(new FileReader(fileName));
          //Then, you can use in.readLine(); to read a single line at a time.
          //To read until the end, write a while loop as such:

          String line;
          while((line = in.readLine()) != null)
            {

                //className.equals(line);
                //schoolName.equals(in.readLine());

            }
            in.close();
        }
        // If the file is not found.
        catch(FileNotFoundException ex) {
          System.err.println("Unable to open file '" +
                          fileName + "'");
          System.out.println("\n\nProceding without the setup Data.");

        }// end catch

    }// end getSetup

    //Gets the index of a id from the master roster list
    public int indexOf(String id){
        if(masterRoster==null || masterRoster.size()==0){
          return -1;
        }
        for(int i=0;i<masterRoster.size();i++){
          if((masterRoster.get(i) !=null && masterRoster.get(i).UIN !=null && masterRoster.get(i).UIN.equalsIgnoreCase(id)) ||
              (masterRoster.get(i) !=null && masterRoster.get(i).IDSwipe !=null && masterRoster.get(i).IDSwipe.equalsIgnoreCase(id))){
                //System.out.println("yep "+id);
            return i;
          }
        }
        //System.out.println("nope"+id);
        return -1;
    }//end indexOf

    public Roster elementAt(String uin){
        int index=indexOf(uin);
        if(index >= 0 && index < masterRoster.size()){
           Roster curr=masterRoster.get(index);
           return curr;
        }
        return null;
    }// end elementAt

   public void getRoster(String className)throws IOException {

      String fileName = "data/"+className.replaceAll("\\s+","")+"MR.csv";
      try
        {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
  //Then, you can use in.readLine(); to read a single line at a time.
  //To read until the end, write a while loop as such:

            String line;
            while((line = in.readLine()) != null)
              {
                Roster tempRoster = new Roster();
                //if(deBug)System.out.println(line);
                tempRoster.addToRoster(line);
                //System.out.println(line);

                masterRoster.add(tempRoster);

              }
              in.close();
          }
  // If the file is not found.
      catch(FileNotFoundException ex) {
        System.err.println(
                      "Unable to open file '" +
                        fileName + "'");
        System.out.println("\n\nProceding without the master class roster.");

      }// end catch

    }  //end getRoster

    public void saveRoster(String className)throws IOException {

       String fileName = "data/"+className.replaceAll("\\s+","")+"MR.csv";
       String rosterLine;
       try
         {

               //Method writeFile1

               File fout = new File(fileName);
               FileOutputStream fos = new FileOutputStream(fout);

               BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

               for (Roster studentRecord : masterRoster ) {
                 rosterLine = studentRecord.writeBackRoster();
                 bw.write(rosterLine);
                 //System.out.println(rosterLine);
                 bw.newLine();
               }

             bw.close();
   //Then, you can use in.readLine(); to read a single line at a time.
   //To read until the end, write a while loop as such:

           }
   // If the file is not found.
       catch(FileNotFoundException ex) {
         System.err.println(
                       "Unable to open file '" +
                         fileName + "'");
         System.out.println("\n\nUpdating Roster is not possible.");

       }// end catch

     }  //end saveRoster

    public String printDate() {
      // Requirement 1, be aware of the date.
      Date date = new Date();
      System.out.println();
      System.out.println(date);
      /*
  		 * To convert java.util.Date to String, use SimpleDateFormat class.
       * From Java.Examples.Com
  		 * crate new SimpleDateFormat instance with desired date format.
  		 * We are going to use yyyy-mm-dd hh:mm:ss here.
  		 */
  		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm");

  		//to convert Date to String, use format method of SimpleDateFormat class.
  		String strDate = dateFormat.format(date);

  		//if (deBug) System.out.println("Date converted to String: " + strDate);

      return strDate;
    }//end printDate

    public void printGreeting(String className, String schoolName) throws IOException, InterruptedException {
      cls();
      System.out.println();
      System.out.println("Welcome to the Attendance Tracker for "+className+" at "
                          +schoolName+".");
      System.out.println("Please swipe your id with the mag stripe");
      System.out.println("away from the green light.");
    }// end printGreeting

    public String getMasterID() {
      String tempID1, tempID2;
      Scanner ans = new Scanner(System.in);

      do{
          System.out.println("Please swipe or enter the master ID to start attendance tracking for today.");
          System.out.println();
          tempID1 = ans.nextLine();
          System.out.println("Please swipe or enter the master ID to confirm.");
          System.out.println();
          tempID2 = ans.nextLine();
          //print greeting
          //get id
         }while(!tempID1.equals(tempID2));
      return tempID1;
    } // end getMasterID


     public void getAttendance(ArrayList<String> al, String masterID, String className, String schoolName)throws IOException, InterruptedException {
       Scanner ans = new Scanner(System.in);
       int studentCount  = 1;
       String IDEntered, IDTemp;
       Roster current = null;

       do {
         //cls();
         System.out.println("\n\nPlease swipe your ID if you have it, \n"+
                            "(with the mag stripe away from the green light)\n"+
                            "or enter your UIN:");
         System.out.print(studentCount+".\t");
         IDEntered = ans.nextLine();
         if (IDEntered.equals("")) {  //case where enter was pressed with no dada
             System.out.println("Unable to locate record. Please try again.");
             continue;
         }
         if(IDEntered.charAt(0)=='%'){   //case where ID card is slid
           if(!IDEntered.contains("?")){
             System.out.println("Unable to locate record. Please try again.");
             continue;
           }
           IDTemp = IDEntered.substring(IDEntered.indexOf("%") + 1, IDEntered.indexOf("?"));
           //al.add(IDTemp);
           current = elementAt(IDTemp); // looking to see if ID is in the database.
           if(current == null){
             System.out.println("Your ID is not associated with your Student Record for this class.\n"+
                                "Please enter your UIN: ");
             IDEntered = ans.nextLine();
             current = elementAt(IDEntered);
             if(current == null){
               System.out.println("Unable to locate record. Please try again.");
               continue;
               }
          }
           validStudent(className, schoolName, current, al);
           studentCount++;
           current.addIDSwipe(IDTemp);
          }
          else{  // case where UIN is entered on keyboard
            current = elementAt(IDEntered);
            if(current == null){
               System.out.println("Unable to locate record. Please try again.");
               continue;
            }
            validStudent(className, schoolName, current, al);
            studentCount++;
         }

      } while (!IDEntered.equals(masterID));
      ans.close();

      if(deBug) System.out.println(al);
     }//end getAttendance

     public ArrayList<String> getAttendanceFile(ArrayList<String> al, String className)throws IOException, InterruptedException {
       Scanner ans = new Scanner(System.in);
       int studentCount  = 1;
       String  IDTemp;
       Roster current = null;
       ArrayList<String> alUIN = new ArrayList<String>();

       System.out.println("Please enter the data file name: ");
       String fileName = ans.nextLine();
       try
         {
             BufferedReader in = new BufferedReader(new FileReader(fileName));
     //Then, you can use in.readLine(); to read a single line at a time.
     //To read until the end, write a while loop as such:

             String line;
             while((line = in.readLine()) != null)
               {

                 al.add(line);

               }
               in.close();
           }
     // If the file is not found.
       catch(FileNotFoundException ex) {
         System.err.println(
                       "Unable to open file '" +
                         fileName + "'");
         System.out.println("\nFile not Found.\nExiting without processing file.");
         System.exit(0);

       }// end catch

       for(String IDEntered : al) {

        if(IDEntered.charAt(0)=='%'){   //case where ID card is slid
           IDTemp = IDEntered.substring(IDEntered.indexOf("%") + 1, IDEntered.indexOf("?"));
           //al.add(IDTemp);
           current = elementAt(IDTemp); // looking to see if ID is in the database.
           if(current == null){
               continue;
          }
        }
        else{  // case where UIN is entered on keyboard
            current = elementAt(IDEntered);
            if(current == null){
               continue;
            }

         }
      alUIN.add(current.UIN);
      }
      ans.close();
      return alUIN;
    }//end getAttendanceFile

     public  void validStudent(String className, String schoolName, Roster current, ArrayList<String> al)throws IOException, InterruptedException {
       // a valid studend has been found.
       current.simpleMessage();
       al.add(current.UIN);
       System.out.println("Please Verify ID Picture.");
       currID.showIDPic(current.UIN);
       printGreeting(className, schoolName);
       current.attendanceMessage();

     }// end validStudent

     public void saveAttendanceFile(ArrayList<String> al, String date)throws IOException {
       int i=0;
   	   if (deBug){
          for (String myValue : al) {
   	  		   i++;
       	   	System.out.println(myValue);
          }
   		}
      File fout = new File("data/"+date+"-attendance.txt");
      FileOutputStream fos = new FileOutputStream(fout);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

      i=0;
      for (String myValue : al) {
        i++;
        bw.write(myValue);
        bw.newLine();
      }

    bw.close();
       System.out.println("Your data has been stored in data/"+date+"-attendance.txt");
     }// end saveAttendanceFile

  /*
   * a quick process that clears the screen  Currently only for windows users.
   */

      public void cls() throws IOException, InterruptedException {
          new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      }//end cls
  }
  //End of class
