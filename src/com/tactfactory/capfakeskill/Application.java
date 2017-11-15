package com.tactfactory.capfakeskill;

import java.util.Arrays;

public class Application {
    private static final boolean PRODUCTION = false;

    /**
     * Program run.
     * @param args
     * @throws DatabaseNotReadyException
     */
    public static void main(String[] args) throws DatabaseNotReadyException {
        DatabaseManager database = DatabaseManager.getInstance();

        database.prepareDb(PRODUCTION);
        database.getConnection();

        SkillTypeDao dao = new SkillTypeDao();

        // Arrays.asList => creates list from given values.
        dao.insert(Arrays.asList(
                new SkillType("coucou"),
                new SkillType("bonne"),
                new SkillType("inutile"),
                new SkillType("marrante")));

        dao.findAll().forEach(type -> System.out.println(type));
    }
}
