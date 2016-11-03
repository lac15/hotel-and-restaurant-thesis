package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;

public class FoodTypeVo implements Serializable {

    private static final long serialVersionUID = 3421600328505763772L;

    private Long id;
    private String name;

    public FoodTypeVo(){}

    public FoodTypeVo(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
