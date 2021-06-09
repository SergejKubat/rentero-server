package com.rentero.renteroserver.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String model;
    @Column(name = "main_image_url")
    private String mainImageUrl;
    private int capacity;
    private int doors;
    private int hp;
    @Column(name = "engine_size")
    private int engineSize;
    private int year;
    private String fuel;
    @Column(name = "gear_stick")
    private String gearStick;
    private String description;
    @Column(name = "price_per_day")
    private double pricePerDay;
    private boolean reserved;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations;

    public Car() {
    }

    public Car(String brand, String model, String mainImageUrl, int capacity, int doors, int hp, int engineSize, int year, String fuel, String gearStick, String description, double pricePerDay, boolean reserved) {
        this.brand = brand;
        this.model = model;
        this.mainImageUrl = mainImageUrl;
        this.capacity = capacity;
        this.doors = doors;
        this.hp = hp;
        this.engineSize = engineSize;
        this.year = year;
        this.fuel = fuel;
        this.gearStick = gearStick;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.reserved = reserved;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getGearStick() {
        return gearStick;
    }

    public void setGearStick(String gearStick) {
        this.gearStick = gearStick;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}