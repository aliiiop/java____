import java.util.*;

public class Cart {
    private Map<Product, Integer> items; // Product и количество
    
    public Cart() {
        items = new HashMap<>();
    }
    
    // Добавление товара
    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Количество должно быть больше 0!");
            return;
        }
        
        if (items.containsKey(product)) {
            int currentQty = items.get(product);
            items.put(product, currentQty + quantity);
            System.out.println("Обновлено: " + product.getTitle() + " x" + (currentQty + quantity));
        } else {
            items.put(product, quantity);
            System.out.println("Добавлено: " + product.getTitle() + " x" + quantity);
        }
    }
    
    // Удаление товара (полностью или частично)
    public void removeProduct(int productId, int quantity) {
        Product toRemove = null;
        for (Product p : items.keySet()) {
            if (p.getId() == productId) {
                toRemove = p;
                break;
            }
        }
        
        if (toRemove == null) {
            System.out.println("Товар не найден в корзине!");
            return;
        }
        
        int currentQty = items.get(toRemove);
        
        if (quantity >= currentQty) {
            items.remove(toRemove);
            System.out.println("Товар полностью удален из корзины: " + toRemove.getTitle());
        } else {
            items.put(toRemove, currentQty - quantity);
            System.out.println("Удалено " + quantity + " шт. Осталось: " + (currentQty - quantity));
        }
    }
    
    // Полная очистка корзины
    public void clearCart() {
        items.clear();
        System.out.println("Корзина полностью очищена!");
    }
    
    // Расчет общей суммы
    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
    
    // Показать корзину
    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Корзина пуста!");
            return;
        }
        
        System.out.println("\n=== ВАША КОРЗИНА ===");
        System.out.println("----------------------------------------");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            double subtotal = p.getPrice() * qty;
            System.out.printf("%s\n   Количество: %d | Сумма: %.2f$\n", 
                            p, qty, subtotal);
            System.out.println("----------------------------------------");
        }
        System.out.printf("ИТОГО: %.2f$\n", calculateTotal());
    }
    
    // Получить все товары в корзине
    public Map<Product, Integer> getItems() {
        return items;
    }
    
    // Проверка пустая ли корзина
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
