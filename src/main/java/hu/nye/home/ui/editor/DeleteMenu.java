package hu.nye.home.ui.editor;

import hu.nye.home.model.MapVO;
import hu.nye.home.service.commands.ComponentDeleting;
import hu.nye.home.service.util.Utils;
import hu.nye.home.service.util.UtilsImp;

import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;

public class DeleteMenu {
    public static void delete(SortedMap<String, List<String>> components, MapVO mapVO){
        Scanner scanner = new Scanner(System.in);
        Utils utils = new UtilsImp();
        boolean switcher = true;
        while(switcher){
            System.out.println("Honnan szeretne törölni? (pl: A1)");
            String coordinate2 = scanner.nextLine();
            int[] position2 = utils.string2Int(coordinate2);
            if (position2[0] != 0 && position2[1] != 0) {
                if(components.containsKey(coordinate2)){
                    String messega = ComponentDeleting.deleteComponent(components, mapVO,coordinate2);
                    System.out.println(messega);
                    System.out.println(mapVO.toString());
                }
                else{
                    System.out.println("Az adott koordináta még nem lett módosítva.");
                }
                switcher = false;
            } else {
                System.out.println("Hiba. Üres vagy helytelen koordináta.");
            }
        }
    }
}
