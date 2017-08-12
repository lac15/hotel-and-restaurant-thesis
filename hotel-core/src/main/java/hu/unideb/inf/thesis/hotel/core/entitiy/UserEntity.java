package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class UserEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String username;

    @Basic
    @Column(nullable = false)
    private String firstname;

    @Basic
    @Column(nullable = false)
    private String lastname;

    @Basic
    @Column(nullable = false)
    private String email;

    @Basic
    @Column(nullable = false)
    private String password;

    @Basic
    @Column(nullable = true)
    private String address;

    @Basic
    @Column(nullable = true)
    private String phone;

    @Basic
    @Column(nullable = false)
    private boolean hotelCustomer;

    @Basic
    @Column(nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<RoleEntity> roles;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<OrderEntity> orders;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<TableReserveEntity> tableReserves;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<RoomReserveEntity> roomReserves;

    public UserEntity(){}

    public UserEntity(String username, String firstname, String lastname, String email, String password, String address, String phone) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.hotelCustomer = false;
        this.active = true;
        this.roles = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.tableReserves = new ArrayList<>();
        this.roomReserves = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isHotelCustomer() {
        return hotelCustomer;
    }

    public void setHotelCustomer(boolean hotelCustomer) {
        this.hotelCustomer = hotelCustomer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public List<TableReserveEntity> getTableReserves() {
        return tableReserves;
    }

    public void setTableReserves(List<TableReserveEntity> tableReserves) {
        this.tableReserves = tableReserves;
    }

    public List<RoomReserveEntity> getRoomReserves() {
        return roomReserves;
    }

    public void setRoomReserves(List<RoomReserveEntity> roomReserves) {
        this.roomReserves = roomReserves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        if (!super.equals(o)) return false;
        UserEntity that = (UserEntity) o;
        return hotelCustomer == that.hotelCustomer &&
                active == that.active &&
                Objects.equals(username, that.username) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, firstname, lastname, email, password, address, phone, hotelCustomer, active);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                "firstname='" + firstname + '\'' +
                "lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                "} ";
    }
}
