package com.tactfactory.capfakeskill;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tactfactory.capfakeskill.dao.dao.ProjectDAO;
import com.tactfactory.capfakeskill.dao.dao.UserDAO;
import com.tactfactory.capfakeskill.entities.Project;
import com.tactfactory.capfakeskill.entities.User;
import com.tactfactory.capfakeskill.exceptions.DatabaseNotReadyException;
import com.tactfactory.capfakeskill.manager.DatabaseManager;
import com.tactfactory.capfakeskill.utils.DumpFields;

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
    	UserDAO daoU = new UserDAO();
    	ProjectDAO daoP = new ProjectDAO();
    	User user;
    	Project project;
    	for (int i = 0; i < 5; i++) {
    		user = new User();
    		user.setEmail("coucou");
    		daoU.insert(user);
    		project = new Project();
    		daoP.insert(project);
    		System.out.println(user);
    		System.out.println(project);
		}

    	System.out.println("---------------------------------");

    	List<User> users = daoU.select();
    	for (User user2 : users) {
			System.out.println(user2);
		}

    	/*User user = DumpFields.createContentsEmpty(User.class);
    	user.setId(12.0);

    	Map<String,Object> map = DumpFields.fielder(user);
    	for (Entry<String, Object> entry : map.entrySet()) {
    		System.out.println(entry.getKey()+ " | " + entry.getValue());
		}*/
    }
}
