package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.tvloet1.seacleaner.entities.Swimmer;

public interface ModifySwimmer {

    /**
     * @param swimmer This method is used to interact with the Swimmer. The method takes in a Swimmer parameter, which
     *                is then used to make changes to the swimmers properties e.g. reduce speed or health.
     * @author Tom Vloet
     * @since 06-JUN-2023
     */
    void modify(Swimmer swimmer);
}
