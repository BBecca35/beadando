package hu.nye.home.ui;

import hu.nye.home.model.MapVO;
import hu.nye.home.model.VariableMap;
import hu.nye.home.service.validator.PossibilityToRemoveCheck;
import java.util.Scanner;

public class EditorMenu {
    public static void edit(MapVO originalMap, VariableMap modifiedMap){
        Scanner scanner = new Scanner(System.in);
        boolean mainSwitcher = true;
        while(mainSwitcher) {
            //boolean switcher1 = true;
            System.out.println("-------------------------------");
            System.out.println("Kérem adja meg az opció számát!");
            System.out.println("1. hozzáadás");
            System.out.println("2. visszavonás");
            System.out.println("3. A szerkesztő bezárása");
            System.out.println("-------------------------------");
            int option = scanner.nextInt();
            if(option == 1){
                System.out.println("A hozzáadás megkezdése.");
                AdderMenu.adder(modifiedMap);

            }else if(option == 2){
                boolean removal = PossibilityToRemoveCheck.applicableRemoval(originalMap,modifiedMap);
                if(!removal) {
                    System.out.println("A visszavonás sikertelen. Még nem történt hozzáadás.");
                }
                else {
                    RemoverMenu.remover(originalMap,modifiedMap);
                }

            } else if (option == 3) {
                System.out.println("A pályaszerkesztő bezárása.");
                mainSwitcher = false;
            } else {
                System.out.println("Hiba. Üres vagy nem létező opció.");
            }
            boolean switcher1 = true;
            while (switcher1) {
                System.out.println("--------------------------------------------");
                System.out.println("Szeretné tovább szerkeszteni a pályát? ");
                System.out.println("1. igen");
                System.out.println("2. nem");
                System.out.println("--------------------------------------------");
                int answerForTheQ = scanner.nextInt();
                if (answerForTheQ == 1) {
                    System.out.println("A pályaszerkesztés folytatása.");
                } else if (answerForTheQ == 2) {
                    System.out.println("A pályaszerkesztő bezárása.");
                    switcher1= false;
                } else {
                    System.out.println("Hiba. Üres vagy nem létező opció.");
                }
            }
        }
    }
}
