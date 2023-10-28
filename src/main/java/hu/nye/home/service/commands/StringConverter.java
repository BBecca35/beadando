package hu.nye.home.service.commands;

public class StringConverter {
    public static int[] string2Int(String positionInString) {
        int[] coordination = {0,0};
        StringBuilder number = new StringBuilder();
        for (char c : positionInString.toCharArray()) {
            if (Character.isLetter(c)) {
                if(c >= 'A' && c <= 'Z'){
                    coordination[0]= c - 'A' + 1;
                    //System.out.println(coordination[0]);
                }
            } else if (Character.isDigit(c)) {
                number.append(c);
                String numberFormat = number.toString();
                coordination[1] = Integer.parseInt(numberFormat);
                //System.out.println(coordination[1]);
            }
        }
        return coordination;
    }
}
