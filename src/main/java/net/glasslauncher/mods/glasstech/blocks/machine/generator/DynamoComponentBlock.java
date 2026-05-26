package net.glasslauncher.mods.glasstech.blocks.machine.generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * All this does is allow players to just right-click on a dynamo to place this block without the need of something like nyatweaks installed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DynamoComponentBlock {
}
