package domain;

/**
 * Created by RZ on 4/20/16.
 */
public class Food {
    private int id;
    private String foodName;
    private String foodNameCN;

    private int foodType_id;
    private double price;
    private double mprice;
    private String description;
    private String img;


    public String getFoodNameCN() {
        return foodNameCN;
    }

    public void setFoodNameCN(String foodNameCN) {
        this.foodNameCN = foodNameCN;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodType_id() {
        return foodType_id;
    }

    public void setFoodType_id(int foodType_id) {
        this.foodType_id = foodType_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMprice() {
        return mprice;
    }

    public void setMprice(double mprice) {
        this.mprice = mprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
