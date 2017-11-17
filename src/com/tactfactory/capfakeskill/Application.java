package com.tactfactory.capfakeskill;

import com.tactfactory.capfakeskill.dao.UserDAO;
import com.tactfactory.capfakeskill.entities.User;
import com.tactfactory.capfakeskill.exceptions.DatabaseNotReadyException;
import com.tactfactory.capfakeskill.manager.DatabaseManager;

public class Application {
    private static final boolean PRODUCTION = false;
    private static final boolean DEVELOPMENT = true;

    /**
     * Program run.
     * @param args
     * @throws DatabaseNotReadyException
     */
    public static void main(String[] args) throws DatabaseNotReadyException {
//        DatabaseManager database = DatabaseManager.getInstance();
//
//        database.prepareDb(PRODUCTION);
//        database.getConnection();
//
//        SkillTypeDao dao = new SkillTypeDao();
//
//        // Arrays.asList => creates list from given values.
//        dao.insert(Arrays.asList(
//                new SkillType("coucou"),
//                new SkillType("bonne"),
//                new SkillType("inutile"),
//                new SkillType("marrante")));
//
//        dao.findAll().forEach(type -> System.out.println(type));
    	DatabaseManager.getInstance().prepareDb(PRODUCTION);
    	UserDAO dao = new UserDAO();
    	dao.insert(new User());
    }
}
