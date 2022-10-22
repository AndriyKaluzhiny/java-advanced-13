package ua.lviv.lgs.domain;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private Double cost;

    public Product(Integer id, String name, String description, Double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Product(String name, String description, Double cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }


    public double getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}

