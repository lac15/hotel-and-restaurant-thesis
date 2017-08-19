package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;

public class DrinkVo implements Serializable {

    private static final long serialVersionUID = 1952000328505763772L;

    private Long id;
    private String name;
    private int price;

    public DrinkVo(){}

    public DrinkVo(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrinkVo drinkVo = (DrinkVo) o;

        if (price != drinkVo.price) return false;
        if (!id.equals(drinkVo.id)) return false;
        return name.equals(drinkVo.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        return result;
    }
}
