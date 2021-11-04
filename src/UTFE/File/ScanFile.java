/**
 * |_|>
 * |_|>    Created by Dimyasha on 30.10.2021
 * |_|>
 */

package UTFE.File;

import java.io.FileInputStream;
import java.util.Scanner;

public class ScanFile {
    public static String ScanTxtFile(String path) {
        FileInputStream fileStream;
        try {
            fileStream = new FileInputStream(path);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        Scanner scanner = new Scanner(fileStream);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            sb.append(nextLine).append('\n');
        }
        return sb.toString();
    }
}