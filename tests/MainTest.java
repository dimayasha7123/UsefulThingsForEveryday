import UTFE.TableOutput.Table;

/**
 * |_|>
 * |_|>    Created by Dimyasha on 30.10.2021
 * |_|>
 */

public class MainTest {
    public static void writtenCodeIDontWantToDelete() {
        /*

        String[] testArr = new String[] {"Привет, \n как дела?", "Привет, \n как дела?\nА?\nА? А?", "Норм", "А?\nА? А?\nА? А? А?\nА? А? А? А?"};
        //String[][] actualArr = Table.strArrToTwoDimensionalStrArr(testArr);
        for (int i = 0; i < actualArr.length; ++i){
            for (int j = 0; j < actualArr[0].length; ++j){
                System.out.print(actualArr[i][j] + " ");
            }
            System.out.println();
        }


        */
    }

    public static void main(String[] args) {
        String[] testArr1 = new String[] {"Привет, \nкак дела?", "Привет, \n как дела?\nА?\nА? А?", "Норм", "А?\nА? А?\nА? А? А?\nА? А? А? А?"};
        Object[] testArr2 = new Object[] {123, 234.2553, "Здарова еще раз", "Hello, \nworld!"};
        Object[][] testArr = new Object[][] {testArr1, testArr2};
        System.out.println(Table.TableToString(testArr));
    }
}