package hu.nye.home.service.validator;

public class OtherComponentCheck {
    public static boolean applicableComponent(int[] coordinate){
        if(coordinate[0] == 1 || coordinate[0] >= 6){
            return true;
        }else if(coordinate[1] == 1 || coordinate[1] >= 6) {
            return true;
        } else {
            return false;
        }
    }
}

