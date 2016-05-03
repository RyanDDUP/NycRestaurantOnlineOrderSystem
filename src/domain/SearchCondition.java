package domain;

/**
 * Created by RZ on 4/22/16.
 */
public class SearchCondition {
    private String foodName;
    private int foodTypeId;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }
}
