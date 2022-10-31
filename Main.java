import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner inLine = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Введите условия задачи");
        String inputLine = String.valueOf(inLine.nextLine().replace(" ", ""));
        String prtText = "";
        boolean charLetter = false;
        boolean checkLetterType = true;
        boolean checkNumCount = false;
        boolean checkFNLetterType = false;
        boolean checkInputError = false;
        int fChar = 0;
        int nChar = 0;
        String actionType="";
        int calcCount=0;
        int arrayLength = Integer.valueOf(inputLine.length());
    // Проверка значений вводимых пользователем ---
        String strChar="";
        String nextStrChar="";

        for (int i = 0; i < arrayLength; i++){
            strChar=String.valueOf(inputLine.charAt(i));
            // проверка на наличи минимального количества символов --
            if (arrayLength<3){
                prtText = "Минимального количества символов для вычесления должно быть больше 3-х";
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println(prtText);
                    break;
                }
                //break;
            }
            //--

            // проверка на наличи некорректных символов и количество арифмитеческих операций,
            // так же проверка шмешивания цифр --
            if ( Character.isLetter(inputLine.charAt(i)) ){
                // проверка граничных знаков --
                if (i==0||i==arrayLength-1) {
                    switch (strChar) {
                        case ("+"):
                        case ("/"):
                        case ("-"):
                        case ("*"):
                            checkFNLetterType = true;
                            break;
                    }
                    if (checkFNLetterType) {
                        prtText = "Не корректный ввод условий задачи," +
                                "\nв начале или в конце не может быть знаков арифмитеческих операций";
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println(prtText);
                            break;
                        }
                    }
                }
                //--
                charLetter = true;
                switch (strChar) {
                    case ("+"):
                    case ("/"):
                    case ("-"):
                    case ("*"):
                    case ("I"):
                    case ("V"):
                    case ("X"):
                        checkLetterType = false;
                        break;
                }
                if (checkLetterType){
                    prtText = "Не корректный ввод символов," +
                            "\nдоступны только арифмитеческие операций '+,-,*,/' и римские или арабские цыфры";
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println(prtText);
                        break;
                    }
                }
                if (i<arrayLength-1){
                    if ( Character.isDigit(inputLine.charAt(i+1)) ){
                         prtText = "Не корректный ввод значения: "
                                 +String.valueOf(inputLine.charAt(i))+String.valueOf(inputLine.charAt(i+1));
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println(prtText);
                            break;
                        }
                    }
                }
            } else if ( Character.isDigit(inputLine.charAt(i)) ){
                charLetter = false;
            }
        }
        for (int i = 0; i < arrayLength; i++) {
            strChar = String.valueOf(inputLine.charAt(i));
            if (strChar.equals("+") || strChar.equals("-") || strChar.equals("*") || strChar.equals("/")) {
                calcCount++;
            }
            if (calcCount>1){
                prtText = "Калькулятор вычесляет только одну арифмитеческую операцию";
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println(prtText);
                    break;
                }
            }
        }

        // замена римских знаков на цыфру

        for (int i = 0; i < arrayLength; i++) {
            strChar = String.valueOf(inputLine.charAt(i));
            if (strChar.equals("+") || strChar.equals("-") || strChar.equals("*") || strChar.equals("/")) {
                actionType = strChar;
                for (int k = 0; k < i; k++) {
                    if (k == 0) {
                        strChar = String.valueOf(inputLine.charAt(k));
                    } else {
                        strChar = strChar + String.valueOf(inputLine.charAt(k));
                    }
                }
                for (int n = i+1; n < arrayLength; n++) {
                    if (n == i+1) {
                        nextStrChar = String.valueOf(inputLine.charAt(n));
                    } else {
                        nextStrChar = nextStrChar + String.valueOf(inputLine.charAt(n));
                    }
                }
                break;
            }
        }
        if (Character.isDigit(strChar.charAt(0))){
            if (Integer.valueOf(strChar)>10){
                checkNumCount=true;
            }
        }else if (Character.isDigit(nextStrChar.charAt(0))){
            if (Integer.valueOf(nextStrChar)>10){
                checkNumCount=true;
            }
        }else if (Character.isLetter(strChar.charAt(0))){
            switch (strChar) {
                case ("I"):
                case ("II"):
                case ("III"):
                case ("IV"):
                case ("V"):
                case ("VI"):
                case ("VII"):
                case ("VIII"):
                case ("IX"):
                case ("X"):
                    checkNumCount=false;
                    break;
                default:
                    checkNumCount=true;
                    break;
            }
        }else if (Character.isLetter(nextStrChar.charAt(0))) {
            switch (nextStrChar) {
                case ("I"):
                case ("II"):
                case ("III"):
                case ("IV"):
                case ("V"):
                case ("VI"):
                case ("VII"):
                case ("VIII"):
                case ("IX"):
                case ("X"):
                    checkNumCount = false;
                    break;
                default:
                    checkNumCount = true;
                    break;
            }
        }
        if (checkNumCount){
            prtText = "Вводимые значения не должны превышать 10";
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println(prtText);
                System.exit (1);
            }
        }

        if (Character.isDigit(strChar.charAt(0))){
            fChar=1;
        }
        if (Character.isDigit(nextStrChar.charAt(0)) ) {
            nChar = 1;
        }
        if ((fChar+nChar)==1){
            prtText = "Не корректный ввод условий задачи," +
                    "\nКалькулятор умеет работать только с арабскими или римскими цифрами одновременно";
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println(prtText);
                System.exit (1);
            }
        }

        String firsNum="";
        String secondNum="";
        if (charLetter){
            for (int k = 0; k < 2; k++) {
                if(k==1){
                    strChar= nextStrChar;
                }
                switch (strChar) {
                    case ("I"):
                        strChar = "1";
                        break;
                    case ("II"):
                        strChar = "2";
                        break;
                    case ("III"):
                        strChar = "3";
                        break;
                    case ("IV"):
                        strChar = "4";
                        break;
                    case ("V"):
                        strChar = "5";
                        break;
                    case ("VI"):
                        strChar = "6";
                        break;
                    case ("VII"):
                        strChar = "7";
                        break;
                    case ("VIII"):
                        strChar = "8";
                        break;
                    case ("IX"):
                        strChar = "9";
                        break;
                    case ("X"):
                        strChar = "10";
                        break;
                }
                if(k==1){
                    secondNum=strChar;
                }else{
                    firsNum=strChar;
                }
            }

        }else{
            firsNum=strChar;
            secondNum=nextStrChar;
        }
        int result =0;
        if (calcCount<2){
            switch (actionType) {
                case ("+"):
                    result=Integer.valueOf(firsNum)+Integer.valueOf(secondNum);
                    break;
                case ("/"):
                    result=Integer.valueOf(firsNum)/Integer.valueOf(secondNum);
                    break;
                case ("-"):
                    result=Integer.valueOf(firsNum)-Integer.valueOf(secondNum);
                    break;
                case ("*"):
                    result=Integer.valueOf(firsNum)*Integer.valueOf(secondNum);
                    break;
            }
        }
        String strResult=String.valueOf(result);
        String strAllResult="";

        if (charLetter) {
            for (int k = 0; k < Integer.valueOf(String.valueOf(result).length()); k++) {
                if ( Integer.valueOf(String.valueOf(result).length()) < 2 )
                {
                    switch (Integer.valueOf(String.valueOf(result).charAt(0))) {
                        case (1):
                            strAllResult = "I";
                            break;
                        case (2):
                            strAllResult = "II";
                            break;
                        case (3):
                            strAllResult = "III";
                            break;
                        case (4):
                            strAllResult = "IV";
                            break;
                        case (5):
                            strAllResult = "V";
                            break;
                        case (6):
                            strAllResult = "VI";
                            break;
                        case (7):
                            strAllResult = "VII";
                            break;
                        case (8):
                            strAllResult = "VIII";
                            break;
                        case (9):
                            strAllResult = "IX";
                            break;
                    }
                } else {
                      switch ( Integer.parseInt(String.valueOf(strResult.charAt(k))) ) {
                            case (1):
                                if (k<1){
                                    strAllResult = "X";
                                }else {
                                    strAllResult = strAllResult + "I";
                                }
                                break;
                            case (2):
                                if (k<1){
                                    strAllResult = "XX";
                                }else {
                                    strAllResult = strAllResult +"II";
                                }
                                break;
                            case (3):
                                if (k<1){
                                    strAllResult = "XXX";
                                }else {
                                    strAllResult = strAllResult +"III";
                                }
                                break;
                            case (4):
                                if (k<1){
                                    strAllResult = "XL";
                                }else {
                                    strAllResult = strAllResult +"IV";
                                }
                                break;
                            case (5):
                                if (k<1){
                                    strAllResult = "L";
                                }else {
                                    strAllResult = strAllResult +"V";
                                }
                                break;
                            case (6):
                                if (k<1){
                                    strAllResult = "LX";
                                }else {
                                    strAllResult = strAllResult +"VI";
                                }
                                break;
                            case (7):
                                if (k<1){
                                    strAllResult = "LXX";
                                }else {
                                    strAllResult = strAllResult +"VII";
                                }
                                break;
                            case (8):
                                if (k<1){
                                    strAllResult = "LXXX";
                                }else {
                                    strAllResult = strAllResult +"VIII";
                                }
                                break;
                            case (9):
                                if (k<1){
                                    strAllResult = "XC";
                                }else {
                                    strAllResult = strAllResult +"IX";
                                }
                                break;
                        }
                    }
                }
            }else {
            strAllResult=strResult;
        }


        if (calcCount<2){
            System.out.println("Результата вычисления: "+strAllResult);
        }
    }
}