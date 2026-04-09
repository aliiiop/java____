public class Game {
    private int id; private String title; private String genre; private double price;
    private String description; private int downloads;
    public Game(int id, String title, String genre, double price, String description) {
        this.id=id; this.title=title; this.genre=genre; this.price=price;
        this.description=description; this.downloads=0;
    }
    public int getId(){return id;} public String getTitle(){return title;}
    public String getGenre(){return genre;} public double getPrice(){return price;}
    public String getDescription(){return description;} public int getDownloads(){return downloads;}
    public void incrementDownloads(){downloads++;}
    public String toString(){return String.format("[%d] %s - %.2f$ | %s | Скачиваний: %d",id,title,price,genre,downloads);}
}
