package com.example.reciprice.model;

public class Offer {
    private String merchant;
    private String domain;
    private String title;
    private int price;
    private String link;

    public Offer() {
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Offers{" +
                "merchant='" + merchant + '\'' +
                ", domain='" + domain + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", link='" + link + '\'' +
                '}';
    }
}
