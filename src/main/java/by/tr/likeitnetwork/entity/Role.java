package by.tr.likeitnetwork.entity;


public enum Role {
    ADMIN(10), USER(2), GUEST(1) ;

    private int role;

    Role(int role) {
        this.role = role;
    }

    public int getRole(){
        return role;
    }
}
