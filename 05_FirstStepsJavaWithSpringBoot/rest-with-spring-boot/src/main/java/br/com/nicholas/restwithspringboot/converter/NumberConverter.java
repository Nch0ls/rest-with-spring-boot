package br.com.nicholas.restwithspringboot.converter;

public class NumberConverter {
    public static boolean isNumeric(String str) {
        if(str == null) return false;
        String number = str.replaceAll(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static Double convertToDouble(String string){
        if(string == null) return 0D;
        String number = string.replaceAll(",",".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }
}
