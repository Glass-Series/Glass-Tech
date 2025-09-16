package net.glasslauncher.gradleplugin.resourcegen;

import org.gradle.api.Project;
import org.gradle.api.provider.Property;
import org.gradle.internal.impldep.com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceGenExtension {
    public final Property<ResourceGenPatternTargets> patternTargets;

    public ResourceGenExtension(Project project) {
        patternTargets = project.getObjects().property(ResourceGenPatternTargets.class).convention(ResourceGenPatternTargets.Companion.create());
    }
}
