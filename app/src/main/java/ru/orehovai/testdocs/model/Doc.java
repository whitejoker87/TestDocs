package ru.orehovai.testdocs.model;

public class Doc {

    private String name;
    private String id;
    private String type;
    private String link;
    private String date;
    private String size;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
