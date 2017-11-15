package com.tactfactory.capfakeskill;

@SuppressWarnings("serial")
public class DatabaseNotReadyException extends Exception {
    private static final String MESSAGE =
            "Database not initialized, please see the prepareDb method.";

    public DatabaseNotReadyException() {
        super(DatabaseNotReadyException.MESSAGE);
    }
}
