package com.hs1;


import com.google.gson.Gson;
import com.hsOne.SubscriptionChecker;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //1. Call the method getSubscriptions to get a list of Subscription information in JSON
        // (In this case the customer ID does not matter so choose your favorite number).
        SubscriptionChecker subscriptionChecker = new SubscriptionChecker();

        //2. Encode, encrypt or somehow obfuscate the data and save it to a file.
        //utilizing inversion of control for our security so we can set encode / decode for unit testing
        Security security = new Security((int) (Math.random() * 10) + 1);
        String encodedJSON = security.encode(subscriptionChecker.getSubscriptions(123));

        //write encoded JSON to file
        FileUtil.writeToFile(encodedJSON, "output.txt");

        //3. Load the file you saved and decode or decrypt the data.
        String encodedDataFromFile = FileUtil.getFileContents("output.txt");

        //decode subscriptions from file
        String decodedJSON = security.decode(encodedDataFromFile);

        //convert it to JSON & map to DTO
        Gson gson = new Gson();
        Subscriptions[] subscriberArray = gson.fromJson(decodedJSON, Subscriptions[].class);

        //4. Print out each subscription name and whether or not the customer is subscribed to the console.
        for (Subscriptions subscription : subscriberArray) {
            subscription.printSubscriptionNameAndStatus();
        }
    }
}
