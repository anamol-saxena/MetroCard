package com.example.geektrust;

import com.example.geektrust.constant.Common;
import com.example.geektrust.domain.CheckIn;
import com.example.geektrust.service.CheckInService;
import com.example.geektrust.service.MetroCardService;
import com.example.geektrust.service.PrintSummaryService;
import com.example.geektrust.service.impl.CheckInServiceImpl;
import com.example.geektrust.service.impl.MetroCardServiceImpl;
import com.example.geektrust.service.impl.PrintSummaryServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MetroStationService {

    MetroCardService metroCardService = new MetroCardServiceImpl();
    CheckInService checkInService = new CheckInServiceImpl(metroCardService);
    PrintSummaryService printSummaryService = new PrintSummaryServiceImpl(checkInService);

    public void start(String[] args) throws IOException{

        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(Common.SPACE, Common.TWO);
                switch (input[0]) {
                    case "BALANCE":
                        String[] cardProperties = input[1].split(Common.SPACE, Common.TWO);
//                        card number - cardProperties[0]
//                        card balance - Integer.parseInt(cardProperties[1])
                        metroCardService.addCard(cardProperties[0], Integer.parseInt(cardProperties[1]));
                        break;
                    case "CHECK_IN":
                        String[] checkInDetails = input[1].split(Common.SPACE, Common.THREE);
//                        card number - checkInDetails[0]
//                        passenger type - checkInDetails[1]
//                        from station - checkInDetails[2]
                        checkInService.checkInPassenger(new CheckIn(checkInDetails[0], checkInDetails[1], checkInDetails[2]));
                        break;
                    case "PRINT_SUMMARY":
//                        action
                        printSummaryService.printSummary();
                        break;
                    default:
                        break;
                }

            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            throw new IOException("Error while reading input");
        }
    }
}
