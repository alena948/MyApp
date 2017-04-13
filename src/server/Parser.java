package server;

import jdk.nashorn.internal.runtime.ParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Алёна on 26.03.2017.
 */
public class Parser {
    public ArrayList<String> parse(File file) throws ParserException, FileNotFoundException {

        Scanner scan = new Scanner(file);
        ArrayList<String> data = new ArrayList<>();
        while (scan.hasNextLine()) {
            String[] dataDescr = scan.nextLine().split("/");
            for (int i = 1; i < dataDescr.length; i++) {
                data.add(dataDescr[i]);
            }
        }
        return data;
    }
}
