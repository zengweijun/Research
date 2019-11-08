package com.nius.server.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
    private List<Entity> entities = null;
    private List<Mapping> mappings = null;

    // key: Entity.name, value:Entity.clz
    private Map<String, String> entityMap = new HashMap<>();

    // key: Mapping.pattern, value:Mapping.name
    private Map<String, String> mappingMap = new HashMap<>();

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        this.entities = entities;
        this.mappings = mappings;

        for (Entity  e: entities) {
            entityMap.put(e.getName(), e.getClz());
        }
        for (Mapping m: mappings) {
            for (String pattern : m.getPatterns())
            mappingMap.put(pattern, m.getName());
        }
    }

    // 通过url的路劲，找到对应的class
    public String getClz(String pattern) {
        String name = mappingMap.get(pattern);
        return entityMap.get(name);
    }
}
