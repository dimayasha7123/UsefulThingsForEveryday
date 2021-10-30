/**
 * |_|>
 * |_|>    Created by Dimyasha on 30.10.2021
 * |_|>
 */

package UTFE.TableOutput;

import java.text.DecimalFormat;

public class Table {
    //todo Добавить красивую рамочку и выбор режима работы, либо филлер, либо рамочка
    //todo Обработка пустых строк и null
    //region
    //region Filler
    static char filler = '\u25c6';//2588

    public static char getFiller() {
        return filler;
    }

    public static void SetFiller(char newFiller) {
        filler = newFiller;
    }
    //endregion

    static String lineOf(char lineFiller, int length) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(lineFiller).repeat(Math.max(0, length)));
        return sb.toString();
    }

    //region Decimal Places
    static int decPlaces = 2;

    public static int GetDecimalPlaces() {
        return decPlaces;
    }

    public static void SetDecimalPlaces(int decimalPlaces) {
        int min = 0;
        int max = 15;
        if (decimalPlaces < min) decimalPlaces = min;
        if (decimalPlaces > max) decimalPlaces = max;
        decPlaces = decimalPlaces;
        SetFormatString(decPlaces);
    }

    //endregion
    //region FormatString
    static String formatString = "###,###.##";

    static String GetFormatString() {
        return formatString;
    }

    static void SetFormatString(int decPlaces) {
        formatString = "###,###." + lineOf('#', decPlaces);
    }
    //endregion

    //todo Перенос текста для уменьшения ширины строки таблицы
    //todo Удаление переходов на следующую строку
    //todo Вывод многострочных строк
    static String stringConverter(Object obj) {
        if (obj instanceof Byte || obj instanceof Short || obj instanceof Integer ||
                obj instanceof Long || obj instanceof Double || obj instanceof Float) {
            return new DecimalFormat(GetFormatString()).format(obj);
        }
        return obj.toString();
    }

    static String MakeTableHorizontalString(int[] lengths, char first, char middle, char last, char other) {
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        for (int i = 0; i < lengths.length; ++i) {
            sb.append(String.valueOf(other).repeat(lengths[i]));
            if (i != lengths.length - 1) sb.append(middle);
            else sb.append(last);
        }
        return sb.toString();
    }

    static String MakeTableDataString(int[] lengths, String[] data, char border, char middle) {
        StringBuilder sb = new StringBuilder();
        sb.append(border);
        for (int i = 0; i < data.length; ++i) {
            String formatString = " " + "%-" + (lengths[i] - 2) + "s ";
            sb.append(String.format(formatString, data[i]));
            if (i != data.length - 1) sb.append(middle);
            else sb.append(border);
        }
        return sb.toString();
    }

    public static String TableToString(Object[][] input) {

        //region lengths and string[][]
        int n = input.length;
        int m = input[0].length;
        int[] lengths = new int[m];
        int sumLength = m + 1;
        String[][] inputStr = new String[n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                inputStr[j][i] = stringConverter(input[j][i]);
                lengths[i] = Math.max(inputStr[j][i].length(), lengths[i]);
            }
            lengths[i] += 2;
            sumLength += lengths[i];
        }
        //endregion

        //region Char Borders
        char headUpLeft = '\u250f';
        char headDownLeft = '\u2523';
        char headUpMid = '\u2533';
        char headDownMid = '\u2547';
        char headUpRight = '\u2513';
        char headDownRight = '\u252b';
        char headVert = '\u2503';
        char headHoriz = '\u2501';

        char bodyVertBorder = '\u2503';
        char bodyHorizBorder = '\u2501';
        char bodyDownLeft = '\u2517';
        char bodyDownRight = '\u251b';
        char bodyMidLeft = '\u2520';
        char bodyDownMid = '\u2537';
        char bodyMidRigth = '\u2528';
        char bodyThinVert = '\u2502';
        char bodyThinHoriz = '\u2500';
        char bodyThinCross = '\u253c';
        //endregion

        StringBuilder sb = new StringBuilder();

        //region head
        sb.append(MakeTableHorizontalString(lengths, headUpLeft, headUpMid, headUpRight, headHoriz));
        sb.append('\n');
        sb.append(MakeTableDataString(lengths, inputStr[0], headVert, headVert));
        sb.append('\n');
        sb.append(MakeTableHorizontalString(lengths, headDownLeft, headDownMid, headDownRight, headHoriz));
        sb.append('\n');
        //endregion

        //region body
        for (int i = 1; i < n; ++i) {
            sb.append(MakeTableDataString(lengths, inputStr[i], bodyVertBorder, bodyThinVert));
            sb.append('\n');
            if (i != n - 1)
                sb.append(MakeTableHorizontalString(lengths, bodyMidLeft, bodyThinCross, bodyMidRigth, bodyThinHoriz));
            else
                sb.append(MakeTableHorizontalString(lengths, bodyDownLeft, bodyDownMid, bodyDownRight, bodyHorizBorder));
            sb.append('\n');
        }
        //endregion

        //delete me

        return sb.toString();
    }
}
