module seacleaner {
    requires hanyaeger;

    exports com.github.tvloet1.seacleaner;
    exports com.github.tvloet1.seacleaner.entities.map;

    opens audio;
    opens backgrounds;
    opens sprites;
    opens sprites.litter;
    opens sprites.obstacles;
    opens sprites.modifiers;
}
