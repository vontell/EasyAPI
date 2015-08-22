package resources.infrastructure;

import resources.exceptions.*;

/**
 * The base structure of an EasyApiComponent
 * The structure that this interface defines will ensure that each component
 * of an API object will contain the same structure and flow when utilizing
 * the RESTful service
 * @author Aaron Vontell
 * @date 8/21/2015
 * @version 0.1
 */
public interface EasyApiComponent {

    void downloadData() throws ApiException, AuthRequiredException, BadConnectionException, BadRequestException, DataNotSetException;
    String getRawData();

}
