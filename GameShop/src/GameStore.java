import java.util.*;

public class GameStore {
    private List<Product> products;
    private Map<String, User> users;
    private User currentUser;
    private Scanner scanner;
    
    public GameStore() {
        products = new ArrayList<>();
        users = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeProducts();
    }
    
    private void initializeProducts() {
        products.add(new Product(1, "Cyber Warrior 2077", "Action", 59.99, "Футуристический экшен в открытом мире"));
        products.add(new Product(2, "Fantasy Quest", "RPG", 49.99, "Эпическое фэнтези приключение"));
        products.add(new Product(3, "Racing Extreme", "Racing", 39.99, "Ультра-быстрые гонки"));
        products.add(new Product(4, "Strategy Master", "Strategy", 29.99, "Сложная экономическая стратегия"));
        products.add(new Product(5, "Horror Mansion", "Horror", 19.99, "Психологический хоррор"));
        products.add(new Product(6, "Puzzle World", "Puzzle", 9.99, "Затягивающие головоломки"));
        products.add(new Product(7, "Space Explorer", "Adventure", 44.99, "Космическое приключение"));
        products.add(new Product(8, "Fighter Arena", "Action", 34.99, "Динамичные файтинги"));
    }
    
    public void start() {
        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }
    
    private void showAuthMenu() {
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║     GAMESHOP STEAM-LIKE    ║");
        System.out.println("╚════════════════════════════╝");
        System.out.println("1. Вход");
        System.out.println("2. Регистрация");
        System.out.println("3. Выход");
        System.out.print("Выберите действие: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1: login(); break;
            case 2: register(); break;
            case 3: System.exit(0);
            default: System.out.println("❌ Неверный выбор!");
        }
    }
    
