import java.util.*;

public class User {
    private String username;
    private String password;
    private double balance;
    private List<Product> purchasedProducts;
    private List<Integer> wishlist;
    private Cart cart;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 100.0;
        this.purchasedProducts = new ArrayList<>();
        this.wishlist = new ArrayList<>();
        this.cart = new Cart();
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public double getBalance() { return balance; }
    public List<Product> getPurchasedProducts() { return purchasedProducts; }
    public List<Integer> getWishlist() { return wishlist; }
    public Cart getCart() { return cart; }
    
    public void addBalance(double amount) { 
        balance += amount; 
    }
    
    public boolean deductBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public void addPurchasedProduct(Product product) { 
        purchasedProducts.add(product); 
    }
    
    public void addToWishlist(int productId) { 
        wishlist.add(productId); 
    }
}
