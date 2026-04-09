import java.util.*;
public class GameStore {
    private List<Game> games=new ArrayList<>(); private Map<String,User> users=new HashMap<>();
    private User currentUser; private Scanner scanner=new Scanner(System.in);
    public GameStore(){
        games.add(new Game(1,"CyberPunk 2077","Action",59.99,"Футуристический экшен"));
        games.add(new Game(2,"Brawl Stars","Moba",49.99,"Бабл Квас"));
        games.add(new Game(3,"NeedForSpeed","Racing",39.99,"Ультра-быстрые гонки"));
        games.add(new Game(4,"Clash Royale","Strategy",29.99,"Мощная стратегия"));
        games.add(new Game(5,"Bite By Night","Horror",19.99,"Психологический хоррор"));
        games.add(new Game(6,"Block Blast","Puzzle",9.99,"Затягивающие головоломки"));
        games.add(new Game(7,"Roblox","Sandbox",99.99,"Платформа для детей"));
        games.add(new Game(8,"GTA VI","Adventure",67.67,"Тату и борода"));
        games.add(new Game(9,"Fortnite","Battle Royale",69.69,"Full piece Full box"));
        games.add(new Game(10,"Dota 2","Strategy",29.99,"Первый скилл и третий"));
        games.add(new Game(11,"CS2","Shooter",99.99,"Gold gold gold"));
        games.add(new Game(12,"Minecraft","Sandbox",22.99,"Да ты че..."));
        games.add(new Game(13,"Terraria","Sandbox",9.99,"Gym + Calamity "));
    }
    public void start(){while(true){if(currentUser==null)showAuthMenu();else showMainMenu();}}
    private void showAuthMenu(){
        System.out.println("\n=== GAMESHOP ===");
        System.out.println("1.Вход 2.Регистрация 3.Выход");
        int c=scanner.nextInt(); scanner.nextLine();
        if(c==1)login();else if(c==2)register();else if(c==3)System.exit(0);
        else System.out.println("Неверно!");
    }
    private void login(){
        System.out.print("Логин: "); String u=scanner.nextLine();
        System.out.print("Пароль: "); String p=scanner.nextLine();
        User user=users.get(u);
        if(user!=null&&user.getPassword().equals(p)){currentUser=user; System.out.println("Добро пожаловать, "+u+"!");}
        else System.out.println("Неверно!");
    }
    private void register(){
        System.out.print("Логин: "); String u=scanner.nextLine();
        if(users.containsKey(u)){System.out.println("Уже есть!");return;}
        System.out.print("Пароль: "); String p=scanner.nextLine();
        users.put(u,new User(u,p)); System.out.println("Успех! Теперь войдите.");
    }
    private void showMainMenu(){
        System.out.println("\n=== "+currentUser.getUsername()+" | Баланс: "+currentUser.getBalance()+"$ ===");
        System.out.println("1.Каталог 2.Мои игры 3.Пополнить 4.Купить 5.Поиск 6.Wishlist 7.Топ 8.Выход");
        int c=scanner.nextInt(); scanner.nextLine();
        if(c==1)showAllGames();else if(c==2)showMyGames();else if(c==3)addBalance();
        else if(c==4)buyGame();else if(c==5)searchByGenre();else if(c==6)manageWishlist();
        else if(c==7)showTopGames();else if(c==8)currentUser=null;
    }
    private void showAllGames(){System.out.println("\n=== ВСЕ ИГРЫ ===");for(Game g:games){System.out.println(g); System.out.println("   "+g.getDescription()+"\n");}}
    private void showMyGames(){System.out.println("\n=== МОИ ИГРЫ ===");var my=currentUser.getPurchasedGames();if(my.isEmpty())System.out.println("Нет игр");else for(Game g:my)System.out.println("✓ "+g.getTitle());}
    private void addBalance(){System.out.print("Сумма: ");currentUser.addBalance(scanner.nextDouble());System.out.println("Баланс: "+currentUser.getBalance()+"$");}
    private void buyGame(){
        System.out.print("ID игры: ");int id=scanner.nextInt();
        Game g=findGameById(id); if(g==null){System.out.println("Нет игры");return;}
        for(Game gg:currentUser.getPurchasedGames())if(gg.getId()==id){System.out.println("Уже есть!");return;}
        if(currentUser.deductBalance(g.getPrice())){currentUser.addPurchasedGame(g);g.incrementDownloads();System.out.println("Куплено! Остаток: "+currentUser.getBalance()+"$");}
        else System.out.println("Не хватает денег!");
    }
    private void searchByGenre(){System.out.print("Жанр: ");String genre=scanner.nextLine();System.out.println("\n=== "+genre.toUpperCase()+" ===");boolean f=false;for(Game g:games)if(g.getGenre().equalsIgnoreCase(genre)){System.out.println(g);f=true;}if(!f)System.out.println("Нет игр");}
    private void manageWishlist(){
        System.out.println("1.Добавить 2.Показать");int c=scanner.nextInt();
        if(c==1){System.out.print("ID: ");int id=scanner.nextInt();if(findGameById(id)!=null&&!currentUser.getWishlist().contains(id)){currentUser.addToWishlist(id);System.out.println("Добавлено!");}else System.out.println("Ошибка!");}
        else if(c==2){System.out.println("\n=== WISHLIST ===");var w=currentUser.getWishlist();if(w.isEmpty())System.out.println("Пуст");else for(int id:w)System.out.println(findGameById(id));}
    }
    private void showTopGames(){System.out.println("\n=== ТОП 3 ===");var sorted=new ArrayList<>(games);sorted.sort((a,b)->b.getDownloads()-a.getDownloads());for(int i=0;i<Math.min(3,sorted.size());i++)System.out.println((i+1)+". "+sorted.get(i));}
    private Game findGameById(int id){for(Game g:games)if(g.getId()==id)return g;return null;}
}
