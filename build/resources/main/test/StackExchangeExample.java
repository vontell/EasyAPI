package test;

import stackexchange.StackExchange;

/**
 * Class to test the operations and prove as an example for the StackExchange class
 * @author Aaron Vontell
 * @version 0.1
 */
public class StackExchangeExample {

    public static void main(String[] args) {

        //Looks for sites using a static method in StackExchange
        String[] stackSites = StackExchange.getSiteParameters();
        for (String site : stackSites) {}

    }

}
