package com.tactfactory.capfakeskill.dao.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableField {
    private String name;

    /** Java type. */
    private String type;
    /** SQL type (and precision). */
    private String definitionType;

    private boolean autoincrement = false;
    private boolean nullable = false;
    private boolean unique = false;

    private boolean pk = false;
    private String foreignKey = null; // ex: group(id)

    public TableField(String name, String type) {
        this.setName(name.trim());

        Pattern pattern = Pattern.compile("^\\s*(\\w+)\\s*(\\(.*\\))?\\s*$");
        Matcher matcher = pattern.matcher(type.trim());

        if (matcher.find()) {
            String typeAlone = matcher.group(1);
            this.setDefinitionType(typeAlone.toUpperCase() + matcher.group(2));
            this.setType(typeAlone);
        }
    }

    public TableField(String name, String type, boolean pk) {
        this(name, type);
        this.setPk(pk);
    }

    public String getCreationLine() {
        String result = this.name + " " + this.definitionType;

        if (this.isAutoincrement()) {
            result += " AUTO_INCREMENT";
        }

        if (this.isUnique()) {
            result += " UNIQUE";
        }

        result += (this.isNullable() ? " " : " NOT ") + "NULL";

        if (this.isPk()) {
            result += " PRIMARY KEY";
        }

        return result ;
    }

    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    private void setType(String type) {
        switch (type.toLowerCase()) {
        case "varchar":
        case "text":
            this.type = "string";
            break;

        case "integer":
        case "int":
        case "bigint":
        case "tinyint":
            this.type = "int";
            break;

        case "bool":
        case "boolean":
            this.type = "boolean";
            break;

        default:
            this.type = "object";
            break;
        }
    }
    private String getDefinitionType() {
        return definitionType;
    }
    private void setDefinitionType(String definitionType) {
        this.definitionType = definitionType;
    }
    public boolean isAutoincrement() {
        return autoincrement;
    }
    private void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }
    public boolean isNullable() {
        return nullable;
    }
    private void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
    public boolean isPk() {
        return pk;
    }
    private void setPk(boolean pk) {
        this.pk = pk;

        if (this.isPk()) {
            this.setAutoincrement(true);
            this.setNullable(false);
        }
    }
    public String getForeignKey() {
        return foreignKey;
    }
    @SuppressWarnings("unused")
    private void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public boolean isUnique() {
        return unique;
    }

    public TableField setUnique(boolean unique) {
        this.unique = unique;
        return this;
    }
}
