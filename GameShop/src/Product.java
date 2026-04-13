public class Product {
    private int id;
    private String title;
    private String genre;
    private double price;
    private String description;
    private int salesCount;
    
    public Product(int id, String title, String genre, double price, String description) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.description = description;
        this.salesCount = 0;
    }
    
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public int getSalesCount() { return salesCount; }
    
    public void incrementSales() { this.salesCount++; }
    
    @Override
    public String toString() {
        return String.format("[%d] %s - %.2f$ | %s | Продано: %d", 
                           id, title, price, genre, salesCount);
    }
}
