package com.revature.p1.util.scenemgmt;

import com.revature.p1.util.datastructs.linkedlist.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 11:22 AM
 * Description: Allows the program to navigate through screens.
 */
public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();

    /**
     *
     * Description: Adds a screen to a linked list making it retrievable
     *
     * @param screen
     * @return
     */
    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    /**
     *
     * Description: Navigates to a screen via it's route screen
     *
     * @param route
     */
    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render();
            }
        }
    }
}