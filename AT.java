/*
Robert Lightfoot
CSCE 111 Attendance Tracker Project
1/22/2018 was the start date.
Attendance Tracker
*/

import java.util.*;
import java.io.*;  //needed to write out the file.

public class AT { // Attendance Tracker (AT for short) dictates the flow of logic for my program.

public static final boolean deBug = true;
//public static String className = "College 101";
//public static String schoolName = "Any School, Anywhere.";

  public static void main(String[] args)throws IOException, InterruptedException {

      RosterHandler rosterHandler=new RosterHandler();
      // My methods now lie in RosterHandler
      ArrayList<String> todaysAttendance = new ArrayList<String>();
      // stores the ids of students in attendance today


      String className = "CSCE 315";
      String schoolName = "Texas A&M";
      // end setup Variables

      String strDate;
      //local variables.

      //Introduction portion of the Attendance Tracker
      // This helps find the methods in the Roster Handler class.
      rosterHandler.cls(); //clears the screen
      strDate = rosterHandler.printDate();
      rosterHandler.getRoster(className);
      rosterHandler.printGreeting(className, schoolName);
      // end of Introduction portion

      // Processing portion of the Attendance tracker
      // This helps find the methods in the RosterHandler class.
     String masterID = rosterHandler.getMasterID();

      rosterHandler.printGreeting(className, schoolName);
      // greet each student and print instructions.
      rosterHandler.getAttendance(todaysAttendance, masterID, className, schoolName);

      rosterHandler.cls();

      rosterHandler.saveAttendanceFile(todaysAttendance, strDate);
      rosterHandler.saveRoster(className);

    }// end of public static void main
}


/*
if(deBug){
      Roster curr=rosterHandler.elementAt("106002418");
      if(curr!=null)
        {
            System.out.println(curr.fName+" "+curr.lName+" "+curr.UIN+" "+curr.IDSwipe);
            curr.IDSwipe = "123";
            System.out.println(curr.fName+" "+curr.lName+" "+curr.UIN+" "+curr.IDSwipe);

          }
}
*/