    private void login() {
        System.out.print("👤 Логин: ");
        String username = scanner.nextLine();
        System.out.print("🔒 Пароль: ");
        String password = scanner.nextLine();
        
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("✅ Добро пожаловать, " + username + "!");
        } else {
            System.out.println("❌ Неверный логин или пароль!");
        }
    }
    
    private void register() {
        System.out.print("👤 Придумайте логин: ");
        String username = scanner.nextLine();
        
        if (users.containsKey(username)) {
            System.out.println("❌ Пользователь уже существует!");
            return;
        }
        
        System.out.print("🔒 Придумайте пароль: ");
        String password = scanner.nextLine();
        
        users.put(username, new User(username, password));
        System.out.println("✅ Регистрация успешна! Теперь войдите.");
    }
    
    private void showMainMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   Добро пожаловать, " + currentUser.getUsername() + "!");
        System.out.println("║   Баланс: " + currentUser.getBalance() + "$");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n1. 🎮 Каталог игр");
        System.out.println("2. 🛒 Корзина");
        System.out.println("3. 💳 Мои игры");
        System.out.println("4. 💰 Пополнить баланс");
        System.out.println("5. 🔍 Поиск по жанру");
        System.out.println("6. ❤️ Список желаемого");
        System.out.println("7. 🏆 Топ продаж");
        System.out.println("8. 🚪 Выход из аккаунта");
        System.out.print("\nВыберите действие: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1: showCatalog(); break;
            case 2: manageCart(); break;
            case 3: showMyProducts(); break;
            case 4: addBalance(); break;
            case 5: searchByGenre(); break;
            case 6: manageWishlist(); break;
            case 7: showTopProducts(); break;
            case 8: currentUser = null; break;
            default: System.out.println("❌ Неверный выбор!");
        }
    }
    
    private void showCatalog() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║            🎮 КАТАЛОГ ИГР            ║");
        System.out.println("╚════════════════════════════════════════╝");
        for (Product product : products) {
            System.out.println(product);
            System.out.println("   📝 " + product.getDescription());
            System.out.println("   ─────────────────────────────");
        }
    }
    
    private void manageCart() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║            🛒 КОРЗИНА                 ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. 📥 Добавить товар");
        System.out.println("2. 📤 Удалить товар");
        System.out.println("3. 👁️ Показать корзину");
        System.out.println("4. 🗑️ Очистить корзину");
        System.out.println("5. 💳 Оформить покупку");
        System.out.println("6. 🔙 Назад");
        System.out.print("\nВыбор: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1: addToCart(); break;
            case 2: removeFromCart(); break;
            case 3: currentUser.getCart().showCart(); break;
            case 4: currentUser.getCart().clearCart(); break;
            case 5: checkout(); break;
            case 6: return;
            default: System.out.println("❌ Неверный выбор!");
        }
    }
    
    private void addToCart() {
        System.out.print("Введите ID игры: ");
        int id = scanner.nextInt();
        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();
        
        Product product = findProductById(id);
        if (product == null) {
            System.out.println("❌ Игра не найдена!");
            return;
        }
        
        currentUser.getCart().addProduct(product, quantity);
    }
    
    private void removeFromCart() {
        currentUser.getCart().showCart();
        if (currentUser.getCart().isEmpty()) return;
        
        System.out.print("Введите ID игры для удаления: ");
        int id = scanner.nextInt();
        System.out.print("Введите количество (0 для полного удаления): ");
        int quantity = scanner.nextInt();
        
        if (quantity == 0) quantity = Integer.MAX_VALUE;
        currentUser.getCart().removeProduct(id, quantity);
    }
    
    private void checkout() {
        Cart cart = currentUser.getCart();
        
        if (cart.isEmpty()) {
            System.out.println("❌ Корзина пуста! Добавьте товары.");
            return;
        }
        
        cart.showCart();
        double total = cart.calculateTotal();
        
        System.out.println("\n💰 Ваш баланс: " + currentUser.getBalance() + "$");
        System.out.println("💸 Сумма покупки: " + total + "$");
        
        if (currentUser.getBalance() >= total) {
            System.out.print("Подтверждаете покупку? (да/нет): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("да")) {
                // Покупка всех товаров в корзине
                for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();
                    
                    // Добавляем игру пользователю (учитываем количество)
                    for (int i = 0; i < quantity; i++) {
                        currentUser.addPurchasedProduct(product);
                        product.incrementSales();
                    }
                }
                
                // Списываем деньги
                currentUser.deductBalance(total);
                // Очищаем корзину
                cart.clearCart();
                
                System.out.println("✅ Покупка успешно оформлена!");
                System.out.println("💰 Остаток на счете: " + currentUser.getBalance() + "$");
            } else {
                System.out.println("❌ Покупка отменена.");
            }
        } else {
            System.out.println("❌ Недостаточно средств!");
            System.out.println("💰 Нужно: " + total + "$ | Есть: " + currentUser.getBalance() + "$");
        }
    }
    
    private void showMyProducts() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           💳 МОИ ИГРЫ                ║");
        System.out.println("╚════════════════════════════════════════╝");
        List<Product> myProducts = currentUser.getPurchasedProducts();
        if (myProducts.isEmpty()) {
            System.out.println("📭 У вас пока нет игр. Загляните в каталог!");
        } else {
            for (Product product : myProducts) {
                System.out.println("✓ " + product.getTitle() + " - " + product.getGenre());
            }
            System.out.println("\n📊 Всего игр: " + myProducts.size());
        }
    }
    
    private void addBalance() {
        System.out.print("💰 Сумма пополнения: ");
        double amount = scanner.nextDouble();
        currentUser.addBalance(amount);
        System.out.println("✅ Баланс пополнен! Текущий баланс: " + currentUser.getBalance() + "$");
    }
    
    private void searchByGenre() {
        System.out.print("🔍 Введите жанр (Action, RPG, Racing, Strategy, Horror, Puzzle, Adventure): ");
        String genre = scanner.nextLine();
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║      ИГРЫ В ЖАНРЕ " + genre.toUpperCase() + "      ║");
        System.out.println("╚════════════════════════════════════════╝");
        boolean found = false;
        for (Product product : products) {
            if (product.getGenre().equalsIgnoreCase(genre)) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ Игры такого жанра не найдены!");
        }
    }
    
    private void manageWishlist() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           ❤️ СПИСОК ЖЕЛАЕМОГО         ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Добавить в список");
        System.out.println("2. Показать список");
        System.out.print("Выбор: ");
        
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            System.out.print("Введите ID игры: ");
            int id = scanner.nextInt();
            
            if (findProductById(id) != null && !currentUser.getWishlist().contains(id)) {
                currentUser.addToWishlist(id);
                System.out.println("✅ Игра добавлена в список желаемого!");
            } else {
                System.out.println("❌ Игра не найдена или уже в списке!");
            }
        } else if (choice == 2) {
            System.out.println("\n=== СПИСОК ЖЕЛАЕМОГО ===");
            List<Integer> wishlist = currentUser.getWishlist();
            if (wishlist.isEmpty()) {
                System.out.println("📭 Список пуст");
            } else {
                for (int id : wishlist) {
                    Product product = findProductById(id);
                    if (product != null) {
                        System.out.println(product);
                    }
                }
            }
        }
    }
    
    private void showTopProducts() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         🏆 ТОП ПРОДАЖ                ║");
        System.out.println("╚════════════════════════════════════════╝");
        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort((p1, p2) -> Integer.compare(p2.getSalesCount(), p1.getSalesCount()));
        
        for (int i = 0; i < Math.min(5, sortedProducts.size()); i++) {
            Product p = sortedProducts.get(i);
            System.out.println((i+1) + ". " + p.getTitle() + " - " + p.getSalesCount() + " продаж");
        }
    }
    
    private Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
