import java.util.Scanner;

public class Main {
    static String str;
    static String[] arrDelimeter = {" +", "-", " *", "/", "%"};
    static String[] arrDelimeter2 = {"\\+", "-", "\\*", "/", "%"};
    static String[] subStr = new String[2];
    static String[] arrRim = new String[] {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static String[] arrRimTens = new String[] {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

    public static void main(String[] args) {
        System.out.println("Введите арифметическое выражение с числами от 1 до 10 включительно или от I до X включительно:");

        //Создаем объект inData класса Scanner с данными, полученными от пользователя
        Scanner inData = new Scanner(System.in);
        str = inData.nextLine();

        //Делим строку по разделителю и получаем строковый массив элементов subStr
        String delimeter = null;
        int countDelimeter = 0;
        for (int i = 0; i < arrDelimeter.length; i++) {
            if (str.contains(arrDelimeter[i].trim())) {
                subStr = str.split(arrDelimeter2[i]);
                delimeter = arrDelimeter2[i];
                countDelimeter++;
            }
        }
        if (countDelimeter == 0) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
            System.exit(0);
        }
        if (countDelimeter >= 2) {
            System.out.println("throws Exception //т.к. формат математической операции не удоволетворяет заданию");
            System.exit(0);
        }

        //Проверяем можно ли изменить тип элементов в массиве со строкового на int
        try {
            //здесь String преобразуем в int
            int a = Integer.parseInt(subStr[0]);
            int b = Integer.parseInt(subStr[1]);
            if (subStr.length > 2) {
                System.out.println("throws Exception //т.к. формат математической операции не удоволетворяет заданию");
                System.exit(0);
            }

            //Вызываем метод для операций над арабскими числами
            int tempArab = func_arab(a, b);
            System.out.println(tempArab);

        } catch (NumberFormatException nfe) {
            if (subStr.length == 2) {
                int a = 0;
                int b = 0;

                //перебираем массив и определяем каким арабским числам соответствуют a и b
                for (int j = 0; j < arrRim.length; j++) {
                    if (subStr[0].equals(arrRim[j])) {
                        a = j;
                    }
                    if (subStr[1].equals(arrRim[j])) {
                        b = j;
                    }
                }

                //Вызываем метод для операций над римскими числами
                int temp = 0;
                if (a != 0 && b != 0) {
                    temp = func_rim(a, b);
                } else if (a != 0 || b != 0) {
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                    System.exit(0);
                } else {
                    System.out.println("напоминаю, числа должны быть от I до X включительно");
                    System.exit(0);
                }

                //Вызываем метод перевода арабских чисел в римские
                funcConvertToRim(temp);

            } else if (subStr.length > 2) {
                System.out.println("throws Exception //т.к. формат математической операции не удоволетворяет заданию");
                System.exit(0);
            }
        }
        inData.close();
    }

    //Метод для операций с арабскими числами
    public static int func_arab (int a, int b) {
        int result = 0;
        if (a <= 10 && b <= 10) {
            if (str.contains(arrDelimeter[0].trim())) {
                result = a + b;
            } else if (str.contains(arrDelimeter[1])) {
                result = a - b;
            } else if (str.contains(arrDelimeter[2].trim())) {
                result = a * b;
            } else if (str.contains(arrDelimeter[3])) {
                if (b!=0) {
                    result = a / b; //остаток отбрасывается, потому что int
                } else {
                    System.out.println("на ноль делить нельзя");
                    System.exit(0);
                }
            } else if (str.contains(arrDelimeter[4])) {
                //арифметическая операция - остаток от деления
                System.out.println("данная арифметическая операция не предусмотрена в работе данной программы");
                System.exit(0);
            }
        } else {
            System.out.println("напоминаю, числа должны быть от 1 до 10 включительно");
            System.exit(0);
        }
        return result;
    }

    //Метод для операций с римскими числами
    public static int func_rim (int a, int b) {
        int result = 0;
        if (a <= 10 && b <= 10) {
            if (str.contains(arrDelimeter[0].trim())) {
                result = a + b;
            } else if (str.contains(arrDelimeter[1])) {
                result = a - b;
            } else if (str.contains(arrDelimeter[2].trim())) {
                result = a * b;
            } else if (str.contains(arrDelimeter[3])) {
                if (b!=0) {
                    result = a / b; //остаток отбрасывается, потому что int
                } else {
                    System.out.println("в римской системе счисления нет нуля" +
                            " - возможно, вы пытаетесь использовать разные системы счисления");
                    System.exit(0);
                }
            } else if (str.contains(arrDelimeter[4])) {
                //арифметическая операция - остаток от деления
                System.out.println("данная арифметическая операция не предусмотрена в работе данной программы");
                System.exit(0);
            }
        }
        return result;
    }

    //Метод для перевода арабских чисел в римские
    public static void funcConvertToRim (int temp) {
        if (temp > 0) {
            String calcResult = Integer.toString(temp);

            //реверсируем строку, чтобы сначала шли единицы, потом десятки
            StringBuilder bufferCalcResult = new StringBuilder(calcResult);
            bufferCalcResult.reverse();
            String bufCalcRes_str = bufferCalcResult.toString();

            //разбиваем строку на массив символов
            char[] arrResult = bufCalcRes_str.toCharArray();

            String tens = null;
            String units = null;
            String tens_str = null;
            String units_str = null;

            units = Character.toString(arrResult[0]);
            if (arrResult.length == 2) {
                tens = Character.toString(arrResult[1]);
                for (int j = 0; j < arrRimTens.length; j++) {
                    String j_str = Integer.toString(j);
                    if (tens.equals(j_str)) {
                        tens_str = arrRimTens[j];
                    }
                }
            } else {
                tens_str = "";
            }

            for (int j = 0; j < arrRim.length; j++) {
                String j_str = Integer.toString(j);
                if (units.equals(j_str)) {
                    units_str = arrRim[j];
                }
            }
            System.out.println(tens_str + units_str);
        } else if (temp < 0) {
            System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
            System.exit(0);
        }
    }
}

