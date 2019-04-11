import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ShowPictureID {
 public  void showIDPic(String UIN)throws InterruptedException {
   String filename;
   filename = "images/"+UIN+".jpg";
  //for(int i =0;i<2;i++){


  JFrame frame = new JFrame();
  ImageIcon icon = new ImageIcon(filename);
  JLabel label = new JLabel(icon);
  frame.add(label);
  //frame.setDefaultCloseOperation
    //     (JFrame.HIDE_ON_CLOSE);
  frame.pack();
  frame.setVisible(true);

  Thread.sleep(1500);

 frame.setVisible(false); //you can't see me!
 frame.dispose(); //Destroy the JFrame object
}
}
