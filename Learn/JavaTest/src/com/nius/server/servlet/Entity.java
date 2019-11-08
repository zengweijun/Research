package com.nius.server.servlet;

/**
 <servlet>
    <servlet-name>login</servlet-name>
    <servlet-class>com.nius.server.servlet.LoginServlet</servlet-class>
 </servlet>
 */
public class Entity {
    private String name; // 名称
    private String clz;  // 类名
    public Entity() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClz() {
        return clz;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }
}
