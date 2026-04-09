import java.util.*;
public class User {
    private String username,password; private double balance;
    private List<Game> purchasedGames=new ArrayList<>(); private List<Integer> wishlist=new ArrayList<>();
    public User(String username,String password){this.username=username;this.password=password;this.balance=100.0;}
    public String getUsername(){return username;} public String getPassword(){return password;}
    public double getBalance(){return balance;} public List<Game> getPurchasedGames(){return purchasedGames;}
    public List<Integer> getWishlist(){return wishlist;} public void addBalance(double a){balance+=a;}
    public boolean deductBalance(double a){if(balance>=a){balance-=a;return true;}return false;}
    public void addPurchasedGame(Game g){purchasedGames.add(g);} public void addToWishlist(int i){wishlist.add(i);}
}
