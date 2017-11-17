package com.tactfactory.capfakeskill.entities;

public class SkillType {
    private Integer id;
    private String name;

    public SkillType(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public SkillType setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * @return
     */
    public SkillType setName(String name) {
        this.name = name.toLowerCase();
        return this;
    }
}
