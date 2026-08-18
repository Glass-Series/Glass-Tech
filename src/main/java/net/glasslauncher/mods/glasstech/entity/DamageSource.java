package net.glasslauncher.mods.glasstech.entity;

public enum DamageSource {
    FALLING(Nature.ENVIRONMENT),
    DROWNING(Nature.ENVIRONMENT),
    FIRE(Nature.ENVIRONMENT),
    LAVA(Nature.ENVIRONMENT),
    CRUSHING(Nature.ENVIRONMENT),
    ;

    public final Nature nature;

    DamageSource(Nature nature) {
        this.nature = nature;
    }

    public enum Nature {
        ENVIRONMENT,
        PLAYER,
        MOB,
    }
}
