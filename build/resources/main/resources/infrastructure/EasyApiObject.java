package resources.infrastructure;

/**
 * The base structure of an EasyApiObject
 * The structure that this interface defines will ensure that each API
 * object has identical structure, in order to minimize the learning
 * curve for each separate API
 * @author Aaron Vontell
 * @date 8/21/2015
 * @version 0.2
 */
public abstract class EasyApiObject {

    /**
     * Retrieve the list of all requests available in this API
     * @return The list of requests
     */
    public abstract String[] getPossibleRequests();

    /**
     * Find out if a request is available
     * @param request The request to search for
     * @return Does the request exist
     */
    public boolean possibleRequestsContains(String request){
        for(String possible : getPossibleRequests()){
            if(possible.equals(request)){
                return true;
            }
        }
        return false;
    }

}
