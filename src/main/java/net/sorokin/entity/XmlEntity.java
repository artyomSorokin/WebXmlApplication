package net.sorokin.entity;


public class XmlEntity {

    private String title;
    private String artist;
    private String country;
    private String company;
    private double price;
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XmlEntity xmlEntity = (XmlEntity) o;

        if (Double.compare(xmlEntity.price, price) != 0) return false;
        if (year != xmlEntity.year) return false;
        if (title != null ? !title.equals(xmlEntity.title) : xmlEntity.title != null) return false;
        if (artist != null ? !artist.equals(xmlEntity.artist) : xmlEntity.artist != null) return false;
        if (country != null ? !country.equals(xmlEntity.country) : xmlEntity.country != null) return false;
        return company != null ? company.equals(xmlEntity.company) : xmlEntity.company == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + year;
        return result;
    }
}
