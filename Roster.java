
  public class Roster{
    public String fName, lName, UIN, mName, eof = "e", IDSwipe = "";
    public String[] values = new String[6];
    public boolean deBug = true;

    public Roster(){
    };

    public void addToRoster(String line){
      values = line.split(",");
      fName = values[1];
      lName = values[0];
      mName = values[2];
      UIN = values[3];
      IDSwipe = values[4];
    }

    public String writeBackRoster(){

      values[1] = fName;
      values[0] = lName;
      values[2] = mName;
      values[3] = UIN;
      values[4] = IDSwipe;
      values[5] = eof;

      String writeLine = String.join(",", values);
      //System.out.println(writeLine);
      return writeLine;
    }


    public void addIDSwipe(String ID){
       IDSwipe = ID;
       if(deBug)System.out.println(IDSwipe);
    }



    public void simpleMessage(){
      //System.out.println("This is the CSCE111 class called from TAMU");
      //System.out.println("The number of studets is : "+ students);
      System.out.println("The student info is : "+ fName + " "+ lName);
    }

    public void attendanceMessage(){
      //System.out.println("This is the CSCE111 class called from TAMU");
      //System.out.println("The number of studets is : "+ students);
      System.out.println("\n\n\tWelcome : "+ fName + " "+ lName+" your attendance has been recorded.");
    }
}
