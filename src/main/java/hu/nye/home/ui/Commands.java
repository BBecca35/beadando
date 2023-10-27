package hu.nye.home.ui;

public class Commands {

    public static String[][] addComponent(String coordinate1, String compoment1, String[][] map) {
        String[][] newMap = new String[map[0].length][map[0].length];
        int[] position = string2Int(coordinate1);
        for(int i = 0; i < map.length; i++){
            System.arraycopy(map[i], 0, newMap[i], 0, map.length);
        }

        for (int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++){
                if(j == position[0]-1 && i == position[1]-1){
                    if(newMap[i][j].equals("_")){
                        newMap[i][j] = compoment1;
                    }
                }
            }
        }
        return newMap;
    }

    public static String[][] removeComponent(String coordinate2, String[][] oldMap, String[][] newMap) {
        String[][] anotherMap = new String[newMap[0].length][newMap[0].length];
        String[][] originalMap = new String[oldMap[0].length][oldMap[0].length];
        int[] position = string2Int(coordinate2);

        for (int i = 0; i < anotherMap.length; i++) {
            for(int j = 0; j < anotherMap[0].length; j++){
                anotherMap[i][j] = newMap[i][j];
                originalMap[i][j] = oldMap[i][j];
            }
        }

        for (int i = 0; i < oldMap.length; i++) {
            for(int j = 0; j < oldMap[0].length; j++){
                if(j == position[0]-1 && i == position[1]-1){
                    anotherMap[i][j] = originalMap[i][j];
                }
            }
        }
        return anotherMap;
    }

    public static int[] string2Int(String positionInString) {
        int[] coordination = {0,0};
        StringBuilder number = new StringBuilder();
        for (char c : positionInString.toCharArray()) {
            if (Character.isLetter(c)) {
                if(c >= 'A' && c <= 'Z'){
                   coordination[0]= c - 'A' + 1;
                   System.out.println(coordination[0]);
                }
            } else if (Character.isDigit(c)) {
                number.append(c);
                String numberFormat = number.toString();
                coordination[1] = Integer.parseInt(numberFormat);
                System.out.println(coordination[1]);
            }
        }
        return coordination;
    }
}
