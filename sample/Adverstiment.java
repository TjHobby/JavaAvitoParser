package sample;

public class Adverstiment {
    private String name;
    private int price;
    private boolean delivery;
    private String place;
    private String url;
    private String creatingDate;

    public Adverstiment(String name, int price, String place, boolean delivery, String url, String creatingDate) {
        this.name = name;
        this.price = price;
        this.delivery = delivery;
        this.place = place;
        this.url = url;
        this.creatingDate = creatingDate;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatingDate() {
        return creatingDate;
    }
}
