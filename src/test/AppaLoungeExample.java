package test;

import appalounge.AppaLogin;
import appalounge.AppaLounge;

/**
 * Class to test the operations and prove as an example for the APPA Lounge API
 * @author Aaron Vontell
 * @version 0.1
 */
public class AppaLoungeExample {

    public static void main(String[] args){

        // Set your API key and create an API Object
        /*
        final String API_KEY = "API_KEY";
        AppaLounge appaObject = new AppaLounge();

        APPAAnnounce announcer = appaObject.createAnnouncementObject();
        try {
            announcer.downloadData();
            for(int i = 0; i < announcer.getNumAnnouncements(); i++){
                System.out.println(announcer.getMessageAt(i) + "\t" + announcer.getCreatorAt(i) + "\t" + announcer.getCreatedAt(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        AppaLounge appaObject = new AppaLounge();
        AppaLogin loginator = appaObject.createLoginObject();
        try{
            loginator.setParameters("vontell", "W1nt3rsn0w!");
            loginator.downloadData();
            System.out.println(loginator.getKey());
        } catch (Exception e) {

        }

    }
}
