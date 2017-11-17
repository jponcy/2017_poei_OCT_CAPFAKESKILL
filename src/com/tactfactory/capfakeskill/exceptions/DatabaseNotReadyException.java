package com.tactfactory.capfakeskill.exceptions;

@SuppressWarnings("serial")
public class DatabaseNotReadyException extends Exception {
    private static final String MESSAGE =
            "Database not initialized, please see the prepareDb method.";

    public DatabaseNotReadyException() {
        super(DatabaseNotReadyException.MESSAGE);
    }
}
