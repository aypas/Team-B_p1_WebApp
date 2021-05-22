package com.revature.p0.screens;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/5/2021
 * Time: 9:06 AM
 * Description: Template for other screens to follow
 */
public abstract class Screen {

    protected String name;
    protected String route;

    public Screen(String name, String route) {
        this.name = name;
        this.route = route;
    }

    /**
     *
     * Gets the name of the screen
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Gets the routing name of the screen
     *
     * @returnString
     */
    public String getRoute() {
        return route;
    }

    public abstract void render();
}
