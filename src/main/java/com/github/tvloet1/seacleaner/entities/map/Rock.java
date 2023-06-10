package com.github.tvloet1.seacleaner.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.tvloet1.seacleaner.entities.Swimmer;
import com.github.tvloet1.seacleaner.entities.modifiers.ModifySwimmer;

public class Rock extends SpriteEntity implements Collider, ModifySwimmer {
    public Rock(Coordinate2D location, Size size, String resource) {
        super(resource, location, size);
    }

    @Override
    public void modify(Swimmer swimmer) {
        swimmer.haltMovement(getBoundingBox());
    }
}
