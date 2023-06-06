package com.github.tvloet1.seacleaner.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.tvloet1.seacleaner.SeaCleaner;
import com.github.tvloet1.seacleaner.entities.Swimmer;
import com.github.tvloet1.seacleaner.entities.enemies.ElectricEel;
import com.github.tvloet1.seacleaner.entities.enemies.FirePufferFish;
import com.github.tvloet1.seacleaner.entities.enemies.PoisonPufferFish;
import com.github.tvloet1.seacleaner.entities.enemies.PufferFish;
import com.github.tvloet1.seacleaner.entities.map.ObstacleTileMap;
import com.github.tvloet1.seacleaner.entities.spawners.LitterSpawner;
import com.github.tvloet1.seacleaner.entities.spawners.ModifierSpawner;
import com.github.tvloet1.seacleaner.entities.text.GameText;

public class GameLevel extends DynamicScene implements EntitySpawnerContainer, TileMapContainer {

    private SeaCleaner seacleaner;

    public GameLevel(SeaCleaner seacleaner) {
        this.seacleaner = seacleaner;
    }
    /**
     * @author Tom Vloet
     * @since 04-APR-2023
     * Setup music and set background image.
     */
    @Override
    public void setupScene() {
        setupMusic();
        setBackgroundImage("backgrounds/backgroundGame.jpg");
    }

    /**
     * @author Tom Vloet
     * @since 04-APR-2023
     * Setup entities of the game level.
     */
    @Override
    public void setupEntities() {
        var scoreText = new GameText(new Coordinate2D(5, 5), "Score");
        addEntity(scoreText);
        var healthText = new GameText(new Coordinate2D(5, 35), "Health");
        addEntity(healthText);
        var swimmer = new Swimmer(new Coordinate2D(500, 150), seacleaner, scoreText, healthText);
        addEntity(swimmer);
        var firePufferfish = new FirePufferFish(new Coordinate2D(1000, 150), 30,3, this);
        addEntity(firePufferfish);
        var poisonPufferfish = new PoisonPufferFish(new Coordinate2D(500, 500), 25,2, this);
        addEntity(poisonPufferfish);
        var electricEel = new ElectricEel(new Coordinate2D(350, 600), 10, 4);
        addEntity(electricEel);
    }

    public void addEntityToCollection(YaegerEntity entity){
        this.addEntity(entity);
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Setup entity spawners of the game level.
     */
    @Override
    public void setupEntitySpawners() {
        addEntitySpawner(new LitterSpawner(getWidth(),seacleaner.getSoundEffectsOn()));
        addEntitySpawner(new ModifierSpawner(getWidth(),seacleaner.getSoundEffectsOn()));
    }

    @Override
    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Setup tile map of the game level.
     */
    public void setupTileMaps() {
        addTileMap(new ObstacleTileMap());
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Depending on the seacleaner value for musicOn turn the music on and lower volume.
     */
    private void setupMusic() {
        if(seacleaner.getMusicOn()) {
            setBackgroundAudio("audio/behindEnemyLines.mp3");
            setBackgroundAudioVolume(0.25);
        }
    }

}
