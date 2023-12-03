package hu.nye.home.ui.editor;

import hu.nye.home.model.Hero;
import hu.nye.home.model.MapVO;
import hu.nye.home.service.commands.ComponentChanging;
import hu.nye.home.service.util.*;

import java.util.*;

public class ChangerMenu {
    public static void change(MapVO mapVO, SortedMap<String, List<String>> components, Hero hero) {
        Utils utils = new UtilsImp();
        Scanner scanner = new Scanner(System.in);
        boolean switcher2 = true;
        while (switcher2) {
            System.out.println("Melyik koordinátán lévő elemet szeretné módosítani (pl: A1)?");
            String coordinate1 = scanner.nextLine();
            int[] position1 = utils.string2Int(coordinate1);
            if (position1[0] != 0 && position1[1] != 0) {
                if (!(components.containsKey(coordinate1))) {
                    components.put(coordinate1, new ArrayList<String>());
                    components.get(coordinate1).add("_");
                }
                boolean switcher3 = true;
                while (switcher3) {
                    try {
                        System.out.println("Kérem, válassza ki az elemet,\namire szeretné módosítani a jelenlegit!");
                        System.out.println("------------------------------------");
                        System.out.println("1. W - fal");
                        System.out.println("2. H - hős");
                        System.out.println("3. U - wumpusz");
                        System.out.println("4. P - verem");
                        System.out.println("5. G - arany");
                        System.out.println("\nAz elem száma: ");
                        String numberOfComponentInStr = scanner.nextLine();
                        System.out.println("------------------------------------");
                        int numberOfComponent = Integer.parseInt(numberOfComponentInStr);
                        String component = utils.convertNumberToComponent(numberOfComponent);
                        if(component != null){
                            if (component.equals("H")) {
                                boolean switcher4 = true;
                                while (switcher4) {
                                    try {
                                        System.out.println("Kérem, Adja meg a hős kezdeti irányát.");
                                        System.out.println("------------------------------------");
                                        System.out.println("1. észak");
                                        System.out.println("2. dél");
                                        System.out.println("3. kelet");
                                        System.out.println("4. nyugat");
                                        System.out.println("\nAz irány száma: ");
                                        System.out.println("------------------------------------");
                                        String numberOfDirectionInStr = scanner.nextLine();
                                        int numberOfDirection = Integer.parseInt(numberOfDirectionInStr);
                                        String direction = utils.convertNumberToDirection(numberOfDirection);
                                        if(direction != null){
                                            hero.setHeading(numberOfDirectionInStr);
                                            switcher4 = false;
                                        }
                                        else{
                                            System.out.println("Az adott számmal ellálott irány nem létezik.");
                                        }
                                    }catch (Exception e){
                                        System.out.println("Hibás érték. Kérem helyesen töltse ki a mezőt!");
                                    }
                                }
                                position1[0] -= 1;
                                position1[1] -= 1;
                                hero.setStartCoordinate(position1);
                                hero.setActualCoordinate(position1);
                            }
                            String messega = ComponentChanging.changeComponent(coordinate1, component, mapVO, components, hero);
                            System.out.println(messega);
                            System.out.println(mapVO.toString());
                            switcher3 = false;
                            switcher2 = false;
                        }else{
                            System.out.println("Az adott számmal ellálott elem nem létezik.");
                        }
                    } catch (Exception e) {
                        System.out.println("Hibás érték. Kérem helyesen töltse ki a mezőt!");
                    }

                }
            }
        }
    }
}
