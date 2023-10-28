package hu.nye.home.ui;

import hu.nye.home.model.MapVO;
import hu.nye.home.model.VariableMap;
import hu.nye.home.service.commands.ComponentRemoving;
import hu.nye.home.service.commands.StringConverter;

import java.util.Scanner;

public class RemoverMenu {
    public static void remover(MapVO mapVO, VariableMap variableMap){
        Scanner scanner = new Scanner(System.in);
        System.out.println("A visszavonás megkezdése.");
        boolean switcher5 = true;
        while (switcher5) {
            System.out.println("Honnan szeretne visszavonni? (pl: A1)");
            String coordinate2 = scanner.nextLine();
            int[] position2 = StringConverter.string2Int(coordinate2);
            if (position2.length == 2) {
                ComponentRemoving.removeComponent(coordinate2, mapVO, variableMap);
                System.out.println(variableMap.toString());
                switcher5 = false;
            } else {
                System.out.println("Hiba. Üres vagy helytelen koordináta.");
            }
        }
    }
}
