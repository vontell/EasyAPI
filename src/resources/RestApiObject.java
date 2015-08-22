package resources;

/**
 * An interface that will outline the features that all EasyAPI REST objects should follow
 * Created by Aaron Vontell on 8/18/15.
 */
public interface RestApiObject {

    void downloadData(String dataRequest);
    String[] getPossibleRequests();
    boolean possibleRequestsContains(String request);

}
