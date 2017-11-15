package com.tactfactory.capfakeskill;

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
    }
}
