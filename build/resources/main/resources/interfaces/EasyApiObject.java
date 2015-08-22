package resources.interfaces;

/**
 * The base structure of an EasyApiObject
 * The structure that this interface defines will ensure that each API
 * object has identical structure, in order to minimize the learning
 * curve for each separate API
 * @author Aaron Vontell
 * @date 8/21/2015
 * @version 0.1
 */
public interface EasyApiObject {

    String[] getPossibleRequests();
    boolean possibleRequestsContains(String request);

}
