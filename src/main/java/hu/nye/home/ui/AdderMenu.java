package hu.nye.home.ui;

import hu.nye.home.model.VariableMap;
import hu.nye.home.service.commands.ComponentAdding;
import hu.nye.home.service.commands.StringConverter;

import java.util.Scanner;

public class AdderMenu {
    public static void adder(VariableMap variableMap){
        Scanner scanner = new Scanner(System.in);
        boolean switcher2 = true;
        while (switcher2) {
            System.out.println("Kérem adja meg, milyen elemet szeretne hozzáadni! (W, H, U, P, G)");
            String component = scanner.nextLine();
            if ((component.equals("H")) || (component.equals("W")) || (component.equals("U")) || (component.equals("P")) || (component.equals("G"))) {
                boolean switcher3 = true;
                while (switcher3) {
                    System.out.println("Hova kívánja hozzáadni (pl: A1)?");
                    String coordinate1 = scanner.nextLine();
                    int[] position1 = StringConverter.string2Int(coordinate1);
                    if(position1.length == 2){
                        boolean addition = ComponentAdding.addComponent(position1, component, variableMap);
                        if (addition) {
                            System.out.println(variableMap.toString());
                            switcher3 = false;
                        } else {
                            System.out.println("A hozzáadás sikertelen.");
                            switcher3 = false;
                        }

                    }else{
                        System.out.println("Hiba. Üres vagy helytelen koordináta.");
                    }
                }

            } else {
                System.out.println("Hiba. Üres vagy nem létező elem.");
            }
            System.out.println("A hozzáadás sikeres.");
            switcher2 = false;
        }
    }
}
