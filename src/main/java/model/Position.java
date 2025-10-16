package model;

public enum Position {
    Prezes("Prezes",25000.0,1),
    Wiceprezes("Wiceprezes",18000.0,2),
    Manager("Manager",12000.0,3),
    Programista("Programista",8000.0,4),
    Stażysta("Stażysta",3000.0,5);
    public final String name;
    public final double salary;
    public final int hierarchy;

    private Position(String name, double salary, int hierarchy) {
        this.name = name;
        this.salary = salary;
        this.hierarchy = hierarchy;
    }

}
